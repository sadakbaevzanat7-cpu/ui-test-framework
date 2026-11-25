package pages;

import config.ConfigReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ExceptionsPage extends BasePage {
    private final By row1Input  = By.cssSelector("#row1 input.input-field");
    private final By row1Edit   = By.cssSelector("#row1 button[name='Edit']");
    private final By row1Save   = By.cssSelector("#row1 button[name='Save']");
    private final By addBtn     = By.id("add_btn");
    private final By row2Input  = By.cssSelector("#row2 input.input-field");
    private final By row2Save   = By.cssSelector("#row2 button[name='Save']");
    private final By confirm    = By.id("confirmation");

    public ExceptionsPage(WebDriver driver) { super(driver); }

    @Step("Test Exceptions бетін ашу")
    public ExceptionsPage open() {
        for (int attempt = 0; attempt < 3; attempt++) {
            driver.get(ConfigReader.baseUrl() + "/practice-test-exceptions/");
            if (!driver.getTitle().contains("429")) break;
            try { Thread.sleep(15000); } catch (InterruptedException ignored) { }
        }
        waitUntilVisible(row1Input);
        return this;
    }

    @Step("Row 1 өрісі өңдеуге қолжетімді ме")
    public boolean isRow1Enabled() { return waitUntilVisible(row1Input).isEnabled(); }

    @Step("Row 1: Edit басу")
    public ExceptionsPage editRow1() {
        click(row1Edit);
        wait.until(ExpectedConditions.elementToBeClickable(row1Input));
        return this;
    }

    @Step("Row 1 мәнін енгізу: {text}")
    public ExceptionsPage setRow1Text(String text) {
        type(row1Input, text);
        return this;
    }

    @Step("Row 1: Save басу")
    public ExceptionsPage saveRow1() {
        click(row1Save);
        return this;
    }

    @Step("Row 1 мәнін алу")
    public String getRow1Value() { return waitUntilVisible(row1Input).getAttribute("value"); }

    @Step("Add басу (Row 2 пайда болады)")
    public ExceptionsPage addRow2() {
        click(addBtn);
        waitUntilVisible(row2Input);
        return this;
    }

    @Step("Row 2 мәнін енгізу: {text}")
    public ExceptionsPage setRow2Text(String text) {
        type(row2Input, text);
        return this;
    }

    @Step("Row 2: Save басу")
    public ExceptionsPage saveRow2() {
        click(row2Save);
        return this;
    }

    @Step("Растау хабарламасын алу")
    public String getConfirmation() { return getText(confirm); }
}
