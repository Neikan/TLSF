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
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;

  private HelperSession helperSession;
  private HelperNavigation helperNavigation;
  private HelperDbOracle helperDb;
  private HelperCC helperCC;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

//    helperDb = new HelperDbOracle(); // Если нет подключения к БД, то упадем раньше, чем начнется инициализация всего остального

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

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    helperCC = new HelperCC(wd);
    helperNavigation = new HelperNavigation(wd);
    helperSession = new HelperSession(wd);
    helperSession.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }

  public void stop() {
    wd.quit();
  }

  public HelperNavigation goTo() {
    return helperNavigation;
  }

  public HelperDbOracle db() {
    return helperDb;
  }

  public HelperCC cc() {
    return helperCC;
  }
}
