const mongoose = require('mongoose');
const moment = require('moment');
const Room = require('./room.model');

const messageSchema = new mongoose.Schema({
    senderId: mongoose.Types.ObjectId,
    roomId: {
        type: mongoose.Types.ObjectId,
        validate: {
            validator: roomId => roomId !== "",
            message: "RoomId is not empty"
        },
    },
    content: String,
    time: Date
});

messageSchema.set('toJSON', { virtuals: true });


const Message = mongoose.model('message', messageSchema, 'message');

const AddMessage = async (senderId, roomId, content, time) => {
    await Message.create({ roomId: roomId, time: time, content: content, senderId: senderId });
}

const FindConversationsOfUser = async userId => {
    try {
        // Get all rooms of user
        const roomIds = await Room.FindAllRoomsOfUser(userId);
        // Put all roomId in array to filter
        const arrExp = [];
        for(const roomId of roomIds) {
            const _id = roomId._id.toString();
            const expression =  { roomId: mongoose.Types.ObjectId(_id) } ;
            arrExp.push(expression);
        }
        const conversations = await Message.aggregate([
            // Stage 1 - get all roomDetails by roomId
            { $match: { $or: arrExp } },
            // Stage 2 - find last message time
            { 
                $group: {
                    _id: "$roomId", 
                    lastMessageTime: { $max: "$time" }, 
                    contents: { $push: { content: "$content", time: "$time" } } 
                } 
            },
            // Stage 3 - find room name by roomId
            { 
                $lookup: {
                    from: "room", localField: "_id", foreignField: "_id", as: "roomInfo"
                }
            },
            // Stage 4 - get last message and message time
            { 
                $project: { 
                    "roomInfo.name": 1, 
                    contents: {
                        $filter: {
                            input: "$contents",
                            as: "content",
                            cond: { $eq: ["$$content.time", "$lastMessageTime"] }
                        }
                    }
                }
            },
            // Stage 5 - clean data
            { 
                $replaceRoot: { 
                    newRoot: { 
                        $mergeObjects: [{ roomId: "$_id" }, { $arrayElemAt: ["$roomInfo", 0] }, { $arrayElemAt: ["$contents", 0] }] 
                    } 
                } 
            },
            // Stage 6 - sort by time
            { $sort: { time: -1 } }
        ]);
        FormatData(conversations);
        return conversations;
    } catch(err) {
        console.log(err);
        return null;
    }
}

const IsToday = someDate => {
    const today = new Date();
    return someDate.getDate() === today.getDate() &&
            someDate.getMonth() === today.getMonth() &&
            someDate.getYear() === today.getYear();
}

const FormatData = arr => {
    for(const item of arr) {
        if(IsToday(new Date(item.time))) {
            item.time =  moment(item.time).format('hh:mm a');
        } else {
            item.time = moment(item.time).format('MMM Do')
        }
    }
}

const GetMessagesInRoom = async roomId => {
    try {
        const messages = await Message.aggregate([
            // Stage 1 - get all records in room by group roomId
            { $match: { roomId: mongoose.Types.ObjectId(roomId) } },
            // Stage 2 - find sender by senderId in stage 1
            { $lookup: { from: "user", localField: "senderId", foreignField: "_id", as: "user" } },
            // Stage 3 - reshape documents
            { 
                $replaceRoot: { 
                    newRoot: { 
                        $mergeObjects: [ { $arrayElemAt: ["$user", 0] }, "$$ROOT"] 
                    } 
                } 
            },
            // Stage 4 - clean data
            { $project: { displayName: 1, content: 1, time: 1, senderId: 1, _id: 0 } },
            // Stage 5 - sort by time
            { $sort: { time: 1 } }
        ]);
        return messages;
    } catch(err) {
        console.log(err);
        return null;
    }
}

const GetInfoRoom = async userId => {
    try {
        const rooms = Message.aggregate([
            // Stage 1 - Find all rooms of user
            { $match: { userId: mongoose.Types.ObjectId(userId) } },
            // Stage 2 - Group room
            { $group: { _id: "$roomId" } },
            // Stage 3 - find room info by roomId
            { $lookup: { from: "room", localField: "_id", foreignField: "_id", as: "room" } },
            // Stage 4 - clean data
            { $replaceRoot: { newRoot: { $arrayElemAt: ["$room", 0] } } }
        ]);
        return rooms;
    } catch(err) {
        console.log(err);
        return null;
    }
}

const FindNewestMessageInRoom = async roomId => {
    try {
        const msg = await Message.aggregate([
            // Stage 1 - get all roomDetails by roomId
            { $match: { roomId: mongoose.Types.ObjectId(roomId) } },
            // Stage 2 - find last message time
            { 
                $group: {
                    _id: "$roomId", 
                    lastMessageTime: { $max: "$time" }, 
                    contents: { $push: { content: "$content", time: "$time" } } 
                } 
            },
            // Stage 3 - find room name by roomId
            { 
                $lookup: {
                    from: "room", localField: "_id", foreignField: "_id", as: "roomInfo"
                }
            },
            // Stage 4 - get last message and message time
            { 
                $project: { 
                    "roomInfo.name": 1, 
                    contents: {
                        $filter: {
                            input: "$contents",
                            as: "content",
                            cond: { $eq: ["$$content.time", "$lastMessageTime"] }
                        }
                    }
                }
            },
            // Stage 5 - clean data
            { 
                $replaceRoot: { 
                    newRoot: { 
                        $mergeObjects: [{ roomId: "$_id" }, { $arrayElemAt: ["$roomInfo", 0] }, { $arrayElemAt: ["$contents", 0] }] 
                    } 
                } 
            },
            // Stage 6 - sort by time
            { $sort: { time: -1 } }
        ]);
        FormatData(msg);
        return msg[0];
    } catch(err) {
        console.log(err);
        return null;
    }
}

const DeleteMessagesInRoom = async roomId => {
    await Message.deleteMany({ roomId: mongoose.Types.ObjectId(roomId) });
}

module.exports = { 
    FindConversationsOfUser,
    GetMessagesInRoom,
    GetInfoRoom,
    AddMessage,
    FindNewestMessageInRoom,
    DeleteMessagesInRoom
}