# spring-boosterpack-validation


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
