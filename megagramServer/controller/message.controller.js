//const Message = require('../../model/message.model');
const Message = require('../model/message.model');

// Get info rooms by user id
const GetInfoRooms = async (req, res) => {
    const userId = req.body.userId;
    console.log(`UserId: ${ userId }`);
    const rooms = await Message.GetInfoRoom(userId);
    res.json(rooms);
}

// Get all conversations of user to show in message fragment
const FindConversationsOfUser = async (req, res) => {
    const userId = req.body.userId;
    console.log(`UserId: ${ userId }`);
    const conversations = await Message.FindConversationsOfUser(userId);
    res.json(conversations);
}

// Get all messages in room to show in message activity
const GetMessages = async (req, res) => {
    const roomId = req.body.roomId;
    console.log(`RoomId: ${ roomId }`);
    const messages = await Message.GetMessagesInRoom(roomId);
    res.json(messages);
}

module.exports = {
    GetInfoRooms,
    FindConversationsOfUser,
    GetMessages
}