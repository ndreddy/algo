/**
 * If we execute this Javascript, what will the console show?
 * It's not "outside". It's not "inside". The script won't throw an error!
 */

var text = 'outside';

function logIt() {
    console.log(text);
    var text = 'inside';
};
logIt();

/**
 * Function-level scope. Functions create new scopes in Javascript:
 * @type {string}
 */
function setVar() {
    // inside this function we have a new scope
    // so this variable, declared in this function's scope, won't be available outside the function
    var varInFunction = 'inside a function';
}

setVar();
console.log(varInFunction); // throws 'ReferenceError: varInFunction is not defined'


/**
 * Blocks like if statements and for loops do not create a new scope
 * (this is also true of Python and recent versions of Ruby, but untrue of Java and C):
 * ES6 introduces let for block.
 */
if (true) {
    // this if statement doesn't create a new scope
    // so varInIf is available in the global scope
    var varInIf = 'inside an if statement';
}
console.log(varInIf); // logs 'inside an if statement'