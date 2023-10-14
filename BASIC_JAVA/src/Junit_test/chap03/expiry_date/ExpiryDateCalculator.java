package Junit_test.chap03.expiry_date;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

//    public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
////        return LocalDate.of(2019,4,1);
////        return billingDate.plusMonths(1);
//
//        return (this.calculateExpiryDate(new PayData(billingDate, payAmount)));
//    }

    public LocalDate calculateExpiryDate(PayData payData) {
//        int addedMonths = 1;
        int addedMonths = payData.getPaymentAmount() / 10_000;
        if(payData.getFirstBillingDate() != null) {
//            if(payData.getFirstBillingDate().equals(LocalDate.of(2019, 1, 31))) {
//                return LocalDate.of(2019, 3, 31);
//            }

            //결제일에 1달을 더해서 날짜를 구함
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
            //만약 결제일 1달 후의 일자와 처음 결제일의 일자가 다르다면
            if (payData.getFirstBillingDate().getDayOfMonth() !=
            candidateExp.getDayOfMonth()) {

                //후보 만료일
                /**
                 * YearMonth.from()는 입력받은 값에 대하여 "년-월"을 반환한다. ex) 2019-04
                 * lengthOfMonth()는 해당 월일의 마지막 일을 반환한다 . ex) 2019-04 에 대해 30 을 반환.
                 */
                if(YearMonth.from(candidateExp).lengthOfMonth() <
                payData.getFirstBillingDate().getDayOfMonth()) {
                    return candidateExp.withDayOfMonth(
                            YearMonth.from(candidateExp).lengthOfMonth());
                }

                //계산된 1달 후의 결제날짜에 처음 결제일을 넣어 만든다.
                return candidateExp.withDayOfMonth(
                        payData.getFirstBillingDate().getDayOfMonth()
                );
            }
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
