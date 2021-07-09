const isEven = (element) => element % 2 === 0;

function containsEven(n) {
  const digits = (`${n}`).split('');
  return isEven(digits.length);
}

function findNumbers(nums) {
  return nums.filter(containsEven).length;
}