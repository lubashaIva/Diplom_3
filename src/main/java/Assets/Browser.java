package Assets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class Browser {

    public WebDriver getWebDriver() {
        String browser = System.getProperty("browser", Resources.chromeBrowser);
        if (!Objects.equals(browser.toLowerCase(), Resources.chromeBrowser)
                && !Objects.equals(browser.toLowerCase(), Resources.yandexBrowser)) {
            throw new IllegalArgumentException("Не поддерживаемый браузер: " + browser);
        }
        System.setProperty("webdriver.chrome.driver", String.format(Resources.pathToDriver, browser.toLowerCase()));
        ChromeDriver driver = new ChromeDriver();
        // Разворачиваем браузер на весь экран
        driver.manage().window().maximize();
        return driver;
    }
}
