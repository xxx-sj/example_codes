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
//        int addedMonths = payData.getPaymentAmount() / 10_000;
//        if(payData.getFirstBillingDate() != null) {
//            if(payData.getFirstBillingDate().equals(LocalDate.of(2019, 1, 31))) {
//                return LocalDate.of(2019, 3, 31);
//            }

            /**
             * 결제일에 +달을 더해서 날짜를 구함
             */
//            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
//            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();

        /**
         *만약 결제일 1달 후의 일자와 처음 결제일의 일자가 다르다면
         */
//            if(dayOfFirstBilling != candidateExp.getDayOfMonth()) {
//            if (payData.getFirstBillingDate().getDayOfMonth() !=
//            candidateExp.getDayOfMonth()) {

                //후보 만료일
                /**
                 * YearMonth.from()는 입력받은 값에 대하여 "년-월"을 반환한다. ex) 2019-04
                 * lengthOfMonth()는 해당 월일의 마지막 일을 반환한다 . ex) 2019-04 에 대해 30 을 반환.
                 */
//                if(YearMonth.from(candidateExp).lengthOfMonth() <
//                payData.getFirstBillingDate().getDayOfMonth()) {
//                    return candidateExp.withDayOfMonth(
//                            YearMonth.from(candidateExp).lengthOfMonth());
//                }

                /**
                 *후보 만료일의 마지막 일자 ex)4월이라면  = 30
                 */
//                final int dayLenOfCandiMon =
//                        YearMonth.from(candidateExp).lengthOfMonth();

                /**
                *후보 만료일이 첫 결제일보다 작다면 후보만료일의 일자로 한 후보 만료일 반환
                 */
//                if(dayLenOfCandiMon < payData.getFirstBillingDate().getDayOfMonth()) {
//                    return candidateExp.withDayOfMonth(dayLenOfCandiMon);
//                }
                /**
                *계산된 1달 후의 결제날짜에 처음 결제일을 넣어 만든다.
                 */
//                return candidateExp.withDayOfMonth(dayOfFirstBilling);
//            } else {
//                첫 결제일과 후보만료일의 일자가 같은경우
//                return candidateExp;
//            }
//        } else {
//            //첫 결제일이 없다면
//            return payData.getBillingDate().plusMonths(addedMonths);
//        }


        int addedMonths = payData.getPaymentAmount() == 100_000 ?  12: payData.getPaymentAmount() / 10_000;
        if(payData.getFirstBillingDate() != null) {
            return this.expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
//        //결제일에 addedMonth 만큼 더해 예상 만료일을 찾는다.
//        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
//        // 첫 결제일의 일자만을 갖는다.
//        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
//
//        //예상 만료일의 일수와 첫 결제일의 일수가 다르다면
//        if(!isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {
//            //예상만료일의 월에 해당하는 일수를 뽑는다. ex) candidate = 2019-04 28
//            //candidate.getDayOfMonth = 28, YearMonth.from(candidate).lengthOfMonth() = 30;
//            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
//
//            //예상 만료일이 첫 결제일보다 작다면 후보만료일의 일자로 한 예상 만료일 반환
//            if(dayLenOfCandiMon < payData.getFirstBillingDate().getDayOfMonth()) {
//                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
//            }
//            //계산된 1달 후의 결제날짜에 처음 결제일을 넣어 만든다.
//            return candidateExp.withDayOfMonth(dayOfFirstBilling);
//
//        } else {
//            //첫 결제일과 후보만료일의 일자가 같은경우
//            return candidateExp;
//        }

        //결제일에 addedMonth 만큼 더해 예상 만료일을 찾는다.
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

        //첫 결제일의 일수와 예상만료일의 일자가 다르다면
        if(!isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {
            //해당 월의 마지막 일자를 찾은다음
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
            //첫 결제일의 일자만 갖는다.
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            //예상 만료일의 마지막 일자가 첫 결제일의 일자보다 작다면 ex) 30 < 31 ;
            if(dayLenOfCandiMon < dayOfFirstBilling) {
                //만료일의 마지막 일수로 계산
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            //아니라면 첫 결제일의 일수로 계산
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            //결제일과 예상 만료일이 같을경우는 canidateExp 반환 (x달 더한 값)
            return candidateExp;
        }
    }

    private boolean isSameDayOfMonth(LocalDate date1, LocalDate date2) {
        return date1.getDayOfMonth() == date2.getDayOfMonth();
    }

    /**
     * 인자로 받은 date의 월에 해당하는 가장 마지막 일자를 반환한다.
     * ex ) 1-21 => return 31; 4-11 => return 30;
     *
     * @return LocalDate의 마지막 일자를 반환.
     */
    private int lastDayOfMonth(LocalDate date) {
        return YearMonth.from(date).lengthOfMonth();
    }
}
