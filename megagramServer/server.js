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
    socket.emit('server_back_tn', "chao em");
  });

http.listen(port, function () {
  console.log('Server listening at port %d', port);
});