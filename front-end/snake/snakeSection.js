// 蛇的一节
import {DIR} from "./const.js";

class SnakeSection {
    coordinateX;
    coordinateY;
    direction;
    color;
    rectangle;

    constructor(coordinateX, coordinateY, direction, color) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.direction = direction;
        this.color = color;
        this.rectangle = this.createRectangle();
    }

    render(map) {
        map.appendChild(this.rectangle);
    }

    createRectangle() {
        let rectangle = document.createElement('div');
        // 设置样式
        rectangle.style.position = 'absolute';
        rectangle.style.width = SnakeSection.LENGTH + 'px';
        rectangle.style.height = SnakeSection.LENGTH + 'px';
        rectangle.style.left = this.coordinateX + 'px';
        rectangle.style.top = this.coordinateY + 'px';
        rectangle.style.backgroundColor = this.color;
        return rectangle;
    }

    moveRectangle() {
        this.rectangle.style.left = this.coordinateX + 'px';
        this.rectangle.style.top = this.coordinateY + 'px';
    }

    move() {
        switch (this.direction) {
            case DIR.RIGHT:
                this.coordinateX += SnakeSection.LENGTH;
                break;
            case DIR.LEFT:
                this.coordinateX -= SnakeSection.LENGTH;
                break;
            case DIR.UP:
                this.coordinateY -= SnakeSection.LENGTH;
                break;
            case DIR.DOWN:
                this.coordinateY += SnakeSection.LENGTH;
                break;
        }
        this.moveRectangle();
    }
}

SnakeSection.LENGTH = 20;

export default SnakeSection;