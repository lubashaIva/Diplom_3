package com.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class LoginPage {

    // PageObject для страницы входа
    private final WebDriver driver;

    // Email
    private final By emailField = By.xpath(".//input[@type='text']");
    // Пароль
    private final By passwordField = By.xpath(".//input[@type='password']");
    //Кнопка Войти
    private final By loginButton = By.xpath(".//button[text()='Войти']");


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Вход в аккаунт")
    public void login(String email, String password) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Step("Проверка отображения кнопки Войти")
    public boolean loginButtonIsDisplayed() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        try {
            return driver.findElement(loginButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}