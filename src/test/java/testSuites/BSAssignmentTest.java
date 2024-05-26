package testSuites;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.DriverActions;

public class BSAssignmentTest {

    public void searchSamsungS10(DriverActions driverActions){
        String url="https://www.flipkart.com/";
        HomePageTest homeTest = new HomePageTest(driverActions,url);
        ProductsListTest prdtLstTest = new ProductsListTest(driverActions);

        homeTest.searchProduct("Samsung Galaxy S 10");
        Assert.assertEquals(prdtLstTest.getQueryValue(),"Samsung Galaxy S 10");

        prdtLstTest.selectCategory("Mobiles");
        Assert.assertEquals(prdtLstTest.getCurrentURL(),
                "https://www.flipkart.com/mobiles/pr?sid=tyy,4io&q=Samsung+Galaxy+S+10&otracker=categorytree");

        prdtLstTest.applyBrandFilter("SAMSUNG");
        Assert.assertEquals(prdtLstTest.getCurrentURL(),
                "https://www.flipkart.com/mobiles/pr?sid=tyy%2C4io&q=Samsung+Galaxy+S+10&otracker=categorytree&p%5B%5D=facets.brand%255B%255D%3DSAMSUNG");

        prdtLstTest.applyPackageFilter("Flipkart assured");
        Assert.assertEquals(prdtLstTest.getCurrentURL(),
                "https://www.flipkart.com/mobiles/pr?sid=tyy%2C4io&q=Samsung+Galaxy+S+10&otracker=categorytree&p%5B%5D=facets.brand%255B%255D%3DSAMSUNG&p%5B%5D=facets.fulfilled_by%255B%255D%3DPlus%2B%2528FAssured%2529"
        );

        prdtLstTest.selectSortingPrice("High to Low");
        Assert.assertEquals(prdtLstTest.getCurrentURL(),
                "https://www.flipkart.com/mobiles/pr?sid=tyy%2C4io&q=Samsung+Galaxy+S+10&otracker=categorytree&p%5B%5D=facets.brand%255B%255D%3DSAMSUNG&p%5B%5D=facets.fulfilled_by%255B%255D%3DPlus%2B%2528FAssured%2529&sort=price_desc");

        prdtLstTest.getPrdtDetails();
    }
}
