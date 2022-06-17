package com.fantasybaby.dev.error.other.jdk8.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.summingInt;

/**
 * create a collector
 *
 * @author Reid.Liu
 * @date 2022/06/17
 */
public class MostPopularCollector<T> implements Collector<T, Map<T, Integer>, Optional<T>> {
    @Override
    public Supplier<Map<T, Integer>> supplier() {
        return HashMap::new;
    }

    /**
     * 每出现一次 +1
     *
     * @return {@link BiConsumer}<{@link Map}<{@link T}, {@link Integer}>, {@link T}>
     */
    @Override
    public BiConsumer<Map<T, Integer>, T> accumulator() {
        return (acc, elem) -> acc.merge(elem, 1, (old, value) -> old + value);
    }

    /**
     * 合路器
     *
     * @return {@link BinaryOperator}<{@link Map}<{@link T}, {@link Integer}>>
     */
    @Override
    public BinaryOperator<Map<T, Integer>> combiner() {
        return (a, b) -> Stream.concat(a.entrySet().stream(), b.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, summingInt(Map.Entry::getValue)));
    }

    /**
     * 通过map value的排序 得到最多出现的那个值
     *
     * @return {@link Function}<{@link Map}<{@link T}, {@link Integer}>, {@link Optional}<{@link T}>>
     */
    @Override
    public Function<Map<T, Integer>, Optional<T>> finisher() {
        return (acc) -> acc.entrySet().stream()
                .reduce(BinaryOperator.maxBy(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey);
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
