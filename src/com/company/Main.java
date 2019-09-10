package com.company;

///구간의 합집합

import java.util.*;

public class Main {

    public static ArrayList<Integer> box = new ArrayList<>();
    public static long finalResult = 0;

    //    public static long max = Integer.MIN_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //총 몇개의 숫자를 입력 받을 것인지
        int a;
        long b[][];
        long c;
        int d[];
        int count = 0;
        long max = Integer.MIN_VALUE;
        a = sc.nextInt();

        b = new long[a][2];

        for (int i = 0; i < a; i++) {
            b[i][0] = sc.nextLong();
            b[i][1] = sc.nextLong();
            if (max < b[i][1]) max = b[i][1];
        }

        c = sc.nextLong();

        countN(0, Long.MAX_VALUE, b, c);

        System.out.println(finalResult);

        sc.close();

    }

    public static int findN(long[][] b, long k, long target, boolean x) {
        long result = 0;

        for (int i = 0; i < b.length; i++) {
            long left = b[i][0];
            long right = b[i][1];

            if (right < k) result += right - left + 1;
            else if (right >= k && left <= k) result += k - left;
        }

        if (x && result == target && findN(b, k + 1, target, false) == 0 && findN(b, k - 1, target, false) == -1)
            return 1;
        else if (result > target) return 0;
        else if (x && result < target && findN(b, k + 1, target, false) == 0) return 1;
        else return -1;

    }


    public static void countN(long s, long e, long[][] b, long target) {
        if (s > e) return;
        else {
            long mid = (s + e) / 2;
            int result = findN(b, mid, target, true);

            if (result == 1) finalResult = mid;
            else if (result == 0) countN(s, mid - 1, b, target);
            else {
                countN(mid + 1, e, b, target);
            }
        }

    }

}

