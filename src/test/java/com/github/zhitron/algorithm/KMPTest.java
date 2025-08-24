package com.github.zhitron.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * KMP算法测试类
 * 包含对KMP算法各种情况的测试用例
 */
public class KMPTest {

    /**
     * 测试在输入列表开头找到模式的情况
     */
    @Test
    public void testIndexOf_FoundAtBeginning() {
        List<String> input = Arrays.asList("A", "B", "C", "D", "E");
        List<String> pattern = Arrays.asList("A", "B");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(0, kmp.indexOf(0));
    }

    /**
     * 测试在输入列表中间找到模式的情况
     */
    @Test
    public void testIndexOf_FoundAtMiddle() {
        List<String> input = Arrays.asList("A", "B", "C", "D", "E");
        List<String> pattern = Arrays.asList("C", "D");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(2, kmp.indexOf(0));
    }

    /**
     * 测试在输入列表末尾找到模式的情况
     */
    @Test
    public void testIndexOf_FoundAtEnd() {
        List<String> input = Arrays.asList("A", "B", "C", "D", "E");
        List<String> pattern = Arrays.asList("D", "E");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(3, kmp.indexOf(0));
    }

    /**
     * 测试在输入列表中未找到模式的情况
     */
    @Test
    public void testIndexOf_NotFound() {
        List<String> input = Arrays.asList("A", "B", "C", "D", "E");
        List<String> pattern = Arrays.asList("F", "G");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(-1, kmp.indexOf(0));
    }

    /**
     * 测试使用起始索引查找模式的情况
     */
    @Test
    public void testIndexOf_WithStartIndex() {
        List<String> input = Arrays.asList("A", "B", "A", "B", "C");
        List<String> pattern = Arrays.asList("A", "B");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(0, kmp.indexOf(0));
        assertEquals(2, kmp.indexOf(1));
        assertEquals(2, kmp.indexOf(2));
        assertEquals(-1, kmp.indexOf(3));
    }

    /**
     * 测试空模式的情况
     */
    @Test
    public void testIndexOf_EmptyPattern() {
        List<String> input = Arrays.asList("A", "B", "C");
        List<String> pattern = Collections.emptyList();

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(-1, kmp.indexOf(0));
    }

    /**
     * 测试空输入列表的情况
     */
    @Test
    public void testIndexOf_EmptyInput() {
        List<String> input = Collections.emptyList();
        List<String> pattern = Collections.singletonList("A");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(-1, kmp.indexOf(0));
    }

    /**
     * 测试模式长度大于输入列表长度的情况
     */
    @Test
    public void testIndexOf_PatternLongerThanInput() {
        List<String> input = Arrays.asList("A", "B");
        List<String> pattern = Arrays.asList("A", "B", "C", "D");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(-1, kmp.indexOf(0));
    }

    /**
     * 测试从末尾查找模式的情况
     */
    @Test
    public void testLastIndexOf_Found() {
        List<String> input = Arrays.asList("A", "B", "C", "A", "B");
        List<String> pattern = Arrays.asList("A", "B");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(3, kmp.lastIndexOf(input.size() - pattern.size()));
    }

    /**
     * 测试从末尾未找到模式的情况
     */
    @Test
    public void testLastIndexOf_NotFound() {
        List<String> input = Arrays.asList("A", "B", "C", "D", "E");
        List<String> pattern = Arrays.asList("F", "G");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(-1, kmp.lastIndexOf(input.size() - pattern.size()));
    }

    /**
     * 测试从末尾查找空模式的情况
     */
    @Test
    public void testLastIndexOf_EmptyPattern() {
        List<String> input = Arrays.asList("A", "B", "C");
        List<String> pattern = Collections.emptyList();

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(-1, kmp.lastIndexOf(input.size()));
    }

    /**
     * 测试从末尾查找模式但输入为空的情况
     */
    @Test
    public void testLastIndexOf_EmptyInput() {
        List<String> input = Collections.emptyList();
        List<String> pattern = Collections.singletonList("A");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(-1, kmp.lastIndexOf(0));
    }

    /**
     * 测试generateNext方法的正确性
     */
    @Test
    public void testGenerateNext() {
        // 测试 generateNext 方法的正确性
        String pattern = "ABABC";
        int[] expected = {-1, 0, 0, 1, 2};
        int[] actual = KMP.generateNext(pattern.length(), (i, j) -> pattern.charAt(i) == pattern.charAt(j));

        assertArrayEquals(expected, actual);
    }

    /**
     * 测试自定义比较器（忽略大小写）的情况
     */
    @Test
    public void testCustomComparator() {
        List<String> input = Arrays.asList("a", "b", "c", "e", "f");
        List<String> pattern = Arrays.asList("B", "C");

        KMP<String> kmp = KMP.of(input, pattern)
                .setCompare(String::equalsIgnoreCase); // 忽略大小写的比较器

        assertEquals(1, kmp.indexOf(0));
    }
}
