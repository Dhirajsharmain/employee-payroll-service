package bridgelabz.service;

import bridgelabz.exception.EmployeePayrollValidation;
import bridgelabz.model.EmployeePayrollData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EmployeePayrollFileIOService {
    public static String PAYROLL_FILE_NAME = "payroll-file.txt";

    /**
     * Method for writing the data into txt file.
     *
     * @param employeePayrollList : employee payroll data
     * @throws EmployeePayrollValidation
     */
    public void writeIntoFile(List<EmployeePayrollData> employeePayrollList) throws EmployeePayrollValidation {
        StringBuffer empBuffer = new StringBuffer();
        employeePayrollList.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });
        try {
            Files.write(Paths.get(PAYROLL_FILE_NAME), empBuffer.toString().getBytes());
        } catch (IOException e) {
            throw new EmployeePayrollValidation(e.getMessage());
        }
    }
}
