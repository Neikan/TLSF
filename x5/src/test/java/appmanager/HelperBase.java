package appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.net.MalformedURLException;

public class HelperBase {
  protected ApplicationManager app;
  protected WebDriver wd;

  public HelperBase(ApplicationManager app) throws MalformedURLException {
    this.app = app;
    this.wd = app.getDriver();
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void doubleClick(By locator) {
    new Actions(wd).doubleClick(wd.findElement(locator)).perform();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {//Добавлена возможность запуска тестов с пропуском полей
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {//Добавлена проверка на пропуск полей с неизменяемыми в тесте значениями
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  protected void attach(By locator, File file) {
    if (file != null) {
      wd.findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }

  public boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
