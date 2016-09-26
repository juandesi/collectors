/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package desi.juan.collectors;

import static desi.juan.collectors.ImmutableCollectors.toList;
import static desi.juan.collectors.ImmutableCollectors.toMap;
import static desi.juan.collectors.ImmutableCollectors.toSet;
import static java.util.function.Function.identity;
import static java.util.stream.Stream.of;
import static org.hamcrest.collection.IsIn.isIn;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class CollectorsTestCase {

  private static final String[] INPUT = {"JUAN", "GERCHO", "SANTIAGO", "LUCAS", "MAURO", "GUIDO", "BEGNI", "CHICHO"};
  private static final String CAPO = "CAPO";

  @Test(expected = UnsupportedOperationException.class)
  public void immutableList() {
    List<String> collect = of(INPUT).collect(toList());
    collect.add("Value");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void immutableSet() {
    Set<String> collect = of(INPUT).collect(toSet());
    collect.add("Value");
  }

  @Test
  public void immutableListMapping() {
    List<String> collect = of(INPUT).collect(toList(s -> s + "-" + CAPO));
    collect.forEach(p -> {
      String[] split = p.split("-");
      assertThat(split.length, is(2));
      assertThat(split[0], isIn(INPUT));
      assertThat(split[1], is(CAPO));
    });
  }

  @Test
  public void immutableSetMapping() {
    Set<String> collect = of(INPUT).collect(toSet(s -> s + "-" + CAPO));
    collect.forEach(p -> {
      String[] split = p.split("-");
      assertThat(split.length, is(2));
      assertThat(split[0], isIn(INPUT));
      assertThat(split[1], is(CAPO));
    });
  }

  @Test(expected = UnsupportedOperationException.class)
  public void immutableMap() {
    Map<String, Integer> collect = of(INPUT).collect(toMap(identity(), String::length));
    collect.put("", 0);
  }
}
