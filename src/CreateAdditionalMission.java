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

    public void addMission1(ChromeDriver driver) throws Exception {
        Common utils = new Common(driver);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, +1);
        Date oneHourAhead = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String waybillsPlannedStartDate = sdf.format(oneHourAhead);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,450)", "");
        //Нажать "Применить" в фильтре
        utils.isElementClickable("apply-filter").click();
        //Хватает первый элемент
        utils.waitingForID("checkbox-id").click();
        //Форма "Активный путевой лист"
        Thread.sleep(2000);
        utils.waitingForID("open-update-form").click();
        utils.waitingForID("create-mission").click();
        //Задание - Технологическая операция
        Thread.sleep(2000);
        utils.waitingForID("react-select-47--value").click();
        utils.waitingForID("119").click(); //Сопровождение дорожно-ремонтных работ


        // //Наличие текста: "Дата не должна выходить за пределы путевого листа"
        Thread.sleep(2000);
        Common OutOfWaybillsSchedule = new Common(driver);
        List<String> allErrors = OutOfWaybillsSchedule.getAllErrorsTextListed();
        System.out.println(allErrors);

        if (!allErrors.contains(" Дата не должна выходить за пределы путевого листа")) {
            utils.waitingForID("date-start_input").click();

            utils.waitingForID("date-start_input").clear();
            utils.waitingForID("date-start_input").sendKeys(waybillsPlannedStartDate);
        } else
            System.out.println(waybillsPlannedStartDate);

        //Задание - Элемент

        utils.isElementClickable("react-select-48--value");
        driver.findElement(By.id("react-select-48--value")).click();

        utils.isElementVisible("102").click();

        //Создание маршрута
        //  waitingForClick.isElementClickable("create-route");
        utils.waitingForID("create-route").click();
        //Название маршрута
        utils.waitingForClass("ol-unselectable");
        utils.isElementClickable("route-name");

        utils.waitingForID("route-name").click();
        utils.waitingForID("route-name").sendKeys("Маршрут №2");
        //Список выбранных ДТ
        utils.waitingForID("react-select-56--value").click();
        utils.waitingForID("375814").click();
        utils.waitingForID("react-select-56--value").click();
        utils.waitingForID("188657").click();
        //Нажатие кнопки "Создать".
        utils.waitingForID("route-submit").click();
        //Отображается уведомление: "Данные успешно сохранены".

        //Наличие текста: "Время выполнения задания для ОДХ должно составлять не более 5 часов"
        Common LessThan5hours = new Common(driver);
        List<String> allErrors1 = LessThan5hours.getAllErrorsTextListed();

        if (!allErrors.contains("Время выполнения задания для ОДХ должно составлять не более 5 часов")) {
            Thread.sleep(2000);
            utils.waitingForID("date_end_input").click();
            utils.waitingForID("date_end_input").clear();
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
            utils.waitingForID("date_end_input").sendKeys(dateStartInput);
            utils.waitingForID("m-submit").click();
            utils.waitingForID("waybill-submit").click();

        }
    }
}
