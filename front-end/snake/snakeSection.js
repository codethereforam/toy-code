import {DIR} from "./const.js";
import Section from "./section.js";

// 蛇的一节
class SnakeSection extends Section {
    direction;

    constructor(coordinateX, coordinateY, color, direction) {
        super(coordinateX, coordinateY, color)
        this.direction = direction;
    }

    render(map) {
        map.appendChild(this.rectangle);
    }

    // 根据方向移动一步
    moveOneStep() {
        switch (this.direction) {
            case DIR.RIGHT:
                this.coordinateX += Section.UNIT_LENGTH;
                break;
            case DIR.LEFT:
                this.coordinateX -= Section.UNIT_LENGTH;
                break;
            case DIR.UP:
                this.coordinateY -= Section.UNIT_LENGTH;
                break;
            case DIR.DOWN:
                this.coordinateY += Section.UNIT_LENGTH;
                break;
        }
        super.moveRectangle();
    }
    

}

export default SnakeSection;