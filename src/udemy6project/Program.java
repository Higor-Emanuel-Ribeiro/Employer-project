package udemy6project;


import entities.Departament;
import entities.HourContract;
import entities.Worker;
import entities.WorkerLevel;
import entities.services.IncomeTax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            System.out.print("Enter department's name: ");
            String departamentName = sc.nextLine();
            System.out.println("ENTER WORKER DATA:");
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Level: ");
            String level = sc.nextLine().toUpperCase();
            System.out.print("Base salary: ");
            Double baseSalary = sc.nextDouble();

            Worker worker = new Worker(name, WorkerLevel.valueOf(level), baseSalary, new Departament(departamentName));

            System.out.println("");
            System.out.print("How many contracts to this worker? ");
            Integer numberOfContracts = sc.nextInt();
            
            if (numberOfContracts < 1) {
                System.out.println("OK! Thank you!");
            }
            else {
                for (int i = 0; i < numberOfContracts; i++) {
                    System.out.println("Enter contract #" + (i+1) + " data: ");

                    System.out.print("Contract date (DD/MM/YYYY): ");
                    Date date = sdf.parse(sc.next());
                    System.out.print("Value per hour: ");
                    Double valuePerHour = sc.nextDouble();
                    System.out.print("Contract duration (hours): ");
                    double hours = sc.nextDouble();
                    System.out.println();

                    HourContract contract = new HourContract(date, valuePerHour, hours);
                    worker.addContract(contract);
                }
                
                System.out.println("Name: " + worker.getName());
                System.out.println("Departament: " + worker.getDepartament().getName());
                System.out.println("Total income: " + worker.income());

            }

            System.out.println();
            IncomeTax incomeTax = new IncomeTax();
            System.out.println("Salary + contracts with monthly income tax deduction: " + incomeTax.incomeTaxCalculation(worker.income()));

            sc.close();
        }
        catch (ParseException e) {
            System.out.println("Error! " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Error! Data not found");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error! Unidentified career level");
        }
    } 
}
