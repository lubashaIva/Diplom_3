package com.assets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class Browser {

    public WebDriver getWebDriver() {
        String browser = System.getProperty("browser", Resources.CHROME_BROWSER);
        if (!Objects.equals(browser.toLowerCase(), Resources.CHROME_BROWSER)
                && !Objects.equals(browser.toLowerCase(), Resources.YANDEX_BROWSER)) {
            throw new IllegalArgumentException("Не поддерживаемый браузер: " + browser);
        }
        System.setProperty("webdriver.chrome.driver", String.format(Resources.PATH_TO_DRIVER, browser.toLowerCase()));
        ChromeDriver driver = new ChromeDriver();
        // Разворачиваем браузер на весь экран
        driver.manage().window().maximize();
        return driver;
    }
}
