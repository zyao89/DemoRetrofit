/**
 * Created by zyao89 on 2017/2/24.
 */

var date = require('./src/date.js');
var express = require('express');
var bodyParser = require('body-parser');//body解析必要
var app = express();

var users = [];//用户池

var id = 1;

app.use(bodyParser.urlencoded({extended:true}));
app.use(bodyParser.json());

app.use(function (req, res, next) {
    console.log('==> Time: ' + (new date()).format("yyyy-MM-dd hh:mm:ss"));
    next();
});

app.all("/", function (req, res) {
    res.send('Welcome...')
});

app.post("/login", function (req, res) {
    console.log('   IP: ' + req.ip);
    console.log('   URL: ' + req.baseUrl);
    console.log('   HostName: ' + req.hostname);
    console.log('BODY: ' + JSON.stringify(req.body));

    var user = {
        id : id,
        username : req.body.username,
        password : req.body.password
    }

    users.push(user)

    var data = {
        id : id++,
        message: "post is success...",
        code : 200
    };
    res.json(data);
    res.end();
});

app.get("/login", function (req, res) {
    console.log('   IP: ' + req.ip);
    console.log('   URL: ' + req.originalUrl);
    console.log('   HostName: ' + req.hostname);
    console.log('BODY: ' + JSON.stringify(req.params));

    var user = {
        id : id,
        username : req.params.username,
        password : req.params.password
    }

    users.push(user)

    var data = {
        id : id++,
        message: "get is success...",
        code : 200
    };
    res.json(data);
    res.end();
});

app.post("/user/edit", function (req, res) {
    console.log('   IP: ' + req.ip);
    console.log('   URL: ' + req.baseUrl);
    console.log('   HostName: ' + req.hostname);
    console.log('BODY: ' + JSON.stringify(req.body));

    var user = {
        id : id,
        username : req.body.username,
        password : req.body.password
    }

    users.push(user)

    var data = {
        id : id++,
        message: "post is success...",
        code : 200
    };
    res.json(data);
    res.end();
});

app.put("/put", function (req, res) {
    console.log('   URL: ' + req.baseUrl);
    console.log('BODY: ' + JSON.stringify(req.body));
    var data = {
        id : id++,
        message: "put is success...",
        code : 200
    };
    res.json(data);
    res.end();
});

app.get("/get/:id", function (req, res, next) {
    console.log('   URL: ' + req.baseUrl);
    console.log('BODY: ' + JSON.stringify(req.params));
    var id = req.params.id;
    res.json(users[id]);
    res.end();
});

app.get("/get", function (req, res, next) {
    console.log('   URL: ' + req.baseUrl);
    console.log('   originalUrl: ' + req.originalUrl);
    console.log('BODY: ' + JSON.stringify(req.params));
    var id = req.params.id;
    var data = {
        id : id,
        message: "get is success...  " + JSON.stringify(users[id]),
        code : 200
    };
    res.json(data);
    res.end();
});

app.delete("/delete", function (req, res, next) {
    console.log('   URL: ' + req.baseUrl);
    console.log('BODY: ' + JSON.stringify(req.body));
    var id = req.body.id;
    var data = {
        id : id,
        message: "delete is success...  " + JSON.stringify(users[id]),
        code : 200
    };
    res.json(data);
    res.end();
});

var server = app.listen(9999, function () {
    var host = server.address().address;
    var port = server.address().port;

    console.log('Server is running... listen: %s', port);
});