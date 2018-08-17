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
        Common waitingForClick = new Common(driver);
        Common waitingForVisibility = new Common(driver);
        Common presenceOfID = new Common(driver);
        Common presenceOfClass = new Common(driver);
        Common waitALittleBit = new Common(driver);
Common elementsCoords = new Common(driver);

       //driver.findElement(By.className("kate-waits")).wait();

        waitALittleBit.implicitWait("link-waybillJournal");
        presenceOfID.waitingForID("link-waybillJournal").click();
        waitALittleBit.implicitWait("open-create-form");
        //Нажать кнопку "Создать"
        waitingForClick.isElementClickable("open-create-form").click();

        //Выезд план.
        presenceOfID.waitingForID("plan-departure-date_input").clear();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, +1);
        Date oneHourAhead = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String waybillsPlannedStartDate = sdf.format(oneHourAhead);
        presenceOfID.waitingForID("plan-departure-date_input").clear();
        presenceOfID.waitingForID("plan-departure-date_input").sendKeys(waybillsPlannedStartDate);
        //Сопровождающий
        presenceOfID.waitingForID("accompanying-person-id").click();
        presenceOfID.waitingForID("react-select-29--value").click();
        presenceOfID.waitingForID("139156").click();
        //Режим работы
        presenceOfID.waitingForID("react-select-30--value").click();
        presenceOfID.waitingForID("1").click();
        //Транспортное средство (поиск по рег. номер ТС)
        presenceOfID.waitingForID("react-select-31--value").click();
        presenceOfID.waitingForID("19707").click();
        waitingForClick.isElementClickable("react-select-31--value-item");
        //Прицеп
        presenceOfID.waitingForID("react-select-32--value").click();

        if (presenceOfID.waitingForID("21880").isDisplayed()) {
            presenceOfID.waitingForID("21880").click();
        }
        else
            System.out.println("Прицеп не найден");

        //Водитель
        presenceOfID.waitingForID("react-select-33--value").click();
        presenceOfID.waitingForID("34216").click();

        //Создание задания
        presenceOfID.waitingForID("create-mission").click();


        //Задание - Технологическая операция
        waitingForClick.isElementClickable("m-comment");
        presenceOfID.waitingForID("react-select-36--value").click();
        presenceOfID.waitingForID("react-select-36--value").click();
        presenceOfID.waitingForID("101").click();


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

        waitingForClick.isElementClickable("react-select-38--value").click();
        driver.findElement(By.id("react-select-38--value")).click();

        waitingForVisibility.isElementVisible("102").click();
        //Выбор имеющегося маршрута
        waitALittleBit.implicitWait("react-select-40--value");
        waitingForClick.isElementClickable("react-select-40--value").click();
        waitingForClick.isElementClickable("19953").click();
        //Создание маршрута

       // waitingForClick.isElementClickable("create-route").click();
        //Название маршрута (баг, не работает)
//        waitingForClick.isElementClickable("route-name");
//        waitingForClick.isElementClickable("select-from-odh");
//        presenceOfID.waitingForID("route-name").click();
//        presenceOfID.waitingForID("route-name").sendKeys("Маршрут №1");
//        //Список выбранных ОДХ
//        presenceOfID.waitingForID("react-select-45--value").click();
//        presenceOfID.waitingForID("10003565").click();
//        presenceOfID.waitingForID("react-select-45--value").click();
//        presenceOfID.waitingForID("10003294").click();
//        //Нажатие кнопки "Создать".
//        presenceOfID.waitingForID("route-submit").click();
        //Отображается уведомление: "Данные успешно сохранены". - необходимо навешать айдишник на уведомление. Есть только текст и класс
//

        waitingForClick.isElementClickable("m-submit").click();
        //Наличие текста: "Время выполнения задания для ОДХ должно составлять не более 5 часов"
//
//        Common LessThan5hours = new Common(driver);
//        List<String> allErrors1 = LessThan5hours.getAllErrorsTextListed();
//        if (!allErrors.contains("Время выполнения задания для ОДХ должно составлять не более 5 часов")) {
//            waitingForClick.isElementClickable("react-select-35--value");
//            presenceOfID.waitingForID("date_end_input").click();
//            presenceOfID.waitingForID("date_end_input").clear();
//            if (!driver.findElement(By.id("date_end_input")).getAttribute("value").isEmpty()) ;
//            String dateStartInput = driver.findElement(By.id("date-start_input")).getAttribute("value");
//            if (dateStartInput.isEmpty())
//                System.out.println("lazha");
//            else
//                System.out.println(dateStartInput);
//
//
//            try {
//                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
//                Date res = new Date(format.parse(dateStartInput).getTime() + 1000 * 3600);
//                dateStartInput = format.format(res);
//            } catch (Exception e) {
//                System.out.println("Не увижу этот текст");
//            }
//            presenceOfID.waitingForID("date_end_input").sendKeys(dateStartInput);
//            presenceOfID.waitingForID("m-submit").click();
//        }

        //Отображается уведомение:"Задание создано успешно".
        //ПЛ.Происходит переход на форму "Создать новый путевой лист".


        //ПЛ."Счетчик моточасов оборудования/Выезд из гаража, м/ч".
        //  Thread.sleep(1000);
        //presenceOfID.waitingForID("motohours-equip-start").clear();
        // presenceOfID.waitingForID("motohours-equip-start").sendKeys("9000");

        //ПЛ."Выдать, л".

//           driver.findElement(By.id("motohours-start")).clear();
//            presenceOfID.waitingForID("motohours-start").sendKeys("9000");
        //       presenceOfID.waitingForID("fuel-to-give").sendKeys("9000");

        //ПЛ."Простои на линии, ч."/Работа. - нужен id
        waitingForClick.isElementClickable("downtime-hours-work");
        presenceOfID.waitingForID("downtime-hours-work").click();
        presenceOfID.waitingForID("downtime-hours-work").clear();
        presenceOfID.waitingForID("downtime-hours-work").sendKeys("1");
        //ПЛ."Простои на линии, ч."/Дежурство. - нужен id
        presenceOfID.waitingForID("downtime-hours-duty").click();
        presenceOfID.waitingForID("downtime-hours-duty").clear();
        presenceOfID.waitingForID("downtime-hours-duty").sendKeys("1");
        //ПЛ."Простои на линии, ч."/Обед. - нужен id
        presenceOfID.waitingForID("downtime-hours-dinner").click();
        presenceOfID.waitingForID("downtime-hours-dinner").clear();
        presenceOfID.waitingForID("downtime-hours-dinner").sendKeys("1");
        //ПЛ."Простои на линии, ч."/Ремонт. - нужен id
        presenceOfID.waitingForID("downtime-hours-repair").click();
        presenceOfID.waitingForID("downtime-hours-repair").clear();
        presenceOfID.waitingForID("downtime-hours-repair").sendKeys("1");

        Common MissionNumber = new Common(driver);
        MissionNumber.getNumbersFromText("react-select-34--value-0");
        //Получить "Выезд план."
        String planDepartureDate = driver.findElement(By.id("plan-departure-date_input")).getAttribute("value");

        //Получить "Транспортное средство"
        Common carFromWaybill = new Common(driver);
        tmp = carFromWaybill.getCarsNumber("react-select-31--value-item");
        //Нажатие кнопки Выдачи ПЛ.
        //Выгрузка "Форма 2 (грузовое ТС)"
        presenceOfID.waitingForID("waybill-print-dropdown_save").click();

        driver.findElement(By.linkText("Форма №2 (грузовое ТС)")).click();
//Возможно сообщение: "Дата планируемого выезда должна быть больше фактической даты возвращения т.с. в предыдущем путевом листе."
        //Задание создано успешно.
        Thread.sleep(2000);
        return planDepartureDate;
    }


}