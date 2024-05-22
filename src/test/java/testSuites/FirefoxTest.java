package testSuites;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.DriverActions;


public class FirefoxTest {
    DriverActions driverActions;

    @BeforeClass
    public void initiateDriver(){
        this.driverActions = DriverActions.getFFInstance();
        this.driverActions.maximizeDriverWindow();
    }

    @Test
    public void searchGalaxyS10(){
       BSAssignmentTest bsTest = new BSAssignmentTest();
       bsTest.searchSamsungS10(this.driverActions);
    }

    @AfterTest
    public void quitDriver(){
        this.driverActions.quitDriver();
        this.driverActions = null;
    }
}
