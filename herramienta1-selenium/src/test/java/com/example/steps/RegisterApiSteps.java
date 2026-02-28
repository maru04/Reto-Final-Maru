package com.example.steps;

import org.junit.Assert;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import java.time.Duration;

import io.qameta.allure.Allure;

public class RegisterApiSteps {

    private HttpResponse<String> lastResponse;

    public HttpResponse<String> getLastResponse() {
        return lastResponse;
    }

    public String getLastResponseBody() {
        return lastResponse != null ? lastResponse.body() : "";
    }

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    @When("registro un nuevo usuario vía API con username {string} y password {string}")
    public void registroUsuarioApi(String username, String password) throws Exception {

        String payload = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.demoblaze.com/signup"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        lastResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Logs en cobsola
        System.out.println("[API][REGISTER] Status: " + lastResponse.statusCode());
        System.out.println("[API][REGISTER] Body: " + lastResponse.body());

        //  Allure
        Allure.addAttachment(
                "API Response",
                "application/json",
                lastResponse.body(),
                ".json"
        );
    }

    @Then("la respuesta de registro debe ser exitosa")
    public void validarRegistroExitoso() {
        Assert.assertTrue("El registro falló", lastResponse.statusCode() == 200
                || lastResponse.body().contains("Sign up successful"));
    }

    @Then("la respuesta de registro debe indicar usuario existente")
    public void validarRegistroFallidoUsuarioExistente() {
        Assert.assertTrue("No se detectó usuario existente", lastResponse.body().contains("This user already exist"));
    }
}