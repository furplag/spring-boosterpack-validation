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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import jp.furplag.util.commons.ObjectUtils;

public abstract class PairNumbers<N extends Number> extends StrictPair<N> {

  protected static final Class<?>[] FRACTIONS = {Float.class, Double.class, BigDecimal.class};
  protected static final Class<?>[] INTEGERS = {Byte.class, Short.class, Integer.class, Long.class, BigInteger.class};

  protected PairNumbers(N left, N right) {
    super(left, right);
  }

  /**
   * Checks whether the objects a valid pair of numbers.
   *
   * @param left the object to try parsing to a number.
   * @param right the object to try parsing to a number.
   * @return returns true if the objects parsable to a number.
   */
  public static boolean isCreatable(Object left, Object right, Class<?>... classes) {
    if (ArrayUtils.isEmpty(classes)) return isCollectable(left) && isCollectable(right);

    return isNumberOf(left, classes) && isNumberOf(right, classes);
  }

  @SuppressWarnings("rawtypes")
  public static PairNumbers of(Object left, Object right) {
    if (!isCreatable(left, right)) return null;
    if (isCreatable(left, right, INTEGERS)) return new PairIntegers(left, right);

    return new PairFractions(left, right);
  }

  @Override
  public abstract boolean isEquals();

  /**
   * Checks whether the stringified object a valid Java number.
   *
   * @param o the object to try parsing to a number.
   * @return returns true if the object parsable to a number.
   */
  protected static boolean isCollectable(Object o) {
    return NumberUtils.isCreatable(Objects.toString(o, null));
  }

  /**
   *
   *
   * @param o the object to try parsing to a fraction number.
   * @return returns true if the object parsable to a fraction number.
   */
  protected static boolean isFraction(Object o) {
    return isNumberOf(o, FRACTIONS);
  }

  /**
   *
   *
   * @param o the object to try parsing to a not fraction number.
   * @return returns true if the object parsable to a integer number.
   */
  protected static boolean isInteger(Object o) {
    return isNumberOf(o, INTEGERS);
  }

  /**
   *
   *
   * @param o the object to try parsing to a number.
   * @param classes {@code Class<? extends Number>[]}.
   * @return returns true if specified classes contains the parsed number class.
   */
  private static boolean isNumberOf(Object o, Class<?>... classes) {
    // @formatter:off
    return isCollectable(o) ? ObjectUtils.isAny(NumberUtils.createNumber(Objects.toString(o)).getClass(), classes) : false;
    // @formatter:on
  }
}
