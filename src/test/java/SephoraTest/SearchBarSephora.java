package SephoraTest;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchBarSephora {

    private WebDriver driver;

    @BeforeClass
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }
    @AfterClass
    public void driverQuit() {
        driver.quit();
    }

    public void checkIfFoundPharasesAreProperly(String expectedPhrase) { //to będę sprawdzać

        //włączenie przeglądarki, znalezienie wyszukiwarki, wpisanie frazy i enter
        findWrittenPhrase(expectedPhrase);
        ArrayList<String> products = getProductsList();
        System.out.println(products);
        for (String product : products) {
            product = product.toLowerCase();
            Assert.assertTrue(product.contains(expectedPhrase), "Nazwy nie są zgodne!");
        }

    }

    private void findWrittenPhrase(String phrase) {
        //wejdź na serphorę
        driver.get("https://www.sephora.pl");
        //znajdź gdzie jest wyszukiwarka
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"wrapper\"]/header/div[3]/div[1]/div[2]/div/input"));
        //wpisz rituals
        searchBar.sendKeys(phrase);
        //zatwierdź
        searchBar.sendKeys(Keys.RETURN);
    }
    private ArrayList<String> getProductsList() {
        ArrayList<String> products = new ArrayList<>();
        List<WebElement> nameLocalisation = driver.findElements(By.xpath("//*[@id=\"search-result-items\"]/li/div/div[3]/div/a/span"));
        for (WebElement element : nameLocalisation) {
            String elementText = element.getText();
            products.add(elementText);
        }
        return products;
    }
    @Test
    public void checkIfSearchBarFindsRituals() {
        checkIfFoundPharasesAreProperly("rituals");
    }
    @Test
    public void checkIfSearchBarFindsDior() {
        checkIfFoundPharasesAreProperly("benefit");
    }
}
