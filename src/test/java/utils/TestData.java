package utils;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return new Object[][]{
                {"Valid Login", ConfigReader.get("email"), ConfigReader.get("password"), "PASS", "NONE"},
                {"Invalid Password", ConfigReader.get("email"), "wrongpass", "FAIL", "SERVER_ERROR"},
                {"Blank Password", ConfigReader.get("email"), "", "FAIL", "BROWSER_VALIDATION"}
        };
    }
}
