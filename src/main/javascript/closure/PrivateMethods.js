/**
 * This constructor makes three private instance variables: param, secret, and that.
 * The members of an object (function) are all public members.
 * Private methods are inner functions of the constructor.
 * To make private methods useful, we need to introduce a privileged method.
 *
 */
function Container(param) {

    function dec() {   // Private method
        if (secret > 0) {
            secret -= 1;
            return true;
        } else {
            return false;
        }
    }

    // Public member
    this.member = param; // member is public, param is private

    // Private members
    var secret = 3;     // private
    var that = this;    // private

    //privileged method.
    this.service = function () {
        return dec() ? that.member : null;
    };
}

//In the constructor: initialize public instance variables
var myContainer = new Container('abc');

// In the prototype: add public methods
Container.prototype.stamp = function (string) {
    return this.member + string;
}

console.log(myContainer.stamp('def')); // 'abcdef'.
console.log(myContainer.service()); // 'abc' for the first three times, then null
console.log(myContainer.service()); // 'abc' for the first three times, then null
console.log(myContainer.service()); // 'abc' for the first three times, then null
console.log(myContainer.service()); // 'abc' for the first three times, then null