package com.page.object;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class ProfilePage {

    // PageObject для страницы профиля
    private final WebDriver driver;

    // Кнопка Конструктор
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    // Логотип
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a[@href='/']");
    // Кнопка Выйти
    private final By exitButton = By.xpath(".//button[text()='Выход']");


    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Нажатие на кнопку Конструктор")
    public void constructorButtonClick () {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(constructorButton));
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие на Логотип")
    public void logoClick () {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(logo));
        driver.findElement(logo).click();
    }

    @Step("Нажатие на кнопку Выход после ожидания ее появления")
    public void exitButtonClick () {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        driver.findElement(exitButton).click();
    }

    @Step("Проверка отображения кнопки Выход")
    public boolean exitButtonIsDisplayed() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        try {
            return driver.findElement(exitButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
