
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SozdanieSotrudnika {
        private WebDriver driver;
        private String baseUrl;
        private boolean acceptNextAlert = true;
        private StringBuffer verificationErrors = new StringBuffer();


        public void testUntitledTestCase() throws Exception {
            driver.get("http://dev2-ets.gost-group.com/#/dashboard");
            driver.findElement(By.id("show-nsi")).click();
            driver.findElement(By.id("link-employees")).click();
            driver.findElement(By.id("open-create-form")).click();
            //Фамилия
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия'])[1]/following::input[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия'])[1]/following::input[1]")).clear();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия'])[1]/following::input[1]")).sendKeys("Пупыркин1");
            //Имя
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Имя'])[1]/following::input[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Имя'])[1]/following::input[1]")).clear();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Имя'])[1]/following::input[1]")).sendKeys("Василий1");
            //Отчество
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отчество'])[1]/following::input[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отчество'])[1]/following::input[1]")).clear();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отчество'])[1]/following::input[1]")).sendKeys("Валерьянович1");
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Срок действия специального удостоверения'])[3]/following::div[6]")).click();
            driver.findElement(By.id("rw_6_input")).click();
            driver.findElement(By.id("rw_6_input")).clear();
            driver.findElement(By.id("rw_6_input")).sendKeys("28.01.1975");
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Телефон'])[3]/following::input[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Телефон'])[3]/following::input[1]")).clear();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Телефон'])[3]/following::input[1]")).sendKeys("89052860021");
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Выберите...'])[13]/following::div[3]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Должность'])[3]/following::div[4]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Общее'])[3]/following::div[4]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Медицинская справка №'])[1]/following::div[2]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Табельный номер'])[3]/following::input[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Табельный номер'])[3]/following::input[1]")).clear();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Табельный номер'])[3]/following::input[1]")).sendKeys("0937");
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Специальное удостоверение'])[3]/following::input[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Специальное удостоверение'])[3]/following::input[1]")).clear();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Специальное удостоверение'])[3]/following::input[1]")).sendKeys("67868317823");
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='вс'])[1]/following::div[5]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Срок действия специального удостоверения'])[3]/following::button[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='август 2018'])[1]/following::button[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='сентябрь 2018'])[1]/following::button[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='вс'])[1]/following::td[27]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Водительское удостоверение'])[3]/following::input[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Водительское удостоверение'])[3]/following::input[1]")).clear();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Водительское удостоверение'])[3]/following::input[1]")).sendKeys("234789329879");
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Водительское удостоверение'])[3]/following::div[2]")).click();
            driver.findElement(By.id("rw_7_input")).click();
            driver.findElement(By.id("rw_7_input")).clear();
            driver.findElement(By.id("rw_7_input")).sendKeys("25.03.2015");
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='водитель'])[2]/following::label[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Основное ТС'])[2]/following::div[4]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Вторичное ТС'])[2]/following::div[4]")).click();
            driver.findElement(By.id("react-select-17--value")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Медицинская справка №'])[1]/following::input[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Медицинская справка №'])[1]/following::input[1]")).clear();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Медицинская справка №'])[1]/following::input[1]")).sendKeys("3426378483");
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Медицинская справка №'])[1]/following::div[2]")).click();
            driver.findElement(By.id("rw_8_input")).click();
            driver.findElement(By.id("rw_8_input")).clear();
            driver.findElement(By.id("rw_8_input")).sendKeys("01.01.2020");
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='СНИЛС №'])[3]/following::input[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='СНИЛС №'])[3]/following::input[1]")).clear();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='СНИЛС №'])[3]/following::input[1]")).sendKeys("67812312387");
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Медицинские справки'])[1]/following::div[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Добавить файл'])[2]/following::button[1]")).click();
        }

        @AfterClass(alwaysRun = true)
        public void tearDown() throws Exception {
            driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }

        private boolean isElementPresent(By by) {
            try {
                driver.findElement(by);
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        private boolean isAlertPresent() {
            try {
                driver.switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

        private String closeAlertAndGetItsText() {
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                if (acceptNextAlert) {
                    alert.accept();
                } else {
                    alert.dismiss();
                }
                return alertText;
            } finally {
                acceptNextAlert = true;
            }
        }
    }



