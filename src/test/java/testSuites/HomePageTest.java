package testSuites;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import pageObjects.HomePage;
import utilities.DriverActions;

public class HomePageTest {
    HomePage homePage;
    DriverActions driverActions;
    public HomePageTest(DriverActions da,String url){
        driverActions = da;
        homePage = new HomePage(da.getDriver());
        this.driverActions.getUrl(url);
        Assert.assertEquals(this.driverActions.getCurrentUrl(),"https://www.flipkart.com/");
    }

    public void searchProduct(String queryString){
        this.driverActions.sendText(homePage.searchBox,queryString);
        this.driverActions.pressKey(homePage.searchBox, Keys.RETURN);
        this.driverActions.waitUntilPageLoaded();
    }
}
