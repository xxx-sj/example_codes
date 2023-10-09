package java_class.annotation_ex;

import java_class.annotation_ex.annotation.CustomAnnotation;
import java_class.annotation_ex.ex.AnnotationTestClass;

import java.lang.annotation.Annotation;

public class Annotation_ex {
    public static void main(String[] args) {
        AnnotationTestClass an = new AnnotationTestClass();
        Annotation_ex.printAnnotation(an);
    }

    static void printAnnotation(Object obj) {
        if(obj == null) {
            System.out.println("print null");
            return;
        }

        Class<?> cl = obj.getClass();
        CustomAnnotation annotation = cl.getAnnotation(CustomAnnotation.class);
        if(annotation == null) {
            System.out.println(obj.toString());
            return;
        }
        System.out.println("annotation.count() = " + annotation.count());
        System.out.println("annotation.testBy() = " + annotation.testBy());
        System.out.println("annotation.testDate().yymmdd() = " + annotation.testDate().yymmdd());
        System.out.println("annotation.testDate().hhmmss() = " + annotation.testDate().hhmmss());
        System.out.println("annotation.testType() = " + annotation.testType());

        String[] strings = annotation.testTools();
        for (String str : strings) {
            System.out.println("annotation.str[] = " + str);
        }

        System.out.println();

        Annotation[] annotations = cl.getAnnotations();
        for (Annotation annotation1 : annotations) {
            System.out.println("annotation1 = " + annotation1);
        }
    }
}
