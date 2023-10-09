package java_class.annotation_ex.ex;

import java_class.annotation_ex.annotation.CustomAnnotation;
import java_class.annotation_ex.annotation.DateTime;
import java_class.annotation_ex.annotation_enum.TestType;

@CustomAnnotation(
        count = 1,
        testBy = "sj",
        testType = TestType.FIRST,
        testDate = @DateTime(yymmdd = "231019", hhmmss = "172803"),
        testTools = {"aa", "bb", "cc"}
        )
@SuppressWarnings({"111"}) //잘못쓰여진 어노테이션은 무시된다.
@Deprecated
public class AnnotationTestClass {

}
