const mongoose = require('mongoose');


const roomSchema = mongoose.Schema({
    name: String,
    members: {
        type: String,
        required: true
    },
});


module.exports = mongoose.model('Rooms', roomSchema);