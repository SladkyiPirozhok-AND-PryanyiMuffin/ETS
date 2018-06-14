import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by User on 14.05.2018.
 */
public class Delete {
    private ChromeDriver driver;
    public void DeleteWaybill (ChromeDriver driver) throws Exception {



        Thread.sleep(11000);
//Хватает первый элемент
        driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/div[1]/div[1]/div[2]/div/div/div/div/table/tbody[1]/tr/td[1]/div/input")).click();

        driver.findElement(By.id("remove-element")).click();
        driver.findElement(By.xpath("(//button[@type='button'])[19]")).click();
    }}