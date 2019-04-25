package appmanager;

import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class HelperSession extends HelperBase {

  public HelperSession(ApplicationManager app) throws MalformedURLException {
    super(app);
  }

  public void loginAdmin() {
    wd.get(app.getProperty("web.baseUrl"));
    type(By.name("otds_username"), app.getProperty("web.adminLogin"));
    type(By.name("otds_password"), app.getProperty("web.adminPassword"));
    click(By.cssSelector("input.button"));
  }

}
