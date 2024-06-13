package testelements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testscooterpageobjects.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;
import static testscooterpageobjects.Urls.MAIN_URL;

@RunWith(Parameterized.class)
public class TestScooterBottonsAndOrderForm {

    //выбор браузера для теста
    Browsers browser = new Browsers();
    WebDriver driver = browser.webDriverFromChrome();

    //переменная для локаторов кнопок заказа наверху и внизу
    private final By buttonOrder;
    //переменная для имени в форме заказа
    private final String firstName;
    //переменная для фамилии в форме заказа
    private final String secondName;
    //переменная для адреса в форме заказа
    private final String address;
    //переменная для номера телефона в форме заказа
    private final String numberForCall;

    public TestScooterBottonsAndOrderForm(By buttonOrder, String firstName, String secondName, String address, String numberForCall){
        this.buttonOrder = buttonOrder;
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.numberForCall = numberForCall;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] setText() {
        WebDriver tempDriver = new Browsers().webDriverFromChrome();
        OrderFormPageObject orderForm = new OrderFormPageObject(tempDriver);

        // переменная для локатора кнопки заказа наверху
        Object buttonUp = orderForm.getButUp();
        //переменная для кнопки заказа внизу
        Object buttonDown = orderForm.getBottomBut();

        return new Object[][] {
                {buttonUp, "ПоЛиНа", "Ох", "Санкт-Петербург, улица Шоссейная, дом 234", "77777777777"},
                {buttonUp, "саша", "васильев", "село Северное>, улица Центральная, дом 30", "87078888888"},
                {buttonDown, "Анна", "Иванова", "Москва, улица Южная, дом 3", "87777777777"},
                {buttonDown, "АЛЕКС", "АЛЕКСЕЕВИЧ", "Уссурийск-на-Амуре, улица Де, дом 6", "79080008800"},
        };
    }

    @Test
    public void checkOrderScooter(){
        // перешли на страницу тестового приложения
        driver.get(MAIN_URL);

        //дали разрешение на куки
        CookiesWindowPageObject objCookiesAllow = new CookiesWindowPageObject(driver);
        objCookiesAllow.allowCookies();

        //клик на кнопку заказа и после ввод в форму заказа данные
        OrderFormPageObject formTextWindow = new OrderFormPageObject(driver);
        formTextWindow.inputTextIntoOrderUserDataWindow(buttonOrder, firstName, secondName, address, numberForCall);

        //заполнили детали заказа - время/цвет/дата
        RentWindowPageObject objRentWindow = new RentWindowPageObject(driver);
        objRentWindow.rentWindow();

        //клик на кнопку подтверждения в окне подтверждения информации - здесь баг, кнопка
        //некликабельна
        OrderWindowPageObject objConfirmationWindow = new OrderWindowPageObject(driver);
        objConfirmationWindow.setConfirmWindowOrder();

        WebDriverWait wait = new WebDriverWait(driver, 10); // ожидание до 10 секунд
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(objConfirmationWindow.orderConfirmed));
        assertTrue("Заказ успешно оформлен! ", confirmationMessage.isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}