var express    = require('express');        // call express
var app        = express();  
var http = require('http').Server(app);
var io = require('socket.io')(http);
var port = process.env.PORT || 3000;
const mongoose = require('mongoose');

app.get('/',function(req,res){
    res.send("Welcome to my socket");
});
 // connect mongoose
 mongoose.set('useNewUrlParser', true);
mongoose.set('useUnifiedTopology', true);
mongoose.set('useCreateIndex', true);
mongoose.connect("mongodb://localhost:27017/megagramDB");

// //tao Schema
// const userSchema=new mongoose.Schema({
//   name: String,
//   age: Number
// })
// //tao model
// const user=mongoose.model('user', userSchema);
// // add gia tri
// user.create([
//   {name: "Thong Bui", age: 21},
//   {name: "Thai Bui", age: 19},
//   {name: "Thui Bui", age: 20}
// ])

//const user_info=mongoose.get('user_info');

//check user connect socketIO
  io.on('connection', function(socket){
    
    console.log('User Connection '+socket.id);
    
      socket.on('client-gui-tn',function(message){
          console.log(socket.id+ " "+message);
          socket.broadcast.emit('onMessage', {noidung: message})
      })
      socket.on('client-login', (user_login)=>{

          var username=user_login["username"];
          var password = user_login['password'];
          console.log(username+" "+password);
          if(username=="0796705768" && password=="123"){
              console.log("tdn va pass dung roi!");
              socket.emit('login_status', {nd: true, id: socket.id} )
          }
          else{
            socket.emit('login_status', {nd: false} )
          }
      })
      // Check disconnected
    socket.on('disconnect', reason => {
      console.log(`${ new Date().toLocaleTimeString() }: ${ socket.id } has disconnected because ${ reason }`);
  });
  });
http.listen(port, function () {
  console.log('Server listening at port %d', port);
});