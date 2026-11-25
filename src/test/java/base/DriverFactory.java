package base;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static void initDriver() {
        boolean headless = ConfigReader.headless();
        WebDriver driver;
        switch (ConfigReader.browser().toLowerCase()) {
            case "firefox" -> {
                FirefoxOptions o = new FirefoxOptions();
                if (headless) o.addArguments("-headless");
                driver = new FirefoxDriver(o);
            }
            case "chrome" -> {
                ChromeOptions o = new ChromeOptions();
                if (headless) o.addArguments("--headless=new");
                o.addArguments("--window-size=1920,1080", "--disable-notifications");
                o.addArguments("--blink-settings=imagesEnabled=false");
                o.addArguments("--disable-save-password-bubble");
                o.setExperimentalOption("prefs", java.util.Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "profile.password_manager_leak_detection", false));
                driver = new ChromeDriver(o);
            }
            default -> throw new IllegalArgumentException("Қолдау жоқ браузер: " + ConfigReader.browser());
        }
        driver.manage().window().maximize();   // implicit wait қолданылмайды
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() { return DRIVER.get(); }

    public static void quitDriver() {
        WebDriver d = DRIVER.get();
        if (d != null) {
            d.quit();
            DRIVER.remove();
        }
    }
}
