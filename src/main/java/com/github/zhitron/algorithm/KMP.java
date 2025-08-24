package com.github.zhitron.algorithm;

import com.github.zhitron.lambda.predicate.TwicePredicateInt;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;

/**
 * KMP算法实现类，用于高效地在数组或列表中查找子序列。
 *
 * @param <E> 元素类型
 * @author zhitron
 */
@SuppressWarnings("NumberEquality")
public final class KMP<E> {
    /**
     * 输入序列的长度提供器
     */
    private IntSupplier inputLength;
    /**
     * 输入序列的元素访问器
     */
    private IntFunction<E> inputAccessor;
    /**
     * 模式序列的长度提供器
     */
    private IntSupplier targetLength;
    /**
     * 模式序列的元素访问器
     */
    private IntFunction<E> targetAccessor;
    /**
     * 元素比较器，默认使用Objects::equals
     */
    private BiPredicate<E, E> compare = Objects::equals;

    /**
     * 创建一个新的KMP实例
     *
     * @param <E> 元素类型
     * @return 新的KMP实例
     */
    public static <E> KMP<E> of() {
        return new KMP<>();
    }

    /**
     * 创建一个KMP实例，用于在给定的数组中查找目标子数组。
     *
     * @param input  要搜索的数组
     * @param target 要查找的目标子数组
     * @param <E>    数组元素的类型
     * @return 配置好的KMP实例
     */
    public static <E> KMP<E> of(E[] input, E[] target) {
        return KMP.<E>of()
                .setInputLength(() -> input == null ? 0 : input.length)
                .setInputAccessor((i) -> input[i])
                .setTargetLength(() -> target == null ? 0 : target.length)
                .setTargetAccessor((i) -> target[i]);
    }

    /**
     * 创建一个KMP实例，用于在给定的列表中查找目标子列表。
     *
     * @param input  要搜索的列表
     * @param target 要查找的目标子列表
     * @param <E>    列表元素的类型
     * @return 配置好的KMP实例
     */
    public static <E> KMP<E> of(List<E> input, List<E> target) {
        return KMP.<E>of()
                .setInputLength(() -> input == null ? 0 : input.size())
                .setInputAccessor(input::get)
                .setTargetLength(() -> target == null ? 0 : target.size())
                .setTargetAccessor(target::get);
    }

    /**
     * 创建一个KMP实例，用于在给定的boolean数组中查找目标子数组。
     *
     * @param input  要搜索的boolean数组
     * @param target 要查找的目标boolean子数组
     * @return 配置好的KMP实例
     */
    public static KMP<Boolean> of(boolean[] input, boolean[] target) {
        return KMP.<Boolean>of()
                .setInputLength(() -> input == null ? 0 : input.length)
                .setInputAccessor((i) -> input[i])
                .setTargetLength(() -> target == null ? 0 : target.length)
                .setTargetAccessor((i) -> target[i])
                .setCompare((ie, te) -> ie == te);
    }

    /**
     * 创建一个KMP实例，用于在给定的char数组中查找目标子数组。
     *
     * @param input  要搜索的char数组
     * @param target 要查找的目标char子数组
     * @return 配置好的KMP实例
     */
    public static KMP<Character> of(char[] input, char[] target) {
        return KMP.<Character>of()
                .setInputLength(() -> input == null ? 0 : input.length)
                .setInputAccessor((i) -> input[i])
                .setTargetLength(() -> target == null ? 0 : target.length)
                .setTargetAccessor((i) -> target[i])
                .setCompare((ie, te) -> ie == te);
    }

    /**
     * 创建一个KMP实例，用于在给定的byte数组中查找目标子数组。
     *
     * @param input  要搜索的byte数组
     * @param target 要查找的目标byte子数组
     * @return 配置好的KMP实例
     */
    public static KMP<Byte> of(byte[] input, byte[] target) {
        return KMP.<Byte>of()
                .setInputLength(() -> input == null ? 0 : input.length)
                .setInputAccessor((i) -> input[i])
                .setTargetLength(() -> target == null ? 0 : target.length)
                .setTargetAccessor((i) -> target[i])
                .setCompare((ie, te) -> ie == te);
    }

    /**
     * 创建一个KMP实例，用于在给定的short数组中查找目标子数组。
     *
     * @param input  要搜索的short数组
     * @param target 要查找的目标short子数组
     * @return 配置好的KMP实例
     */
    public static KMP<Short> of(short[] input, short[] target) {
        return KMP.<Short>of()
                .setInputLength(() -> input == null ? 0 : input.length)
                .setInputAccessor((i) -> input[i])
                .setTargetLength(() -> target == null ? 0 : target.length)
                .setTargetAccessor((i) -> target[i])
                .setCompare((ie, te) -> ie == te);
    }

    /**
     * 创建一个KMP实例，用于在给定的int数组中查找目标子数组。
     *
     * @param input  要搜索的int数组
     * @param target 要查找的目标int子数组
     * @return 配置好的KMP实例
     */
    public static KMP<Integer> of(int[] input, int[] target) {
        return KMP.<Integer>of()
                .setInputLength(() -> input == null ? 0 : input.length)
                .setInputAccessor((i) -> input[i])
                .setTargetLength(() -> target == null ? 0 : target.length)
                .setTargetAccessor((i) -> target[i])
                .setCompare((ie, te) -> ie == te);
    }

    /**
     * 创建一个KMP实例，用于在给定的long数组中查找目标子数组。
     *
     * @param input  要搜索的long数组
     * @param target 要查找的目标long子数组
     * @return 配置好的KMP实例
     */
    public static KMP<Long> of(long[] input, long[] target) {
        return KMP.<Long>of()
                .setInputLength(() -> input == null ? 0 : input.length)
                .setInputAccessor((i) -> input[i])
                .setTargetLength(() -> target == null ? 0 : target.length)
                .setTargetAccessor((i) -> target[i])
                .setCompare((ie, te) -> ie == te);
    }

    /**
     * 创建一个KMP实例，用于在给定的float数组中查找目标子数组。
     *
     * @param input  要搜索的float数组
     * @param target 要查找的目标float子数组
     * @return 配置好的KMP实例
     */
    public static KMP<Float> of(float[] input, float[] target) {
        return KMP.<Float>of()
                .setInputLength(() -> input == null ? 0 : input.length)
                .setInputAccessor((i) -> input[i])
                .setTargetLength(() -> target == null ? 0 : target.length)
                .setTargetAccessor((i) -> target[i])
                .setCompare((ie, te) -> Float.compare(ie, te) == 0);
    }

    /**
     * 创建一个KMP实例，用于在给定的double数组中查找目标子数组。
     *
     * @param input  要搜索的double数组
     * @param target 要查找的目标double子数组
     * @return 配置好的KMP实例
     */
    public static KMP<Double> of(double[] input, double[] target) {
        return KMP.<Double>of()
                .setInputLength(() -> input == null ? 0 : input.length)
                .setInputAccessor((i) -> input[i])
                .setTargetLength(() -> target == null ? 0 : target.length)
                .setTargetAccessor((i) -> target[i])
                .setCompare((ie, te) -> Double.compare(ie, te) == 0);
    }

    /**
     * 生成KMP算法的next数组。
     * KMP算法是一种字符串匹配算法，next数组用于在匹配失败时确定模式串的滑动位置。
     *
     * @param length  模式串的长度
     * @param compare 用于比较模式串中字符的谓词
     * @return 返回生成的next数组，next数组的每个元素表示在匹配失败时模式串的滑动位置
     */
    public static int[] generateNext(int length, TwicePredicateInt compare) {
        int[] next = new int[length];
        next[0] = -1; // 初始化next数组的第一个元素为-1
        int i = 0, j = -1;
        // 通过双指针法计算next数组
        while (i < length - 1) {
            // 如果j为-1或者当前字符匹配成功，则i和j同时向后移动，并更新next数组
            if (j == -1 || compare.test(i, j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                // 如果字符匹配失败，则j回退到next[j]的位置
                j = next[j];
            }
        }
        return next;
    }

    /**
     * 私有构造函数，防止外部实例化
     */
    private KMP() {
    }

    /**
     * 获取输入序列的长度
     *
     * @return 输入序列的长度
     */
    public int getInputLength() {
        return inputLength.getAsInt();
    }

    /**
     * 获取目标序列的长度
     *
     * @return 目标序列的长度
     */
    public int getTargetLength() {
        return targetLength.getAsInt();
    }

    /**
     * 设置输入序列的长度提供器
     *
     * @param inputLength 长度提供器
     * @return 当前KMP实例
     */
    public KMP<E> setInputLength(IntSupplier inputLength) {
        this.inputLength = inputLength;
        return this;
    }

    /**
     * 设置输入序列的元素访问器
     *
     * @param inputAccessor 元素访问器
     * @return 当前KMP实例
     */
    public KMP<E> setInputAccessor(IntFunction<E> inputAccessor) {
        this.inputAccessor = inputAccessor;
        return this;
    }

    /**
     * 设置模式序列的长度提供器
     *
     * @param targetLength 长度提供器
     * @return 当前KMP实例
     */
    public KMP<E> setTargetLength(IntSupplier targetLength) {
        this.targetLength = targetLength;
        return this;
    }

    /**
     * 设置模式序列的元素访问器
     *
     * @param targetAccessor 元素访问器
     * @return 当前KMP实例
     */
    public KMP<E> setTargetAccessor(IntFunction<E> targetAccessor) {
        this.targetAccessor = targetAccessor;
        return this;
    }

    /**
     * 设置元素比较器
     *
     * @param compare 元素比较器
     * @return 当前KMP实例
     */
    public KMP<E> setCompare(BiPredicate<E, E> compare) {
        this.compare = compare;
        return this;
    }

    /**
     * 查找指定数组在当前列表中的第一次出现的位置。
     * 该函数使用KMP算法进行匹配，以提高查找效率。
     *
     * @param start 起始查找位置
     * @return 如果找到指定数组，则返回其在当前列表中的起始索引；否则返回 -1
     */
    public int indexOf(int start) {
        int inputLen = inputLength.getAsInt();
        int valuesLen = targetLength.getAsInt();
        // 检查输入数组是否为空或 null，如果是则直接返回 -1
        // 检查输入数组长度是否大于当前列表长度，如果是则直接返回 -1
        if (inputLen == 0 || valuesLen == 0 || valuesLen > inputLen) return -1;
        if (start >= inputLen) return -1;
        if (start < 0) start = 0;
        // 使用KMP算法生成next数组，用于优化匹配过程
        int[] next = KMP.generateNext(valuesLen, (i, j) -> compare.test(targetAccessor.apply(i), targetAccessor.apply(j)));
        // 初始化指针，i 用于遍历当前列表，j 用于遍历输入数组
        int i = start, j = 0;
        while (i < inputLen && j < valuesLen) {
            // 如果字符匹配，则继续比较下一个字符
            if (j == -1 || compare.test(inputAccessor.apply(i), targetAccessor.apply(j))) {
                i++;
                j++;
            } else {
                // 如果字符不匹配，则根据next数组调整 j 的位置
                j = next[j];
            }
        }
        // 如果 j 等于输入数组的长度，说明找到了匹配的数组，返回起始索引
        if (j == valuesLen) {
            return i - j;
        } else {
            // 否则返回 -1，表示未找到匹配的数组
            return -1;
        }
    }

    /**
     * 查找指定数组在当前列表中的最后一次出现的位置。
     * 该函数使用修改版的KMP算法从末尾开始匹配，以提高查找效率。
     *
     * @param start 起始查找位置
     * @return 如果找到指定数组，则返回其在当前列表中的起始索引；否则返回 -1
     */
    public int lastIndexOf(int start) {
        int inputLen = inputLength.getAsInt();
        int valuesLen = targetLength.getAsInt();
        // 检查输入数组是否为空或 null，如果是则直接返回 -1
        // 检查输入数组长度是否大于当前列表长度，如果是则直接返回 -1
        if (inputLen == 0 || valuesLen == 0 || valuesLen > inputLen) return -1;
        if (start <= 0) return -1;
        if (start > inputLen - valuesLen) start = inputLen - valuesLen;
        // 使用KMP算法生成next数组，用于优化匹配过程
        int[] next = KMP.generateNext(valuesLen, (i, j) -> compare.test(targetAccessor.apply(i), targetAccessor.apply(j)));
        // 从列表末尾开始匹配，i为列表中的起始位置，j为模式串的匹配位置
        int i = start;
        int j = 0;
        // 使用KMP算法进行匹配
        while (i >= 0 && j < valuesLen) {
            if (j == -1 || compare.test(inputAccessor.apply(i + j), targetAccessor.apply(j))) {
                j++;
            } else {
                // 根据next数组移动i，并更新j的值
                i -= (j - next[j]);
                j = next[j];
            }
        }
        // 判断是否找到匹配的数组
        if (j == valuesLen) {
            return i;
        } else {
            return -1;
        }
    }
}
