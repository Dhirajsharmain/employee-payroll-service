package bridgelabz.test;

import bridgelabz.exception.EmployeePayrollValidation;
import bridgelabz.service.EmployeePayrollService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmployeePayrollServiceTest {

    public static String Home = System.getProperty("user.home");
    EmployeePayrollService employeePayrollService;

    @Before
    public void setup(){
        employeePayrollService = new EmployeePayrollService();
    }

    @Test
    public void listFilesAndDirectories_shouldReturnFilesCount_whenPathIsProvided() throws EmployeePayrollValidation {
        int result = employeePayrollService.listFileAndDirectories("E:\\BridgeLabs Training\\demo");
        assertEquals(9,result);
    }

    @Test
    public void givenPathWhenCheckedThenConfirm() throws IOException {

        //check file exists
        Path playPath = Paths.get("E:\\BridgeLabs Training\\demo2");
        assertTrue(Files.exists(playPath));

        //Delete file and check file not exists
        if (Files.exists(playPath))
            employeePayrollService.deleteFile(playPath.toFile());
        assertTrue(Files.notExists(playPath));

        // Create Directory
        Files.createDirectory (playPath);
        assertTrue (Files.exists(playPath));

        // Create File
        IntStream.range(1, 10).forEach(cntr -> {
            Path tempFile = Paths.get(playPath + "/temp" + cntr);
            assertTrue(Files.notExists(tempFile));
            try {
                Files.createFile(tempFile);
            } catch (IOException e) { }
            assertTrue(Files.exists(tempFile));
        });
    }
}
