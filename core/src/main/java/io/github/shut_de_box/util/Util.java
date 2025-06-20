package io.github.shut_de_box.util;

// Implementation gotten from https://geeksforgeeks.org/java/java-program-for-subset-sum-problem-dp-25/
public class Util {

    public static boolean isSubsetSum1Or2(int[] set, int sum) {
        int n = set.length;

        // Check for single element
        for (int i = 0; i < n; i++) {
            if (set[i] == sum)
                return true;
        }

        // Check for pair of elements
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (set[i] + set[j] == sum)
                    return true;
            }
        }
        return false;
    }

    public static void doWithChance(float chance, Runnable toRun) {
        double randomN = Math.random(); 
        if (randomN < chance) {
            toRun.run();
        }
    }
}