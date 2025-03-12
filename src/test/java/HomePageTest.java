import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


/*
1. Откройте страницу https://bonigarcia.dev/selenium-webdriver-java/
2. Проверьте, что все ссылки для Chapter 3-9 доступны и при нажатии открывается соответствующая страница
3. Проверьте заголовок страницы и url
4. Проверьте, что все ссылки принадлежат определенному Chapter, например, WebForm в Chapter 3. WebDriver Fundamentals
 */


public class HomePageTest {

    WebDriver driver; //запускает браузер
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setup() {
        driver = new ChromeDriver(); //запускает браузер
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterEach
    void tearDown () {
        driver.getPageSource(); //сохранение html страницы
        driver.quit(); //закрывает весь браузер

    }


    @Test
    void openHomePageTest () {
        driver.manage().window().maximize();
        String actualTitle = driver.getTitle();

        String expectedTitle = "Hands-On Selenium WebDriver with Java";
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

//2. Проверьте, что все ссылки для Chapter 3-9 доступны и при нажатии открывается соответствующая страница

    @Test
    void findWebFormTest () {
        //WebElement webForm = driver.findElement(By.xpath("//a[@href='web-form.html']")) ;// - внутри любой вложенности href
        //webForm.click();

        driver.findElement(By.xpath("//h5[text() = 'Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'web-form')]")).click();
        String expectedUrl = "web-form.html";
        String actualUrl = driver.getCurrentUrl();

        WebElement expectedTitle = driver.findElement(By.className("display-6"));

        Assertions.assertEquals(BASE_URL + expectedUrl, actualUrl); //check URL
        Assertions.assertEquals("Web form", expectedTitle.getText()); //check

        driver.getPageSource();
    }
}

//        String chapter3 = driver.findElement(By.linkText("Chapter 3. WebDriver Fundamentals")).getText();// - по тексту
//        String chapter4 = driver.findElement(By.xpath("//h5[text() = 'Chapter 4. Browser-Agnostic Features']")).getText();
//        String chapter5 = driver.findElement(By.xpath("//a[text() = 'Geolocation']/../h5[test() = 'Chapter 5. Browser-Specific Manipulation']")).getText();
//
//        String chapter7 = driver.findElement(By.linkText("Chapter 7. The Page Object Model (POM)")).getText();// - по тексту
//        String chapter8 = driver.findElement(By.xpath("//h5[text() = 'Chapter 8. Testing Framework Specifics']")).getText();
//        String chapter9 = driver.findElement(By.xpath("//a[text() = 'A/B Testing']/../h5[test() = 'Chapter 9. Third-Party Integrations']")).getText();





