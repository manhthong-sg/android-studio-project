const express = require('express');
//const userController = require('../controller/user.controller');
const User=require('../model/User');

const router = express.Router();

router.get('/', async (req, res)=>{
    try{
        const users= await User.find();
        res.json(users);
    }catch(err){
        res.json({message: err});
    }
})
 router.post('/', async (req,res)=>{
    
     const user= new User({
        displayName: req.body.displayName,
        email: req.body.email,
        password: req.body.password,
        phoneNumber: req.body.phoneNumber
     });
     try{
         const saveUser= await user.save();
         res.json(saveUser);
     }
     catch(err){
         res.json({message: err});
     }
 })
module.exports = router;