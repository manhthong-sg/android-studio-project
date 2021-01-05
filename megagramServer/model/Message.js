const mongoose = require('mongoose');
//const Room = require('./room.model');

const messageSchema = mongoose.Schema({
    UniqueId: {
        type: String,
        required: true
    },
    roomId: {
        type: mongoose.Types.ObjectId,
        validate: {
            validator: roomId => roomId !== "",
            message: "RoomId is not empty",
            required: true
        },
    },
    Message: String,
    Time: String
});

module.exports = mongoose.model('Messages', messageSchema);
