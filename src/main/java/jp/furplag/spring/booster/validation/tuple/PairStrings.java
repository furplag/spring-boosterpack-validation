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

import java.util.Objects;

import jp.furplag.util.commons.StringUtils;

public class PairStrings extends StrictPair<String> {

  public PairStrings(String left, String right) {
    super(StringUtils.trim(left, true), StringUtils.trim(right, true));
  }

  public PairStrings(Object left, Object right) {
    this((String) Objects.toString(left, null), Objects.toString(right, null));
  }

  @Override
  public boolean isCollect() {
    return isEquals() && !"".equals(left);
  }
}
