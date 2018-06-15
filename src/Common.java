import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Common {

    private ChromeDriver driver;
    private String browserName;
    private String browserVersion;

    public Common(ChromeDriver driver) {
        this.driver = driver;
    }

    public void setUp(ChromeDriver driver) throws Exception {


        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        browserName = "Chrome";
        browserVersion = "46";
        System.out.println("Automated test run. We’re running on " + browserName + " " + browserVersion);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    public WebElement findElement(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        WebElement webElement = null;

        try {
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            //.debug("Find element: " + locator + " => true");
        } catch (Exception ex) {
           // LOG.warn("Web element not found: " + locator);
        }
        return webElement;
    }
    public void closeNotification(String notificationsClass) {
        if (driver.findElement(By.className(notificationsClass)).isDisplayed()) {
            driver.findElement(By.className("notification-dismiss")).click();
        }
    }

    //Ждать до отображения элемента по ID
    public WebElement waitingForID(String elementID) {
        WebDriverWait wait = new WebDriverWait(driver, 90);
        WebElement WaitingForID = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementID)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementID))).equals(elementID);

                driver.findElementById(elementID).isDisplayed();
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElementById(elementID)).perform();
        return WaitingForID;
    }

    //Ждать, когда элемент станет "кликабельным"
    public WebElement isElementClickable(String elementID) {
        WebDriverWait wait = new WebDriverWait(driver, 180);
        WebElement isElementClickable = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementID)));
        WebElement newElem = findElement(By.id(elementID), 180);
        if(isElementClickable == newElem)
            return isElementClickable;
        else
            return newElem;
    }

    public WebElement isElementClickableByXpath(String putHereXpath) {
        WebDriverWait wait = new WebDriverWait(driver, 90);
        WebElement IsElementClickableByXpath = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(putHereXpath)));
        return IsElementClickableByXpath;
    }

    public WebElement isElementClickableByClass(String elementsClass) {
        WebDriverWait wait = new WebDriverWait(driver, 180);
        WebElement isElementClickableByClass = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementsClass)));
        return isElementClickableByClass;
    }

    //Ждать, когда отобразится элемент
    public WebElement isElementVisible(String elementID) {
        WebDriverWait wait = new WebDriverWait(driver, 90);
        WebElement IsElementVisible = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(elementID))));
        return IsElementVisible;
    }

    //Наличие класса
    public WebElement waitingForClass(String elementsClass) {
        WebDriverWait wait = new WebDriverWait(driver, 90);
        WebElement WaitingForClass = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(elementsClass)));
        return WaitingForClass;
    }

    //Вынуть числа
    public Matcher getNumbersFromText(String elementID) {
        String MissionTitle = driver.findElement(By.id(elementID)).getText();
        Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher = pat.matcher(MissionTitle);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        return matcher;
    }

    //Лист всех текстов из каждого элемента класса "error"
    public List<String> getAllErrorsTextListed() throws Exception {
        List<WebElement> errorList = driver.findElements(By.className("error"));


        List<String> errorTextList = new ArrayList<>();
        for (int i = 0; i < errorList.size(); i++)
            errorTextList.add(errorList.get(i).getText());
        return errorTextList;
    }
//

    public List<String> getAllTitleTextListed() throws Exception {
        List<WebElement> titleList = driver.findElements(By.className("error"));


        List<String> errorTextList = new ArrayList<>();
        for (int i = 0; i < titleList.size(); i++)
            errorTextList.add(titleList.get(i).getText());
        return errorTextList;
    }

    //Строку в номер
    public String stringToNumber(String missionNT) {
        String str = missionNT;
        String numberOnly = str.replaceAll("[^0-9]", "");
        return numberOnly;
    }

    //Получить рег.номер ТС.
    public String getCarsNumber(String elementID) {
        String carGovTitle = driver.findElement(By.id(elementID)).getText();
        String carGovNumber = carGovTitle.substring(0, 9);
        System.out.println("Номер ТС: " + carGovNumber);
        return carGovNumber;
    }

    //Строку в номер
    public String getCharsFromString(String elementID, int min, int max) {
        String getSomeText = driver.findElement(By.id(elementID)).getText();
        String getFewChars = getSomeText.substring(min, max);
        System.out.println("Из строки " + getSomeText + "получено:" + getFewChars);
        return getFewChars;
    }
    //deal with a list of elements
//    public void ClickOnTheCheckbox(String ElementType) {
//
//
//        WebElement someElement = driver.findElement(By.cssSelector("input"));
//        String typeOfElement = someElement.getAttribute("type");
//        //Пробегаемся и выбираем то, что нужно
//        List<WebElement> someElements = driver.findElements(By.cssSelector("input"));
//        for (WebElement anElement : someElements) {
//            if (anElement.getAttribute("type").equals(ElementType)) {
//             //то поисходт  ;
//            }
//        }
//    }
//    public String getAllErrorsList(int error_number) throws Exception{
//       List<WebElement> errorList = driver.findElements(By.className("error"));
//
//
////        String allerrorslist = "";
////        for (int a = 0; a < errorList.size(); a++){
////            System.out.println(errorList.get(a).getText());
////            result = result.concat("|||||").concat(errorList.get(a).getText());
////        }
////        if (errorList.size() > 0) {
////
////        }
////        else {
////        }
//        return errorList.get(error_number).getText();
////        System.out.println(allerrorslist);
    //   }

    public WebElement isElementAppear(String elementID) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement IsElementAppear = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementID)));
        return IsElementAppear;
    }

//The below code will wait until the overlay disppears

    public void waitUntilTheOverlayDisppears(String elementID) {
        By loadingImage = By.id(elementID);//loading image ID
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingImage));
        return;
    }

//    public void closeNotification(String elementsClass) {
//        Common presenceOfClass = null;
//        if (presenceOfClass.waitingForClass("notification-message").isDisplayed()) {
//            presenceOfClass.waitingForClass("notification-dismiss").click();
//            System.out.println(elementsClass);
//        }
//        else
//            System.out.println("Уведомлений нет");
//        return;
//    }

//
//    public static String getCarNum(){
//        //Получить "Транспортное средство"
//        ChromeDriver driver = new ChromeDriver();
//        Common getCreatedCarID = new Common(driver);
//        String carNumber = getCreatedCarID.getCarsNumber("react-select-30--value-item");
//
//        return carNumber;
//    }
}




