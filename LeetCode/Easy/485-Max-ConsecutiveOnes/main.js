function bar(state, bit) {
  const [r, c] = state;

  if (bit === 1) return [r, c + 1];
  if (c > r)
    return [c, 0];
  else
    return [r, 0];
}

function findMaxConsecutiveOnes(bits) {
  var temp = bits.reduce(bar, [0, 0]);
  return Math.max(...temp);
}