// 蛇的一节
class SnakeSection {
    coordinateX;
    coordinateY;
    direction;
    color;
    rectangle;

    constructor(coordinateX, coordinateY, direction, color){
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.direction = direction;
        this.color = color;
        this.rectangle = this.createRectangle();
    }

    render(map){
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

    move(){
        switch(this.direction) {
            case 'right':
                this.coordinateX += SnakeSection.LENGTH ;
                break;
            case 'left':
                this.coordinateX -= SnakeSection.LENGTH ;
                break;
            case 'top':
                this.coordinateY -= SnakeSection.LENGTH ;
                break;
            case 'bottom':
                this.coordinateY += SnakeSection.LENGTH ;
                break;
        }
        this.moveRectangle();
    }
}

SnakeSection.LENGTH = 20;

export default SnakeSection;