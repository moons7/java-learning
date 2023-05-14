package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 */
public class Solution1 {

    // 暴力循环破解
//    public int[] twoSum(int[] nums, int target) {
//        int start = 0, end = nums.length - 1;
//        int[] result = new int[2];
//        for (; start <= end ; ++start) {
//            for (int i = start, j = end; i < j; --j) {
//                if (nums[i] + nums[j] == target) {
//                    result[0] = i;
//                    result[1] = j;
//                    return result;
//                }
//            }
//        }
//        return result;
//    }

    //根据结果反向演算hashMap定位坐标
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int requireInt = target - nums[i];
            final Integer integer = map.get(requireInt);
            if (integer != null) {
                return new int[] {i,integer};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
