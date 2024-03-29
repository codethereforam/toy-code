import Snake from "./snake.js";
import {DIR} from "./const.js";
import Section from "./section.js";
import Food from "./food.js"

// 游戏的入口文件
class Game {
    constructor() {
        this.map = this.createMap();
        this.snake = new Snake();
        this.food = Food.buildRandomLocationFood(this.map);
        // 定时器
        this.timerId = null;
    }

    start() {
        this.food.render(this.map);
        this.snake.initAndRender(this.map);
        this.runSnake();
        this.bindKey();
    }

    // 让蛇动起来
    runSnake() {
        this.timerId = setInterval(() => {
            this.snake.move();
            //当蛇遇到边界游戏结束
            this.checkHitBound();
            if (this.snake.canEatFood(this.food)) {
                console.log("吃到了食物");
                this.snake.createTailSection(this.map);
                this.food.moveRandomLocation(this.map);
            }
            this.snake.changeDirection();
        }, 150);
    }

    checkHitBound() {
        if (this.isOutOfBoundary(this.snake.getHead())) {
            console.log('Game Over');
            clearInterval(this.timerId);
        }
    }

    isOutOfBoundary(section) {
        return section.coordinateX < 0 || section.coordinateY < 0 ||
            (section.coordinateX + Section.UNIT_LENGTH) > this.map.offsetWidth ||
            (section.coordinateY + Section.UNIT_LENGTH) > this.map.offsetHeight;
    }

    // 绑定键盘事件 控制蛇的方向
    bindKey() {
        document.addEventListener('keydown', e => {
            switch (e.keyCode) {
                case 37:
                    this.snake.headDirection = DIR.LEFT;
                    break;
                case 38:
                    this.snake.headDirection = DIR.UP;
                    break;
                case 39:
                    this.snake.headDirection = DIR.RIGHT;
                    break;
                case 40:
                    this.snake.headDirection = DIR.DOWN;
                    break;
            }
            this.snake.getHead().direction = this.snake.headDirection;
        });
    }

    createMap() {
        let map = document.createElement('div');
        // 设置样式
        map.style.position = 'absolute';
        map.style.width = 400 + 'px';
        map.style.height = 400 + 'px';
        map.style.left = 0 + 'px';
        map.style.top = 0 + 'px';
        map.style.border = '4px solid orange';
        // 加载到页面
        document.body.appendChild(map);
        return map;
    }
}

export default Game;