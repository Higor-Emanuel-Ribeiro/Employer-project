package entities.services;

public class IncomeTax {
    
    public Double incomeTaxCalculation(double income) {
        Double value = 0.0;
        Double taxExemption = 1903.98;

        if (income < taxExemption) {
            System.out.println("This worker is exempt from income tax!");
        }
        else {
            value = income - (income * 0.016);
        }

        return value;
    }
}
