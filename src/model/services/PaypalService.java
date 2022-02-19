package model.services;

public class PaypalService implements OnlinePaymentMethod {

    @Override
    public double paymentFee(double amount) {
        return amount + (amount * 0.02);
    }

    @Override
    public double interest(double amount, Integer months) {
        return amount + (amount * 0.01) * months;
    }
}
