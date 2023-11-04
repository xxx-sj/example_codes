package java_ex.annotation_ex.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface DateTime {
    String yymmdd();
    String hhmmss();
}
