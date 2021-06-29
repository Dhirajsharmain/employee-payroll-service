/**
 * ****************************************************************************
 * Purpose: The purpose of this class is to learn about IO stream and  create
 * Employee Payroll Service system to read and write Employee Payroll to a
 * console and demonstrate File Operations like:
 * - Check File Exists
 * - Delete File and Check File Not Exist
 * - Create Directory
 * - Create Empty File
 * - List Files, Directories as well as Files with Extension
 *
 * @author Dhiraj
 * @version 1.0
 * @since 29-06-2021
 * ****************************************************************************
 */
package bridgelabz.service;

import bridgelabz.exception.EmployeePayrollValidation;
import bridgelabz.model.EmployeePayrollData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
    private List<EmployeePayrollData> employeePayrollList;

    public EmployeePayrollService() {
    }

    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }

    /**
     * Main Method or Starting point of this program.
     *
     * @param args
     */
    public static void main(String[] args) throws EmployeePayrollValidation {

        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();

        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);

        Scanner scanner = new Scanner(System.in);

        employeePayrollService.readEmployeePayrollData(scanner);
        employeePayrollService.writeEmployeePayrollData();
    }

    /**
     * Method for writing employee payroll data into console.
     */
    private void writeEmployeePayrollData() {
        System.out.println("\nWriting Employee Payroll Roaster to Console\n" + employeePayrollList);
    }

    /**
     * Method for taking the employee payroll data through console.
     *
     * @param scanner
     * @throws EmployeePayrollValidation : Custom exception
     */
    private void readEmployeePayrollData(Scanner scanner) throws EmployeePayrollValidation {
        try {

            System.out.println("Enter Employee ID: ");

            int id = scanner.nextInt();

            System.out.println("Enter Employee Name: ");

            String name = scanner.next();

            System.out.println("Enter Employee Salary: ");

            double salary = scanner.nextDouble();

            employeePayrollList.add(new EmployeePayrollData(id, name, salary));
        } catch (Exception e) {
            throw new EmployeePayrollValidation(e.getMessage());
        }

    }

}
