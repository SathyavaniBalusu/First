package BusinessLogic;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;

import java.util.*;

import static PageObjects.NewPageObjects.*;



public class eCommerce {

    public static WebDriver driver;

    public eCommerce(WebDriver driver) throws Exception {

        this.driver = driver;

    }

    public void mainSearch(String SearchData){

        try {
            WebElement Search = driver.findElement(By.xpath(search));
            Search.click();
            Search.sendKeys(SearchData);
            Search.sendKeys(Keys.ENTER);
            driver.findElement(By.xpath(Site)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void eCommerceLogin(String Email, String password){
        try {
            Actions action = new Actions(driver);
            WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Account & Lists')]"));
            action.moveToElement(element).perform();
            WebElement SigninButton = driver.findElement(By.xpath(Signin));
            if(SigninButton.isDisplayed()){
                SigninButton.click();
                WebElement emailField = driver.findElement(By.xpath(email));
                if(emailField.isDisplayed()){
                   emailField.sendKeys(Email);
                   driver.findElement(By.xpath(Continue)).click();
                   WebElement PasswordField = driver.findElement(By.xpath(Password));
                   if(PasswordField.isDisplayed()){
                       PasswordField.sendKeys(password);
                       driver.findElement(By.xpath(Login)).click();
                       Thread.sleep(6000);
                       WebElement Signinelement = driver.findElement(By.xpath("(//span[contains(text(),'Account & Lists')])[1]"));
                       if(Signinelement.isDisplayed()){
                           System.out.println("element is displayed");
                       action.moveToElement(Signinelement).perform();
                       WebElement SignOutLink = driver.findElement(By.xpath(Signout));
                       if(SignOutLink.isDisplayed()) {
                           System.out.println("User signed in successfully!");
                       }
                   }
                }
            }
            else{
                    WebElement Signinelement = driver.findElement(By.xpath("(//span[contains(text(),'Account & Lists')])[1]"));
                    action.moveToElement(Signinelement).perform();
                    WebElement SignOutLink = driver.findElement(By.xpath(Signout));
                    if(SignOutLink.isDisplayed()){
                        System.out.println("User is already signed in!");
                }

                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
   }
   public void productSearch(String productSearch){
        try{
             WebElement Search = driver.findElement(By.xpath(ProductSearch));
             Search.sendKeys(productSearch);
             driver.findElement(By.xpath(Suggestions)).click();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
   }
   public void fetchElementDetails(){
        try{
            List<WebElement> elementprice= driver.findElements(By.xpath("//span[@class='a-price-whole']"));
            List<WebElement> elementname = driver.findElements(By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']"));
            Map<Integer,String> map = new LinkedHashMap<Integer,String>();
            for(int i=0; i<elementprice.size(); i++){
                map.put(Integer. valueOf(elementprice.get(i).getText()), elementname.get(i).getText());
            }
            System.out.println(map.size());
            System.out.println(map);
            Map<Integer, String> sortedMap = new TreeMap<Integer, String>(map);
            System.out.println(sortedMap.size());
            System.out.println(sortedMap);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
   }
   public void cart() {
            driver.findElement(By.xpath(Cart)).click();
            WebElement CartItemCheck = driver.findElement(By.xpath("//form[@id='activeCartViewForm']/div[@data-name='Active Items']/div[@class='a-row sc-list-item sc-list-item-border sc-java-remote-feature']/div[@class='sc-list-item-content']/div/div[2]/p/span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']"));
            if (CartItemCheck.isDisplayed()) {
                float CartValue = 0;
                String[] a= new String[10];
                List<WebElement> CartItemPrice = driver.findElements(By.xpath("//form[@id='activeCartViewForm']/div[@data-name='Active Items']/div[@class='a-row sc-list-item sc-list-item-border sc-java-remote-feature']/div[@class='sc-list-item-content']/div/div[2]/p/span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']"));
                List<WebElement> CartItemName = driver.findElements(By.xpath("//form[@id='activeCartViewForm']/div[@data-name='Active Items']/div[@class='a-row sc-list-item sc-list-item-border sc-java-remote-feature']/div[@class='sc-list-item-content']/div/div/div/div/div[@class='a-fixed-left-grid-col a-col-right']/ul/li/span/a/span[@class='a-size-medium sc-product-title']"));
                for (int i = 0; i < CartItemPrice.size(); i++) {
                    a[i] = CartItemPrice.get(i).getText();
                    CartValue = CartValue + Integer.parseInt(a[i]);

                    System.out.println(CartItemName.get(i).getText() + CartItemPrice.get(i).getText());
                }
                System.out.println("Total cart value is: " + CartValue);
            }

   }
    @AfterMethod

    public void afterMethod() {
        driver.close();
    }
}
