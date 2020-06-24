package tester.algorithm;

import com.google.common.collect.Maps;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 43;
        LocalDateTime start = LocalDateTime.now();
//         System.out.println(coinChange1(coins, amount));
//        System.out.println(coinChange2(coins, amount));
        System.out.println(coinChange3(coins, amount));
        System.out.printf("耗时 %ds", Duration.between(start, LocalDateTime.now()).getSeconds());
    }

    /**
     * 递归实现
     */
    private static int coinChange1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        int res = -1;
        for (int coin : coins) {
            int temp = coinChange1(coins, amount - coin);
            if (temp < 0) continue;
            res = temp + 1;
        }
        return res > 0 ? res : -1;
    }

    private static Map<Integer, Integer> memo = Maps.newHashMapWithExpectedSize(64);

    /**
     * 备忘录
     */
    private static int coinChange2(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }
        int res = -1;
        for (int coin : coins) {
            int temp = coinChange2(coins, amount - coin);
            if (temp < 0) continue;
            res = temp + 1;
        }
        res = res > 0 ? res : -1;
        memo.put(amount, res);
        return res;
    }

    /**
     * 动态规划实现
     */
    private static int coinChange3(int[] coins, int amount) {
        int max = amount + 1;
        int[] enumTable = new int[max];
        Arrays.fill(enumTable, max);
        // 0 颗硬币无需组合
        enumTable[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (i - coin < 0) continue;
                enumTable[i] = Math.min(enumTable[i], 1 + enumTable[i - coin]);
            }
        }
        return enumTable[amount] == max ? -1 : enumTable[amount];
    }

}
