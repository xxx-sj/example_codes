package Junit_test.chap03.expiry_date;

import java.time.LocalDate;

public class PayData {
    private LocalDate firstBillingDate;

    private LocalDate billingDate;
    private int paymentAmount;

    private PayData() {}
    public PayData(LocalDate firstBillingDate, LocalDate billingDate, int paymentAmount) {
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.paymentAmount = paymentAmount;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public LocalDate getFirstBillingDate() {
        return firstBillingDate;
    }

    public void setFirstBillingDate(LocalDate firstBillingDate) {
        this.firstBillingDate = firstBillingDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PayData payData = new PayData();

        public Builder firstBillingDate(LocalDate firstBillingDate) {
            payData.firstBillingDate = firstBillingDate;
            return this;
        }

        public Builder billingDate(LocalDate billingDate) {
            payData.billingDate = billingDate;
            return this;
        }

        public Builder payAmount(int payAmount) {
            payData.paymentAmount = payAmount;
            return this;
        }

        public PayData build() {
            return payData;
        }
    }
}
