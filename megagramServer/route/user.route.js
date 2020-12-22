const express = require('express');
const userController = require('../controller/user.controller');

const router = express.Router();

// Login
router.post('/login', userController.Login);

// Find user by id
router.post('/getUser', userController.GetUser);

// Search users by displayName
// JSON response keys: [{ _id, displayName }, ... ]
router.post('/searchUsers', userController.SearchUserByDisplayName);

router.post('/createUser', userController.InsertUser);

//router.post('/searchByAccount', userController.FindUserByAccountName);

router.post('/updateUser', userController.UpdateUser);

module.exports = router;