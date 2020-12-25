const express = require('express');
//const userController = require('../controller/user.controller');
const Room=require('../model/Room');

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
    
     const room= new Room({
        name: req.body.name,
        members: req.body.members
     });
     try{
         const saveRoom= await room.save();
         res.json(saveRoom);
     }
     catch(err){
         res.json({message: err});
     }
 })
module.exports = router;