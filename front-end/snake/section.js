// 蛇的一节/食物
import {getRandom} from "./util.js";

class Section {
    static UNIT_LENGTH = 20;

    coordinateX;
    coordinateY;
    color;
    rectangle;

    constructor(coordinateX, coordinateY, color) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
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
        rectangle.style.width = Section.UNIT_LENGTH + 'px';
        rectangle.style.height = Section.UNIT_LENGTH + 'px';
        rectangle.style.left = this.coordinateX + 'px';
        rectangle.style.top = this.coordinateY + 'px';
        rectangle.style.backgroundColor = this.color;
        return rectangle;
    }

    moveRectangle() {
        this.rectangle.style.left = this.coordinateX + 'px';
        this.rectangle.style.top = this.coordinateY + 'px';
    }

    static getRandomCoordinateX(map) {
        return getRandom(0, map.offsetWidth / Section.UNIT_LENGTH) * Section.UNIT_LENGTH;
    }

    static getRandomCoordinateY(map) {
        return getRandom(0, map.offsetHeight / Section.UNIT_LENGTH) * Section.UNIT_LENGTH;
    }
}

export default Section;