package tests.cc;

import org.testng.annotations.Test;
import tests.TestBase;

import java.io.IOException;

public class OpenWT extends TestBase {

  @Test
  public void testOpenWT () throws IOException {
    app.session().loginAdmin();
    app.goTo().docs();
    app.goTo().ссmain();
    app.goTo().dou();
    app.goTo().dp();
    app.goTo().dps();
    app.goTo().tasks();
    app.goTo().control();
    app.goTo().reports();

  }
}
