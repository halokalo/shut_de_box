package io.github.shut_de_box.util;

// Implementation gotten from https://geeksforgeeks.org/java/java-program-for-subset-sum-problem-dp-25/
public class MathUtil {

    public static boolean isSubsetSum(int set[], int n, int sum) {
        // Base Cases
        if (sum == 0)
            return true;
        if (n == 0)
            return false;

        // If last element is greater than
        // sum, then ignore it
        if (set[n - 1] > sum)
            return isSubsetSum(set, n - 1, sum);

        // Else, check if sum can be obtained
        // by any of the following
        // (a) including the last element
        // (b) excluding the last element
        return isSubsetSum(set, n - 1, sum) || isSubsetSum(set, n - 1, sum - set[n - 1]);
    }
}