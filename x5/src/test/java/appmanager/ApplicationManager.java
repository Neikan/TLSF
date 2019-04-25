package appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class ApplicationManager {
  private final Properties properties;
  private WebDriver wd;

  private String browser;
  private HelperSession helperSession;
  private HelperNavigation helperNavigation;
  private HelperDbOracle helperDb;
  private HelperCC helperCC;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
//    session().login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
//    helperSession.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    //helperDb = new HelperDbOracle(); // Если нет подключения к БД, то упадем раньше, чем начнется инициализация всего остального
  }

  public void stop() {
    if (wd != null) {
      wd.quit();
    }
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public WebDriver getDriver() throws MalformedURLException {
    if (wd == null) {
      if ("".equals(properties.getProperty("selenium.server"))) {
        if (browser.equals(BrowserType.FIREFOX)) {
          wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
          wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
          wd = new InternetExplorerDriver();
        }
      } else {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
      }
    }
    return wd;
  }

  public HelperDbOracle db() {
    return helperDb;
  }

  public HelperNavigation goTo() throws MalformedURLException {
    if (helperNavigation == null) {
      helperNavigation = new HelperNavigation(this);
    }
    return helperNavigation;
  }

  public HelperCC cc() throws MalformedURLException {
    if (helperCC == null) {
      helperCC = new HelperCC(this);
    }
    return helperCC;
  }

  public HelperSession session() throws MalformedURLException {
    if (helperSession == null) {
      helperSession = new HelperSession(this);
    }
    return helperSession;
  }

}
