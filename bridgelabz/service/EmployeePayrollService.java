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
import bridgelabz.model.ServiceType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeePayrollService {
    private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

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
    public static void main(String[] args) throws EmployeePayrollValidation, IOException {

        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
        Scanner scanner = new Scanner(System.in);

        System.out.println(employeePayrollService.isExists("E:\\BridgeLabs Training\\demo2"));
        employeePayrollService.readEmployeePayrollData(scanner);
        employeePayrollService.writeEmployeePayrollData(ServiceType.FILE_IO);

        employeePayrollService.listFileAndDirectories("E:\\BridgeLabs Training\\demo2");
        employeePayrollService.createFile("E:\\BridgeLabs Training\\demo2", "Myfile");
    }

    /**
     * Method for writing employee payroll data into console.
     */
    public void writeEmployeePayrollData(ServiceType serviceType) throws EmployeePayrollValidation {

        switch (serviceType) {
            case FILE_IO:
                EmployeePayrollFileIOService employeePayrollFileIOService = new EmployeePayrollFileIOService();
                employeePayrollFileIOService.writeIntoFile(employeePayrollList);
                break;
            case CONSOLE_IO:
                System.out.println("\nWriting Employee Payroll Roaster to Console\n" + employeePayrollList);
            default:
                break;
        }
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

    public void printData() throws EmployeePayrollValidation {
        try {
            Files.lines(new File("payroll-file.txt").toPath()).forEach(System.out::println);
        } catch (IOException e) {
            throw new EmployeePayrollValidation(e.getMessage());
        }
    }


    public long countEntries() throws EmployeePayrollValidation {
        long entries = 0;
        try {
            entries = Files.lines(new File("payroll-file.txt").toPath()).count();
        } catch (IOException e) {
            throw new EmployeePayrollValidation(e.getMessage());
        }
        return entries;
    }

    /**
     * Method for deleting file form directory.
     *
     * @param contentToDelete :
     * @return : tru or false
     */
    public boolean deleteFile(File contentToDelete) {
        File[] allContent = contentToDelete.listFiles();
        if (allContent != null)
            for (File file : allContent) {
                deleteFile(file);
            }
        return contentToDelete.delete();
    }

    /**
     * Method for delete files recursively  from a directory.
     *
     * @param address : directory path
     * @return : true or false
     */
    public boolean deleteFilesRecursively(String address) {
        Path playPath = Paths.get(address);
        if (Files.exists(playPath))
            deleteFile(playPath.toFile());
        return Files.notExists(playPath);
    }

    /**
     * Method for checking if file of directory and file is exists or not.
     *
     * @return : true or false
     */
    public boolean isExists(String address) {
        Path homePath = Paths.get(address);
        if (Files.exists(homePath)) {
            System.out.println(homePath + " Path is Exists");
        }
        return Files.exists(homePath);
    }

    /**
     * Method for creating directory.
     *
     * @param address : path wtth directory name
     * @return : true or false
     * @throws IOException
     */
    public boolean createDirectory(String address) throws IOException {
        Path playPath = Paths.get(address);
        // Create Directory
        Files.createDirectory(playPath);
        if (Files.exists(playPath))
            System.out.println(playPath + " Is Created");
        return Files.exists(playPath);
    }

    /**
     * Method for creating file in a particular directory.
     *
     * @param address  : path of directory
     * @param fileName : file name
     * @return : true or false
     * @throws EmployeePayrollValidation
     */
    public boolean createFile(String address, String fileName) throws EmployeePayrollValidation {
        Path tempFile = Paths.get(address + "/" + fileName);
        try {
            Files.createFile(tempFile);
            System.out.println(tempFile);
        } catch (IOException e) {
            throw new EmployeePayrollValidation(e.getMessage());
        }
        return Files.exists(tempFile);
    }

    /**
     * Method for find out the total files and folder at a particular directory.
     *
     * @param path : path for a directory
     * @return : count of file and folders exists.
     * @throws EmployeePayrollValidation
     */
    public int listFileAndDirectories(String path) throws EmployeePayrollValidation {
        Path playPath = Paths.get(path);
        try {
            List<Path> elementsList = Files.list(playPath).filter(Files::isRegularFile).collect(Collectors.toList());
            elementsList.stream().forEach(e -> System.out.println(e));
            return elementsList.size();
        } catch (Exception e) {
            throw new EmployeePayrollValidation(e.getMessage());
        }
    }

}
