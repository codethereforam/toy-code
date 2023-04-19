// 蛇类
import SnakeSection from "./snakeSection.js";
import {intersect} from "./util.js";
import {DIR} from "./const.js";
import Section from "./section.js";

class Snake {
    constructor() {
        // 存储snakeSection
        this.sections = [];
        this.headDirection = DIR.RIGHT;
    }

    initAndRender(map) {
        //创建蛇头
        let head = new SnakeSection(Section.getRandomCoordinateX(map), Section.getRandomCoordinateY(map), 'black', this.headDirection);
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
        let newAddTailCoordinateX = tail.coordinateX;
        let newAddTailCoordinateY = tail.coordinateY;
        switch (tailDirection) {
            case DIR.RIGHT:
                newAddTailCoordinateX = tail.coordinateX - Section.UNIT_LENGTH;
                break;
            case DIR.LEFT:
                newAddTailCoordinateX = tail.coordinateX + Section.UNIT_LENGTH;
                break;
            case DIR.UP:
                newAddTailCoordinateY = tail.coordinateY + Section.UNIT_LENGTH;
                break;
            case DIR.DOWN:
                newAddTailCoordinateY = tail.coordinateY - Section.UNIT_LENGTH;
                break;
        }

        let newAddTail = new SnakeSection(newAddTailCoordinateX, newAddTailCoordinateY, 'blue', tailDirection);
        this.addSection(newAddTail);
        newAddTail.render(map);
    }

    move() {
        this.sections.forEach(e => e.moveOneStep());
    }

    getTail() {
        return this.sections[this.sections.length - 1];
    }

    getHead() {
        return this.sections[0];
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
            this.sections[i].direction = this.sections[i-1].direction;
        }
        //改变蛇头方向
        this.getHead().direction = this.headDirection;
    }
}

export default Snake;