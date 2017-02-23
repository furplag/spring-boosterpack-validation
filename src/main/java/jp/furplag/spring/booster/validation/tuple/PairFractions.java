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
import java.util.Objects;

import org.apache.commons.lang3.math.NumberUtils;

public class PairFractions extends PairNumbers<BigDecimal> {

  protected PairFractions(BigDecimal left, BigDecimal right) {
    super(left, right);
  }

  public PairFractions(Object left, Object right) {
    this((BigDecimal) (isCollectable(left) ? NumberUtils.createBigDecimal(Objects.toString(left, null)) : null), (BigDecimal) (isCollectable(right) ? NumberUtils.createBigDecimal(Objects.toString(right, null)) : null));
  }

  @Override
  public boolean isEquals() {
    return isPresents() && left.compareTo(right) == 0;
  }

  public static void main(String[] args) {
    System.out.println(new PairFractions(123.456f, 123.456d));
  }
}
