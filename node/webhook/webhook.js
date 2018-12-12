var crypto = require('crypto'); // 提供通用的加密和哈希算法
var Koa = require('koa');
const KoaRouter = require('koa-router');
const router = new KoaRouter();
const app = new Koa();

router.post('/webhook', async function (ctx, next) {
    console.log(ctx.request.header['x-hub-signature']);
    var sign = ctx.request.header['x-hub-signature']   //输出为：sha1=${secret的加密字符串}
    var event = ctx.request.header['x-github-event']    //输出为：事件名称(push)
    if (event === 'push') {
        // 根据请求的body和secret计算sha1的值
        var hmac = crypto.createHmac('sha1', 'your github secret');
        hmac.update(new Buffer(JSON.stringify(ctx.request.body))); //ctx.req.body时github传过来的post数据(跟ctx.request.body一样的)
        var signature = 'sha1=' + hmac.digest('hex'); //用这个跟sign对比
        // 可在此验证sign真伪
        if (signature === sign) {
            console.log("hello");
        }
    }
    ctx.body = {message: 'hello, github'}
});
app.use(router.routes());
app.listen(8080);