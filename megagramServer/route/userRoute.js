const express = require('express');
//const userController = require('../controller/user.controller');
const User=require('../model/User');

const router = express.Router();

//get all from users
router.get('/', async (req, res)=>{
    try{
        const users= await User.find();
        res.json(users);
    }catch(err){
        res.json({message: err});
    }
})
//get user profile by id
// router.get('/:userID', async (req, res)=>{
//     try{
//         const user= await User.findById(req.params.userID);
//         res.json(user);
//     }catch{
//         res.json({message: err});
        
//     }
// })

// show user profile by phoneNumber
router.get('/data/:phoneNumber', async (req, res)=>{
    try{
        const user= await User.find({ phoneNumber: req.params.phoneNumber});
        if(user != null && user != ""){
        res.json(user);
        }else{
            res.status(404);
            //user.password="0_found"
            res.send({
                //error: 'Not found'
                displayName: "No found",
                email: "No found",
                password: "0_found",
                phoneNumber: "No found"
            })
        }
    }catch(err){
        res.json({message: err});
    }
})
//show password when know phone number
// router.get('/:phoneNumber/password', async (req, res)=>{
//     try{
//         //const user= await User.findById(req.params.userID);
//         const user= await User.findOne({ phoneNumber: req.params.phoneNumber});
//         res.json({password: user.password});
//     }catch{
//         res.json({message: err});
//     }
// })

//update user profile
router.post('/data/update', async(req, res)=>{
    var newvalues = {$set:
    {
        displayName: req.body.displayName,
        gender: req.body.gender,
        birthday: req.body.birthday,
        phoneNumber: req.body.phoneNumber,
        email: req.body.email,
        address: req.body.address
    }};
    await User.updateOne({phoneNumber: req.body.phoneNumber}, newvalues, function(err,res) {
        if (err) return res.json({msg: err});        
    });
    res.json({msg:'Thay đổi mật khẩu thành công'})
});
//submits a user
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