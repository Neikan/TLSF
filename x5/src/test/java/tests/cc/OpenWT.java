package tests.cc;

import org.testng.annotations.Test;
import tests.TestBase;

public class OpenWT extends TestBase {

  @Test
  public void testOpenWT () throws InterruptedException {
    app.goTo().docs();
    app.goTo().tasks();
    app.goTo().control();
    app.goTo().reports();
  }
}
