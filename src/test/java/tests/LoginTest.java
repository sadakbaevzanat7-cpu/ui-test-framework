package tests;

import base.BaseTest;
import base.DriverFactory;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoggedInPage;
import pages.LoginPage;
import utils.CsvReader;

@Epic("Practice Test Automation")
@Feature("Test Login Page")
public class LoginTest extends BaseTest {

    @DataProvider(name = "invalidLogin")
    public Object[][] invalidLogin() {
        return CsvReader.read("testdata/invalid_login.csv");
    }

    @Test(description = "TC-01 Дұрыс деректермен кіру")
    @Story("Сәтті кіру")
    @Severity(SeverityLevel.BLOCKER)
    @Description("student / Password123 деректерімен кіргенде Logged In Successfully беті ашылуы тиіс")
    public void validLogin() {
        LoggedInPage page = new LoginPage(DriverFactory.getDriver()).open().loginAs("student", "Password123");
        Assert.assertTrue(page.urlContains("logged-in-successfully"));
        Assert.assertEquals(page.getHeader(), "Logged In Successfully");
        Assert.assertTrue(page.isLogoutDisplayed());
    }

    @Test(dataProvider = "invalidLogin", description = "TC-02 Қате деректермен кіру (CSV)")
    @Story("Қате кіру")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Қате, бос және шекаралық (256 таңба) деректерде қате хабарламасы көрсетілуі тиіс")
    public void invalidLoginShowsError(String user, String pass, String expectedError) {
        LoginPage page = new LoginPage(DriverFactory.getDriver()).open().loginExpectingError(user, pass);
        Assert.assertEquals(page.getErrorMessage(), expectedError);
        Assert.assertTrue(page.getCurrentUrl().contains("practice-test-login"));
    }

    @Test(description = "TC-03 Жүйеден шығу")
    @Story("Шығу")
    @Severity(SeverityLevel.NORMAL)
    @Description("Log out басқаннан кейін логин беті қайта ашылуы тиіс")
    public void logout() {
        LoginPage login = new LoginPage(DriverFactory.getDriver()).open()
                .loginAs("student", "Password123").logout();
        Assert.assertTrue(login.getCurrentUrl().contains("practice-test-login"));
    }
}
