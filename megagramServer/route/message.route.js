const express = require('express');
const messageController = require('../controller/message.controller');

const router = express.Router();

// Get info rooms by user id
router.post('/getInfoRooms', messageController.GetInfoRooms);

// Get all conversations of user 
// JSON response : [{ roomId, name, content, time }, ... ]
router.post('/conversations', messageController.FindConversationsOfUser);

// Get all messages in room
// JSON response : [{ content, displayName, time, senderId }, ... ]
router.post('/messages', messageController.GetMessages);

module.exports = router;