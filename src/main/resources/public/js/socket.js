/**
 * Base on websocket.org documentation and vacax (GitHub) websocket projects.
 * */

let webSocket;
let timeToVerify = 100000;

function connect() {
    //Creating the socket
    webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/broadcast");

    //Events
    webSocket.onmessage = function(data){ received(data); };
    webSocket.onopen  = function(e){ console.log("Connected with status " + this.readyState); };
    webSocket.onclose = function(e){ console.log("Disconnected with status " + this.readyState); };
}

function received(data) {
    console.log("New message: " + data.data);
    $("#messages").append("<p>" + data.data + "</p>");
}

function verifyConnection() {
    if (!webSocket || webSocket.readyState == 3) {
        connect();
    }
}

//Verify the connection every timeToVerify
setInterval(verifyConnection, timeToVerify);

//JQuery
$(document).ready(function () {
    console.log("Starting WebSocket...");

    //Handshaking with the server
    connect();

    $("#send").click(function () {
        let value = $("#message").val();
        if (value) {
            webSocket.send(value);
            $("#message").val("")
        }
    });
});