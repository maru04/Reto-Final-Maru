package com.example.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

public class Hooks {

    public static WebDriver driver;

    private static final Logger logger =
            Logger.getLogger(Hooks.class.getName());

    private static final String executionFolder =
            "evidencia_selenium/" +
            LocalDateTime.now().toString().replace(":", "-");

    // =========================
    // HOOK UI
    // =========================
    @Before("@ui")
    public void setUpUI() {

        logger.info("Iniciando navegador Chrome...");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com");

        logger.info("Navegador iniciado correctamente.");
    }

    // =========================
    // SCREENSHOT EN CADA STEP
    // =========================
    @AfterStep
    public void afterStep(Scenario scenario) throws Exception {

        String scenarioName =
                scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");

        File scenarioDir =
                new File(executionFolder + "/" + scenarioName);

        if (!scenarioDir.exists()) scenarioDir.mkdirs();

        // Screenshot solo si es UI
        if (driver != null) {

            byte[] screenshot =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.BYTES);
            String fileName =
                    "step_" + System.currentTimeMillis() + ".png";

            Allure.addAttachment(
                fileName,
                new ByteArrayInputStream(screenshot)
            );

            File screenshotFile =
                    new File(scenarioDir, fileName);

            Files.write(screenshotFile.toPath(), screenshot);

            //scenario.attach(screenshot, "image/png", fileName);

            logger.info("Screenshot guardado: " + fileName);
        }
    } 

    // =========================
    // CIERRE
    // =========================
    @After("@ui")
    public void tearDownUI() {

        if (driver != null) {

            logger.info("Cerrando navegador...");
            driver.quit();
            driver = null;
        }
    }
}