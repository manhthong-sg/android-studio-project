var express    = require('express');        // call express
var app        = express();  
var http = require('http').Server(app);
var io = require('socket.io')(http);
var port = process.env.PORT || 3000;


app.get('/',function(req,res){
    res.send("Welcome to my socket");
});

var mess=[];
  io.sockets.on('connection', function(socket){

    console.log('User Conncetion');
    
      socket.on('client-gui-tn',function(msg){
          console.log(msg);
          mess.push(msg);
          io.sockets.emit('onMessage',{noidung:mess})
      })


    // socket.emit('user-connect' , {socketID: socket.id});
    // socket.on('client-gui-tn', function(msg){
    //   console.log("Message " + msg+socket.id);
      
    //  //io.emit('server-gui-tn', {noidung: msg,socket:socket.id});
    //   io.to(socket.id).emit('server-gui-tn',{noidung:msg,socket:socket.id})
    // });
  });
http.listen(port, function () {
  console.log('Server listening at port %d', port);
});