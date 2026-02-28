package com.example.steps;

import com.example.pages.RegisterPage;
import com.example.hooks.Hooks;
import com.example.context.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import org.openqa.selenium.WebDriver;
import org.junit.Assert;

public class RegisterSteps {

    WebDriver driver;
    RegisterPage registerPage;

    @Given("el usuario navega a la página de registro")
    public void navegarPaginaRegistro() {
        driver = Hooks.driver;
        registerPage = new RegisterPage(driver);
        registerPage.abrirModal();
    }

    @When("completa el formulario con datos válidos")
    public void completaFormulario() {

        String usuarioGenerado = "usuarioTest" + System.currentTimeMillis();
        String password = "password123";

        TestContext.setUsuario(usuarioGenerado);
        TestContext.setPassword(password);

        registerPage.completarFormulario(usuarioGenerado, password);
    }

    @And("presiona el botón registrar")
    public void presionaBoton() {
        registerPage.clickRegistrar();
    }

    @Then("el usuario se registra correctamente")
    public void validarRegistro() {
        String alert = registerPage.capturarAlerta();
        Assert.assertTrue(alert.contains("Sign up successful"));
    }

    // =============================
    // Escenario: Usuario duplicado
    // =============================

    @When("crea un nuevo usuario")
    public void creaNuevoUsuario() {

        String usuarioGenerado = "usuarioTest" + System.currentTimeMillis();
        String password = "password123";

        TestContext.setUsuario(usuarioGenerado);
        TestContext.setPassword(password);

        registerPage.completarFormulario(usuarioGenerado, password);
        registerPage.clickRegistrar();

        String alert = registerPage.capturarAlerta();
        Assert.assertTrue(alert.contains("Sign up successful"));
    }

    @And("intenta registrar el mismo usuario nuevamente")
    public void intentaRegistrarMismoUsuario() {

        driver.navigate().refresh();
        registerPage.abrirModal();

        registerPage.completarFormulario(
                TestContext.getUsuario(),
                TestContext.getPassword()
        );

        registerPage.clickRegistrar();
    }

    @Then("se muestra el mensaje de usuario existente")
    public void validarUsuarioExistente() {

        String alert = registerPage.capturarAlerta();
        Assert.assertTrue(alert.contains("This user already exist"));
    }
}
