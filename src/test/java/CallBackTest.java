import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CallBackTest {
    private WebDriver driver;

    @BeforeAll
    static void setAppAll() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void  setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void ternDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void ShouldPutCorrectForm() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Терентьева Валентина");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79099999990");
        driver.findElement(By.cssSelector(".checkbox_size_m ")).click();
        driver.findElement(By.cssSelector(".button_view_extra ")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        Assertions.assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время." , text);

    }

    @Test
    void ShouldPutDefice() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иванов-Толмачев Сергей");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79099999990");
        driver.findElement(By.cssSelector(".checkbox_size_m ")).click();
        driver.findElement(By.cssSelector(".button_view_extra ")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        Assertions.assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время." , text);
    }
@Test
    void NameInInglish() {
    driver.get("http://localhost:9999");
    driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Braun Fix");
    driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79099999990");
    driver.findElement(By.cssSelector(".checkbox_size_m ")).click();
    driver.findElement(By.cssSelector(".button_view_extra ")).click();
    String text = driver.findElement(By.className("input__sub")).getText();
    Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы." , text.trim());
}
@Test
    void WrongNumber() {
    driver.get("http://localhost:9999");
    driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иванов Илья");
    driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("89099999990");
    driver.findElement(By.cssSelector(".checkbox_size_m ")).click();
    driver.findElement(By.cssSelector(".button_view_extra ")).click();
    String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
    Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678." , text.trim());
}
}


