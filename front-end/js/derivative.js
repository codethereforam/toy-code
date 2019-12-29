const DELTA_X = 0.000001;
function d(f) {
    return x => (f(x + DELTA_X) - f(x)) / DELTA_X;
}
f = x => x * x + x + 1;
f1 = d(f);
console.log(f1(3));
f2 = d(f1);
console.log(f2(1));