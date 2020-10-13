/**
 * Variable hoisting
 * Another unusual thing about variables in JavaScript is that
 * you can refer to a variable declared later, without getting an exception.
 * This concept is known as hoisting; variables in JavaScript are in a sense
 * "hoisted" or lifted to the top of the function or statement.
 * However, variables that are hoisted will return a value of undefined.
 * So even if you declare and initialize after you use or refer to this variable,
 * it will still return undefined.
 */

/**
 * Example 1
 */
console.log(x === undefined); // true
var x = 3;

/**
 * Example 2
 */
// will return a value of undefined
var myvar = 'my value';
(function () {
    console.log(myvar); // undefined
    var myvar = 'local value';
})();

/**
 * The above examples will be interpreted the same as:
 */


/**
 * Example 1
 */
var x;
console.log(x === undefined); // true
x = 3;

/**
 * Example 2
 */
var myvar = 'my value';
(function() {
    var myvar;
    console.log(myvar); // undefined
    myvar = 'local value';
})();

/**
 * Because of hoisting, all var statements in a function should be placed as near to the top
 * of the function as possible. This best practice increases the clarity of the code.
 */

/**
 * In ECMAScript 2015, let (const) will not hoist the variable to the top of the block.
 * However, referencing the variable in the block before the variable declaration results
 * in a ReferenceError. The variable is in a "temporal dead zone" from the start of the block
 * until the declaration is processed.
 */
console.log(y); // ReferenceError
let y = 3;

/**
 * Function hoisting
 * For functions, only the function declaration gets hoisted to the top and not the function expression.
 */

/* Function declaration */
foo(); // "bar"

function foo() {
    console.log('bar');
}

/* Function expression */

baz(); // TypeError: baz is not a function

var baz = function() {
    console.log('bar2');
}