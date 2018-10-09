import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.junit.runners.Suite;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;


import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;


import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;


public class RunningTest {

    private ChromeDriver driver;
    private String browserName;
    private String browserVersion;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        browserName = "Chrome";
        browserVersion = "46";
        System.out.println("Automated test run. We’re running on " + browserName + " " + browserVersion);
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        WebElement html = driver.findElement(By.tagName("html"));
        html.sendKeys(Keys.chord(Keys.CONTROL, "300"));
   // }

//    @Test(description = "Вход в систему")
//    public void waybillCD() throws Exception {
        Login Enter = new Login();
        Enter.User(driver, "", "");
  }

    @Test(description = "Создание ПЛ через реестр Журнал путевых листов")
    public void w1aybill1C2D() throws Exception {
        CreateWaybill FromJournal = new CreateWaybill();
        String createWaybillDate;
        String currentTMP = FromJournal.tmp;
        FromJournal.tmp = null;
        FromJournal.CreateWaybill(driver);
    }

    //Фильтр по "Выезд план"
    @Test(description = "Поиск и добавление задания + удаление задания")
    public void waybillC2D3() throws Exception {

        CreateWaybill FromJournal = new CreateWaybill();
        String createWaybillDate = null;
        Filter lookingForWaybill = new Filter();
        lookingForWaybill.ByPlanDepartureDate(driver, createWaybillDate);
        analyzeLog();
        CreateAdditionalMission oneMoreMission = new CreateAdditionalMission();
        oneMoreMission.addMission1(driver);
        analyzeLog();
        DeleteAdditionalMission deleteOneMoreMission = new DeleteAdditionalMission();
        deleteOneMoreMission.deleteMission1(driver);
        analyzeLog();
    }

    @Test(description = "Удаление ПЛ")
    public void waybillC2D333() throws Exception {
        Delete AfterCreation = new Delete();
        AfterCreation.DeleteWaybill(driver);
        analyzeLog();
        // createWaybillDate = FromJournal.CreateWaybill(driver);
    }

    //Создать сотрудника
    @Test(description = "Удаление ПЛ")
    public void waybillC2eD3() throws Exception {
        CreateEmployee addEmployee = new CreateEmployee(driver);
        addEmployee.createDriver();
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();}
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
    public void afterMethod(ITestResult result)
    {
        try
        {
            if(result.getStatus() == ITestResult.SUCCESS)
            {

                //Do something here
                System.out.println("passed **********");
            }

            else if(result.getStatus() == ITestResult.FAILURE)
            {
                //Do something here
                System.out.println("Failed ***********");

            }

            else if(result.getStatus() == ITestResult.SKIP ){

                System.out.println("Skiped***********");

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}



