var express    = require('express');        // call express
var app        = express();  
var http = require('http').Server(app);
var io = require('socket.io')(http);
var port = process.env.PORT || 3000;
const mongoose = require('mongoose');
const bodyParser=require('body-parser');

const Message=require('./model/Message');

app.use(bodyParser.json());
app.get('/',function(req,res){
    res.send("Welcome to my socket");
});

//Imports routes
const userRoute= require('./route/userRoute'); 
const roomRoute=require('./route/roomRoute');
const messageRoute=require('./route/messageRoute');

app.use('/users', userRoute);
app.use('/rooms', roomRoute);
app.use('/messages', messageRoute);

app.use((req, res,next)=>{
  res.status(404);
  res.send({
    error: 'Not found'
  })
})

// app.use(express.static('client/'));



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
      socket.on('client-gui-tn', async function(message){
          console.log(message.senderId+ ": "+ message.nd + " send at: "+ message.time);
          const message1= new Message({
            UniqueId: message['senderId'],
            roomId: message.roomnhan,
           Message: message.nd,
           Time: message.time
        });
        try{
            const saveMessage= await message1.save();
        }
        catch(err){
            console.log(err)
        }
          
          io.sockets.emit('onMessage', {id: message['senderId'], noidung: message.nd, time: message.time, roomnhan: message.roomnhan})
      })


      // Check disconnected
    socket.on('disconnect', reason => {
      console.log(`${ new Date().toLocaleTimeString() }: ${ socket.id } has disconnected because ${ reason }`);
  });
  });
http.listen(port, function () {
  console.log('Server listening at port %d', port);
});