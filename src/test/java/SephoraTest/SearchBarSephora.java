package SephoraTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
    @Test
    public void checkIfFoundPharasesAreProperly() { //to będę sprawdzać
        //włączenie przeglądarki, znalezienie wyszukiwarki, wpisanie frazy i enter
        findWrittenPhrase();

    }

    private void findWrittenPhrase() {
        //wejdź na serphorę
        driver.get("https://www.sephora.pl");
        //znajdź gdzie jest wyszukiwarka
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"wrapper\"]/header/div[2]/div[2]/div/div[2]/div/div/input"));
        //wpisz rituals
        searchBar.sendKeys("rituals");
        //zatwierdź
        searchBar.sendKeys(Keys.RETURN);
    }


}
