import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 16.05.2018.
 */
public class CreateAdditionalMission {
  //  private ChromeDriver driver;

    public void AddMission1(ChromeDriver driver) throws Exception {
        Common presenceOfID = new Common(driver);
        Common WaitingForClick = new Common(driver);
        Common WaitingForVisibility = new Common(driver);
        Common waitingForClick = new Common(driver);
        Common presenceOfClass = new Common(driver);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, +1);
        Date oneHourAhead = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String waybillsPlannedStartDate = sdf.format(oneHourAhead);
        Common WaitingForClickByXpath = new Common(driver);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,450)", "");
        //Хватает первый элемент
        presenceOfID.waitingForID("checkbox-id").click();
        //Форма "Активный путевой лист"

        presenceOfID.waitingForID("open-update-form").click();

        presenceOfID.waitingForID("create-mission").click();
        //Задание - Технологическая операция

        presenceOfID.waitingForID("react-select-50--value").click();
        presenceOfID.waitingForID("100").click();


        // //Наличие текста: "Дата не должна выходить за пределы путевого листа"
        Common OutOfWaybillsSchedule = new Common(driver);
        List<String> allErrors = OutOfWaybillsSchedule.getAllErrorsTextListed();
        System.out.println(allErrors);

        if (!allErrors.contains(" Дата не должна выходить за пределы путевого листа")) {
            presenceOfID.waitingForID("date-start_input").click();

            presenceOfID.waitingForID("date-start_input").clear();
            presenceOfID.waitingForID("date-start_input").sendKeys(waybillsPlannedStartDate);
        } else
            System.out.println(waybillsPlannedStartDate);

        //Задание - Элемент

        WaitingForClick.isElementClickable("react-select-52--value").click();
        driver.findElement(By.id("react-select-52--value")).click();

        WaitingForVisibility.isElementVisible("101").click();

        //Создание маршрута

        presenceOfID.waitingForID("create-route").click();
        //Название маршрута
        presenceOfClass.waitingForClass("ol-unselectable");
        waitingForClick.isElementClickable("route-name");

        presenceOfID.waitingForID("route-name").click();
        presenceOfID.waitingForID("route-name").sendKeys("Маршрут №2");
        //Список выбранных ОДХ
        presenceOfID.waitingForID("react-select-60--value").click();
        presenceOfID.waitingForID("462688").click();
        //Нажатие кнопки "Создать".
        presenceOfID.waitingForID("route-submit").click();
        //Отображается уведомление: "Данные успешно сохранены".

        //Наличие текста: "Время выполнения задания для ОДХ должно составлять не более 5 часов"
        Common LessThan5hours = new Common(driver);
        List<String> allErrors1 = LessThan5hours.getAllErrorsTextListed();

        if (!allErrors.contains("Время выполнения задания для ОДХ должно составлять не более 5 часов")) {
            presenceOfID.waitingForID("date_end_input").click();
            presenceOfID.waitingForID("date_end_input").clear();
            if (!driver.findElement(By.id("date_end_input")).getAttribute("value").isEmpty()) ;
            String dateStartInput = driver.findElement(By.id("date-start_input")).getAttribute("value");
            if (dateStartInput.isEmpty())
                System.out.println("lazha");
            else
                System.out.println(dateStartInput);


            try {
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                Date res = new Date(format.parse(dateStartInput).getTime() + 1000 * 3600);
                dateStartInput = format.format(res);
            } catch (Exception e) {
                System.out.println("Не увижу этот текст");
            }
            presenceOfID.waitingForID("date_end_input").sendKeys(dateStartInput);
            presenceOfID.waitingForID("m-submit").click();
            presenceOfID.waitingForID("waybill-submit").click();

        }
    }
}
