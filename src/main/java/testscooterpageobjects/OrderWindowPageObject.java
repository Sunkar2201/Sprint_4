package testscooterpageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderWindowPageObject {
    private static WebDriver driver;

    public OrderWindowPageObject(WebDriver driver){
        this.driver = driver;
    }

    //локатор для поиска поля подтверждения заказа
    private final By confirmWindow = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    //локатор для кнопка Да
    private final By buttonYes = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    public final By orderConfirmed = By.xpath(".//div[text()='Заказ оформлен']");

    //проверка доступности окна подтверждения
    //клик по кнопке да
    public void setConfirmWindowOrder(){
        driver.findElement(confirmWindow).isEnabled();
        driver.findElement(buttonYes).click();
    }
}