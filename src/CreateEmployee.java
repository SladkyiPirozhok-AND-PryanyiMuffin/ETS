import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class CreateEmployee {

    private ChromeDriver driver;

    public CreateEmployee(ChromeDriver driver) {
        this.driver = driver;
    }

    public String createDriver() throws Exception {
        Common utils = new Common(driver);
        Thread.sleep(2000);
        utils.waitingForID("show-nsi").click();
        utils.waitingForID("link-employees").click();
        SoftAssert softAssert = new SoftAssert();

        utils.findElementByIdAndClick("open-create-form");
        //Задизейблена ли кнопка "Сохранить"
        if (driver.findElement(By.id("save_employee")).isEnabled()) {
            //Кнопка "Сохранить" доступна
            System.out.println("Кнопка \"Сохранить\" доступна при незаполненных обязательных полей");
        } else {
            //Кнопка "Сохранить" недоступна
            System.out.println("Кнопка \"Сохранить\" недоступна пока не заполнены необходимые поля");
        }
        //Получение всех проверок на незаполненной форме.

        List<String> errorTextList = utils.getAllErrorsTextListed();
        utils.validateErrorText(errorTextList, "Поле \"Фамилия\" должно быть заполнено");
        utils.validateErrorText(errorTextList, "Поле \"Имя\" должно быть заполнено");
        System.out.println(errorTextList);
        //Фамилия
        Faker somePerson = new Faker();
        String lastName = somePerson.name().lastName();
        utils.waitingForID("last_name").sendKeys(lastName + "ов");
        utils.hardAssertEquals("last_name", lastName + "ов");
        String employeeSurname = driver.findElement(By.id("last_name")).getAttribute("value");
        //Табельный номер
        String personnelNumber = somePerson.number().numberBetween(10000000, 90000000) + somePerson.pokemon().name();
        utils.waitingForID("personnel_number").sendKeys(personnelNumber);
        utils.softAssert("personnel_number", personnelNumber);
        //Имя
        String name = somePerson.funnyName().name();
        utils.waitingForID("first_name").sendKeys(name + "чка");
        utils.hardAssertEquals("first_name", name + "чка");
        utils.softAssert("first_name", name + "чка");
        //Специальное удостоверение
        String specialLicense = somePerson.number().numberBetween(100000000, 900000000) + somePerson.currency().code();
        utils.waitingForID("special_license").sendKeys(specialLicense);
        utils.softAssert("special_license", specialLicense);
        //Отчество
        String middleName = somePerson.superhero().power();
        utils.waitingForID("middle_name").sendKeys(middleName + "вич");
        utils.softAssert("middle_name", middleName + "вич");
        //Срок действия специального удостоверения
        utils.waitingForID("special_license_date_end_input").sendKeys("28.09.2020");
        utils.softAssert("special_license_date_end_input", "28.09.2020");
        //Дата рождения
        utils.waitingForID("birthday_input").sendKeys("28.09.1963");
        utils.softAssert("birthday_input", "28.09.1963");
        //Водительское удостоверение
        String driversLicenseNumber = somePerson.idNumber().validSvSeSsn();
        utils.waitingForID("drivers_license").sendKeys(driversLicenseNumber + " - МОГУ ВОДИТЬ");
        utils.softAssert("drivers_license", driversLicenseNumber + " - МОГУ ВОДИТЬ");
        //Телефон
        String EmployeeMobila = somePerson.phoneNumber().cellPhone();
        utils.waitingForID("phone").sendKeys(EmployeeMobila);
        utils.softAssert("phone", EmployeeMobila);
        //Срок действия водительского удостоверения
        utils.waitingForID("drivers_license_date_end_input").sendKeys("28.09.2020");
        utils.softAssert("drivers_license_date_end_input", "28.09.2020");
        //Должность
        //nameModal: "employee", nameField: "position_id" value="15"
        utils.clickAndChoose("employee-position_id", "15");
        utils.softAssertGetText("employee-position_id-value", "водитель");
        System.out.println("после водитеоя " + errorTextList);
        //Основное ТС
        utils.clickAndChoose("employee-prefer_car", "19707");//0302НВ77/ 4ПТБ000106/ Фронтальный погрузчик/ АМКОДОР 332С4 (-/-/60)/ Амкодор - 332С4
        utils.softAssertGetText("employee-prefer_car-value", "0302НВ77/ 4ПТБ000106/ Фронтальный погрузчик/ АМКОДОР 332С4 (-/-/60)/ Амкодор - 332С4");
        //Состояние
        utils.clickAndChoose("employee-active", "1");
        utils.softAssertGetText("employee-active-value", "Работает");
        //Вторичное ТС
        utils.clickAndChoose("employee-secondary_car", "19713");//0302НВ77/ 4ПТБ000106/ Фронтальный погрузчик/ АМКОДОР 332С4 (-/-/60)/ Амкодор - 332С4
        utils.softAssertGetText("employee-prefer_car-value", "0302НВ77/ 4ПТБ000106/ Фронтальный погрузчик/ АМКОДОР 332С4 (-/-/60)/ Амкодор - 332С4");
        //utils.softAssertGetText("employee-secondary_car-value", "0305НВ77/ 4ПТБ000111/ Фронтальный погрузчик/ АМКОДОР 332С4 (-/-/60)/ Амкодор 332С4");
        //Подразделение
        utils.clickAndChoose("employee-company_structure_id", "38");//ДЭУ.ДТ
        utils.softAssertGetText("employee-company_structure_id-value", "ДЭУ.ДТ");
        //Медицинская справка №
        String medicalCertificateNumber = somePerson.number().numberBetween(100000000, 900000000) + somePerson.currency().code();
        utils.waitingForID("medical_certificate").sendKeys(medicalCertificateNumber);
        utils.softAssert("medical_certificate", medicalCertificateNumber);
        //Общее - узнать значение списка "Общее" = "Да".
        //utils.waitingForID("employee-is_common-value").click();
        //Срок действия медицинской справки
        utils.waitingForID("medical_certificate_date_input").sendKeys("28.09.2020");
        utils.softAssert("medical_certificate_date_input", "28.09.2020");
        //Медицинские справки
        utils.uploadFile("C:\\Users\\User\\Desktop\\тестовый.docx", "button-medical_certificate_files");
        utils.softAssertGetText("employee-medical_certificate_files-list", "тестовый.docx\n" +
                "×");
        //Водительские удостоверения
        utils.uploadFile("C:\\Users\\User\\Desktop\\тестовый.xlsx", "button-driver_license_files");
        utils.softAssertGetText("employee-driver_license_files-list", "тестовый.xlsx\n" +
                "×");
        System.out.println("После ввода");
        errorTextList = utils.getAllErrorsTextListed();

        for (String err : errorTextList)
            System.out.println("После заполнения полей, сохранилась ошибка!!!! - " + err);
        //Кнопка "Сохранить"
        utils.waitingForID("save_employee").click();
        softAssert.assertAll();

        return employeeSurname;
    }

}