package com.mt.javabase;

import java.util.BitSet;

/**
 * test java &(and), |(or), ^(huo), >>(right move), <<(left move), >>>(left move). ~(non)
 * & also can be used same as % but much quick than %
 */
public class Test1 {
    public static void main(String[] args) {
        // 1: 0001
        // 2: 0010
        System.out.println(1&2); // 0000: 0
        System.out.println(1|2); // 0011: 3
        System.out.println(1^2); // 0011: 3
        System.out.println(1<<2); // 0100: 4
       // System.out.println(1<<<2); // not exist <<<
        System.out.println(1>>2); // 0000: 0
        System.out.println(1>>>2); // 0000: 0
        System.out.println(~1); //1110:

        //% and &, if we calculate 10 % a, so its same result as 10 & (a-1), but a must be a=2,4.6.8 ...2*n, ...
        System.out.println(10 % 4);  //2
        System.out.println(10 & (4-1));  //2
        BitSet d = new BitSet(10);
        d.set(0);
        d.set(3);
        d.set(8);
        d.set(11);
        System.out.println(d);
    }
}
