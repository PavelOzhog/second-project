package org.ibs.appline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test.base.BaseTest;

import java.util.Set;

public class SecondTest extends BaseTest {



    @DisplayName("Первый тест")
    @Tag("TestGroup1")
    @Test
    void testCase() throws InterruptedException {
        WebElement btnIpoteka = driver.findElement(By.xpath("//ul[@class = 'main-menu']//a[contains(text(),'Ипотека')]"));
        btnIpoteka.click();

        String applyFormXpath = "//a[(@class='menu-section-link') and contains(text(),'Подать заявку')]";
        WebElement toApplyForm = driver.findElement(By.xpath(applyFormXpath));
        toApplyForm.click();

        switchToByText("Ипотека в Райффайзенбанке: вторичное жилье, новостройки, рефинансирование");


        //убрать текст из xpath
        WebElement headerForm = driver.findElement(By.xpath("//*[contains(text(),'Предварительное решение по ипотеке')]"));
        Assertions.assertEquals(headerForm.getText(), "Предварительное решение по ипотеке", "Указанная форма не открылась");


        fillFIO(By.xpath("//*[@data-marker-field='fullName']"), "Иванов Иван Иванович");


        fillDate(By.xpath("//*[@name='birthDate']"), "01.01.1990");

        fillAdress(By.xpath("//*[@name='birthPlace']"), "Москва");


        String radioXpath = "//*[@type='radio' and @value='M']//parent::*";
        WebElement gender = driver.findElement(By.xpath(radioXpath));
        gender.click();



        WebElement isCitizen = driver.findElement(By.xpath("//*[@data-marker='Switcher.Jackdaw']"));
        isCitizen.click();

        //неправильный xpath
        String selectCountryXpath = "//*[@class='Selectstyles__Box-cmangp-0 kMPkqr']";
        WebElement elementSelect = driver.findElement(By.xpath(selectCountryXpath));
        elementSelect.click();


        WebElement countryName = driver.findElement(By.xpath("//*[contains(text(),'Германия')]"));
        countryName.click();


        fill(By.xpath("//*[@name='foreignSeries']"), "1111");
        fill(By.xpath("//*[@data-marker-field='foreignNumber']"), "1111111111");
        fillDate(By.xpath("//*[@data-marker-field='foreignIssuedDate']"), "01.01.2020");
        fill(By.xpath("//*[contains(@name,'foreignIssuedBy')]"), "111");

        fillAdressOfStay(By.xpath("//*[contains(@name,'registrationAddress')]"), "г Москва, Ломоносовский пр-кт, д 27Д");

        WebElement btnContinue = driver.findElement(By.xpath("//*[@data-marker='MainForm.constructor.Button']"));
        scrollToElementJs(btnContinue);

        wait.until(ExpectedConditions.elementToBeClickable(btnContinue));


        String value = "9099999999";
        fillPhone(By.xpath("//*[contains(@data-marker-field,'phone')]"), value);



        String income = "//*[@placeholder='Доход' or text()='Доход']/ancestor::div[contains(@data-marker, 'Fieldset.value.Root') or contains(@data-marker, 'Fieldset.Root')]/div[@data-marker='Fieldset.value.Error']";
        WebElement incomeAttention = driver.findElement(By.xpath(income));
        Assertions.assertEquals("Поле обязательно для заполнения", incomeAttention.getAttribute("innerText"), "Данного элемента нет на странице");

        WebElement townAttention = driver.findElement(By.xpath("//*[contains(text(),'Выберите из списка')]"));                                         //проверка сообщения при неккоректно введном поле
        Assertions.assertEquals("Выберите из списка", townAttention.getAttribute("innerText"), "Данного элемента нет на странице");

        String contactInf = "//h3[contains(text(),'Контактные')]/../..//*[contains(text(),'Поле обязательно для заполнени')]";
        WebElement contactInfIns = driver.findElement(By.xpath(contactInf));
        Assertions.assertEquals("Поле обязательно для заполнения",contactInfIns.getAttribute("innerText"), "Данного элемента нет на странице");


        String agreement ="//*[contains(text(),'Я соглашаюсь с условиями обработки персональных')]/../../..//*[contains(text(),'Поле обязательно для заполнения')]";
        WebElement agreementAlert = driver.findElement(By.xpath(agreement));
        Assertions.assertEquals("Поле обязательно для заполнения",agreementAlert.getAttribute("innerText"), "Данного элемента нет на странице");

    }





//    public void switchToByText(String text) {
//        //      Получаем идентификатор вкладки на которой мы сейчас находимся
//        String parentWindow = driver.getWindowHandle();
////      Получаем список вкладок
//        List<String> vkladki = new ArrayList<>(driver.getWindowHandles());
//        for (String windowHandle : vkladki) {
////          Если не совпал идентификатор с нашей вкладкой значит нужно переключиться
////          Т.е. попадем в if
//            if (!windowHandle.equals(parentWindow)) {
//                driver.switchTo().window(windowHandle);
////              Проверка текста на вкладке если текст совпал то останавливаем цикл
//                if (driver.getTitle().equals(text)) {
//                    return;
//                }
//            }
//        }
//        Assertions.fail("Вкладка с текстом '" + text + "' не найдена");
//    }


    //        Assertions.assertTrue(
//                ()->{}
//
//
//
//        );

//        switchToByText("Подать заявку");
//        WebElement btnSendApplic = driver.findElement(By.xpath("//*[contains(@class,'section-link') and contains (text(),'Подать заявку')]"));
//        btnSendApplic.click();


    //        wait = new WebDriverWait(driver, 10, 1000);
//
//        toApplyForm.sendKeys(Keys.chord(Keys.CONTROL, Keys.TAB));


//        String windowHandle = driver.getWindowHandle();
//
//        driver.switchTo().window(windowHandle);
//
//        WebElement element  = driver.findElement(By.xpath("//*[@data-marker-field='fullName']"));
//        element.click();
//        element.clear();
//        element.sendKeys("Иванов");

}
