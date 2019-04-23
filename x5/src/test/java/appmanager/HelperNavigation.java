package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperNavigation extends HelperBase{

  public HelperNavigation(WebDriver wd) {
    super(wd);
  }

  public void docs() {
    wd.findElement(By.id("tab_button_0-btnIconEl")).click();
  }

  public void tasks() {
    wd.findElement(By.id("tab_button_1-btnIconEl")).click();
  }

  public void control() {
    wd.findElement(By.id("tab_button_2-btnIconEl")).click();
  }

  public void reports() {
    wd.findElement(By.id("tab_button_3-btnIconEl")).click();
  }



}
