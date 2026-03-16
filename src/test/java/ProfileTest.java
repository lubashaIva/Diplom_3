import com.assets.Browser;
import com.assets.Resources;
import com.github.javafaker.Faker;
import com.page.object.LoginPage;
import com.page.object.MainPage;
import com.page.object.ProfilePage;
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

public class ProfileTest {

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
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка возможности входа в личный кабинет после нажатия на кнопку Личный кабинет на главной странице")
    public void switchingToProfileFromMain() {
        driver.get(Resources.LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        MainPage mainPage = new MainPage(driver);
        mainPage.profileButtonClick();
        ProfilePage profilePage = new ProfilePage(driver);
        assertTrue("После нажатия на кнопку не произошел редирект в личный кабинет", profilePage.exitButtonIsDisplayed());

    }

    @Test
    @DisplayName("Переход в конструктор через кнопку Конструктор")
    @Description("Проверка возможности перехода к конструктору после нажатия на кнопку Конструктор в профиле пользователя")
    public void switchingToConstructorAfterConstructorButtonClick() {
        driver.get(Resources.LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        MainPage mainPage = new MainPage(driver);
        mainPage.profileButtonClick();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.constructorButtonClick();
        assertTrue("После нажатия на кнопку не произошел редирект на главную страницу", mainPage.profileButtonIsDisplayed());

    }

    @Test
    @DisplayName("Переход в конструктор через Логотип")
    @Description("Проверка возможности перехода к конструктору после нажатия на Логотип в профиле пользователя")
    public void switchingToConstructorAfterLogoClick() {
        driver.get(Resources.LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        MainPage mainPage = new MainPage(driver);
        mainPage.profileButtonClick();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.logoClick();
        assertTrue("После нажатия на кнопку не произошел редирект на главную страницу", mainPage.profileButtonIsDisplayed());

    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка возможности выйти из аккаунта после нажатия на кнопку Выход в профиле пользователя")
    public void logoutAfterExitButtonClick() {
        driver.get(Resources.LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        MainPage mainPage = new MainPage(driver);
        mainPage.profileButtonClick();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.exitButtonClick();
        assertTrue("Выход из профиля не произошел", loginPage.loginButtonIsDisplayed());

    }

}