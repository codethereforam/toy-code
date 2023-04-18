// 蛇的一节/食物
class Section {
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

}

Section.UNIT_LENGTH = 20;

export default Section;