import Assets.Browser;
import Assets.Resources;
import PageObject.MainPage;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class MainPageTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        Browser browser = new Browser();
        driver = browser.getWebDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    @Description("Проверка возможности перехода к разделу Начинки на главной странице")
    public void SwitchingToSectionTopping() {
        driver.get(Resources.mainURL);

        MainPage mainPage = new MainPage(driver);
        mainPage.sectionToppingClick();

        String expectedText = "Начинки";
        String actualText = mainPage.returnSelectedSection(expectedText);
        assertEquals("Не произошел переход к нужному разделу", expectedText, actualText);
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    @Description("Проверка возможности перехода к разделу Соусы на главной странице")
    public void SwitchingToSectionSauce() {
        driver.get(Resources.mainURL);

        MainPage mainPage = new MainPage(driver);
        mainPage.sectionToppingClick();
        mainPage.sectionSauceClick();

        String expectedText = "Соусы";
        String actualText = mainPage.returnSelectedSection(expectedText);
        assertEquals("Не произошел переход к разделу Соусы", expectedText, actualText);
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    @Description("Проверка возможности перехода к разделу Булки на главной странице")
    public void SwitchingToSectionBun() {
        driver.get(Resources.mainURL);

        MainPage mainPage = new MainPage(driver);
        mainPage.sectionToppingClick();
        mainPage.sectionBunClick();

        String expectedText = "Булки";
        String actualText = mainPage.returnSelectedSection(expectedText);
        assertEquals("Не произошел переход к разделу Булки", expectedText, actualText);
    }

}
