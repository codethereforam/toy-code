{
    var numbers = [2, 45, 78, 78, 45, 44, 9, 3, 11, 7]
    console.log(numbers[2])
    numbers[2] = 79
    console.log(numbers)

    var swap = (nums, index1, index2) => {
        var x = nums[index1]
        nums[index1] = nums[index2]
        nums[index2] = x
    }
    swap(numbers, 0, 2)
    console.log(numbers)

    var sort = (nums) => {
        for (var i = 0; i < nums.length - 1; i++) {
            for (var indexi = i + 1; indexi < nums.length; indexi++) {
                if (nums[indexi] < nums[i]) {
                    swap(nums, i, indexi)
                }
                else {
                }
            }
        }
    }
    sort(numbers)
    console.log(numbers)
}