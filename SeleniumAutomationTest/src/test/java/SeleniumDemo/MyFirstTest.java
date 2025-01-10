package SeleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class MyFirstTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private SoftAssert softAssert;

    @BeforeTest
    public void setup() throws InterruptedException {

        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/Users/p1363204/Documents/ChromeDriver/chromedriver-mac-x64/chromedriver");

        // Initialize the ChromeDriver with the options
        this.driver = new ChromeDriver();

        Actions action = new Actions(driver);

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        softAssert = new SoftAssert();

        // Maximize the browser window
        driver.manage().window().maximize();

    }

    @Test (priority = 0)
    public void OpenSwagLabsAsStandardUser() {

        // Open a website
        driver.get("https://www.saucedemo.com/v1/index.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //Assertion
        String fieldTitle = driver.findElement(By.xpath("//div[@class='product_label']")).getText();
        Assert.assertEquals(fieldTitle, "Products");

    }

    @Test (priority = 1)
    public void logoutWebApp() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@style])[2]"))).click();
        driver.findElement(By.xpath("(//button[@style])[2]")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();

        //Assertion
        boolean present = driver.findElement(By.id("login_credentials")).isDisplayed();
        Assert.assertTrue(present);
    }

    @Test (priority = 2)
    public void OpenSwagLabsAsLockedOutUser() {

        // Open a website
        driver.get("https://www.saucedemo.com/v1/index.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //Assertion
        String errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        String expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";

        Assert.assertEquals(errorMessage, expectedErrorMessage);
    }

    @Test (priority = 3)
    public void OpenSwagLabsAsProblemUser() {

        // Open a website
        driver.get("https://www.saucedemo.com/v1/index.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.id("user-name")).sendKeys("problem_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //Assertion
        boolean brokenImage = driver.findElement(By.xpath("//img[contains(@src, 'ToBreakTheUrl')]")).isDisplayed();
        Assert.assertTrue(brokenImage);


    }

    @Test (priority = 4)
    public void AddtoCart1() {

        // Open a website
        driver.findElement(By.xpath("(//button[@class='btn_primary btn_inventory'])[1]")).click();

        //Assertion
        String itemCounter = driver.findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']")).getText();

        Assert.assertEquals(itemCounter, "1");
    }


    //    @Test (priority = 5)
    public void LoginwithCorrectCredentials() {

        //Open a Website
        driver.get("https://staging-reliefapp-cms.pcf.org.sg/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String placeHolderValue = driver.findElement(By.xpath("//input[contains(@placeholder, 'Email')]")).getAttribute("placeholder");
        String expectedPlaceholder = "Enter Email or Phone Numbe";
        softAssert.assertEquals(placeHolderValue,expectedPlaceholder);

        String RememberMeMessage = driver.findElement(By.xpath("//span[@class='CheckBox_labelText__1BLEL']")).getText();
        String ExpectedMessage = "Remember Me";
        softAssert.assertEquals(RememberMeMessage, ExpectedMessage);

        //Login Section (Happy Path)
        driver.findElement(By.id("email")).sendKeys("00000096");
        driver.findElement(By.xpath("//button[@class='Button_container__2B8O8 Button_danger__5o5di ']")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner']")));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.xpath("//input[@type='text'][1]")).sendKeys("1");
        driver.findElement(By.xpath("//input[@type='text'][2]")).sendKeys("2");
        driver.findElement(By.xpath("//input[@type='text'][3]")).sendKeys("3");
        driver.findElement(By.xpath("//input[@type='text'][4]")).sendKeys("4");
        driver.findElement(By.xpath("//input[@type='text'][5]")).sendKeys("5");
        driver.findElement(By.xpath("//input[@type='text'][6]")).sendKeys("6");

        driver.findElement(By.xpath("//button[@class='Button_container__2B8O8 Button_danger__5o5di ']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner']")));

        softAssert.assertAll();
    }

}
