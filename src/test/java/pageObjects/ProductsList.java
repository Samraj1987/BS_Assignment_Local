package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class ProductsList {
    WebDriver driver;

    @FindBy(xpath = "//input[@name='q']")
    public WebElement searchBox;

    @FindBy(xpath="//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div[1]/div/div/section")
    public WebElement categories;

    @FindBy(xpath="//a[@title=\"Mobiles\"]" )
    public WebElement categoryMobile;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div[1]/div/section[3]")
    public WebElement brandFilter;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div[1]/div/section[4]")
    public WebElement packageFilter;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[1]/div/div/div[3]")
    public WebElement sortByDiv;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div/div[2]")
    //*[@id="container"]/div/div[3]/div/div[2]
    public WebElement prdtsLst;

    //@FindBy(className = "cPHDOP col-12-12")
    //public List<WebElement> prdtsLst;

    //@FindBy(css = ".cPHDOP.col-12-12")
    //public List<WebElement> prdtsLst;


    public ProductsList(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, 30), this);
    }


}
