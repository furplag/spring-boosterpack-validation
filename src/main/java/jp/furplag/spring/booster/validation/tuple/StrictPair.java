/**
 * Copyright (C) 2016+ furplag (https://github.com/furplag)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.furplag.spring.booster.validation.tuple;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public abstract class StrictPair<T> implements Pair<T, T> {

  protected final T left;

  protected final T right;

  protected final List<T> values;

  @SuppressWarnings("unchecked")
  protected StrictPair(T left, T right) {
    this.left = left;
    this.right = right;
    values = ImmutableList.copyOf(Lists.newArrayList(left, right).stream().sorted((Comparator<? super T>) Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.toList()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isPresents() {
    return left != null && right != null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEquals() {
    return isPresents() && left.equals(right);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isCollect() {
    return isEquals();
  }

  public T smallerOne() {
    return values.get(0);
  }

  public T greaterOne() {
    return values.get(values.size() - 1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    // @formatter:off
    return new StringJoiner(",", "{", "}")
      .add((left != null ? left :right).getClass().getSimpleName())
      .add(Objects.toString(left)).add(Objects.toString(right)).toString();
    // @formatter:on
  }
}
