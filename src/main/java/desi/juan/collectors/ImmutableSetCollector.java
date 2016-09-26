/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package desi.juan.collectors;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

class ImmutableSetCollector<T> implements Collector<T, Builder<T>, Set<T>> {

  @Override
  public Supplier<Builder<T>> supplier() {
    return Builder::new;
  }

  @Override
  public BiConsumer<Builder<T>, T> accumulator() {
    return Builder::add;
  }

  @Override
  public BinaryOperator<Builder<T>> combiner() {
    return (first, second) -> first.addAll(second.build());
  }

  @Override
  public Function<Builder<T>, Set<T>> finisher() {
    return Builder::build;
  }

  @Override
  public Set<Characteristics> characteristics() {
    return ImmutableSet.of();
  }
}
