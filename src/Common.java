import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;


public class Common {

    private static ChromeDriver driver;
    private static String browserName;
    private static String browserVersion;

    public static final String GLOBAL_REACT_NAMESPACE = "react-select-";

    public Common(ChromeDriver driver) {
        this.driver = driver;
    }

    public static void setUp(ChromeDriver driver) throws Exception {


        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        browserName = "Chrome";
        browserVersion = "46";
        System.out.println("Automated test run. We’re running on " + browserName + " " + browserVersion);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }


    //Поиск элемента

    public WebElement findElement(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        WebElement webElement = null;

        try {
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            //.debug("Find element: " + locator + " => true");
        } catch (Exception ex) {
             //LOG.warn("Web element not found: " + locator);
        }
        return webElement;
    }

    public void closeNotification(String notificationsClass) {
        if (driver.findElement(By.className(notificationsClass)).isDisplayed()) {
            driver.findElement(By.className("notification-dismiss")).click();
        }
    }

    //Кликнуть и выбрать из списка
    public static WebElement clickAndChoose (String nameField, String value) {
        WebDriverWait wait = new WebDriverWait(driver, 90);
        WebElement waitingForID = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(nameField+"-container")));
        waitingForID.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", waitingForID);
        WebElement chooseValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(GLOBAL_REACT_NAMESPACE+nameField+"-value-"+value)));//react-select-employee-position_id-18
        chooseValue.click();
        return chooseValue;
    }


    //Ждать до отображения элемента по ID
    public static WebElement waitingForID(String elementID) {
        WebDriverWait wait = new WebDriverWait(driver, 90);
        WebElement WaitingForID = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementID)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", WaitingForID);
        return WaitingForID;
    }

    //Ждать до отображения элемента из списка по ID
    public WebElement waitingForIDFromPlaceholder(String nameField, String elementID) {
        WebDriverWait wait = new WebDriverWait(driver, 90);
        WebElement waitingForID = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(GLOBAL_REACT_NAMESPACE+nameField+elementID)));
        return waitingForID;
    }

    //Ждать, когда элемент станет "кликабельным"
    public WebElement isElementClickable(String elementID) {

        WebElement element = driver.findElement(By.id(elementID));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
        WebDriverWait wait = new WebDriverWait(driver, 90);
        WebElement isElementClickable = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementID)));
        WebElement newElem = findElement(By.id(elementID), 90);
        if (isElementClickable == newElem)
            return isElementClickable;
        else
            return newElem;
    }

    //Элемент кликабельным по xpath
    public WebElement isElementClickableByXpath(String putHereXpath) {
        WebDriverWait wait = new WebDriverWait(driver, 90);
        WebElement IsElementClickableByXpath = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(putHereXpath)));
        return IsElementClickableByXpath;
    }

    //Элемент кликабельным по классу
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

    public void actions(String elementID) {
        WebElement element = driver.findElement(By.id(elementID));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public void findElementByIdAndClick(String elementId) {
        WebElement element = driver.findElement(By.id(elementId));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }


    public void pageLoadingCompletely() {

    }

    //Неявное ожидание
    public void implicitWait(String elementID) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
        driver.findElement(By.id(elementID));
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
        System.out.println("errorList size = " + errorList.size());


        Map<String, Map<String, WebElement>> pola = getInputWindowsWithAtt("col-md-6");

        List<String> errorTextList = new ArrayList<>();
        for (String key : pola.keySet()) {
            System.out.println(pola.get(key).get("Input").getAttribute("value"));

            System.out.println(
                    "В поле \"" + key + "\" заполненном данными : \"" +
                    pola.get(key).get("Input").getAttribute("value") + "\" обнаружена подсказка : " +
                    pola.get(key).get("Error").getText()
            );
            errorTextList.add(pola.get(key).get("Error").getText());
        }

/*
        for(WebElement e : errorList){
            WebElement upperElem = driver.findElementById("");
            if(e.getText() == null)
                System.out.println("Некоторые сообщения о ошибке не заполнены");
            else if(e.getText().isEmpty())C:\Users\User\Desktop\тестовый.xlsx

                System.out.println("Некоторые сообщения о ошибке не заполнены");
            else
                System.out.println(e.getText());
        }*/



        /*
        for (int i = 0; i < errorList.size(); i++)
            errorTextList.add(errorList.get(i).getText());*/
        return errorTextList;
    }


    //Строку в номер 1
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

    //Строку в номер 2
    public String getCharsFromString(String elementID, int min, int max) {
        String getSomeText = driver.findElement(By.id(elementID)).getText();
        String getFewChars = getSomeText.substring(min, max);
        System.out.println("Из строки " + getSomeText + "получено:" + getFewChars);
        return getFewChars;
    }


    //Ждать, пока исчезнет текст
    public void textToDisappear(String elementID, String textToBePresent) {
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        WebElement element = driver.findElement(By.id(elementID));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, textToBePresent)));
    }

    //Ждать, пока исчезнет форма сверху
    public void waitUntilTheOverlayDisppears(String elementID) {
        By loadingImage = By.id(elementID);//loading image ID
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingImage));
        return;
    }

    //Asserts

    public void hardAssert(String elementID) {
        Assert.assertTrue(driver.findElement(By.id(elementID)).isDisplayed());
    }

    public void hardAssertEquals(String retrieveTextFromField, String expected) {
        Assert.assertEquals(driver.findElement(By.id(retrieveTextFromField)).getAttribute("value"), expected);
    }

    @Deprecated
    //todo Избавиться от метода обертки
    public void softAssert(String elementID, String expected) {
        /**
         * Старьё
         * */
        final String value = driver.findElement(By.id(elementID)).getAttribute("value");
        customSoftStringAssert(value, expected, "equals",
                "ВНИМАНИЕ!!! Переменная \"" + value + "\" не равна ожидаемой переменной \"" + expected + "\"");
    }

    public void softAssertGetText(String elementID, String expected) {
        /**
         * Старьё
         * */
        final String value = driver.findElement(By.id(elementID)).getText();
        customSoftStringAssert(value, expected, "equals",
                "ВНИМАНИЕ!!! Переменная \"" + value + "\" не равна ожидаемой переменной \"" + expected + "\"");
    }

    public void customSoftStringAssert(String a, String b, String type, String message) {
        switch (type) {
            case "equals":
                if (a.equals(b))
                    return;
                else
                    System.out.println(message);
                break;
            case "contains":
                if (a.contains(b))
                    return;
                else
                    System.out.println(message);
                break;
            case "rcontains":
                if (b.contains(a))
                    return;
                else
                    System.out.println(message);
                break;
            default:
                System.out.println("LAZHA");
        }
    }


    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(stringSelection, null);
    }

    public void uploadFile(String filesPath, String elementID) throws InterruptedException, AWTException {
        File file = new File(filesPath);
        WebElement uploadButton = driver.findElement(By.id(elementID));
        uploadButton.click();
        setClipboardData(file.getAbsolutePath());
        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(300);
    }


    public void softAssert1(String elementID, String expected) {

        final String value = driver.findElement(By.id(elementID)).getAttribute("value");
        customSoftStringAssert(value, expected, "equals",
                "ВНИМАНИЕ!!! Переменная \"" + value + "\" не равна ожидаемой переменной \"" + expected + "\"");
    }

    private Map<String, Map<String, WebElement>> getInputWindowsWithAtt (String windowClassName){
        List<WebElement> formGroups = driver.findElementsByClassName(windowClassName);
        Map<String, Map<String, WebElement>> result = new HashMap<>();
        for (WebElement e : formGroups) {
            try {
                Map<String, WebElement> pole = new HashMap<>();
                WebElement main = e.findElement(By.className("control-label"));
                WebElement input = e.findElement(By.className("form-control"));
                WebElement error = e.findElement(By.className("error"));
                pole.put("Error", error);
                pole.put("Input", input);
                result.put(main.getText(), pole);
            } catch (Exception ex) {
                continue;
            }
        }
        return result;
    }

    public void validateErrorText(List<String> errorTextList, String expectedText){
        if(!errorTextList.contains(expectedText)){
            System.out.println("Не появилось ожидаемой проверки! : \"" + expectedText + "\"");
        }
    }
}


//    public void fileFromLocalToRemote(String remoteURL) {
//        DesiredCapabilities capabillities = DesiredCapabilities.chrome();
//        driver = new RemoteWebDriver(new URL(remoteURL),capabillities);
//        driver.setFileDetector(new LocalFileDetector());
//
//    }


//    public void assertEmpty(StringBuilder sb) {
//        if (sb.length() &gt; 0) {
//            throw new AssertionError(sb.toString());
//        }


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


//Работа со списком элементов
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




