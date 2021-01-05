const express = require('express');
//const userController = require('../controller/user.controller');
const Room=require('../model/Room');
const Message=require('../model/Message');
const router = express.Router();

router.get('/', async (req, res)=>{
    try{
        const rooms= await Room.find();
        res.json(rooms);
    }catch(err){
        res.json({message: err});
    }
})

 router.post('/', async (req,res)=>{
    
    const user1=req.body.user1;
    const user2=req.body.user2;
     const room= new Room({
        user1: req.body.user1,
        user2:req.body.user2
     });
     const query=[{user1: user1},{user2: user2}];
     const query1=[{user1: user2},{user2: user1}]
     const roomTemp= await Room.findOne({$or: [ {$and:query} , {$and:query1}]})
     if(roomTemp){
         return res.json(roomTemp);
     }else{
        try{
            const saveRoom= await room.save();
            res.json(saveRoom);
        }
        catch(err){
            res.json({message: err});
        }
     }

 })


 //find message from roomId
router.get('/data/:roomId', async(req, res)=>{
    try{
    const message= await Message.find({ roomId: req.params.roomId});
    res.json(message)
    }catch(err){
        res.json({message: err});
    }
})

module.exports = router;