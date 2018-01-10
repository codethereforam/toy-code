var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');

var findRestaurants = function (db, callback) {
    // var cursor = db.collection('restaurants').find({"borough": "Manhattan"});
    // var cursor =db.collection('restaurants').find( { "address.zipcode": "10075" } );
    // var cursor =db.collection('restaurants').find( { "grades.grade": "B" } );
    // 大于
    // var cursor =db.collection('restaurants').find( { "grades.score": { $gt: 30 } } );
    // 小于
    // var cursor =db.collection('restaurants').find( { "grades.score": { $lt: 10 } } );
    // AND 运算
    /*var cursor =db.collection('restaurants').find(
        { "cuisine": "Italian", "address.zipcode": "10075" }
    );*/
    // OR 运算
    /*var cursor =db.collection('restaurants').find(
        { $or: [ { "cuisine": "Italian" }, { "address.zipcode": "10075" } ] }
    );*/
    // var cursor =db.collection('restaurants').find().sort( { "borough": 1, "address.zipcode": 1 } );
    var cursor =db.collection('restaurants').find();
    cursor.each(function (err, doc) {
        assert.equal(err, null);
        if (doc !== null) {
            console.dir(doc);
        } else {
            callback();
        }
    });
};

var url = 'mongodb://localhost:27017';
MongoClient.connect(url, function (err, db) {
    assert.equal(null, err);
    findRestaurants(db.db('test'), function () {
        db.close();
    });
});