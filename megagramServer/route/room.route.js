const express = require('express');
const roomController = require('../controller/room.controller');

const router = express.Router();

// Get member display name
// JSON response : { _id, displayName }
router.post('/memberDisplayName', roomController.GetMemberDisplayNameInSingleChat);

// Get single chat 
// JSON response : [{ _id }]
router.post('/singleChat', roomController.FindSingleChat);

// Get rooms of user
// JSON response: [{ _id, name, createDate }, ...]
router.post('/multiMembersRooms', roomController.FindMultiMembersRooms);

// Pull user from room
router.post('/leaveRoom', roomController.RemoveUserFromRoom);

router.post('/createRoom', roomController.CreateRoom);

module.exports = router;