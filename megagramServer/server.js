var express    = require('express');        // call express
var app        = express();  
var http = require('http').Server(app);
var io = require('socket.io')(http);
var port = process.env.PORT || 3000;


app.get('/',function(req,res){
    res.send("Welcome to my socket");
});


io.on('connection', function (socket) {

    console.log('one user connected : '+socket.id);

    // when the client emits 'new message', this listens and executes
    socket.on('new-message', function (data) {
        // we tell the client to execute 'new message'
        console.log('this is message :'+ data);
    });

});


http.listen(port, function () {
  console.log('Server listening at port %d', port);
});