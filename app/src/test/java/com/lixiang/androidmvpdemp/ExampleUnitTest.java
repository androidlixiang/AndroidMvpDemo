package com.lixiang.androidmvpdemp;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//        int[] a = {3, 2, 4};
//        int[] ints = twoSum(a, 6);
//        System.out.println(Arrays.toString(ints));
//        String aaa=null;
//        System.out.println(aaa);

        System.out.println("   ".length());


    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int aa = target - nums[i];
            if (map.containsKey(aa)) {
                return new int[]{map.get(aa), i};
            }
            map.put(nums[i], i);
        }


        return nums;

    }

    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len + 1];

        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < len; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[len - 1], dp[len - 2]);


    }


}