var express = require('express');
var app = express();

require('./routes/index')(app);

app.listen(7777);