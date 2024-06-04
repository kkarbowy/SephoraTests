package SephoraTest;
/* wejść w makijaż, wyklikać 3 produkty, dodać 3 losowe produkty do koszyka i wejść w koszyk dopiero wtedy
i sprawdzic asercje czy sa 3 produkty
sam test suite ma zawierać 10 przypadkow testowych, gdzie bedzie losowanych 10 x 3 produkty
 */


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

public class ShoppingCartTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String pageUrl = "https://www.sephora.pl";
    private static final String chromedriverPath = "src/main/resources/chromedriver.exe";
    private static final String cookiesPath = "//*[@id=\"footer_tc_privacy_button_3\"]";
    private static final String makeupPath = "//*[@id=\"navigation\"]/ul/li[1]/a";
    private static final String productNameList = "//*[@id=\"search-result-items\"]/li";
    private static final String addToCartId = "add-to-cart";
    private static final String crossCssSelector = "button.ui-button.ui-corner-all.ui-widget.ui-button-icon-only.ui-dialog-titlebar-close";
    private static final String shoppingCartPath = "//*[@id=\"dialog-container\"]/div[3]/a[2]";
    private static final String productInCartClass = "grid-item";
    private static final String hairPath ="//*[@id=\"navigation\"]/ul/li[5]/a";


    @BeforeMethod
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        driver = new ChromeDriver();
    }
    @AfterMethod
    public void driverQuit() {
        driver.quit();
    }

    private void getOnPage(String pageXpath) {
        driver.get(pageUrl);
        WebElement cookies = driver.findElement(By.xpath(cookiesPath));
        cookies.click();
        WebElement page = driver.findElement(By.xpath(pageXpath));
        page.click();
    }

    private void addProductsToCart() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        for(int i = 0; i < 3; i++) {
            List<WebElement> productName = driver.findElements(By.xpath(productNameList));
            // Lista indeksów produktów
            List<Integer> indices = new ArrayList<>();
            for (int j = 0; j < productName.size(); j++) {
                indices.add(j);
            }// Tasowanie listy indeksów, aby uzyskać losową kolejność
            Collections.shuffle(indices);

            int randomIndex = indices.get(i);

            productName.get(randomIndex).click();
            WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(addToCartId)));
            addToCart.click();
            if (i < 2) {
                WebElement cross = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(crossCssSelector)));
                cross.click();
                driver.navigate().back(); //powrót do poprzedniej strony
            }  else {
                WebElement shoppingCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(shoppingCartPath)));
                shoppingCart.click();
            }
        }
    }
        public int countProductsInCart() {
        List<WebElement> productsInCart = driver.findElements(By.className(productInCartClass));
        int numberOfProducts = productsInCart.size();
        return numberOfProducts;
        }

    private void assertProductsCart() {
        addProductsToCart();
        int actualNumberOfProducts = countProductsInCart();
        int expectedNumberOfProducts = 3;
        Assert.assertEquals(actualNumberOfProducts, expectedNumberOfProducts, "Number of products in the cart is not equal 3.");
    }

        @Test(priority = 0)
        public void checkIfTheCartHasProperNumberOfProductsMakeupCategory() {
            getOnPage(makeupPath);
            assertProductsCart();
        }



    @Test(priority = 1)
        public void checkIfTheCartHasProperNumberOfProductsHairCategory() {
        getOnPage(hairPath);
        assertProductsCart();
    }
    }










