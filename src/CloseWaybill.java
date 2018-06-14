import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by User on 16.05.2018.
 */
public class CloseWaybill {
    private ChromeDriver driver;

    public void deleteMission1(ChromeDriver driver) throws Exception {
        Common PresenceOfID = new Common(driver);
        Thread.sleep(1000);
        JavascriptExecutor jse = driver;
        jse.executeScript("window.scrollBy(0,450)", "");
        //Хватает первый элемент
        PresenceOfID.waitingForID("checkbox-id").click();


        PresenceOfID.waitingForID("open-update-form").click();
        //Форма "Активный путевой лист"

        //Выезд из гаража, м/ч
        //Возвращение в гараж, м/ч
        String motohours_start = driver.findElement(By.id("motohours-start")).getText();
        System.out.println("Возвращение в гараж, м/ч " + motohours_start);
        //Выезд из гаража, м/ч
        driver.findElement(By.id("motohours-end")).sendKeys(motohours_start + 2);
        System.out.println("Выезд из гаража, м/ч " + motohours_start);
        PresenceOfID.waitingForID("add-operation").click();
        PresenceOfID.waitingForID("react-select-69--value").click();
        PresenceOfID.waitingForID("625").click();

        PresenceOfID.waitingForID("waybill-submit").click();

    }
}

