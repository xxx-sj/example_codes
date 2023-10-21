package Junit_test.chap07.card_valid;

public class RegisterResult {

    private CardValidity cardValidity;
    public static RegisterResult error(CardValidity validity) {
        return new RegisterResult();
    }

    public static RegisterResult success() {
        return new RegisterResult();
    }


    public CardValidity getValidity() {
        return cardValidity;
    }
}
