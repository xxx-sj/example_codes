package java_ex.annotation_ex.annotation.default_annotation;

public @interface CustomAnnotationDefault {
    int count() default 1;
    String[] testTools() default {"aa", "bb"};

}
