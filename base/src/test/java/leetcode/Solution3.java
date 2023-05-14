package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 2. 无重复字符的最长子串
 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。



 示例 1:

 输入: s = "abcabcbb"
 输出: 3
 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 示例 2:

 输入: s = "bbbbb"
 输出: 1
 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 示例 3:

 输入: s = "pwwkew"
 输出: 3
 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。


 提示：

 0 <= s.length <= 5 * 104
 s 由英文字母、数字、符号和空格组成
 */
public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int strLength = s.length();
        //使用滑动窗口使用set需要clear较耗时
        //todo 更好的效率算法,set不需要clear(),因为已经包含了部分已经处理的信息，可以进行优化，
        // 进行了回溯操作，保留了部分已处理的信息
        Set<Character> set = new HashSet<>();
        int max = 0;
        for (int i = 0; i < strLength; i++) {
            for (int j = i; j < strLength; j++) {
                if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j));
                } else {
                    max = Math.max(set.size(), max);
                    set.clear();
                    break;
                }
            }
        }
        max = Math.max(set.size(), max);
        return max;
    }
}
