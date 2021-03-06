/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package desi.juan.collectors;

import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

class ImmutableListMappingCollector<T, R> implements Collector<T, Builder<R>, List<R>> {


  private Function<T, R> mapper;

  public ImmutableListMappingCollector(Function<T, R> mapper) {
    this.mapper = mapper;
  }

  @Override
  public Supplier<Builder<R>> supplier() {
    return Builder::new;
  }

  @Override
  public BiConsumer<Builder<R>, T> accumulator() {
    return (builder, element) -> builder.add(mapper.apply(element));
  }

  @Override
  public BinaryOperator<Builder<R>> combiner() {
    return (first, second) -> first.addAll(second.build());
  }

  @Override
  public Function<Builder<R>, List<R>> finisher() {
    return Builder::build;
  }

  @Override
  public Set<Characteristics> characteristics() {
    return ImmutableSet.of();
  }
}
