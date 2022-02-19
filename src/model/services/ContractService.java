package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.util.Calendar;
import java.util.Date;

public class ContractService {
    private OnlinePaymentMethod onlinePaymentMethod;

    public ContractService() {
    }

    public ContractService(OnlinePaymentMethod onlinePaymentMethod) {
        this.onlinePaymentMethod = onlinePaymentMethod;
    }

    public void processContract(Contract contract, Integer months) {
        Date installmentDate = contract.getDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(installmentDate);
        for (int i = 1; i <= months; i++) {
            cal.add(Calendar.MONTH, 1);
            double monthlyInterest = onlinePaymentMethod.interest(contract.getTotalValue() / months, i);
            double paymentFee = onlinePaymentMethod.paymentFee(monthlyInterest);
            contract.addInstallment(new Installment(cal.getTime(), paymentFee));
        }
    }
}
