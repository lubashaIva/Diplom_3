import Assets.Browser;
import Assets.Resources;
import PageObject.LoginPage;
import PageObject.RegPage;
import api.UserApiSteps;
import api.UserLoginRequest;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class RegTest {

    private WebDriver driver;
    private boolean skipUserDelete = false;

    @Before
    public void setUp() {
        Browser browser = new Browser();
        driver = browser.getWebDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @After
    public void deleteUser() {
        if (!skipUserDelete) {
            UserApiSteps userApiSteps = new UserApiSteps();
            UserLoginRequest userLoginRequest = new UserLoginRequest(Resources.email, Resources.valid_password);
            userApiSteps.userDeleteAfterLogin(userLoginRequest);
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка возможности регистрации пользователя с валидными данными")
    public void successfulRegistrationWithValidData() {
        skipUserDelete = false;
        driver.get(Resources.registerURL);

        RegPage registerPage = new RegPage(driver);
        registerPage.registration(Resources.name, Resources.email, Resources.valid_password);

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("После нажатия на кнопку не произошел редирект на страницу Входа", loginPage.loginButtonIsDisplayed());
    }

    @Test
    @DisplayName("Ошибка регистрации")
    @Description("Проверка ошибки при попытке регистрации пользователя с паролем менее 6 символов")
    public void failedRegistrationWithPasswordLessThen6Symbols() {
        skipUserDelete = true;
        driver.get(Resources.registerURL);

        RegPage registerPage = new RegPage(driver);
        registerPage.registration(Resources.name, Resources.email, Resources.wrong_password);

        assertTrue("Не появилось сообщение о некорректном пароле", registerPage.wrongPasswordTextIsDisplayed());

    }

}