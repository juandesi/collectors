/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package desi.juan.collectors;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * A class that provides immutable collectors instances.
 */
public class ImmutableCollectors {

  /**
   * Returns an {@link ImmutableListCollector} instance, that accumulates the elements of the stream
   * into a {@link List} of final state.
   */
  public static <T> ImmutableListCollector<T> toList() {
    return new ImmutableListCollector<>();
  }

  /**
   * Returns an {@link ImmutableListMappingCollector} instance, that accumulates the elements of the stream
   * to a {@link List} of final state applying the mapping function to each of them.
   */
  public static <T, R> ImmutableListMappingCollector<T, R> toList(Function<T, R> mapper) {
    return new ImmutableListMappingCollector<>(mapper);
  }

  /**
   * Returns an {@link ImmutableSetCollector} instance, that accumulates the elements of the stream
   * to a {@link Set} of final state.
   */
  public static <T> ImmutableSetCollector<T> toSet() {
    return new ImmutableSetCollector<>();
  }

  /**
   * Returns an {@link ImmutableSetCollector} instance, that accumulates the elements of the stream
   * to a {@link Set} of final state applying the mapping function to each of them.
   */
  public static <T, R> ImmutableSetMappingCollector<T, R> toSet(Function<T, R> mapper) {
    return new ImmutableSetMappingCollector<>(mapper);
  }

  /**
   * Returns an {@link ImmutableMapCollector} instance, that accumulates the elements of the stream
   * to a {@link Map} of final state applying the mapping functions to create the key and the value of each entry.
   */
  public static <T, K, V> ImmutableMapCollector<T, K, V> toMap(Function<T, K> keyProducer, Function<T, V> valueProducer) {
    return new ImmutableMapCollector<>(keyProducer, valueProducer);
  }
}
