package com.example.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public final class WebDriverFactory {
    private WebDriverFactory() {
        // do not instantiate
    }

    /**
     * Return a new WebDriver instance matching the specified name and version.
     *
     * @return the requested WebDriver
     */
    public static WebDriver getInstanceFor(WebDriverManager webDriverManager) {
        WebDriver webDriver = webDriverManager.create();
        if ((webDriver == null) && (webDriverManager.getDriverManagerType() == DriverManagerType.SAFARI) &&
            webDriverManager.config().getOperatingSystem().isMac()) {
            log.error("The Safari instance may already paired with another WebDriver session."
            + "\nCheck Activity Monitor for \"safaridriver\" processes and remove them.");

        }
        return webDriver;
    }

    /**
     * Return a new WebDriver instance matching the specified name, version, and driver.
     *
     * <p>
     *     This method uses WebDriverManager, which allows us to download and
     *     set the browser driver binaries without
     *     having to manually put them in automation scripts.
     *     {@see https://www.toolsqa.com/selenium-webdriver/webdrivermanager/}
     * </p>
     *
     * @param webDriverName the name of the web driver - must match one of DriverManagerType's values.
     * @param browserVersion the version of the browser to use.
     * @param driverVersion the version of the driver to use for the browser.
     *
     * @return the requested WebDriver
     */
    public static WebDriver getInstanceFor(String webDriverName, String browserVersion, String driverVersion) {

        return WebDriverManagerFactory.getInstanceFor(webDriverName, browserVersion, driverVersion).create();
    }

    /**
     * Return a new WebDriver instance matching the specified name and version.
     *
     * @return the requested WebDriver
     */
    public static WebDriver getInstanceFor(String webDriverName, String browserVersion) {
        return getInstanceFor(webDriverName, browserVersion, null);
    }

    /**
     * Return a new WebDriver instance matching the specified name.
     *
     * @return the requested WebDriver
     */
    public static WebDriver getInstanceFor(String webDriverName) {
        return getInstanceFor(webDriverName, null, null);
    }

    /**
     * Return a new WebDriver instance matching the specified name and driver.
     *
     * @return the requested WebDriver
     */
    public static WebDriver getInstanceByDriverVersion(String webDriverName, String driverVersion) {
        return getInstanceFor(webDriverName, null, driverVersion);
    }
}
