import Section from "./section.js";

// 食物
class Food extends Section {
    constructor(coordinateX, coordinateY, color) {
        super(coordinateX, coordinateY, color);
    }

    // 移动到随机位置
    moveRandomLocation(map) {
        this.coordinateX = Section.getRandomCoordinateX(map);
        this.coordinateY = Section.getRandomCoordinateY(map);
        super.moveRectangle();
    }

    static buildRandomLocationFood(map) {
        return new Food(this.getRandomCoordinateX(map), this.getRandomCoordinateY(map), 'red');
    }
}

export default Food;