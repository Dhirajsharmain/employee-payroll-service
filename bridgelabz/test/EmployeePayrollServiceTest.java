package bridgelabz.test;

import bridgelabz.service.EmployeePayrollService;
import org.junit.Before;
import org.junit.Test;

public class EmployeePayrollServiceTest {

    EmployeePayrollService employeePayrollService;

    @Before
    public void setup(){
        employeePayrollService = new EmployeePayrollService();
    }
}
