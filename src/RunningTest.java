import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.logging.*;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


public class RunningTest {

    private ChromeDriver driver;
    private String browserName;
    private String browserVersion;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        browserName = "Chrome";
        browserVersion = "46";
        System.out.println("Automated test run. We’re running on " + browserName + " " + browserVersion);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        //driver = new ChromeDriver(caps);

//        File file = new File("C:/eidriver/IEDriverServer.exe");
//        System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
//        WebDriver driver = new InternetExplorerDriver();
//
//        System.setProperty("webdriver.chrome.driver", "C:\\eidriver\\IEDriverServer.exe");
//        driver = new InternetExplorerDriver();
//        browserName = "IEDriver";
//        browserVersion = "9";
//        System.out.println("Automated test run. We’re running on " + browserName + " " + browserVersion);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @Test
    @Parameters
    public void WaybillCD() throws Exception {
        Login Enter = new Login();
        Enter.User(driver, "", "");
        CreateWaybill FromJournal = new CreateWaybill();
        String createWaybillDate;

        String currentTMP = FromJournal.tmp;
        FromJournal.tmp = null;

        createWaybillDate = FromJournal.CreateWaybill(driver);
        //Фильтр по "Выезд план"
        Filter lookingForWaybill = new Filter();
        lookingForWaybill.ByPlanDepartureDate(driver, createWaybillDate);
        analyzeLog();
        CreateAdditionalMission oneMoreMission = new CreateAdditionalMission();
        oneMoreMission.AddMission1(driver);
        analyzeLog();
        DeleteAdditionalMission deleteOneMoreMission = new DeleteAdditionalMission();
        deleteOneMoreMission.deleteMission1(driver);
        analyzeLog();
//        Delete AfterCreation = new Delete();
//        AfterCreation.DeleteWaybill(driver);
        analyzeLog();
    }
    @Test
    public void WaybillCDde() throws Exception {

    }

    @AfterClass(alwaysRun = true)
//    public void tearDown() throws Exception {
//        driver.quit();}
    public void analyzeLog() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());

            for (LogEntry logEntry : logEntries) {
               // System.out.println("Находимся в " + "добавить переменную названия форм");
                System.out.println("__________________________________________________________");
                if (logEntry.getMessage().toLowerCase().contains("error")) {
                    System.out.println("ОШИБКА: " + logEntry.getMessage());
                } else if (logEntry.getMessage().toLowerCase().contains("warning")) {
                    System.out.println("Предупреждение: " + logEntry.getMessage());
                } else {
                    System.out.println("Инфо: " + logEntry.getMessage());
                }
            }
        }
    }
}



