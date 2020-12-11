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

//tao Schema
const userSchema=new mongoose.Schema({
  name: String,
  age: Number
})
//tao model
const user=mongoose.model('user', userSchema);
// add gia tri
user.create([
  {name: "Thong Bui", age: 21},
  {name: "Thai Bui", age: 19},
  {name: "Thui Bui", age: 20}
])

  io.on('connection', function(socket){

    console.log('User Connection '+socket.id);
    
      socket.on('client-gui-tn',function(message){
          console.log(message);
          io.sockets.emit('onMessage', {noidung: message})
      })
      // Check disconnected
    socket.on('disconnect', reason => {
      console.log(`${ new Date().toLocaleTimeString() }: ${ socket.id } has disconnected because ${ reason }`);
  });
  });
http.listen(port, function () {
  console.log('Server listening at port %d', port);
});