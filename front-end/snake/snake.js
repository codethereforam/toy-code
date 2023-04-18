// 蛇类
import SnakeSection from "./snakeSection.js";
import {intersect} from "./util.js";

class Snake {
    constructor() {
        // 存储蛇
        this.sections = [];
        this.headDirection = 'right';
    }

    initAndRender(map) {
        //创建蛇头
        let head = new SnakeSection(20, 20, this.headDirection, 'black');
        head.render(map);
        this.addSection(head);
        //创建蛇身
        for (let i = 0; i < 2; i++) {
            this.createTailSection(map);
        }
    }

    /**
     * 在蛇的尾部创建一节，并且方向和前一节一致
     */
    createTailSection(map) {
        let tail = this.getTail();
        let tailDirection = tail.direction;
        let newAddTail = new SnakeSection(20, 20, tailDirection, 'blue');
        switch (tailDirection) {
            case 'right':
                newAddTail.coordinateX = tail.coordinateX - SnakeSection.LENGTH;
                newAddTail.coordinateY = tail.coordinateY;
                break;
            case 'left':
                newAddTail.coordinateX = tail.coordinateX + SnakeSection.LENGTH;
                newAddTail.coordinateY = tail.coordinateY;
                break;
            case 'top':
                newAddTail.coordinateX = tail.coordinateX;
                newAddTail.coordinateY = tail.coordinateY + SnakeSection.LENGTH;
                break;
            case 'bottom':
                newAddTail.coordinateX = tail.coordinateX;
                newAddTail.coordinateY = tail.coordinateY - SnakeSection.LENGTH;
                break;
        }
        this.sections.push(newAddTail);
        newAddTail.render(map);
    }

    move() {
        this.sections.forEach(e => e.move());
    }

    getTail() {
        return this.sections[this.sections.length - 1];
    }

    getHead() {
        return this.sections[0];
    }

    render(map) {
        this.sections.forEach(e => e.render(map));
    }

    addSection(snakeSection) {
        this.sections.push(snakeSection);
    }

    canEatFood(food) {
        return intersect(this.getHead().rectangle, food.rectangle);
    }

    changeDirection() {
        /*
         * 改变蛇身方向
         * 除蛇头外，蛇的每节方向变为前一节的方向
         */
        for (let i = this.sections.length - 1; i > 0; i--) {
            this.sections[i].direction = this.sections[i - 1].direction;
        }
        //改变蛇头方向
        this.getHead().direction = this.headDirection;
    }
}

export default Snake;