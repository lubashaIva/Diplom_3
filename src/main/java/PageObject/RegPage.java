package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class RegPage {

    // PageObject для страницы регистрации
    private final WebDriver driver;

    // Имя
    private final By nameField = By.xpath("(//*[contains(@class, 'input pr-6 pl-6')]/input)[1]");
    // Email
    private final By emailField = By.xpath("(//*[contains(@class, 'input pr-6 pl-6')]/input)[2]");
    // Пароль
    private final By passwordField = By.xpath(".//input[@type='password']");
    // Кнопка Зарегистрироваться
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    // Ошибка Некорректный пароль
    private final By wrongPasswordText = By.xpath(".//p[text()='Некорректный пароль']");
    //Кнопка Войти
    private final By loginButton = By.xpath(".//a[@href='/login']");

    public RegPage(WebDriver driver){
        this.driver = driver;
    }

    // Регистрация пользователя
    @Step
    public void registration(String name, String email, String password) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(registerButton));
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registerButton).click();
    }

    // Проверка отображения ошибки о неправильном пароле
    @Step
    public boolean wrongPasswordTextIsDisplayed() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(wrongPasswordText));
        try {
            return driver.findElement(wrongPasswordText).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Нажатие на кнопку Войти
    @Step
    public void loginButtonClick () {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
    }
}
