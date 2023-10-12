package Junit_test.chap02.password;

import java.util.Objects;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
//        return null;
//        return PasswordStrength.STRONG; //무조건 3개 통과 시키기 위해


        //글자 8자 미만일 때 NORMAL
//        if(s.length() < 8) {
//            return PasswordStrength.NORMAL;
//        }
//
//        return PasswordStrength.STRONG;


        //8자 미만 NORMAL, 숫자포함확인 아니면 NORMAL TODO [2]

//        if(Objects.isNull(s) || s.isEmpty()) return PasswordStrength.INVALID;
//
//        if(s.length() < 8)
//            return PasswordStrength.NORMAL;
//
//        boolean containsNum = meetsContainingNumberCriteria(s);
//        if(!containsNum) return PasswordStrength.NORMAL;
//
//        boolean containsUpp = meetsContainingUppercaseCriteria(s);
//        if(!containsUpp) return PasswordStrength.NORMAL;
//
//        return PasswordStrength.STRONG;



//        //1 개 또는 0개 만족
//        if(Objects.isNull(s) || s.isEmpty()) return PasswordStrength.INVALID;
//
////        if(s.length() < 8)
////            return PasswordStrength.NORMAL;
//        //개별 규칙을 검사하는 로직
//        boolean lengthEnough = s.length() >= 8;
//        boolean containsNum = meetsContainingNumberCriteria(s);
//        boolean containsUpp = meetsContainingUppercaseCriteria(s);
//
//        //규칙을 검사한 결과에 따라 암호 강도를 계산하는 로직
//        if(lengthEnough && !containsNum && !containsUpp) return PasswordStrength.WEAK;
//        if(lengthEnough && !containsNum && !containsUpp) return PasswordStrength.WEAK;
//        if(!lengthEnough && !containsNum && containsUpp) return PasswordStrength.WEAK;
//        if(!lengthEnough) return PasswordStrength.NORMAL;
//        if(!containsNum) return PasswordStrength.NORMAL;
//        if(!containsUpp) return PasswordStrength.NORMAL;
//
//        return PasswordStrength.STRONG;


        //1 개 또는 0개 만족
        if(Objects.isNull(s) || s.isEmpty()) return PasswordStrength.INVALID;

        //개별 규칙을 검사하는 로직

        int metCounts = getMetCriteriaCounts(s);

        //규칙을 검사한 결과에 따라 암호 강도를 계산하는 로직
        if(metCounts <= 1) return PasswordStrength.WEAK;
        if(metCounts == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingNumberCriteria(String s) {
        for (char c : s.toCharArray()) {
            if(c >= '0' && c <= '9') {
                return true;
            }
        }

        return false;
    }


    private boolean meetsContainingUppercaseCriteria(String s) {
        for (char c : s.toCharArray()) {
            if(Character.isUpperCase(c)) {
                return true;
            }
        }

        return false;
    }

    private int getMetCriteriaCounts(String s) {
        int metCounts = 0;
        if(s.length() >= 8) metCounts++;
        if(meetsContainingNumberCriteria(s)) metCounts++;
        if(meetsContainingUppercaseCriteria(s)) metCounts++;

        return metCounts;
    }
}
