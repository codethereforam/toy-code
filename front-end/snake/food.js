import Section from "./section.js";
import {getRandom} from "./util.js";

// 食物
class Food extends Section {
    constructor(coordinateX, coordinateY, color) {
        super(coordinateX, coordinateY, color);
    }

    // 移动到随机位置
    moveRandomLocation(map) {
        this.coordinateX = getRandom(0, map.offsetWidth / Section.UNIT_LENGTH - 10) * Section.UNIT_LENGTH;
        this.coordinateY = getRandom(0, map.offsetHeight / Section.UNIT_LENGTH - 10) * Section.UNIT_LENGTH;
        super.moveRectangle();
    }

}

export default Food;