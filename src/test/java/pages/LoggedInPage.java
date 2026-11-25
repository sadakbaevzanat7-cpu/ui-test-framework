package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoggedInPage extends BasePage {
    private final By header = By.cssSelector(".post-title");
    private final By logout = By.linkText("Log out");

    public LoggedInPage(WebDriver driver) { super(driver); }

    @Step("Бет тақырыбын алу")
    public String getHeader() { return getText(header); }

    @Step("URL құрамын күту: {part}")
    public boolean urlContains(String part) {
        return wait.until(ExpectedConditions.urlContains(part));
    }

    @Step("Log out батырмасы көрінеді ме")
    public boolean isLogoutDisplayed() { return isDisplayed(logout); }

    @Step("Жүйеден шығу")
    public LoginPage logout() {
        click(logout);
        return new LoginPage(driver);
    }
}
