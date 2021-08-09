package org.ibs.appline;

import data.CsvConverterToDataFormReg;
import data.DataFormReg;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test.base.BaseTest;

import java.util.Set;


 class ParameterizedPageTest extends BaseTest {


    //
//    @DisplayName("Параметризированный тест")
//    @Tag("TestGroup1")
//    @Test
//    void testCase() throws InterruptedException {
//
//    }


//    @DisplayName("данные из csv")
//    @ParameterizedTest(name = "Индексы FIO={0}, date={1}, town={2}, passSeriesPar ={3},passNumberPar = {4}, passDatePar = {5} ")
//    @CsvSource({
//            "Иванов Иван Иванович, 01011991, Москва, 1111,1111111111, 12121995 ",
//            "Петров Сергей Сергеевич, 12122000, Саратов, 2222, 2222222222, 18041991 "
//    })
//    void filledTest(String FIO, String date, String town, String passSeriesPar, String passNumberPar, String passDatePar) throws InterruptedException {
//        WebElement btnIpoteka = driver.findElement(By.xpath("//ul[@class = 'main-menu']//a[contains(text(),'Ипотека')]"));
//        btnIpoteka.click();
//
//        Set<String> oldWindowsSet = driver.getWindowHandles();
//
//        String applyFormXpath = "//a[(@class='menu-section-link') and contains(text(),'Подать заявку')]";
//        WebElement toApplyForm = driver.findElement(By.xpath(applyFormXpath));
//        toApplyForm.click();
//
//
//        Set<String> newWindowsSet = driver.getWindowHandles();
//        newWindowsSet.removeAll(oldWindowsSet);
//        String newWindowHandle = newWindowsSet.iterator().next();
//        driver.switchTo().window(newWindowHandle);
//
//        WebElement headerForm = driver.findElement(By.xpath("//*[contains(text(),'Предварительное решение по ипотеке')]"));
//        Assertions.assertEquals(headerForm.getText(), "Предварительное решение по ипотеке", "Указанная форма не открылась");
//
//        WebElement element = driver.findElement(By.xpath("//*[@data-marker-field='fullName']"));
//        element.click();
//        element.clear();
//        element.sendKeys(FIO);
//        Thread.sleep(4000);
//        element.sendKeys(Keys.DOWN);
//        element.sendKeys(Keys.RETURN);
//        Thread.sleep(4000);
//
//
//        WebElement birthDate = driver.findElement(By.xpath("//*[@name='birthDate']"));
//        birthDate.click();
//        birthDate.sendKeys(date);
//        Thread.sleep(2000);
//
//        WebElement birthPlace = driver.findElement(By.xpath("//*[@name='birthPlace']"));
//        birthPlace.click();
//        birthPlace.sendKeys(town);
//        Thread.sleep(2000);
//
//
//        String radioXpath = "//*[@type='radio' and @value='M']//parent::*";
//        WebElement gender = driver.findElement(By.xpath(radioXpath));
//        gender.click();
//
//
//        WebElement isCitizen = driver.findElement(By.xpath("//*[@data-marker='Switcher.Jackdaw']"));
//        isCitizen.click();
//
//
//        String selectCountryXpath = "//*[@class='Selectstyles__Box-cmangp-0 kMPkqr']";
//        WebElement elementSelect = driver.findElement(By.xpath(selectCountryXpath));
//        elementSelect.click();
//
//
//        WebElement countryName = driver.findElement(By.xpath("//*[contains(text(),'Германия')]"));
//        countryName.click();
//
//
//        WebElement passSeries = driver.findElement(By.xpath("//*[@name='foreignSeries']"));
//        passSeries.click();
//        passSeries.clear();
//        passSeries.sendKeys(passSeriesPar);
//        Thread.sleep(5000);
//
//
//        WebElement passNumber = driver.findElement(By.xpath("//*[@data-marker-field='foreignNumber']"));
//        passNumber.click();
//        passSeries.clear();
//        passSeries.sendKeys(passNumberPar);
//
//
//        WebElement passDate = driver.findElement(By.xpath("//*[@data-marker-field='foreignNumber']"));
//        passNumber.click();
//        passSeries.clear();
//        passSeries.sendKeys(passDatePar);
//
//        Thread.sleep(5000);


//        fill(By.xpath("//*[@name='foreignSeries']"), passSeries);
//        fill(By.xpath("//*[@data-marker-field='foreignNumber']"), "1111111111");
//        fillDate(By.xpath("//*[@data-marker-field='foreignIssuedDate']"), "01.01.2020");
//        fill(By.xpath("//*[contains(@name,'foreignIssuedBy')]"), "111");


//    }


    @DisplayName("данные из CSV файла")
    @ParameterizedTest(name = "{index} => FIO={0}, date={1}, town={2}, passSeriesPar ={3}")
    @CsvFileSource(resources = "/data/towns.csv",
            lineSeparator = "\n",
            delimiter = ',')

     void valueTownrFromCSVTest(@CsvConverterToDataFormReg DataFormReg data) {

        WebElement btnIpoteka = driver.findElement(By.xpath("//ul[@class = 'main-menu']//a[contains(text(),'Ипотека')]"));
        btnIpoteka.click();

        String applyFormXpath = "//a[(@class='menu-section-link') and contains(text(),'Подать заявку')]";
        WebElement toApplyForm = driver.findElement(By.xpath(applyFormXpath));
        toApplyForm.click();

        switchToByText("Ипотека в Райффайзенбанке: вторичное жилье, новостройки, рефинансирование");


        //убрать текст из xpath
        WebElement headerForm = driver.findElement(By.xpath("//*[contains(text(),'Предварительное решение по ипотеке')]"));
        Assertions.assertEquals(headerForm.getText(), "Предварительное решение по ипотеке", "Указанная форма не открылась");


        fillFIO(By.xpath("//*[@data-marker-field='fullName']"), data.getFIO());


        fillDate(By.xpath("//*[@name='birthDate']"), data.getDate());

        fillAdress(By.xpath("//*[@name='birthPlace']"), data.getTown());


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


        fill(By.xpath("//*[@name='foreignSeries']"), data.getPassSeriesPar());
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


}
