package java_class.basic_class.abstract_ex;


import java_class.basic_class.abstract_ex.job_ex.Warrior;

public class John extends Warrior {

    @Override
    public void printJob() {
        System.out.println("John의 직업은 전사 입니다.");
    }
}
