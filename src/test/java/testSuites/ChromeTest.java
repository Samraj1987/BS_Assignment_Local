package testSuites;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.DriverActions;

public class ChromeTest {
    DriverActions driverActions;
    @BeforeClass
    public void initiateChromeDriver(){
        this.driverActions = DriverActions.getChromeInstance();
        this.driverActions.maximizeDriverWindow();
    }

    @Test
    public void searchGalaxyS10(){
        BSAssignmentTest bsTest = new BSAssignmentTest();
        bsTest.searchSamsungS10(this.driverActions);
    }

    @AfterClass
    public void quitDriver(){
        this.driverActions.quitDriver();
        this.driverActions = null;
    }
}
