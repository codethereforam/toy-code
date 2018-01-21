var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');

var updateRestaurants = function (db, callback) {
    db.collection('restaurants').updateOne(
        {"name": "Vella"},
        {
            $set: {"cuisine": "American (New)"},
            $currentDate: {"lastModified": true}
        }, function (err, results) {
            console.log(results);
            callback();
        });
};

var url = 'mongodb://localhost:27017';
MongoClient.connect(url, function (err, db) {
    assert.equal(null, err);
    updateRestaurants(db.db('test'), function () {
        db.close();
    });
});