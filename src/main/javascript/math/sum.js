var sum = function () {
    var i,j,result = 0;

    // Var args
    for (i = 0; i < arguments.length; i++) {
        result += parseArg(arguments[i]);
    }

    // parses the aruguments
    function parseArg(n) {
        debugger;
        // 1. Array check
        if (Array.isArray(n)) {
            for ( j = 0; j < n.length; j++) {
                result += n[j];
            }
            return result;
        }

        // 2.function check
        if (typeof n === 'function') {
            return parseArg(n());
        }

        //3. Number check
        return isNaN(n) ? 0 : parseInt(n, 10);
    }
    console.log(result);
    return result;
}

sum(function () { return [1,2];},function () { return [3,4];})