package testscooterpageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderFormPageObject {
    private WebDriver driver;

    public OrderFormPageObject(WebDriver driver){
        this.driver = driver;
    }

    //локатор кнопки заказа вверху
    private final By butUp = By.xpath(".//button[@class = 'Button_Button__ra12g']");
    //локатор кнопки заказа внизу
    private final By bottomBut = By.className("Button_Middle__1CSJM");
    //локатор для имени
    private final By firstName = By.xpath(".//div/div[@class = 'Input_InputContainer__3NykH']/input[@placeholder = '* Имя']");
    //локатор для фамилии
    private final By secondName = By.xpath(".//div/div[@class = 'Input_InputContainer__3NykH']/input[@placeholder = '* Фамилия']");
    //локатор для адреса
    private final By address = By.xpath(".//div/div[@class = 'Input_InputContainer__3NykH']/input[@placeholder = '* Адрес: куда привезти заказ']");
    //локатор для поля выбора метро
    private final By station = By.xpath(".//div/input[@class = 'select-search__input']");//для клика по полю
    //локатор для выбора станции в выпавшем списке с метро
    private final By inputStation = By.xpath(".//div[text() = 'Бульвар Рокоссовского']");
    //локатор для поля номера телефона
    private final By numberForCall = By.xpath(".//div/div[@class = 'Input_InputContainer__3NykH']/input[@placeholder = '* Телефон: на него позвонит курьер']");
    //локатор для кнопки Далее
    private final By buttonNext = By.className("Button_Middle__1CSJM");

    //метод для кнопок заказа - внизу и наверху
    public void clickButtonsOrder(By buttons){
        driver.findElement(buttons).click();
    }
    //ввели имя
    public void setUserFirstNameUserDataWindow(String userFirstName) {
        driver.findElement(firstName).sendKeys(userFirstName);
    }

    //ввели фамилию
    public void setSecondNameUserDataWindow(String userSecondName) {
        driver.findElement(secondName).sendKeys(userSecondName);
    }

    //ввели адрес
    public void setAddressUserDataWindow(String userAddress) {
        driver.findElement(address).sendKeys(userAddress);
    }

    //кликнули на поле Метро для выбора станции
    public void clickButtonStationUserDataWindow() {
        driver.findElement(station).click();
    }

    //ждем подгрузки списка после клика
    public void waitForLoadListOfMetroUserDataWindow() {
        new WebDriverWait(driver, 5).until(driver -> (driver.findElement(inputStation).getText() != null));
    }

    //выбор из выпавшего списка станции Динамо
    public void clickChoiceToStationUserDataWindow(){
        driver.findElement(inputStation).click();
    }

    //ввод номера телефона
    public void setNumberForCallUserDataWindow(String userNumberForCall) {
        driver.findElement(numberForCall).sendKeys(userNumberForCall);
    }

    //клик на кнопку Далее
    public void clickButtonNextUserDataWindow() {
        driver.findElement(buttonNext).click();
    }

    //собрали в один метод все предыдущие, чтобы не вызывать их по одному
    public void inputTextIntoOrderUserDataWindow(By buttonOrder, String userFirstName, String userSecondName, String userAddress, String userNumberForCall){
        clickButtonsOrder(buttonOrder);
        setUserFirstNameUserDataWindow(userFirstName);
        setSecondNameUserDataWindow(userSecondName);
        setAddressUserDataWindow(userAddress);
        clickButtonStationUserDataWindow();
        waitForLoadListOfMetroUserDataWindow();
        clickChoiceToStationUserDataWindow();
        setNumberForCallUserDataWindow(userNumberForCall);
        clickButtonNextUserDataWindow();
    }

    public By getButUp() {
        return butUp;
    }
    public By getBottomBut() {
        return bottomBut;
    }
}
