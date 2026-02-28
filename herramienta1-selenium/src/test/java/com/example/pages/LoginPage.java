package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


public class LoginPage {

    WebDriver driver;

    // Locators
    By btnLoginMenu = By.id("login2");
    By inputUsername = By.id("loginusername");
    By inputPassword = By.id("loginpassword");
    By btnLogin = By.xpath("//button[text()='Log in']");
    By nombreUsuario = By.id("nameofuser");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void abrirModal() {

        driver.findElement(By.id("login2")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
    }

    public void completarLogin(String usuario, String password) {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement userField = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("loginusername"))
    );

    WebElement passField = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("loginpassword"))
    );

    userField.clear();
    userField.sendKeys(usuario);

    passField.clear();
    passField.sendKeys(password);
}


    public void clickLogin() {
        driver.findElement(btnLogin).click();
    }

    public String obtenerUsuarioLogueado() {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement usuarioLogueado = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser"))
    );

    return usuarioLogueado.getText();
}

}
