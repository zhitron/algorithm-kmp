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
     * 测试indexOf方法的各种情况
     * 包括正向查找、范围查找、边界条件等
     */
    @Test
    public void test_indexOf() {
        // 测试在输入列表开头找到模式的情况
        List<String> input = Arrays.asList("A", "B", "C", "D", "E");
        List<String> pattern = Arrays.asList("A", "B");
        KMP<String> kmp = KMP.of(input, pattern);
        assertEquals(0, kmp.indexOf(0));

        // 测试在输入列表中间找到模式的情况
        pattern = Arrays.asList("C", "D");
        kmp = KMP.of(input, pattern);
        assertEquals(2, kmp.indexOf(0));

        // 测试在输入列表末尾找到模式的情况
        pattern = Arrays.asList("D", "E");
        kmp = KMP.of(input, pattern);
        assertEquals(3, kmp.indexOf(0));

        // 测试在输入列表中未找到模式的情况
        pattern = Arrays.asList("F", "G");
        kmp = KMP.of(input, pattern);
        assertEquals(-1, kmp.indexOf(0));

        // 测试使用起始索引查找模式的情况
        input = Arrays.asList("A", "B", "A", "B", "C");
        pattern = Arrays.asList("A", "B");
        kmp = KMP.of(input, pattern);
        assertEquals(0, kmp.indexOf(0));
        assertEquals(2, kmp.indexOf(1));
        assertEquals(2, kmp.indexOf(2));
        assertEquals(-1, kmp.indexOf(3));

        // 测试空模式的情况
        input = Arrays.asList("A", "B", "C");
        pattern = Collections.emptyList();
        kmp = KMP.of(input, pattern);
        assertEquals(-1, kmp.indexOf(0));

        // 测试空输入列表的情况
        input = Collections.emptyList();
        pattern = Collections.singletonList("A");
        kmp = KMP.of(input, pattern);
        assertEquals(-1, kmp.indexOf(0));

        // 测试模式长度大于输入列表长度的情况
        input = Arrays.asList("A", "B");
        pattern = Arrays.asList("A", "B", "C", "D");
        kmp = KMP.of(input, pattern);
        assertEquals(-1, kmp.indexOf(0));

        // 测试基本的字符串匹配
        Character[] charInput = {'A', 'B', 'A', 'B', 'C', 'A', 'B', 'C'};
        Character[] charTarget = {'A', 'B', 'C'};
        KMP<Character> charKmp = KMP.of(charInput, charTarget);
        assertEquals(2, charKmp.indexOf(0));

        // 测试从指定位置开始查找
        assertEquals(5, charKmp.indexOf(3));

        // 测试找不到匹配的情况
        Character[] notExist = {'X', 'Y', 'Z'};
        KMP<Character> kmp2 = KMP.of(charInput, notExist);
        assertEquals(-1, kmp2.indexOf(0));

        // 测试指定范围内的查找
        Character[] target = {'A', 'B'};
        KMP<Character> kmp3 = KMP.of(charInput, target);
        assertEquals(0, kmp3.indexOf(0, 4));

        // 测试范围内找不到匹配
        assertEquals(-1, kmp3.indexOf(3, 5));

        // 测试无效范围
        assertEquals(-1, kmp3.indexOf(5, 3));

        // 测试List的匹配
        List<String> listInput = Arrays.asList("Hello", "World", "Hello", "KMP", "Algorithm");
        List<String> listTarget = Arrays.asList("Hello", "KMP");
        KMP<String> listKmp = KMP.of(listInput, listTarget);
        assertEquals(2, listKmp.indexOf(0));

        // 测试int数组
        int[] intInput = {1, 2, 3, 4, 1, 2, 3, 5};
        int[] intTarget = {1, 2, 3};
        KMP<Integer> intKmp = KMP.of(intInput, intTarget);
        assertEquals(0, intKmp.indexOf(0));
        assertEquals(4, intKmp.indexOf(1));

        // 测试byte数组
        byte[] byteInput = {1, 2, 3, 4, 1, 2, 3, 5};
        byte[] byteTarget = {2, 3, 4};
        KMP<Byte> byteKmp = KMP.of(byteInput, byteTarget);
        assertEquals(1, byteKmp.indexOf(0));

        // 测试空数组
        Integer[] empty = {};
        Integer[] emptyTarget = {1, 2};
        KMP<Integer> emptyKmp = KMP.of(empty, emptyTarget);
        assertEquals(-1, emptyKmp.indexOf(0));

        // 测试目标数组为空
        Integer[] intArray = {1, 2, 3};
        Integer[] intEmptyTarget = {};
        KMP<Integer> intEmptyKmp = KMP.of(intArray, intEmptyTarget);
        assertEquals(-1, intEmptyKmp.indexOf(0));

        // 测试目标数组比源数组长
        Integer[] shortInput = {1, 2};
        Integer[] longTarget = {1, 2, 3, 4};
        KMP<Integer> longKmp = KMP.of(shortInput, longTarget);
        assertEquals(-1, longKmp.indexOf(0));
    }

    /**
     * 测试lastIndexOf方法的各种情况
     * 包括反向查找、范围查找、边界条件等
     */
    @Test
    public void test_lastIndexOf() {
        // 测试从末尾查找模式的情况
        List<String> input = Arrays.asList("A", "B", "C", "A", "B");
        List<String> pattern = Arrays.asList("A", "B");
        KMP<String> kmp = KMP.of(input, pattern);
        assertEquals(3, kmp.lastIndexOf(input.size()));

        // 测试从末尾未找到模式的情况
        pattern = Arrays.asList("F", "G");
        kmp = KMP.of(input, pattern);
        assertEquals(-1, kmp.lastIndexOf(input.size()));

        // 测试从末尾查找空模式的情况
        input = Arrays.asList("A", "B", "C");
        pattern = Collections.emptyList();
        kmp = KMP.of(input, pattern);
        assertEquals(-1, kmp.lastIndexOf(input.size()));

        // 测试从末尾查找模式但输入为空的情况
        input = Collections.emptyList();
        pattern = Collections.singletonList("A");
        kmp = KMP.of(input, pattern);
        assertEquals(-1, kmp.lastIndexOf(0));

        // 测试从末尾查找最后一次出现位置
        Character[] charInput = {'A', 'B', 'A', 'B', 'C', 'A', 'B', 'C'};
        Character[] charTarget = {'A', 'B', 'C'};
        KMP<Character> charKmp = KMP.of(charInput, charTarget);
        assertEquals(5, charKmp.lastIndexOf(charInput.length));

        // 测试从指定位置开始向前查找
        assertEquals(2, charKmp.lastIndexOf(4));

        // 测试找不到匹配的情况
        Character[] notExist = {'X', 'Y', 'Z'};
        KMP<Character> kmp2 = KMP.of(charInput, notExist);
        assertEquals(-1, kmp2.lastIndexOf(charInput.length));

        // 测试指定范围内的反向查找
        Character[] target = {'A', 'B'};
        KMP<Character> kmp3 = KMP.of(charInput, target);
        assertEquals(2, kmp3.lastIndexOf(0, 5));

        // 测试范围内找不到匹配
        assertEquals(0, kmp3.lastIndexOf(0, 2));

        // 测试无效范围
        assertEquals(-1, kmp3.lastIndexOf(5, 3));

        // 测试List的lastIndexOf
        List<String> listInput = Arrays.asList("Hello", "World", "Hello", "KMP", "Algorithm", "Hello", "KMP");
        List<String> listTarget = Arrays.asList("Hello", "KMP");
        KMP<String> listKmp = KMP.of(listInput, listTarget);
        assertEquals(5, listKmp.lastIndexOf(listInput.size()));

        // 测试int数组
        int[] intInput = {1, 2, 3, 4, 1, 2, 3, 5};
        int[] intTarget = {1, 2, 3};
        KMP<Integer> intKmp = KMP.of(intInput, intTarget);
        assertEquals(4, intKmp.lastIndexOf(intInput.length));

        // 测试byte数组
        byte[] byteInput = {1, 2, 3, 4, 1, 2, 3, 5};
        byte[] byteTarget = {2, 3};
        KMP<Byte> byteKmp = KMP.of(byteInput, byteTarget);
        assertEquals(5, byteKmp.lastIndexOf(byteInput.length));

        // 测试空数组
        Integer[] empty = {};
        Integer[] emptyTarget = {1, 2};
        KMP<Integer> emptyKmp = KMP.of(empty, emptyTarget);
        assertEquals(-1, emptyKmp.lastIndexOf(0));

        // 测试目标数组为空
        Integer[] intArray = {1, 2, 3};
        Integer[] intEmptyTarget = {};
        KMP<Integer> intEmptyKmp = KMP.of(intArray, intEmptyTarget);
        assertEquals(-1, intEmptyKmp.lastIndexOf(0));

        // 测试目标数组比源数组长
        Integer[] shortInput = {1, 2};
        Integer[] longTarget = {1, 2, 3, 4};
        KMP<Integer> longKmp = KMP.of(shortInput, longTarget);
        assertEquals(-1, longKmp.lastIndexOf(0));
    }

    /**
     * 测试generateNext方法的正确性
     * 验证KMP算法中next数组的生成是否正确
     */
    @Test
    public void test_generateNext() {
        // 测试 generateNext 方法的正确性
        String pattern = "ABABC";
        int[] expected = {-1, 0, 0, 1, 2};
        int[] actual = KMP.generateNext(pattern.length(), (i, j) -> pattern.charAt(i) == pattern.charAt(j));
        assertArrayEquals(expected, actual);
    }

    /**
     * 测试自定义比较器功能
     * 验证是否可以正确使用自定义比较逻辑（如忽略大小写）
     */
    @Test
    public void test_setCompare() {
        // 测试自定义比较器（忽略大小写）的情况
        List<String> input = Arrays.asList("a", "b", "c", "e", "f");
        List<String> pattern = Arrays.asList("B", "C");
        KMP<String> kmp = KMP.of(input, pattern)
                .setCompare(String::equalsIgnoreCase); // 忽略大小写的比较器
        assertEquals(1, kmp.indexOf(0));
    }

    /**
     * 测试在输入列表开头找到模式的情况
     */
    @Test
    public void test_indexOf_FoundAtBeginning() {
        List<String> input = Arrays.asList("A", "B", "C", "D", "E");
        List<String> pattern = Arrays.asList("A", "B");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(0, kmp.indexOf(0));
    }

    /**
     * 测试在输入列表中间找到模式的情况
     */
    @Test
    public void test_indexOf_FoundAtMiddle() {
        List<String> input = Arrays.asList("A", "B", "C", "D", "E");
        List<String> pattern = Arrays.asList("C", "D");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(2, kmp.indexOf(0));
    }

    /**
     * 测试在输入列表末尾找到模式的情况
     */
    @Test
    public void test_indexOf_FoundAtEnd() {
        List<String> input = Arrays.asList("A", "B", "C", "D", "E");
        List<String> pattern = Arrays.asList("D", "E");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(3, kmp.indexOf(0));
    }

    /**
     * 测试在输入列表中未找到模式的情况
     */
    @Test
    public void test_indexOf_NotFound() {
        List<String> input = Arrays.asList("A", "B", "C", "D", "E");
        List<String> pattern = Arrays.asList("F", "G");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(-1, kmp.indexOf(0));
    }

    /**
     * 测试使用起始索引查找模式的情况
     */
    @Test
    public void test_indexOf_WithStartIndex() {
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
    public void test_indexOf_EmptyPattern() {
        List<String> input = Arrays.asList("A", "B", "C");
        List<String> pattern = Collections.emptyList();

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(-1, kmp.indexOf(0));
    }

    /**
     * 测试空输入列表的情况
     */
    @Test
    public void test_indexOf_EmptyInput() {
        List<String> input = Collections.emptyList();
        List<String> pattern = Collections.singletonList("A");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(-1, kmp.indexOf(0));
    }

    /**
     * 测试模式长度大于输入列表长度的情况
     */
    @Test
    public void test_indexOf_PatternLongerThanInput() {
        List<String> input = Arrays.asList("A", "B");
        List<String> pattern = Arrays.asList("A", "B", "C", "D");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(-1, kmp.indexOf(0));
    }

    /**
     * 测试从末尾查找模式的情况
     */
    @Test
    public void test_lastIndexOf_Found() {
        List<String> input = Arrays.asList("A", "B", "C", "A", "B");
        List<String> pattern = Arrays.asList("A", "B");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(3, kmp.lastIndexOf(input.size()));
    }

    /**
     * 测试从末尾未找到模式的情况
     */
    @Test
    public void test_lastIndexOf_NotFound() {
        List<String> input = Arrays.asList("A", "B", "C", "D", "E");
        List<String> pattern = Arrays.asList("F", "G");

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(-1, kmp.lastIndexOf(input.size() - pattern.size()));
    }

    /**
     * 测试从末尾查找空模式的情况
     */
    @Test
    public void test_lastIndexOf_EmptyPattern() {
        List<String> input = Arrays.asList("A", "B", "C");
        List<String> pattern = Collections.emptyList();

        KMP<String> kmp = KMP.of(input, pattern);

        assertEquals(-1, kmp.lastIndexOf(input.size()));
    }

    /**
     * 测试从末尾查找模式但输入为空的情况
     */
    @Test
    public void test_lastIndexOf_EmptyInput() {
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
