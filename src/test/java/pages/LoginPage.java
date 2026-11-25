package pages;

import config.ConfigReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By submit   = By.id("submit");
    private final By error    = By.id("error");

    public LoginPage(WebDriver driver) { super(driver); }

    @Step("Test Login Page бетін ашу")
    public LoginPage open() {
        for (int attempt = 0; attempt < 3; attempt++) {
            driver.get(ConfigReader.baseUrl() + "/practice-test-login/");
            if (!driver.getTitle().contains("429")) break;
            try { Thread.sleep(15000); } catch (InterruptedException ignored) { }
        }
        waitUntilVisible(username);
        return this;
    }
    @Step("Сәтті кіру: {user}")
    public LoggedInPage loginAs(String user, String pass) {
        fillAndSubmit(user, pass);
        return new LoggedInPage(driver);
    }

    @Step("Қате деректермен кіру әрекеті: {user}")
    public LoginPage loginExpectingError(String user, String pass) {
        fillAndSubmit(user, pass);
        return this;
    }

    private void fillAndSubmit(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(submit);
    }

    @Step("Қате хабарламасын алу")
    public String getErrorMessage() { return getText(error); }

    @Step("Ағымдағы URL")
    public String getCurrentUrl() { return driver.getCurrentUrl(); }
}
