package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {
    private WebDriver driver;

    // Localizador para el primer producto en los resultados
    @FindBy(css = ".product-layout .caption a")
    private WebElement firstProduct;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Método para seleccionar el primer producto
    public void selectFirstProduct() {
        firstProduct.click();
    }
}