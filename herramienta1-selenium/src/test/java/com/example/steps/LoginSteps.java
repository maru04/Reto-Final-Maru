package com.example.steps;

import com.example.pages.LoginPage;
import com.example.hooks.Hooks;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.WebDriver;
import org.junit.Assert;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    @Given("el usuario navega a la página principal")
    public void navegarPaginaPrincipal() {

        driver = Hooks.driver;
        loginPage = new LoginPage(driver);
    }

    @When("inicia sesión con usuario {string} y password {string}")
    public void iniciarSesionConCredenciales(String usuario, String password) {

        loginPage.abrirModal();
        loginPage.completarLogin(usuario, password);
        loginPage.clickLogin();
    }

    @Then("el nombre del usuario aparece en la barra superior")
    public void validarLoginCorrecto() {

        String mensaje = loginPage.obtenerUsuarioLogueado();

        Assert.assertTrue(
                mensaje.contains("UsuarioExiste")
        );
    }
}
