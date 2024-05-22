package testSuites;

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
        prdtLstTest.selectCategory("Mobiles");
        prdtLstTest.applyBrandFilter("SAMSUNG");
        prdtLstTest.applyPackageFilter("Flipkart assured");
        prdtLstTest.selectSortingPrice("High to Low");
        prdtLstTest.getPrdtDetails();
    }
}
