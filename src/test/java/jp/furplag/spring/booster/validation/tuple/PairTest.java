/**
 * Copyright (C) 2016+ furplag (https://github.com/furplag/)
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

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.mockito.internal.util.StringJoiner;

import jp.furplag.spring.booster.validation.ValidationTestBase;
import jp.furplag.spring.booster.validation.constraints.Confirmed;

public class PairTest extends ValidationTestBase {

  Object one;

  Object anotherOne;

  @Confirmed
  public Pair<?, ?> getConfirmed() {
    return Pair.of(one, anotherOne);
  }

  @Override
  public String toString() {
    return getConfirmed().toString();
  }

  public PairTest as(Object one, Object anotherOne) {
    this.one = one;
    this.anotherOne = anotherOne;

    return this;
  }

  @Test
  public void emptyIsInvalid() {
    assertThat(validator.validate(as(null, null)).size(), is(1));
  }

  @Test
  public void notPresentedIsInvalid() {
    assertThat(validator.validate(as("test", null)).size(), is(1));
    assertThat(validator.validate(as(null, "")).size(), is(1));
    assertThat(validator.validate(as("", " ")).size(), is(1));
  }

  @Test
  public void notCollectIsInvalid() {
    assertThat(validator.validate(as("", "")).size(), is(1));
    assertThat(validator.validate(as("test", "TEST")).size(), is(1));
    assertThat(validator.validate(as(1, 2)).size(), is(1));
    assertThat(validator.validate(as(10d, 100L)).size(), is(1));
    assertThat(validator.validate(as(1.23456789f, 0.123456789d)).size(), is(1));
    assertThat(validator.validate(as(LocalDate.of(2000, 12, 31), LocalDate.of(2001, 1, 1))).size(), is(1));
    assertThat(validator.validate(as(new int[]{1,2,3}, new Integer[]{1,2,3})).size(), is(1));
  }

  @Test
  public void collectIsValid() {
    assertThat(validator.validate(as(1, 1L)).size(), is(0));
    assertThat(validator.validate(as(123.456f, 123.456d)).size(), is(0));
    assertThat(validator.validate(as(123.456f, 1.23456d * 100d)).size(), is(1));
    assertThat(validator.validate(as("10", BigDecimal.TEN)).size(), is(0));
    assertThat(validator.validate(as("1E-5", .00001f)).size(), is(0));
    assertThat(validator.validate(as(LocalTime.MIN, LocalTime.MAX.plusNanos(1))).size(), is(0));
    assertThat(validator.validate(as("t e s t", StringJoiner.join("t", "e", "s", "t"))).size(), is(0));
  }
}
