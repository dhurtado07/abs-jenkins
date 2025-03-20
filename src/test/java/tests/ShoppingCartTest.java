package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ShoppingCartTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Detecta el sistema operativo y configura la ruta de ChromeDriver
        String os = System.getProperty("os.name").toLowerCase();
        String driverPath;
        if (os.contains("win")) {
            driverPath = "src/test/resources/drivers/chromedriver.exe";
        } else {
            driverPath = "src/test/resources/drivers/chromedriver";
        }
        System.setProperty("webdriver.chrome.driver", new File(driverPath).getAbsolutePath());

        // Configura opciones para Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless"); // Para Jenkins/Docker

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://opencart.abstracta.us/");
    }

    @Test
    public void testShoppingCart() throws Exception {
        // Instancia las páginas con el patrón Page Object
        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Paso 1: Buscar "iPhone"
        homePage.searchProduct("iPhone");

        // Captura de pantalla después de buscar
        takeScreenshot("search_iphone");

        // Paso 2: Seleccionar el primer producto
        searchResultsPage.selectFirstProduct();

        // Paso 3: Agregar al carrito
        productPage.addToCart();
        Thread.sleep(5000); // Espera explícita para que el carrito se actualice

        // Paso 4: Abrir el carrito
        cartPage.openCart();

        // Captura de pantalla del carrito con el producto
        takeScreenshot("cart_with_iphone");

        // Paso 5: Validar que el iPhone está en el carrito
        Assert.assertTrue(cartPage.isIPhoneInCart(), "El iPhone no está en el carrito");

        // Paso 6: Eliminar el iPhone del carrito
        cartPage.removeIPhone();
        Thread.sleep(5000); // Espera explícita para la actualización

        // Captura de pantalla del carrito vacío
        takeScreenshot("cart_empty");

        // Paso 7: Validar que el iPhone ya no está en el carrito
        Assert.assertTrue(cartPage.isCartEmpty(), "El iPhone sigue en el carrito");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Cierra el navegador
        }
    }

    // Método para tomar capturas de pantalla
    private void takeScreenshot(String fileName) throws Exception {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("screenshots/" + fileName + ".png"));
    }
}