package tests;

import base.BaseTest;
import base.DriverFactory;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ExceptionsPage;

@Epic("Practice Test Automation")
@Feature("Test Exceptions")
public class ExceptionsTest extends BaseTest {

    @DataProvider(name = "rowTexts")
    public Object[][] rowTexts() {
        return new Object[][]{{"Sushi"}, {"12345"}, {"Ас үй"}};
    }

    @Test(description = "TC-04 Row 1 өрісі бастапқыда өшірулі")
    @Story("Өңдеу")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit басылмай тұрып Row 1 өрісі disabled болуы тиіс (ElementNotInteractable жағдайы)")
    public void row1DisabledByDefault() {
        ExceptionsPage page = new ExceptionsPage(DriverFactory.getDriver()).open();
        Assert.assertFalse(page.isRow1Enabled());
    }

    @Test(dataProvider = "rowTexts", description = "TC-05 Row 1 мәнін өзгертіп сақтау")
    @Story("Өңдеу")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Edit → мән енгізу → Save: мән сақталып, 'Row 1 was saved' хабарламасы шығуы тиіс")
    public void editAndSaveRow1(String text) {
        ExceptionsPage page = new ExceptionsPage(DriverFactory.getDriver()).open()
                .editRow1().setRow1Text(text).saveRow1();
        Assert.assertEquals(page.getConfirmation(), "Row 1 was saved");
        Assert.assertEquals(page.getRow1Value(), text);
    }

    @Test(description = "TC-06 Row 2 қосу")
    @Story("Жол қосу")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Add басылғанда Row 2 кідіріспен пайда болуы тиіс (NoSuchElement жағдайы)")
    public void addRow2() {
        ExceptionsPage page = new ExceptionsPage(DriverFactory.getDriver()).open().addRow2();
        Assert.assertEquals(page.getConfirmation(), "Row 2 was added");
    }

    @Test(description = "TC-07 Row 2 мәнін сақтау")
    @Story("Жол қосу")
    @Severity(SeverityLevel.NORMAL)
    @Description("Row 2 қосып, мән енгізіп Save басқанда 'Row 2 was saved' шығуы тиіс")
    public void addAndSaveRow2() {
        ExceptionsPage page = new ExceptionsPage(DriverFactory.getDriver()).open()
                .addRow2().setRow2Text("Burger").saveRow2();
        Assert.assertEquals(page.getConfirmation(), "Row 2 was saved");
    }
}
