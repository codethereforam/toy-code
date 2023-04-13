export function getRandom(a, b) {
    let max = Math.max(a, b);
    let min = Math.min(a, b);
    return parseInt(Math.random() * (max - min)) + min;
}

export function intersect(div1, div2) {
    const rect1 = div1.getBoundingClientRect();
    const rect2 = div2.getBoundingClientRect();
    return (rect1.right > rect2.left &&
        rect1.left < rect2.right &&
        rect1.bottom > rect2.top &&
        rect1.top < rect2.bottom)
}