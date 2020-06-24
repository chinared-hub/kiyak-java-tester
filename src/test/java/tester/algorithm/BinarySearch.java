package tester.algorithm;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

/**
 * 二分查找算法
 * 前提条件：数组是有序的
 */
public class BinarySearch {
    private static LocalDateTime start;

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 5, 100, 100};
        start = LocalDateTime.now();
        System.out.println(binarySearchResult(nums, 100));
        System.out.println(binarySearchResult(nums, 2));
        System.out.printf("耗时 %dms\n", Duration.between(start, LocalDateTime.now()).toMillis());
        start = LocalDateTime.now();
        System.out.println(binarySearchLeftResult(nums, 100));
        System.out.println(binarySearchLeftResult(nums, 2));
        System.out.printf("耗时 %dms\n", Duration.between(start, LocalDateTime.now()).toMillis());
        start = LocalDateTime.now();
        System.out.println(binarySearchRightResult(nums, 100));
        System.out.println(binarySearchRightResult(nums, 2));
        System.out.printf("耗时 %dms\n", Duration.between(start, LocalDateTime.now()).toMillis());
    }

    /**
     * 在一个数组中，找到指定的数据
     */
    private static int binarySearchResult(int[] source, int target) {
        int left = 0, right = source.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (source[mid] == target) {
                return mid;
            } else if (source[mid] < target) {
                left = mid + 1;
            } else if (source[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 在一个数组中，找到指定的数据，但是数据在数组中可能重复，我需要找到最靠左的
     * 在 {0, 1, 2, 2, 5, 100, 100} 中找到 2
     * 那结果应该返回数组索引 2
     */
    private static int binarySearchLeftResult(int[] source, int target) {
        int left = 0, right = source.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (source[mid] >= target) {
                // 即使匹配到了，还是要继续的向左收缩，看看左边是否还有能够匹配到的数据
                right = mid - 1;
            } else if (source[mid] < target) {
                left = mid + 1;
            }
        }
        if (left >= source.length || source[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 在一个数组中，找到指定的数据，但是数据在数组中可能重复，我需要找到最靠右的
     * 在 {0, 1, 2, 2, 5, 100, 100} 中找到 2
     * 那结果应该返回数组索引 3
     */
    private static int binarySearchRightResult(int[] source, int target) {
        int left = 0, right = source.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (source[mid] <= target) {
                // 即使匹配到了，还是要继续的向右收缩，看看右边是否还有能够匹配到的数据
                left = mid + 1;
            } else if (source[mid] > target) {
                right = mid - 1;
            }
        }
        // while 循环的结束条件是 left <= right
        // 结束后，要么是一个都没匹配到，要么是匹配到了并且 left = right + 1
        if (left > source.length || source[left - 1] != target) {
            return -1;
        }
        return left - 1;
    }
}
