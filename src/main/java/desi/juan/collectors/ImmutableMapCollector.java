/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package desi.juan.collectors;

import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

class ImmutableMapCollector<T, K, V> implements Collector<T, Builder<K, V>, Map<K, V>> {

  private final Function<T, K> keyProducer;
  private final Function<T, V> valueProducer;

  /**
   * Creates a new instance
   *
   * @param keyProducer a mapping function to produce keys
   * @param valueProducer a mapping function to produce values
   */
  public ImmutableMapCollector(Function<T, K> keyProducer, Function<T, V> valueProducer) {
    this.keyProducer = keyProducer;
    this.valueProducer = valueProducer;
  }

  @Override
  public Supplier<Builder<K, V>> supplier() {
    return Builder::new;
  }

  @Override
  public BiConsumer<Builder<K, V>, T> accumulator() {
    return (builder, element) -> builder.put(keyProducer.apply(element), valueProducer.apply(element));
  }

  @Override
  public BinaryOperator<Builder<K, V>> combiner() {
    return (first, second) -> first.putAll(second.build());
  }

  @Override
  public Function<Builder<K, V>, Map<K, V>> finisher() {
    return Builder::build;
  }

  @Override
  public Set<Characteristics> characteristics() {
    return ImmutableSet.of();
  }
}
