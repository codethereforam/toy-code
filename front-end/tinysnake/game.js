
class Game {

    start() {
        console.log("game begin...");
        this.createRectangle();
    }

    createRectangle() {
        let x = 80;
        let y = 80;

        let rectangle = document.createElement('div');
        // 设置样式
        rectangle.style.position = 'absolute';
        rectangle.style.width = 20 + 'px';
        rectangle.style.height = 20 + 'px';
        rectangle.style.left = x + 'px';
        rectangle.style.top = y + 'px';
        rectangle.style.backgroundColor = 'red';
        document.body.appendChild(rectangle);
        setInterval(function () {
            x = x + 20;
            // y = y + 20;
            rectangle.style.left = x + 'px';
            // rectangle.style.top = y + 'px';
        }, 200);

        return rectangle;
    }

}

export default Game;