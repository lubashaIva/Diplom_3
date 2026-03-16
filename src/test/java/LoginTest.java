import com.assets.Browser;
import com.assets.Resources;
import com.github.javafaker.Faker;
import com.page.object.ForgotPasswordPage;
import com.page.object.LoginPage;
import com.page.object.MainPage;
import com.page.object.RegPage;
import com.api.UserApiSteps;
import com.api.UserCreateRequest;
import com.api.UserLoginRequest;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private final Faker faker = new Faker();
    private String email, password, name = "";

    @Before
    public void setUp() {
        Browser browser = new Browser();
        driver = browser.getWebDriver();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        name = faker.name().firstName();
        createUser();
    }

    private void createUser() {
        UserApiSteps userApiSteps = new UserApiSteps();
        UserCreateRequest userCreateAndEditRequest = new UserCreateRequest(email, password, name);
        userApiSteps.userCreate(userCreateAndEditRequest);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @After
    public void deleteUser() {
        UserApiSteps userApiSteps = new UserApiSteps();
        UserLoginRequest userLoginRequest= new UserLoginRequest(email, password);
        userApiSteps.userDeleteAfterLogin(userLoginRequest);
        clean();
    }

    private void clean() {
        email = "";
        password = "";
        name = "";
    }

    @Test
    @DisplayName("Вход на главной странице по кнопке Войти в аккаунт")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Войти в аккаунт на главной странице")
    public void loginMainPageLoginButton() {
        driver.get(Resources.MAIN_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.loginButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("После нажатия н а кнопку не произошел редирект на страницу Входа",loginPage.loginButtonIsDisplayed());
        loginPage.login(email, password);
        assertTrue("Авторизация не произошла", mainPage.createOrderButtonIsDisplayed());

    }

    @Test
    @DisplayName("Вход на главной странице по кнопке Личный кабинет")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Личный кабинет на главной странице")
    public void loginMainPageProfileButton() {
        driver.get(Resources.MAIN_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.profileButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("После нажатия на кнопку не произошел редирект на страницу Входа", loginPage.loginButtonIsDisplayed());
        loginPage.login(email, password);
        assertTrue("Авторизация не произошла", mainPage.createOrderButtonIsDisplayed());

    }

    @Test
    @DisplayName("Вход со страницы регистрации")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Войти на странице регистрации")
    public void loginRegisterPageLoginButton() {
        driver.get(Resources.REGISTER_URL);

        RegPage registerPage = new RegPage(driver);
        registerPage.loginButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("После нажатия на кнопку не произошел редирект на страницу Входа", loginPage.loginButtonIsDisplayed());
        loginPage.login(email, password);
        MainPage mainPage = new MainPage(driver);
        assertTrue("Авторизация не произошла", mainPage.createOrderButtonIsDisplayed());

    }

    @Test
    @DisplayName("Вход со страницы восстановления пароля")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Войти на странице восстановления пароля")
    public void loginForgotPasswordPageLoginButton() {
        driver.get(Resources.FORGOT_PASSWORD_URL);

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.loginButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("После нажатия на кнопку не произошел редирект на страницу Входа", loginPage.loginButtonIsDisplayed());
        loginPage.login(email, password);
        MainPage mainPage = new MainPage(driver);
        assertTrue("Авторизация не произошла", mainPage.createOrderButtonIsDisplayed());

    }

}