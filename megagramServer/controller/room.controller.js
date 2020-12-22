//const Room = require('../../model/room.model');
const Room = require('../model/room.model');
// Get display name of your friend to set title of action bar
const GetMemberDisplayNameInSingleChat = async (req, res) => {
    const roomId = req.body.roomId;
    const userId = req.body.userId;
    const user = await Room.GetMemberDisplayNameInSingleChat(roomId, userId);
    res.json(user);
}

// Find single chat group of two user 
const FindSingleChat  = async (req, res) => {
    const userId = req.body.userId;
    const searchedUserId = req.body.searchedUserId;
    const roomId = await Room.FindSingleChat(userId, searchedUserId);
    res.json(roomId);
}

// Find rooms of user to show in group fragment
const FindMultiMembersRooms = async (req, res) => {
    const userId = req.body.userId;
    const rooms = await Room.FindMultiMembersRooms(userId);
    res.json(rooms);
}

// Remove user from room when user click leave room
const RemoveUserFromRoom = async (req, res) => {
    const userId = req.body.userId;
    const roomId = req.body.roomId;
    const rs = await Room.RemoveUserFromRoom(userId, roomId);
    res.json(rs);
}

const CreateRoom = async (req, res) => {
    const members = req.body.members;
    const name = req.body.name;
    const rs = await Room.CreateRoom(name, members);
    res.json(rs);
}

module.exports = {
    GetMemberDisplayNameInSingleChat,
    FindSingleChat,
    FindMultiMembersRooms,
    RemoveUserFromRoom,
    CreateRoom
}