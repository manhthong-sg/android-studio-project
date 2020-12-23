var express    = require('express');        // call express
var app        = express();  
var http = require('http').Server(app);
var io = require('socket.io')(http);
var port = process.env.PORT || 3000;
const mongoose = require('mongoose');


// const userRoute = require('./route/user.route');
// const roomRoute = require('./route/room.route');
// const messageRoute = require('./route/message.route');

app.get('/',function(req,res){
    res.send("Welcome to my socket");
});


// app.use(express.static('client/'));
// app.use(bodyParser.json());


 // connect mongoose
 mongoose.set('useNewUrlParser', true);
mongoose.set('useUnifiedTopology', true);
mongoose.set('useCreateIndex', true);
mongoose.connect("mongodb://localhost:27017/megagramDB");

// app.use('/api/room', roomRoute);
// app.use('/api/message', messageRoute);
// app.use('/api/user', userRoute);

//check user connect socketIO
  io.on('connection', function(socket){
    // set up socket 
      console.log('User Connection '+socket.id);
      //server nhan tin nhan
      socket.on('client-gui-tn',function(message){
          console.log(message.senderId+ ": "+ message.nd + " send at: "+ message.time);
          io.sockets.emit('onMessage', {id: message['senderId'], noidung: message.nd, time: message.time})
      })

      // Add user to new room
      socket.on('create_new_room', data => {
        //handleSocket.AddUsersToNewRoom(io, data);
        const AddUsersToNewRoom = async (io, data) => {
          const members = data.members;
          const room = `room: ${ data.roomId }`;
          // The default namespace is '/'
          ns = io.of('/');
          // api namespace.connected return an obj contains all sockets connected (version 2.*)
          for(var id in ns.connected) {
              // Id is the id of the socket
              // Must use ns.connected[id] if use ns.connected.id return undefined
              const socket = ns.connected[id];
              if(members.indexOf(socket.name) >= 0) {
                  socket.join(room);
              }
          }
      }
      })
      
      //client login
      socket.on('client-login', (user_login)=>{

          var username=user_login["username"];
          var password = user_login['password'];
          console.log(username+" "+password);
          
          if(username=="1234" && password=="1234"){
              console.log("tdn va pass dung roi!");
              socket.emit('login_status', {nd: true, id: socket.id} )
          }
          if(username=="123" && password=="123"){
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