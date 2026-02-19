package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

    @Test
    public void openHomePageTest() {
        String title = driver.getTitle();
        System.out.println("TITLE: " + title);
    }
}
