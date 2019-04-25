package appmanager;

import org.openqa.selenium.By;

import java.io.IOException;
import java.net.MalformedURLException;

public class HelperNavigation extends HelperBase {

  public HelperNavigation(ApplicationManager app) throws MalformedURLException {
    super(app);
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


  public void ссmain() throws IOException {
    doubleClick(By.id(String.format("treeview-1014-record-%s", app.getProperty("cc.main"))));
    System.out.println(app.getProperty("cc.main"));
  }

  public void dou() {
    doubleClick(By.id(String.format("treeview-1014-record-%s", app.getProperty("cc.dou"))));
    System.out.println(app.getProperty("cc.dou"));
  }

  public void dp() {
    doubleClick(By.id(String.format("treeview-1014-record-%s", app.getProperty("cc.dp"))));
    System.out.println(app.getProperty("cc.dp"));
  }

  public void dps() {
    doubleClick(By.id(String.format("treeview-1014-record-%s", app.getProperty("cc.dps"))));
    System.out.println(app.getProperty("cc.dps"));
  }

}
