var express    = require('express');        // call express
var app        = express();  
var http = require('http').Server(app);
var io = require('socket.io')(http);
var port = process.env.PORT || 3000;


app.get('/',function(req,res){
    res.send("Welcome to my socket");
});
io.on('connection', (socket) => {
    console.log('a user connected'+' '+ socket.id);
    // socket.on('thong', (data)=>{
    //   console.log(data);
    // })
    socket.on('client-gui-tn', (data)=>{
      console.log(data);
      io.sockets.emit('server-gui-tn', {noidung: data});
    })
  });

http.listen(port, function () {
  console.log('Server listening at port %d', port);
});