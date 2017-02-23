# spring-boosterpack-validation
[![Build Status](https://travis-ci.org/furplag/spring-boosterpack-validation.svg?branch=master)](https://travis-ci.org/furplag/spring-boosterpack-validation)
[![codebeat badge](https://codebeat.co/badges/3360172d-9b95-46a7-a453-e08f0596084b)](https://codebeat.co/projects/github-com-furplag-spring-boosterpack-validation)

several function for Spring Boot beans validation.

## Feature

### Validate pair of input value equal.

#### Usage
```SomeBean.java
public class SomeBean implements Serializable {

  private String passwd;

  private String confirmPasswd;

  @Confirmed
  public Pair<String, String> getConfirmed() {
    return Pair.of(passwd, confirmPasswd);
  }
  ...
}
```

## Author
Furplag

## License
Code is under the [Apache Licence 2.0](LICENCE).
