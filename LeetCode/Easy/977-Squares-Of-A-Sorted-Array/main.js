var sortedSquares = function (nums) {
  var x = nums.map((x) => x * x);
  var y = x.sort((a, b) => a - b);
  return y
}