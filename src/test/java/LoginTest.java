import Assets.Browser;
import Assets.Resources;
import PageObject.ForgotPasswordPage;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegPage;
import api.UserApiSteps;
import api.UserCreateRequest;
import api.UserLoginRequest;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;

    @Before
    public void createUser() {
        UserApiSteps userApiSteps = new UserApiSteps();
        UserCreateRequest userCreateAndEditRequest = new UserCreateRequest(Resources.email, Resources.valid_password, Resources.name);
        userApiSteps.userCreate(userCreateAndEditRequest);
    }


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
        UserApiSteps userApiSteps = new UserApiSteps();
        UserLoginRequest userLoginRequest= new UserLoginRequest(Resources.email, Resources.valid_password);
        userApiSteps.userDeleteAfterLogin(userLoginRequest);
    }

    @Test
    @DisplayName("Вход на главной странице по кнопке Войти в аккаунт")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Войти в аккаунт на главной странице")
    public void LoginMainPageLoginButton() {
        driver.get(Resources.mainURL);

        MainPage mainPage = new MainPage(driver);
        mainPage.loginButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("После нажатия н а кнопку не произошел редирект на страницу Входа",loginPage.loginButtonIsDisplayed());
        loginPage.login(Resources.email, Resources.valid_password);
        assertTrue("Авторизация не произошла", mainPage.createOrderButtonIsDisplayed());

    }

    @Test
    @DisplayName("Вход на главной странице по кнопке Личный кабинет")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Личный кабинет на главной странице")
    public void LoginMainPageProfileButton() {
        driver.get(Resources.mainURL);

        MainPage mainPage = new MainPage(driver);
        mainPage.profileButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("После нажатия на кнопку не произошел редирект на страницу Входа", loginPage.loginButtonIsDisplayed());
        loginPage.login(Resources.email, Resources.valid_password);
        assertTrue("Авторизация не произошла", mainPage.createOrderButtonIsDisplayed());

    }

    @Test
    @DisplayName("Вход со страницы регистрации")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Войти на странице регистрации")
    public void LoginRegisterPageLoginButton() {
        driver.get(Resources.registerURL);

        RegPage registerPage = new RegPage(driver);
        registerPage.loginButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("После нажатия на кнопку не произошел редирект на страницу Входа", loginPage.loginButtonIsDisplayed());
        loginPage.login(Resources.email, Resources.valid_password);
        MainPage mainPage = new MainPage(driver);
        assertTrue("Авторизация не произошла", mainPage.createOrderButtonIsDisplayed());

    }

    @Test
    @DisplayName("Вход со страницы восстановления пароля")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Войти на странице восстановления пароля")
    public void LoginForgotPasswordPageLoginButton() {
        driver.get(Resources.forgotPasswordURL);

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.loginButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("После нажатия на кнопку не произошел редирект на страницу Входа", loginPage.loginButtonIsDisplayed());
        loginPage.login(Resources.email, Resources.valid_password);
        MainPage mainPage = new MainPage(driver);
        assertTrue("Авторизация не произошла", mainPage.createOrderButtonIsDisplayed());

    }

}