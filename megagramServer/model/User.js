const mongoose = require('mongoose');

const userSchema = mongoose.Schema({
    displayName: {
        type: String,
        required: true
    },
    email: {
        type: String,
        required: true
    },
    password: {
        type: String,
        required: true
    },
    phoneNumber: {
        type: String, 
        unique: true,
        required: true
    },
    gender:{
        type: String,
        default: "No data"
    },
    birthday:{
        type: String,
        default: "dd/MM/yyyy"
    },
    address:{
        type: String,
        default: "No data"
    }
});


module.exports = mongoose.model('Users', userSchema);