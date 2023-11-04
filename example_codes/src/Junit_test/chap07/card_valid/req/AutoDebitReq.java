package Junit_test.chap07.card_valid.req;

public class AutoDebitReq {

    private String userId;
    private String cardNumber;

    private AutoDebitReq() {};

    public AutoDebitReq(String userId, String cardNumber) {
        this.userId = userId;
        this.cardNumber = cardNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
