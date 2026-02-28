package com.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class RegisterPage {

    WebDriver driver;
    WebDriverWait wait;

    By botonSignUp = By.id("signin2");
    By modal = By.id("signInModal");
    By usernameInput = By.id("sign-username");
    By passwordInput = By.id("sign-password");
    By botonRegistrar = By.xpath("//button[text()='Sign up']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Abre el modal de registro y espera que los inputs estén listos
    public void abrirModal() {
        wait.until(ExpectedConditions.elementToBeClickable(botonSignUp)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(modal));
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
    }

    // Completa el formulario
    public void completarFormulario(String usuario, String password) {
        driver.findElement(usernameInput).clear();
        driver.findElement(passwordInput).clear();
        driver.findElement(usernameInput).sendKeys(usuario);
        driver.findElement(passwordInput).sendKeys(password);
    }

    // Click en "Sign up"
    public void clickRegistrar() {
        wait.until(ExpectedConditions.elementToBeClickable(botonRegistrar)).click();
    }

    // Captura la alerta y espera que el modal cierre
    public String capturarAlerta() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String texto = alert.getText().trim();
        alert.accept();

        // Espera que el modal desaparezca
        esperarCierreModal();

        return texto;
    }

    // Espera que el modal se cierre después del alert
    private void esperarCierreModal() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(modal));
        } catch (TimeoutException e) {
            System.out.println("El modal no se ocultó inmediatamente, continuamos...");
        }
    }
}
