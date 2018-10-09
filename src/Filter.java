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

Thread.sleep(9000);
        PresenceOfID.waitingForID("show-options-filter").click();
        PresenceOfID.waitingForID("waybill-plan_departure_date-beg-value_input").click();
        PresenceOfID.waitingForID("waybill-plan_departure_date-beg-value_input").sendKeys(planDepartureDate);
        PresenceOfID.waitingForID("apply-filter").click();
    }

//ФИльтр в реестре сотрудников по к
    public void employeeSurname(ChromeDriver driver, String employeeSurname) throws Exception {
        //this.driver = driver;
        Common common = new Common(driver);
        Common PresenceOfID = common;


        PresenceOfID.waitingForID("show-options-filter").click();
        PresenceOfID.waitingForID("rw_3_input").sendKeys(employeeSurname);
        PresenceOfID.waitingForID("apply-filter").click();
    }

}
