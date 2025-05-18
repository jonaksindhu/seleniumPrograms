package excelTests;

import excelTests.utils.ExcelUtils;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {


    @BeforeTest
    public void setupTestData() throws IOException {
        // Set Test Data Excel and Sheet
        System.out.println("************Setup Test Level Data**********");
        ExcelUtils.setExcelFileSheet("UserCreds.xlsx", "Sheet1");
    }

    @Test(priority = 0, description = "Invalid Login Scenario with wrong username and password.")
    public void invalidUserNameInvalidPassword() throws IOException {
        String result;

        XSSFRow row = ExcelUtils.getRowData(1);
        String userName = row.getCell(0).toString();
        String password = row.getCell(1).toString();

        if (userName.equals("Adminuser") && password.equals("admin$123")) {
            result = "Pass";
            ExcelUtils.setCellData("Result", 2, result);
        } else {
            result = "False";
            ExcelUtils.setCellData("Result", 2, result);
        }
    }

    @Test(priority = 0, description = "valid Login Scenario with correct username and password.")
    public void validUserNameValidPassword() throws IOException {
        String result;

        XSSFRow row = ExcelUtils.getRowData(2);
        String userName = row.getCell(0).toString();
        String password = row.getCell(1).toString();

        if (userName.equals("BaseUser") && password.equals("base@123")) {
            result = "Pass";
            ExcelUtils.setCellData("Result", 3, result);
        } else {
            result = "False";
            ExcelUtils.setCellData("Result", 3, result);
        }
    }

    @AfterTest
    public void tearDown() throws IOException {
        ExcelUtils.closeWorkbook();
    }
}