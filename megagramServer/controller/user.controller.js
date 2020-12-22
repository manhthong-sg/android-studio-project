//const User = require('../../model/user.model');
const User = require('../model/user.model');
const GetUser = async (req, res) => {
    const userId = req.body.userId;
    console.log(`UserId: ${ userId }`);
    const user = await User.GetUserById(userId);
    res.json(user);
}

// Login
const Login = async (req, res) => {
    const displayName = req.body.displayName;
    const password = req.body.password;
    const user = await User.Login(displayName, password);
    res.json(user);
}

// Find user by display name to show in search fragment
const SearchUserByDisplayName = async (req, res) => {
    const displayName = req.body.displayName;
    console.log(`Display name: ${ displayName }`);
    const users = await User.SearchUserByDisplayName(displayName);
    res.json(users);
}

// Add new user
const InsertUser = async (req, res) => {
    const password = req.body.password;
    const displayName = req.body.displayName;
    const phoneNumber = req.body.phoneNumber;
    const email=req.body.email;
    const user = await User.InsertUser(phoneNumber, password, displayName, email);
    console.log(user);
    res.json(user);
}

const FindUserByDisplayName = async (req, res) => {
    const displayName = req.body.displayName;
    const users = await User.FindUserByDisplayName(displayName);
    res.json(users);
}

const UpdateUser = async (req, res) => {
    const userId = req.body.userId;
    const displayName = req.body.displayName;
    const password = req.body.password;
    const phoneNumber = req.body.phoneNumber;
    const email=req.body.email;
    const user = await User.UpdateUser(userId, phoneNumber, password, displayName, email );
    res.json(user);
}

module.exports = { 
    GetUser, 
    SearchUserByDisplayName, 
    Login,
    InsertUser,
    FindUserByDisplayName,
    UpdateUser
};