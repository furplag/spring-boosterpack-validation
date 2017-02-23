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
package jp.furplag.spring.booster.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;

public abstract class ValidationTestBase {

  protected Validator validator;

  @Before
  public void before() throws Exception {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  protected <T> void printViolations(Set<ConstraintViolation<T>> violations) {
    for (ConstraintViolation<T> violation : violations) {
      StringBuilder sb = new StringBuilder();
      sb.append("[Path]" + violation.getPropertyPath() + " ");
      sb.append("[Value]" + violation.getInvalidValue() + " ");
      sb.append("[Message]" + violation.getMessage());
      System.err.println(sb.toString());
    }
  }

  protected <T> void validate(Object object, Class<?>... groups) {
    printViolations(validator.validate(object, groups));
  }

  protected <T> void validateThis(Class<?>... groups) {
    validate(this, groups);
  }
}
