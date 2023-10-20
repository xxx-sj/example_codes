package java_ex.annotation_ex.ex;

import java_ex.annotation_ex.annotation.default_annotation.CustomAnnotationDefault;

//@CustomAnnotationDefault //== (count = 1, testTools = {"aa", "bb"})
//@CustomAnnotationDefault(count = 3)
//@CustomAnnotationDefault(count = 3, testTools = {"bb"})
@CustomAnnotationDefault(count = 3, testTools = "bb")
public class AnnotationDefaultTestClass {
}
