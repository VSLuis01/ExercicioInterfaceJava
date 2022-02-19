package application;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter contract data: ");
        System.out.print("Number: ");
        Integer contractNumber = sc.nextInt();
        sc.nextLine();
        System.out.print("Date (dd/MM/yyyy): ");
        Date contractDate = sdf.parse(sc.nextLine());
        System.out.print("Contract value: ");
        Double contractValue = sc.nextDouble();
        System.out.print("Enter number of installments: ");
        Integer installments = sc.nextInt();
        Contract contract = new Contract(contractNumber, contractDate, contractValue);
        ContractService contractService = new ContractService(new PaypalService());
        contractService.processContract(contract, installments);
        System.out.println("INSTALLMENTS");
        for (Installment installment : contract.getInstallments()) {
            System.out.println(sdf.format(installment.getDueDate()) + " - " + String.format("%.2f", installment.getAmount()));
        }

        sc.close();
    }
}
