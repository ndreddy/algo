function fib(n) {
    if (n<0) {
        throw new Error("Negative not allowed");
    } else if (n === 0 || n === 1 ){
        return n;
    }

    var a = 0 , b = 1;

    for (var i = 1; i < n; i++)  {
        a = b;
        b = a+b;
    }

    return a;
}

var val = fib(50);
console.log(val);