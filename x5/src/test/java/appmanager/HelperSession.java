package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperSession extends HelperBase {

  public HelperSession(WebDriver wd) {
    super(wd);
  }

  public void login(String username, String password) {
    type(By.name("otds_username"), username);
    type(By.name("otds_password"), password);
    click(By.cssSelector("input.button"));
  }

}
