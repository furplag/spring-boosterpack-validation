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

/**
 * The value pair for validate input confirmation.
 *
 * @author furplag
 *
 */
public interface Pair<L, R> {

  /**
   *
   *
   * @return returns true if pair of values specified completely.
   */
  boolean isPresents();

  /**
   *
   *
   * @return one equals another one.
   */
  boolean isEquals();

  /**
   *
   *
   * @return {@link jp.furplag.spring.booster.validation.tuple.Pair#isPresents isPresents} and {@link jp.furplag.spring.booster.validation.tuple.Pair#isEquals isEquals}.
   */
  boolean isCollect();

  public static Pair<?, ?> of(Object left, Object right) {
    if (PairNumbers.isCreatable(left, right)) return PairNumbers.of(left, right);

    return new PairStrings(left, right);
  }
}
