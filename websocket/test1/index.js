let express = require('express'),
    app = express(),
    http = require('http').Server(app),
    io = require('socket.io')(http);
app.use(express.static(__dirname + '/public'));
app.get('/', function (req, res) {
    res.sendfile('index.html');
});
http.listen(8080, function () {
    console.log('listening on port 8080');
});
io.on('connection', function(socket){
    console.log('a user connected');
    socket.on('notification', function(msg){
        console.log(msg);
        io.emit('notification', msg);
    });
});