package com.qa.todo_lists;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Helper.findElement;
import static utils.Helper.snapShot;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AcceptanceTests {
    private static final String webpageURL = "http://localhost:63342/todo_lists/src/frontend/TodoListFrontend/HTML/Index.html";
    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeAll
    public static void setup() {
        extent = new ExtentReports("src/test/resources/reports/report1.html", true);
        test = extent.startTest("Chrome tests");

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        Map<String, Object> prefs = new HashMap<>();
        ChromeOptions cOptions = new ChromeOptions();

        // Settings
        prefs.put("profile.default_content_setting_values.cookies", 2);
        prefs.put("network.cookie.cookieBehavior", 2);
        prefs.put("profile.block_third_party_cookies", true);
        cOptions.setHeadless(false);
        cOptions.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(cOptions);
        driver.manage().window().setSize(new Dimension(1366, 768));
    }

    @Test
    public void pageLoadsTest() throws Exception {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get(webpageURL);

        if (driver.getTitle().equals("TODO Lists")) {
            test.log(LogStatus.PASS, "Success, pageLoadsTest");
        } else {
            snapShot(driver, "src/test/resources/reports/PageLoadFail.png");
            test.log(LogStatus.FAIL, test.addScreenCapture("src/test/resources/reports/PageLoadFail.png") + "Test Failed, pageLoadsTest");
        }
        assertEquals(driver.getTitle(), "TODO Lists");
    }

    @Test
    public void createListTest() throws Exception {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get(webpageURL);

        WebElement newList = findElement(driver, By.id("create-list-button"), 5);

        newList.click();

        WebElement nameInput = findElement(driver, By.id("List-name"), 5);
        WebElement submitButton = findElement(driver, By.id("create-list-submit"), 5);

        nameInput.sendKeys("test list name");
        submitButton.click();

        WebElement newListName = findElement(driver, By.id("list-name2"), 5);

        boolean success = newListName.getText().equals("test list name");

        if (success) {
            test.log(LogStatus.PASS, "Success, createListTest");
        } else {
            snapShot(driver, "src/test/resources/reports/createListTest.png");
            test.log(LogStatus.FAIL, test.addScreenCapture("src/test/resources/reports/createListTest.png") + "Test Failed, createListTest");
        }
        assertTrue(success);

    }

    @Test
    public void deleteListTest() throws Exception {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get(webpageURL);

        WebElement deleteButton = findElement(driver, By.id("delete-list1"), 5);
        deleteButton.click();

        //wait to see if list is deleted
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("delete-list1")));

        //check that list is gone
        boolean success = driver.findElements(By.id("delete-list1")).size() == 0;

        if (success) {
            test.log(LogStatus.PASS, "Success, createListTest");
        } else {
            snapShot(driver, "src/test/resources/reports/deleteListTest.png");
            test.log(LogStatus.FAIL, test.addScreenCapture("src/test/resources/reports/deleteListTest.png") + "Test Failed, deleteListTest");
        }
        assertTrue(success);
    }

    @Test
    public void deleteTaskTest() throws Exception {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get(webpageURL);

        WebElement expandTaskButton = findElement(driver, By.id("task-1"), 5).findElement(By.cssSelector("button"));
        expandTaskButton.click();
        WebElement deleteButton = findElement(driver, By.id("delete-task1"), 5);
        deleteButton.click();

        //wait to see if list is deleted
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("delete-task1")));

        //check that task is gone
        boolean success = driver.findElements(By.id("task-1")).size() == 0;

        if (success) {
            test.log(LogStatus.PASS, "Success, deleteTaskTest");
        } else {
            snapShot(driver, "src/test/resources/reports/deleteTaskTest.png");
            test.log(LogStatus.FAIL, test.addScreenCapture("src/test/resources/reports/deleteTaskTest.png") + "Test Failed, deleteTaskTest");
        }
        assertTrue(success);
    }

    @Test
    public void createTaskTest() throws Exception {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get(webpageURL);

        WebElement newTask = findElement(driver, By.id("add-task1"), 5);

        newTask.click();

        WebElement nameInput = findElement(driver, By.id("task-name"), 5);
        WebElement dateInput = findElement(driver, By.id("Due-Date"), 5);
        WebElement submitButton = findElement(driver, By.id("create-task-submit"), 5);

        nameInput.sendKeys("test task name");
        dateInput.sendKeys("1122020");
        submitButton.click();

        WebElement newListName = findElement(driver, By.id("task-2"), 5);

        boolean success = newListName.getText().contains("test task name") && newListName.getText().contains("2020");

        if (success) {
            test.log(LogStatus.PASS, "Success, createListTest");
        } else {
            snapShot(driver, "src/test/resources/reports/createListTest.png");
            test.log(LogStatus.FAIL, test.addScreenCapture("src/test/resources/reports/createListTest.png") + "Test Failed, createListTest");
        }
        assertTrue(success);

    }

    @Test
    public void updateListAndTasksTest() throws Exception {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get(webpageURL);

        WebElement editButton = findElement(driver, By.id("edit-list1"), 5);
        editButton.click();

        WebElement nameInput = findElement(driver, By.id("update-list-name"), 5);
        WebElement submitButton = findElement(driver, By.id("update-list-submit"), 5);

        nameInput.sendKeys("test list name");
        submitButton.click();

        WebElement expandTaskButton = findElement(driver, By.id("task-1"), 5).findElement(By.cssSelector("button"));
        expandTaskButton.click();
        editButton = findElement(driver, By.id("edit-task1"), 5);
        editButton.click();

        nameInput = findElement(driver, By.id("update-task-name"), 5);
        WebElement dateInput = findElement(driver, By.id("update-Due-Date"), 5);
        submitButton = findElement(driver, By.id("update-task-submit"), 5);

        nameInput.sendKeys("test task name");
        dateInput.sendKeys("1122020");
        submitButton.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeContains(editButton, "data-bs-taskname", "test task name"));

        var allTaskLists = findElement(driver, By.id("listContainer"), 5);

        boolean success = allTaskLists.getText().contains("test list name") && allTaskLists.getText().contains("test task name") && allTaskLists.getText().contains("2020");

        if (success) {
            test.log(LogStatus.PASS, "Success, updateListAndTasksTest");
        } else {
            snapShot(driver, "src/test/resources/reports/updateListAndTasksTest.png");
            test.log(LogStatus.FAIL, test.addScreenCapture("src/test/resources/reports/updateListAndTasksTest.png") + "Test Failed, updateListAndTasksTest");
        }
        assertTrue(success);
    }

    @Test
    public void toggleTaskDone() {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get(webpageURL);

        WebElement expandTaskButton = findElement(driver, By.id("task-1"), 5).findElement(By.cssSelector("button"));
        expandTaskButton.click();
        WebElement toggleButton = findElement(driver, By.id("toggle1"), 5);
        toggleButton.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeContains(By.id("task-1"), "data-isdone", "true"));

        toggleButton.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeContains(By.id("task-1"), "data-isdone", "false"));

        test.log(LogStatus.PASS, "Success, updateListAndTasksTest");
    }

    @AfterAll
    public static void teardown() {
        driver.close();
        driver.quit();
        extent.endTest(test);
        extent.flush();
        extent.close();
    }

}
