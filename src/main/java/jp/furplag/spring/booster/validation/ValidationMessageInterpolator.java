package jp.furplag.spring.booster.validation;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.validation.beanvalidation.LocaleContextMessageInterpolator;

public class ValidationMessageInterpolator extends LocaleContextMessageInterpolator {

  public ValidationMessageInterpolator() {
    super(new ResourceBundleMessageInterpolator());
  }
}
