var net = require('net');
var HOST = '127.0.0.1';
var PORT = 6969;

net.createServer(function(sock) {

    console.log('CONNECTED: ' + sock.remoteAddress +':'+ sock.remotePort);

    sock.on('data', function(data) {
        console.log('Otrzymano wiadomość: '+data);
        sock.write("Godd says: "+data);
    });

    sock.on('close', function(data) {
        console.log('CLOSED: ' + sock.remoteAddress +' '+ sock.remotePort);
    });
}).listen(PORT, HOST);

console.log('Server listening on ' + HOST +':'+ PORT);
