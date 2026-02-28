package com.example.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // dice dónde están los .feature,
        glue = "com.example", 
       // features = "classpath:features",
       // glue = {"com.example.steps", "com.example.hooks"},
        //tags = "@registro",
        plugin = {
                //"pretty", "html:target/cucumber-report.html", // Genera evidencias automáticas
                "pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
                //"json:target/cucumber-report.json"
        }
       // monochrome = true // Salida más limpia en consola
)
public class TestRunner {
}

