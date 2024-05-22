package testSuites;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.ProductsList;
import utilities.DriverActions;

import java.util.List;

public class ProductsListTest {
    DriverActions driverActions;
    ProductsList prdtLstPage;


    ProductsListTest(DriverActions driverActions) {
        this.driverActions = driverActions;
        this.prdtLstPage = new ProductsList(this.driverActions.getDriver());
    }

    public void selectCategory(String categoryName) {
        Assert.assertEquals(this.driverActions.getValueAttributeValue(this.prdtLstPage.searchBox),"Samsung Galaxy S 10");
        WebElement aElement = null;
        WebElement element = this.prdtLstPage.categories;

        List<WebElement> categoryDivs = this.driverActions.getElements(element, "tagName", "div");
        if (categoryDivs != null) {
            aElement = this.driverActions.getElementBasedOnText(categoryDivs,
                    categoryName,
                    "tagName",
                    "a");
            this.driverActions.click(aElement);
        }

        this.driverActions.waitUntilPageLoaded();
        //Assert.assertEquals(this.driverActions.getValueAttributeValue(this.prdtLstPage.searchBox),"Samsung Galaxy S 10");
        Assert.assertEquals(this.driverActions.getCurrentUrl(),
                "https://www.flipkart.com/mobiles/pr?sid=tyy,4io&q=Samsung+Galaxy+S+10&otracker=categorytree");
    }

    public void applyBrandFilter(String brandName) {
        String xpath = "//div/div[@title='" + brandName + "']";

        List<WebElement> brands = this.driverActions.getElements(this.prdtLstPage.brandFilter,
                "tagName",
                "div");
        brands.remove(0); //removing the element to hold the label Brand to avoid one iteration in loop
        //looping through to brands and find Samsung element
        for (WebElement ele : brands) {
            WebElement brand = this.driverActions.getElement(ele, "xpath", xpath);
            if (brand != null) {
                this.driverActions.click(brand);
                break;
            }
        }
        Assert.assertEquals(this.driverActions.getCurrentUrl(),
                "https://www.flipkart.com/mobiles/pr?sid=tyy%2C4io&q=Samsung+Galaxy+S+10&otracker=categorytree&p%5B%5D=facets.brand%255B%255D%3DSAMSUNG");
    }

    public void applyPackageFilter(String packageName) {
        String xpath = "";
        if (packageName.equalsIgnoreCase("Flipkart assured")) {
            xpath = "//label/div[2]/div/img[@src='//static-assets-web.flixcart.com/fk-p-linchpin-web/fk-cp-zion/img/fa_62673a.png']";
        }
        WebElement packageCategory = this.driverActions.getElement(this.prdtLstPage.packageFilter,
                "xpath",
                xpath);
        this.driverActions.click(packageCategory);
        Assert.assertEquals(this.driverActions.getCurrentUrl(),
                "https://www.flipkart.com/mobiles/pr?sid=tyy%2C4io&q=Samsung+Galaxy+S+10&otracker=categorytree&p%5B%5D=facets.brand%255B%255D%3DSAMSUNG&p%5B%5D=facets.fulfilled_by%255B%255D%3DPlus%2B%2528FAssured%2529");
    }

    public void selectSortingPrice(String priceSorting) {
        if (priceSorting.equalsIgnoreCase("low to high")) {
            priceSorting = "Price -- Low to High";
        } else {
            priceSorting = "Price -- High to Low";
        }
        List<WebElement> elements = this.driverActions.getElements(this.prdtLstPage.sortByDiv,
                "tagName",
                "div");

        WebElement sortingElement = this.driverActions.getElementBasedOnText(elements,
                priceSorting,
                null,
                null);
        this.driverActions.click(sortingElement);
        Assert.assertEquals(this.driverActions.getCurrentUrl(),
                "https://www.flipkart.com/mobiles/pr?sid=tyy%2C4io&q=Samsung+Galaxy+S+10&otracker=categorytree&p%5B%5D=facets.brand%255B%255D%3DSAMSUNG&p%5B%5D=facets.fulfilled_by%255B%255D%3DPlus%2B%2528FAssured%2529&sort=price_desc");
    }

    public void getPrdtDetails() {
        String xpath = "/html/body/div/div/div[3]/div/div[2]/div[contains(@class,'cPHDOP') and contains(@class,'col-12-12')]";
        String xpathLink = "/div/div/div/a";
        String xpathName = "/div/div/div/a/div[2]/div/div";
        String xpathPrice = "/div/div/div/a/div[2]/div[2]/div[1]/div[1]/div[1]";
        this.driverActions.refreshPage();


        List<WebElement> prdtLst = this.driverActions.getElements("xpath",
                xpath);

        for (int i = 0; i < prdtLst.size() - 2; i++) {
            WebElement pdtElement = prdtLst.get(i);
            String xpathLinkTemp = xpath + "[" + (i + 1) + "]" + xpathLink;
            String xpathNameTemp = xpath + "[" + (i + 1) + "]" + xpathName;
            String xpathPriceTemp = xpath + "[" + (i + 1) + "]" + xpathPrice;
            this.driverActions.scrollDownToElement(pdtElement);
            System.out.println("------------------------");
            WebElement pdtTitle = this.driverActions.getElement("xpath", xpathNameTemp);
            WebElement pdtLink = this.driverActions.getElement("xpath", xpathLinkTemp);
            WebElement pdtPrice = this.driverActions.getElement("xpath", xpathPriceTemp);
            System.out.println("Product Name: " + this.driverActions.getText(pdtTitle));
            System.out.println("Product Price: " + this.driverActions.getText(pdtPrice));
            System.out.println("Product Link:" + this.driverActions.getHrefAttributeValue(pdtLink));
        }
    }
}

