package test.base;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    static void testBeforeAll() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 15, 1000);


    }


    @BeforeEach
    void testBefore() {

        String baseUrl = "https://www.raiffeisen.ru/";
        driver.get(baseUrl);
    }


    @AfterEach
    void afterEach() {
        driver.close();
        driver.quit();
        System.out.println("все должно закрыться");


    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }



    public void closeDynamicPopUp() {
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        try {
            WebElement element = driver.findElement(By.xpath("//*[@class='Ribbon-close']"));
            element.click();
        } catch (NoSuchElementException ignore) {
        } finally {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }
    }

    public void fillDate(By locator, String value) {
        WebElement dateFiled = driver.findElement(locator);
        dateFiled.click();
        dateFiled.clear();
        dateFiled.click();
        dateFiled.sendKeys(value);
        dateFiled.sendKeys(Keys.RETURN);
        dateFiled.sendKeys(Keys.TAB);
        Assertions.assertEquals(dateFiled.getAttribute("defaultValue"), value, "Поле заполнено неккоректно");
    }


    public void fillFIO(By locator, String value) {
        closeDynamicPopUp();
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(value);
        wait.until(ExpectedConditions.attributeContains(locator,"value",value ));
        element.sendKeys(Keys.DOWN);
        element.sendKeys(Keys.RETURN);
        Assertions.assertEquals(element.getAttribute("defaultValue"), value, "Поле заполнено неккоректно");
    }

    public void fillAdressOfStay(By locator, String value)  {
        closeDynamicPopUp();
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(value);
        wait.until(ExpectedConditions.attributeContains(locator,"value",value ));
        element.sendKeys(Keys.DOWN);
        element.sendKeys(Keys.RETURN);
        Assertions.assertEquals(element.getAttribute("defaultValue"), value, "Поле заполнено неккоректно");
    }

    public void fillPhone(By locator, String value) {
        String maskRu = "+7";
        WebElement phoneField = driver.findElement(locator);
        phoneField.clear();
        phoneField.click();
        phoneField.sendKeys(value);
        //wait.until(ExpectedConditions.attributeContains(locator,"value",value ));
        //value = value.substring() + value.substring()
        //Assertions.assertEquals( maskRu + value,phoneField.getAttribute("defaultValue"),"Данные введены не верно");

    }

    public void fillAdress(By locator, String value) {
        closeDynamicPopUp();
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(value);
        element.sendKeys(Keys.TAB);
        Assertions.assertEquals("Поле не заполнено", value, value);
    }


    protected void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void waitUtilElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void fill(By locator, String value) {
        closeDynamicPopUp();
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(value);

    }


    protected void switchToByText(String text) {
//      Получаем идентификатор вкладки на которой мы сейчас находимся
        String parentWindow = driver.getWindowHandle();
//      Получаем список вкладок
        List<String> vkladki = new ArrayList<>(driver.getWindowHandles());
        for (String item : vkladki) {
//          Если не совпал идентификатор с нашей вкладкой значит нужно переключиться
//          Т.е. попадем в if
            if (!item.equals(parentWindow)) {
                driver.switchTo().window(item);
//              Проверка текста на вкладке если текст совпал то останавливаем цикл
                if(driver.getTitle().equals(text)) {
                    return;
                }
            }
        }
        Assertions.fail("Вкладка с текстом '" + text + "' не найдена");
    }



}





