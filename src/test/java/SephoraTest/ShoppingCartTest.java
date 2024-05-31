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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
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
        //explicit
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //konstruktor
        for(int i = 0; i < 3; i++) {
            List<WebElement> productName = driver.findElements(By.xpath("//*[@id=\"search-result-items\"]/li"));
            productName.get(i).click();
            WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart")));
            addToCart.click();
            if (i < 2) {
                WebElement cross = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.ui-button.ui-corner-all.ui-widget.ui-button-icon-only.ui-dialog-titlebar-close")));
                cross.click();
                driver.navigate().back(); //powrót do poprzedniej strony
            }  else {
                WebElement shoppingCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"dialog-container\"]/div[3]/a[2]")));
                shoppingCart.click();
            }
        }
    }

        @Test
        public void test () {
            getOnMakeupPage();
            addProductsToCart();
            System.out.println("X");
        }
    }










