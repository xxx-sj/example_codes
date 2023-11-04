package Junit_test.chap03.test;

import Junit_test.chap03.expiry_date.ExpiryDateCalculator;
import Junit_test.chap03.expiry_date.PayData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * 서비스를 사용하려면 선불 1만원 지불, 한달 뒤가 만료일
 * 2개월 이상 요금 납부 가능
 * 10만원을 납부하면 서비스 1년 제공
 * 
 * 1. 구현하기 쉬운 것부터 먼저 테스트
 * 예외 상황을 먼저 테스트
 * 
 */
public class ExpiryDateCalculatorTest {

    /**
     * 납부일과 납부액 결과값은 계산된 만료일
     */
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
                LocalDate.of(2019, 3, 1),
                10_000,
                LocalDate.of(2019, 4, 1)
        );

        assertExpiryDate(
                LocalDate.of(2019, 5, 5),
                10_000,
                LocalDate.of(2019, 6,  5)
        );
    }

    private void assertExpiryDate(
            LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {

        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        PayData payData = PayData.builder()
                .billingDate(billingDate)
                .payAmount(payAmount)
                .build();
//        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);
//        Assertions.assertEquals(expectedExpiryDate, expiryDate);
        this.assertExpiryDate(payData, expectedExpiryDate);
    }


    /**
     * 예외 상황
     * 납부일이 2019-01-31 이고 납부액이 1만원이면 만료일은 2019-02-28일이다.
     * 납부일이 2019-05-31 이고 납부액이 1만원이면 만료일은 2019-06-30일이다.
     * 납부일이 2020-01-31 이고 납부액이 1만원이면 만료일은 2020-02-29일이다.
     */
    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                LocalDate.of(2019, 1, 31), 10_000,
                LocalDate.of(2019, 2, 28)
        );

        assertExpiryDate(
                LocalDate.of(2019, 5, 31), 10_000,
                LocalDate.of(2019, 6, 30)
        );

        assertExpiryDate(
                LocalDate.of(2020, 1, 31), 10_000,
                LocalDate.of(2020, 2, 29)
        );
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(payData);
        Assertions.assertEquals(expectedExpiryDate, expiryDate);
    }

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨_V2() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019,3 , 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 4, 1)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 5))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 6,  5)
        );
    }


    /**
     * 예외상황
     * 2만원을 지불하면 만료일이 두 달뒤가 뒨다
     * 3만원을 지불하면 만료일이 세 달 뒤가 된다.
     *
     * 2019-01-31이 첫 납부, 2019-02-28에 1만원을 납부하면 만료일이 2019-03-31 이다.
     * 2019-01-30 첫 납부, 2019-02-28에 1만원을 납붛면 다음 만료일은 2019-03-30이다.
     * 2019-05-31이 첫 납부, 2019-06-30에 추가 납부 시 만료인은 2019-07-31이다.
     */


    /**
     * 1.첫 납부일이 2019-01-31이고 만료되는 2019-02-28에 1만원 납부 시 다음 만료는 2019-03-31이다.
     *
     * 2.첫 납부일이 2019-01-30이고 만료되는 2019-02-28에 1만원 납수 시 2019-3-30 만료
     *
     * 3. 첫 납부일이 2019-05-31 이고, 만료되는 2019-06-30에 만원 납부 시 2019-07-31이다.
     */
    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019 , 1, 30))
                .payAmount(10_000)
                .billingDate(LocalDate.of(2019, 2, 28))
                .build();

        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 5, 31))
                .payAmount(10_000)
                .billingDate(LocalDate.of(2019, 6, 30))
                .build();

        assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));

    }
    /**
     * 2만원을 지불하면 만료일이 두달 뒤가 된다
     * 3만원을 지불하면 만료일이 석 달 뒤가 된다.
     */

    @Test
    void 이만원_이상_납부시_비례해서_만료일_계산() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 5, 1)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019, 6, 1)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(50_000)
                        .build(),
                LocalDate.of(2019, 8, 1)
        );
    }

    /**
     * 예외 상황
     * /31, 30, 31, 30
     * 첫 납부일이 2019-01-31이고 만료되는 2019-02-28에 2만원을 납부하면 다음 만료일은 2019-04-30 이다.
     * 이 테스트에서는 오류가 발생한다. 이유는 현재 기준으로 결제일 1달 후가 첫 결제일의 일수보다 작다면 첫 결제일로 변경되기 때문에
     * 4월에 31일은 없어서 오류가 발생, 따라서 통과시키기 위해서는
     * 
     * 후보 만료일이 포함된 달의 마지막날 < 첫 납부일의 일자
     * 의 조건이 참이면 후보 만료일의 마지막 날로 변경해야 한다.
     *
     */

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019,2,28))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 4, 30));

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 20))
                        .billingDate(LocalDate.of(2019,2,19))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 4, 20));

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019,2,28))
                        .payAmount(40_000)
                        .build(),
                LocalDate.of(2019, 6, 30));

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 3, 31))
                        .billingDate(LocalDate.of(2019,4,30))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019, 7, 31));
    }


    @Test
    void 십만원을_납부하면_1년_제공() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 28))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2020, 1, 28)
        );
    }

    @Test
    void 윤달에_십만원_납부() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 2, 29))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2021, 2, 28)
        );
    }

    @Test
    void 첫납부_이후_윤달에_십만원_추가_납부() {
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2020,1,31))
                        .billingDate(LocalDate.of(2020, 2, 29))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2021, 2, 28)
        );
    }


    @Test
    void 십만원_초과_납부시() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019,1,31))
                        .payAmount(130_000)
                        .build(),
                LocalDate.of(2020,4,30)
        );
    }
}
