package Tests;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;
import BusinessLogic.eCommerce;
import ExcelValidation.ExcelOperations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class New {
    @Test(dataProvider = "Authentication")
    public static void openBrowser(String URL, String SearchData, String Email, String Password, String ProductSearch) throws Exception {
        /*LinkedHashMap<String , String> testmap= new LinkedHashMap<>();
        testmap.put("Sathya","test1");
        testmap.put("read","book");
        System.out.println(testmap.get("sathya"));
        String URL, String SearchData, String Email, String Password, String ProductSearch
        */

        System.out.println("Test starts here");
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
        // Creating a object to instantiate the browser driver
        WebDriver driver = new ChromeDriver();
        //Navigating through a particular website
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(URL);
        eCommerce e= new eCommerce(driver);
        e.mainSearch(SearchData);
        e.eCommerceLogin(Email,Password);
        e.productSearch(ProductSearch);
        e.fetchElementDetails();
        e.cart();
    }
    @DataProvider(name = "Authentication",parallel=false)
    public Object[][] Authentication() throws Exception{

       /* return new Object[][] {
                {"https://google.com","amazon","sathyavanib1609@gmail.com","Sathya@1609","kajal"}
        };*/
        // Setting up the Test Data Excel file

        ExcelOperations.setExcelFile("C:\\Users\\Sathya\\First\\src\\test\\resources\\TestData\\DataSheet.xlsx","DataSheet");

        String sTestCaseName = this.toString();


        // From above method we get long test case name including package and class name etc.

        // The below method will refine your test case name, exactly the name use have used

        sTestCaseName = ExcelOperations.getTestCaseName(this.toString());

        // Fetching the Test Case row number from the Test Data Sheet

        // Getting the Test Case name to get the TestCase row from the Test Data Excel sheet

        int iTestCaseRow = ExcelOperations.getRowContains(sTestCaseName, 0);


       Object[][] array = ExcelOperations.getTableArray("C:\\Users\\Sathya\\First\\src\\test\\resources\\TestData\\DataSheet.xlsx","DataSheet",iTestCaseRow);
        /*for (Object[] objects : array) {
            System.out.println(array[0][0]);
            System.out.println(array[0][1]);
            System.out.println(array[0][2]);
            System.out.println(array[0][3]);
            System.out.println(array[0][4]);
        }*/
        return array;
    }

}

