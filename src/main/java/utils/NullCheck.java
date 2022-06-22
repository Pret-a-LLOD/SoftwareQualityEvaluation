package utils;


import static java.util.Objects.isNull;

public class NullCheck {
  public static void notNull(String objectName, Object object, Class<?> clazz) throws
                                                                               Exception {
    if (isNull(object)) {
      throw new Exception(String.format("%s must be set in %s", objectName, clazz.getName()));
    }
  }
}
