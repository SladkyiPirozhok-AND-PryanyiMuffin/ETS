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
public class DeleteAdditionalMission {
    private ChromeDriver driver;

    public void deleteMission1(ChromeDriver driver) throws Exception {
        Common PresenceOfID = new Common(driver);
        Thread.sleep(1000);
        JavascriptExecutor jse = driver;
        jse.executeScript("window.scrollBy(0,450)", "");
        //Хватает первый элемент
        PresenceOfID.waitingForID("checkbox-id").click();
        //Форма "Активный путевой лист"

        PresenceOfID.waitingForID("open-update-form").click();
        driver.findElement(By.className("Select-value-icon")).click();

    }
}

