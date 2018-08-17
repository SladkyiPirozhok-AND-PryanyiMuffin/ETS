import com.codeborne.selenide.impl.WaitingSelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by User on 14.05.2018.
 */
public class Filter {

    private ChromeDriver driver;

    public void ByPlanDepartureDate(ChromeDriver driver, String planDepartureDate) throws Exception {
        //this.driver = driver;
        Common common = new Common(driver);
        Common PresenceOfID = common;
        Common waitingForClick = new Common(driver);
        //  waitingForClick.isElementClickable("show-options-filter");


        PresenceOfID.waitingForID("show-options-filter").click();
        PresenceOfID.waitingForID("rw_3_input").sendKeys(planDepartureDate);
        //driver.findElement(By.id("rw_5_input")).sendKeys(planDepartureDate);
        PresenceOfID.waitingForID("apply-filter").click();
    }

    //Надо подумать как ввести рег.номер ТС
//    public void ByCarNumber(ChromeDriver driver, String tmp) throws Exception {
//        this.driver = driver;
//        Common common = new Common(driver);
//        Thread.sleep(7000);
//        Common PresenceOfID = common;
//        driver.findElement(By.id("show-options-filter")).isEnabled();
//        PresenceOfID.waitingForID("show-options-filter").click();
//        driver.findElement(By.id("react-select-8--value")).sendKeys(tmp);
//        PresenceOfID.waitingForID("apply-filter").click();
//    }
}
