import com.assets.Browser;
import com.assets.Resources;
import com.github.javafaker.Faker;
import com.page.object.LoginPage;
import com.page.object.RegPage;
import com.api.UserApiSteps;
import com.api.UserLoginRequest;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class RegTest {

    private WebDriver driver;
    private final Faker faker = new Faker();
    private String email, password, wrongPassword, name = "";
    
    @Before
    public void setUp() {
        Browser browser = new Browser();
        driver = browser.getWebDriver();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        wrongPassword = faker.internet().password(3, 5);
        name = faker.name().firstName();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @After
    public void deleteUser() {
        UserApiSteps userApiSteps = new UserApiSteps();
        UserLoginRequest userLoginRequest = new UserLoginRequest(email, password);
        userApiSteps.userDeleteAfterLogin(userLoginRequest);
        clean();
    }

    private void clean() {
        email = "";
        password = "";
        wrongPassword = "";
        name = "";
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка возможности регистрации пользователя с валидными данными")
    public void successfulRegistrationWithValidData() {
        driver.get(Resources.REGISTER_URL);

        RegPage registerPage = new RegPage(driver);
        registerPage.registration(name, email, password);

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("После нажатия на кнопку не произошел редирект на страницу Входа", loginPage.loginButtonIsDisplayed());
    }

    @Test
    @DisplayName("Ошибка регистрации")
    @Description("Проверка ошибки при попытке регистрации пользователя с паролем менее 6 символов")
    public void failedRegistrationWithPasswordLessThen6Symbols() {
        driver.get(Resources.REGISTER_URL);

        RegPage registerPage = new RegPage(driver);
        registerPage.registration(name, email, wrongPassword);

        assertTrue("Не появилось сообщение о некорректном пароле", registerPage.wrongPasswordTextIsDisplayed());

    }

}