const mongoose = require('mongoose');
//const Room = require('./room.model');

const messageSchema = mongoose.Schema({
    senderId: mongoose.Types.ObjectId,
    roomId: {
        type: mongoose.Types.ObjectId,
        validate: {
            validator: roomId => roomId !== "",
            message: "RoomId is not empty"
        },
    },
    content: String,
    time: String
});

module.exports = mongoose.model('Messages', messageSchema);
