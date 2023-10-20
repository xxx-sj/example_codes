package java_ex.annotation_ex.annotation;

import java_ex.annotation_ex.annotation_enum.TestType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CustomAnnotation {
    int count();
    String testBy();
    TestType testType();

    DateTime testDate();


    String[] testTools();

}
