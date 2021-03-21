package utils;

        import java.io.File;
        import java.time.Duration;

        import org.openqa.selenium.*;
        import com.google.common.io.Files;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.FluentWait;

public class Helper {

    public static void snapShot(WebDriver webdriver, String fileWithPath) throws Exception {

        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        File DestFile = new File(fileWithPath);

        Files.copy(SrcFile, DestFile);

    }

    public static WebElement findElement(WebDriver driver, By by, int timeout) {
        if (timeout > 0) {
            return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(Exception.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        return driver.findElement(by);
    }
}