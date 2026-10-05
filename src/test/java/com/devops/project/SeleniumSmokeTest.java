package com.devops.project;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Enable when the application is running and ChromeDriver is configured.")
class SeleniumSmokeTest {

    @Test
    void dashboardLoads() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080");
            assertTrue(driver.getTitle().contains("Devops"));
        } finally {
            driver.quit();
        }
    }
}
