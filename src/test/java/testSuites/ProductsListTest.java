package testSuites;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.ProductsList;
import utilities.DriverActions;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;

public class ProductsListTest {
    DriverActions driverActions;
    ProductsList prdtLstPage;


    ProductsListTest(DriverActions driverActions) {
        this.driverActions = driverActions;
        this.prdtLstPage = new ProductsList(this.driverActions.getDriver());
    }

    public String getQueryValue(){
        return this.driverActions.getValueAttributeValue(this.prdtLstPage.searchBox);
    }

    public void selectCategory(String categoryName) {

        //Assert.assertEquals(this.driverActions.getValueAttributeValue(this.prdtLstPage.searchBox),"Samsung Galaxy S 10");
        WebElement aElement = null;
        WebElement element = this.prdtLstPage.categories;

        List<WebElement> categoryDivs = this.driverActions.getElements(element, "tagName", "div");
        try{
            if (categoryDivs != null) {
                aElement = this.driverActions.getElementBasedOnText(categoryDivs,
                        categoryName,
                        "tagName",
                        "a");
                this.driverActions.getText(aElement);
                this.driverActions.click(aElement);
            }
        }
        catch(Exception ex){
            WebElement element1 = this.driverActions.getDriver().findElement(By.xpath("//a[@title=\"Mobiles\"]"));
            this.driverActions.click(element1);
        }

        this.driverActions.waitUntilUrlChangesTo("https://www.flipkart.com/mobiles/pr?sid=tyy,4io&q=Samsung+Galaxy+S+10&otracker=categorytree");

    }

    public void applyBrandFilter(String brandName) {
        String xpath = "//div/div[@title='" + brandName + "']";

        try{
            List<WebElement> brands = this.driverActions.getElements(this.prdtLstPage.brandFilter,
                    "tagName",
                    "div");
            System.out.println("Brands Length:"+brands.size());
            brands.remove(0); //removing the element to hold the label Brand to avoid one iteration in loop
            //looping through to brands and find Samsung element
            for (WebElement ele : brands) {
                WebElement brand = this.driverActions.getElement(ele, "xpath", xpath);
                if (brand != null) {
                    this.driverActions.scrollDownToElement(brand);
                    this.driverActions.click(brand);
                    break;
                }
            }
        }
        catch(Exception ex){
            WebElement element = this.driverActions.getElement("xpath","//div[@title=\""+brandName+"\"]");
            this.driverActions.scrollDownToElement(element);
            this.driverActions.click(element);
        }
        this.driverActions.waitUntilUrlChangesTo("https://www.flipkart.com/mobiles/pr?sid=tyy%2C4io&q=Samsung+Galaxy+S+10&otracker=categorytree&p%5B%5D=facets.brand%255B%255D%3DSAMSUNG");

    }

    public void applyPackageFilter(String packageName) {
        String xpath = "";
        if (packageName.equalsIgnoreCase("Flipkart assured")) {
            xpath = "//label/div[2]/div/img[@src='//static-assets-web.flixcart.com/fk-p-linchpin-web/fk-cp-zion/img/fa_62673a.png']";
        }
        try{
            WebElement packageCategory = this.driverActions.getElement(this.prdtLstPage.packageFilter,
                    "xpath",
                    xpath);
            this.driverActions.scrollDownToElement(packageCategory);
            this.driverActions.click(packageCategory);
        }catch(Exception ex){
            WebElement element = this.driverActions.getElement("xpath","//img[@src='//static-assets-web.flixcart.com/fk-p-linchpin-web/fk-cp-zion/img/fa_62673a.png']");
            this.driverActions.scrollDownToElement(element);
            this.driverActions.click(element);
        }
        this.driverActions.waitUntilUrlChangesTo("https://www.flipkart.com/mobiles/pr?sid=tyy%2C4io&q=Samsung+Galaxy+S+10&otracker=categorytree&p%5B%5D=facets.brand%255B%255D%3DSAMSUNG&p%5B%5D=facets.fulfilled_by%255B%255D%3DPlus%2B%2528FAssured%2529");

    }

    public void selectSortingPrice(String priceSorting) {
        if (priceSorting.equalsIgnoreCase("low to high")) {
            priceSorting = "Price -- Low to High";
        } else {
            priceSorting = "Price -- High to Low";
        }
        try{
            List<WebElement> elements = this.driverActions.getElements(this.prdtLstPage.sortByDiv,
                    "tagName",
                    "div");

            WebElement sortingElement = this.driverActions.getElementBasedOnText(elements,
                    priceSorting,
                    null,
                    null);
            this.driverActions.scrollDownToElement(sortingElement);
            this.driverActions.click(sortingElement);
        }catch (Exception ex){
            WebElement element = this.driverActions.getElement("xpath","//div[text()='"+ priceSorting +"']");
            this.driverActions.scrollDownToElement(element);
            this.driverActions.click(element);
        }

        this.driverActions.waitUntilUrlChangesTo("https://www.flipkart.com/mobiles/pr?sid=tyy%2C4io&q=Samsung+Galaxy+S+10&otracker=categorytree&p%5B%5D=facets.brand%255B%255D%3DSAMSUNG&p%5B%5D=facets.fulfilled_by%255B%255D%3DPlus%2B%2528FAssured%2529&sort=price_desc");

    }

    public void getPrdtDetails() {
        String xpath = "/html/body/div/div/div[3]/div/div[2]/div[contains(@class,'cPHDOP') and contains(@class,'col-12-12')]";
        this.driverActions.refreshPage();
        List<WebElement> prdtLst = this.driverActions.getElements("xpath",
                xpath);

        System.out.println("Products Length:" + (prdtLst.size() - 1));

        for (int i = 0; i < prdtLst.size() - 2; i++) {
            WebElement pdtElement = prdtLst.get(i);
            this.driverActions.scrollDownToElement(pdtElement);

            WebElement pdtALink = this.driverActions.getElement(pdtElement,"xpath","./div/div/div/a");
            String hrefValue = this.driverActions.getHrefAttributeValue(pdtALink);

            WebElement pdtDesc = this.driverActions.getElement(pdtALink,"xpath","./div[contains(@class,'yKfJKb')]");
            WebElement pdtTitle = this.driverActions.getElement(pdtDesc,"xpath","./div[contains(@class,'col-7-12')]/div[contains(@class,'KzDlHZ')]");
            String title = this.driverActions.getText(pdtTitle);

            WebElement pdtPrice = this.driverActions.getElement(pdtDesc,"xpath",
                    "./div[contains(@class,'col col-5-12 BfVC2z')]/div[contains(@class,'cN1yYO')]/div[contains(@class,'hl05eU')]/div[contains(@class,'Nx9bqj')]");
            String price = this.driverActions.getText(pdtPrice);

            System.out.println("------------------------");

            System.out.println("Product Name: " + title);
            System.out.println("Product Price: " + price);
            System.out.println("Product Link:" + hrefValue);
        }


    }

    public String getCurrentURL(){
        return this.driverActions.getCurrentUrl();
    }
}

