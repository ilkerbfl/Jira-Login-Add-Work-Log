package com.ilker.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;

/**
 * Created by İlker ÇATAK on 17.07.2018.
 */
public class AddWorkLog {
    private static WebDriver driver = new ChromeDriver();
    private static final String LOGIN_FORM_URL = "http://192.168.10.110:8080/secure/Dashboard.jspa";
    private static final String ISSUE_PAGE_URL = "http://192.168.10.110:8080/projects/S4J/issues/S4J-85?filter=allopenissues";
    private static final String WORK_HOUR_TEXTBOX_ID = "time-popup";
    private static final String WORK_HOUR = "8h";
    private static final String WORK_TYPE_DROPDOWN_ID = "popup_ÇalışmaTipi_";
    private static final String WORK_TYPE_VISIBLE_TEXT = "Ofis";
    private static final String ACTIVITY_TYPE_DROPDOWN_ID = "popup_YapılanAktivite_";
    private static final String ACTIVITY_TYPE_VISIBLE_TEXT = "GelistirmeTasarım";
    private static final String ADD_WORKLOG_BUTTON_ID = "issue-add-button";
    private static final String AFTER_LOGIN_WAIT_FOR_THIS_COMPONENT_ID = "create_link";
    private static final String MORE_DROPDOWN_ID = "opsbar-operations_more";
    private static final String LOG_WORK_HREF_CONTAINS = "a[href*='TempoAddHours']";
    private static final String USERNAME_TEXTBOX_ID = "login-form-username";
    private static final String PASSWORD_TEXTBOX_ID = "login-form-password";
    private static final String LOGIN_SUBMIT_BUTTON_ID = "login";
    private static final String USERNAME = "ilker.catak";
    private static final String PASSWORD = "155İç-155!";
    private static int trialCount = 5;


    public static void main(String[] args) {

        //Henüz tam test edilmediği için headless opsiyonunu kapalı. Stabil calıstıktan sonra arka planda çalışması hazır
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
//        chromeOptions.addArguments("--disable-gpu");
//         WebDriver driver=new ChromeDriver(chromeOptions);

        if (checkWorkDays()) {
            login();
            openWorklogScreen();
            addWorkLog();
        }
        new WebDriverWait(driver, 10);
        driver.close();
    }

    private static boolean checkWorkDays() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return false;
        }
        return true;
    }


    /**
     * This method first dispatch to login page, fills username password and submit .
     */
    private static void login() {

        if (trialCount==5) {
            driver.get(LOGIN_FORM_URL);
        }

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id(USERNAME_TEXTBOX_ID)));

        WebElement loginTextBox = driver.findElement(By.id(USERNAME_TEXTBOX_ID));
        loginTextBox.click();
        loginTextBox.sendKeys(USERNAME);

        WebElement passwordTextBox = driver.findElement(By.id(PASSWORD_TEXTBOX_ID));
        passwordTextBox.click();
        passwordTextBox.sendKeys(PASSWORD);
        new WebDriverWait(driver, 1);
        driver.findElement(By.id(LOGIN_SUBMIT_BUTTON_ID)).click();
    }

    /**
     * This method waits after loging till page load then dispatch to related issue , clicks "More" dropdown, clicks Log Work
     */
    private static void openWorklogScreen() {
        //Sometimes it does not login in first try so wait for timeoutexception then click again.
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id(AFTER_LOGIN_WAIT_FOR_THIS_COMPONENT_ID)));
        } catch (TimeoutException e) {
            login();
        }

        driver.get(ISSUE_PAGE_URL);

        driver.findElement(By.id(MORE_DROPDOWN_ID)).click();

        driver.findElement(By.cssSelector(LOG_WORK_HREF_CONTAINS)).click();
    }

    /**
     * This method fills; worked hour , work type, activity type then submit
     */
    private static void addWorkLog() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id(WORK_HOUR_TEXTBOX_ID)));
        WebElement workHourTextbox = driver.findElement(By.id(WORK_HOUR_TEXTBOX_ID));
        workHourTextbox.click();
        workHourTextbox.sendKeys(WORK_HOUR);

        new Select(driver.findElement(By.id(WORK_TYPE_DROPDOWN_ID))).selectByVisibleText(WORK_TYPE_VISIBLE_TEXT);

        new Select(driver.findElement(By.id(ACTIVITY_TYPE_DROPDOWN_ID))).selectByValue(ACTIVITY_TYPE_VISIBLE_TEXT);

        driver.findElement(By.id(ADD_WORKLOG_BUTTON_ID)).click();
    }


}
