package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    // Localizador para la barra de búsqueda
    @FindBy(name = "search")
    private WebElement searchBar;

    // Localizador para el botón de búsqueda
    @FindBy(css = "#search button")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Inicializa los elementos con PageFactory
    }

    // Método para buscar un producto
    public void searchProduct(String product) {
        searchBar.sendKeys(product); // Ingresa el texto en la barra
        searchButton.click();        // Hace clic en el botón de búsqueda
    }
}