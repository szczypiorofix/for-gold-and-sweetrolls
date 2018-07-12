var net = require('net');
var HOST = '0.0.0.0';
var PORT = 3000;

try {

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

} catch (err) {
    console.error(err);
}


console.log('Server listening on ' + HOST +':'+ PORT);
