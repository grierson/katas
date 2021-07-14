function sortedSquares(nums) {
    var x = nums.map((x) => x * x);
    var y = x.sort((a, b) => a - b);
    return y;
}

assert(sortedSquares([-4, -1, 0, 3, 10]) == [0, 1, 9, 16, 100]);
