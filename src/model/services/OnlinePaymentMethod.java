package model.services;

public interface OnlinePaymentMethod {
    double paymentFee(double amount);
    double interest (double amount, Integer months);
}
