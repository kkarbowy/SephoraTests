package SephoraTest;
/* wejść w makijaż, wyklikać 3 produkty, dodać 3 losowe produkty do koszyka i wejść w koszyk dopiero wtedy
i sprawdzic asercje czy sa 3 produkty
sam test suite ma zawierać 10 przypadkow testowych, gdzie bedzie losowanych 10 x 3 produkty
 */


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShoppingCartTest {
    private WebDriver driver;

    @BeforeClass
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    private void getOnMakeupPage() {
        //wejdz na sephore
        driver.get("https://www.sephora.pl");
        //kliknij w makijaz
        WebElement cookies = driver.findElement(By.xpath("//*[@id=\"footer_tc_privacy_button_3\"]"));
        cookies.click();
        WebElement makeup = driver.findElement(By.xpath("//*[@id=\"navigation\"]/ul/li[1]/a"));
        makeup.click();
    }

    private void addProductsToCart() {
        List<WebElement> productName = driver.findElements(By.xpath("//*[@id=\"search-result-items\"]/li"));
        productName.get(0).click();
        WebElement addToCart = driver.findElement(By.id("add-to-cart"));
        addToCart.click();
    }

        @Test
        public void test () {
            getOnMakeupPage();
            addProductsToCart();
            System.out.println("X");
        }
    }










