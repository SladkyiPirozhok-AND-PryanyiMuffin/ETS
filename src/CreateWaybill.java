import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 14.05.2018.
 */
public class CreateWaybill {

    public String tmp = null;

    public String CreateWaybill(ChromeDriver driver) throws Exception {
        Common utils = new Common(driver);
        Faker someComment = new Faker();
        String Comment = someComment.chuckNorris().fact();
        String Place = someComment.address().countryCode()+ " " + someComment.address().country() + " " + someComment.address().city() + " " + someComment.address().fullAddress();

        utils.waitingForID("link-waybillJournal").click();

        //Нажать кнопку "Создать"
        Thread.sleep(2000);
        utils.waitingForID("open-create-form").click();
        utils.waitingForID("plan-departure-date_input").clear();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, +1);
        Date oneHourAhead = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String waybillsPlannedStartDate = sdf.format(oneHourAhead);
        utils.waitingForID("plan-departure-date_input").clear();
        utils.waitingForID("plan-departure-date_input").sendKeys(waybillsPlannedStartDate);
        //Подразделение
        //"Возвращение план."
        //Сопровождающий
        utils.clickAndChoose("waybill-accompanying-person-id", "139156");
        //Режим работы - нет норм айдишника
        utils.clickAndChoose("work_mode_id", "1");
        //Транспортное средство (поиск по рег. номер ТС)
        utils.clickAndChoose("waybill-car-id", "19707");
        //Водитель
        utils.clickAndChoose("waybill-driver-id", "1657");
        //Создание задания
        utils.waitingForID("create-mission").click();
         //Задание - Технологическая операция
        utils.isElementClickable("m-comment");
        utils.clickAndChoose("mission-technical-operation-id", "101");
        //Наличие текста: "Дата не должна выходить за пределы путевого листа"
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
        utils.clickAndChoose("mission-municipal_facility_id", "102");
        //Создание маршрута
        Thread.sleep(2000);
        utils.waitingForID("create-route").click();
        //Название маршрута
        utils.waitingForID("route-name").sendKeys(Place);
        //Тип объекта
        utils.clickAndChoose("type", "mixed");
        //Список выбранных ОДХ
        utils.clickAndChoose("odh", "10003254"); //"Армавирская улица"
        utils.clickAndChoose("odh", "10003303"); //"Егорьевский проезд"
        utils.clickAndChoose("odh", "10003261"); //"Белореченская улица (от Краснодарской улицы до улицы Верхние Поля)"
        utils.clickAndChoose("odh", "10003311"); //"Заречье улица"
        //Нажатие кнопки "Создать".
        utils.waitingForID("route-submit").click();
        utils.isElementClickable("m-comment");
        //Отображается уведомление: "Данные успешно сохранены". - необходимо навешать айдишник на уведомление. Есть только текст и класс
        //Ввести комментарий
        Thread.sleep(2000);
        utils.waitingForID("m-comment").sendKeys(Comment);

        utils.findElementByIdAndClick("m-submit");

        //ПЛ."Простои на линии, ч."/Работа. - нужен id
        utils.waitingForID("downtime-hours-work").click();
        utils.waitingForID("downtime-hours-work").clear();
        utils.waitingForID("downtime-hours-work").sendKeys("1");
        //ПЛ."Простои на линии, ч."/Дежурство. - нужен id
        utils.waitingForID("downtime-hours-duty").click();
        utils.waitingForID("downtime-hours-duty").clear();
        utils.waitingForID("downtime-hours-duty").sendKeys("1");
        //ПЛ."Простои на линии, ч."/Обед. - нужен id
        utils.waitingForID("downtime-hours-dinner").click();
        utils.waitingForID("downtime-hours-dinner").clear();
        utils.waitingForID("downtime-hours-dinner").sendKeys("1");
        //ПЛ."Простои на линии, ч."/Ремонт. - нужен id
        utils.waitingForID("downtime-hours-repair").click();
        utils.waitingForID("downtime-hours-repair").clear();
        utils.waitingForID("downtime-hours-repair").sendKeys("1");

//
        utils.getNumbersFromText("mission-id-list-container");
        //Получить "Выезд план."
        String planDepartureDate = driver.findElement(By.id("plan-departure-date_input")).getAttribute("value");

        //Получить "Транспортное средство"
        tmp = utils.getCarsNumber("waybill-car-id-container");
        //Нажатие кнопки Выдачи ПЛ.
        //Выгрузка "Форма 2 (грузовое ТС)"
        utils.waitingForID("waybill-print-dropdown_save").click();
        driver.findElement(By.linkText("Форма №2 (грузовое ТС)")).click();
        //Возможно сообщение: "Дата планируемого выезда должна быть больше фактической даты возвращения т.с. в предыдущем путевом листе."
        //Задание создано успешно.

        return planDepartureDate;
    }


}