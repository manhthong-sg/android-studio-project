const express = require('express');
//const userController = require('../controller/user.controller');
const Message=require('../model/Message');

const router = express.Router();

router.get('/', async (req, res)=>{
    try{
        const messages= await Message.find();
        res.json(messages);
    }catch(err){
        res.json({message: err});
    }
})
 router.post('/', async (req,res)=>{
    
     const message= new Message({
         senderId: req.body.senderId,
         roomId: req.body.roomId,
        content: req.body.content,
        time: req.body.time
     });
     try{
         const saveMessage= await message.save();
         res.json(saveMessage);
     }
     catch(err){
         res.json({message: err});
     }
 })
module.exports = router;