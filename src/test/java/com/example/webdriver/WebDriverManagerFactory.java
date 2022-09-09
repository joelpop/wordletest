package com.example.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Config;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.VoidDriverManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class WebDriverManagerFactory {

    private WebDriverManagerFactory() {
        // do not instantiate
    }

    /**
     * Return a new WebDriverManager instance matching the specified name, version, and driver.
     *
     * <p>
     *     This method uses WebDriverManager, which allows us to download and
     *     set the browser driver binaries without
     *     having to manually put them in automation scripts.
     *     {@see https://bonigarcia.dev/webdrivermanager/} and
     *     {@see https://www.toolsqa.com/selenium-webdriver/webdrivermanager/}.
     * </p>
     *
     * @param webDriverName the name of the web driver - must match one of DriverManagerType's values.
     * @param browserVersion the version of the browser to use.
     * @param driverVersion the version of the driver to use for the browser.
     *
     * @return the requested WebDriverManager
     */
    public static WebDriverManager getInstanceFor(String webDriverName, String browserVersion, String driverVersion) {
        log.info("WebDriverManager requested for browser: {}", webDriverName);

        // find matching DriverManagerType
        DriverManagerType driverManagerType = DriverManagerType.valueOf(webDriverName.toUpperCase());
        log.debug("Matching DriverManagerType browser name: {}", driverManagerType.getBrowserName());

        // get matching WebDriverManager with requested browser and driver versions
        WebDriverManager webDriverManager = WebDriverManager.getInstance(driverManagerType);
        log.debug("Instantiated WebDriverManager: {}", webDriverManager.getClass().getCanonicalName());
        if (browserVersion != null) {
            webDriverManager.browserVersion(browserVersion);
        }
        if (driverVersion != null) {
            webDriverManager.driverVersion(driverVersion);
        }

        // download driver if not present
        webDriverManager.setup();

        if (log.isDebugEnabled()) {
            logWebDriverManager(webDriverManager);
        }

        return webDriverManager;
    }

    /**
     * Return a new WebDriverManager instance matching the specified name and version.
     *
     * @return the requested WebDriverManager
     */
    public static WebDriverManager getInstanceFor(String webDriverName, String browserVersion) {
        return getInstanceFor(webDriverName, browserVersion, null);
    }

    /**
     * Return a new WebDriverManager instance matching the specified name.
     *
     * @return the requested WebDriverManager
     */
    public static WebDriverManager getInstanceFor(String webDriverName) {
        return getInstanceFor(webDriverName, null, null);
    }

    /**
     * Return a new WebDriverManager instance matching the specified name and driver.
     *
     * @return the requested WebDriverManager
     */
    public static WebDriverManager getInstanceByDriverVersion(String webDriverName, String driverVersion) {
        return getInstanceFor(webDriverName, null, driverVersion);
    }

    private static void logWebDriverManager(WebDriverManager webDriverManager) {
        DriverManagerType driverManagerType = webDriverManager.getDriverManagerType();

        // log WebDriver canonical class name corresponding to DriverManagerType
        log.debug("Corresponding WebDriver: {}", driverManagerType.browserClass());

        if (webDriverManager.getClass() == VoidDriverManager.class) {
            return;
        }

        log.debug("[webDriverManager]");
        log.debug("    DriverVersions:          {}", webDriverManager.getDriverVersions());
        log.debug("    DownloadedDriverPath:    {}", webDriverManager.getDownloadedDriverPath());
        log.debug("    DownloadedDriverVersion: {}", webDriverManager.getDownloadedDriverVersion());

        Config config = webDriverManager.config();
        log.debug("    [config]");
        log.debug("        getOperatingSystem: {}", config.getOperatingSystem());
        switch (driverManagerType) {
            case CHROME:
                log.debug("        DownloadUrlPattern: {}", config.getChromeDownloadUrlPattern());
                log.debug("        DriverExport:       {}", config.getChromeDriverExport());
                log.debug("        DriverUrl:          {}", config.getChromeDriverUrl());
                log.debug("        DriverMirrorUrl:    {}", config.getChromeDriverMirrorUrl());
                log.debug("        DriverVersion:      {}", config.getChromeDriverVersion());
                log.debug("        BrowserVersion:     {}", config.getChromeVersion());
                break;
            case CHROMIUM:
                log.debug("        DriverSnapPath:     {}", config.getChromiumDriverSnapPath());
                log.debug("        DriverVersion:      {}", config.getChromiumDriverVersion());
                log.debug("        BrowserVersion:     {}", config.getChromiumVersion());
                break;
            case EDGE:
                log.debug("        DownloadUrlPattern: {}", config.getEdgeDownloadUrlPattern());
                log.debug("        DriverExport:       {}", config.getEdgeDriverExport());
                log.debug("        DriverUrl:          {}", config.getEdgeDriverUrl());
                log.debug("        DriverVersion:      {}", config.getEdgeDriverVersion());
                log.debug("        BrowserVersion:     {}", config.getEdgeVersion());
                break;
            case FIREFOX:
                log.debug("        DriverExport:       {}", config.getFirefoxDriverExport());
                log.debug("        DriverUrl:          {}", config.getFirefoxDriverUrl());
                log.debug("        DriverMirrorUrl:    {}", config.getFirefoxDriverMirrorUrl());
                log.debug("        BrowserVersion:     {}", config.getFirefoxVersion());
                break;
            case IEXPLORER:
                log.debug("        DriverExport:       {}", config.getIExplorerDriverExport());
                log.debug("        DriverUrl:          {}", config.getIExplorerDriverUrl());
                log.debug("        DriverVersion:      {}", config.getIExplorerDriverVersion());
            case OPERA:
                log.debug("        DriverExport:       {}", config.getOperaDriverExport());
                log.debug("        DriverUrl:          {}", config.getOperaDriverUrl());
                log.debug("        DriverMirrorUrl:    {}", config.getOperaDriverMirrorUrl());
                log.debug("        DriverVersion:      {}", config.getOperaDriverVersion());
                log.debug("        BrowserVersion:     {}", config.getOperaVersion());
                break;
            case SAFARI:
                break;
        }
    }
}
