package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    private WebDriver driver;

    // Localizador para el botón del carrito en la barra superior
    @FindBy(id = "cart")
    private WebElement cartButton;

    // Localizador para "View Cart" en el dropdown
    @FindBy(css = "#cart .dropdown-menu p a[href*='cart']")
    private WebElement viewCartLink;

    // Localizador para validar el producto en el carrito
    @FindBy(xpath = "(//a[contains(.,'iPhone')])[2]")
    private WebElement iPhoneInCart;

    // Localizador para el botón de eliminar
    @FindBy(css = "[data-original-title='Remove']")
    private WebElement removeButton;

    // Localizador para validar que el carrito está vacío
    @FindBy(xpath = "//div[@id='content']/p[contains(text(), 'Your shopping cart is empty!')]")
    private WebElement emptyCartMessage;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Método para abrir el carrito
    public void openCart() {
        cartButton.click();
        viewCartLink.click();
    }

    // Método para validar que el iPhone está en el carrito
    public boolean isIPhoneInCart() {
        return iPhoneInCart.isDisplayed();
    }

    // Método para eliminar el iPhone del carrito
    public void removeIPhone() {
        removeButton.click();
    }

    // Método para validar que el carrito está vacío
    public boolean isCartEmpty() {
        try {
            return !iPhoneInCart.isDisplayed();
        } catch (Exception e) {
            return true; // Si no encuentra el elemento, el carrito está vacío
        }
    }
}