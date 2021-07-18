package com.xiaopantx.hutools.bloom;

import cn.hutool.bloomfilter.BitMapBloomFilter;

/**
 * @author panxj
 */
public class BloomTest {

    public static void main(String[] args) {

        BitMapBloomFilter filter = new BitMapBloomFilter(10);

        filter.add("abc");
        filter.add("123");
        filter.add("a");

        boolean contains = filter.contains("abc");
        System.out.println(contains);
    }
}
