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
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void ternDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldPutCorrectForm() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Терентьева Валентина");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79099999990");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время." , text);

    }

    @Test
    void shouldPutDefice() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иванов-Толмачев Сергей");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79099999990");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время." , text);
    }
@Test
    void nameInInglish() {

    driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Braun Fix");
    driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79099999990");
    driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
    driver.findElement(By.cssSelector(".button__text")).click();
    String text = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
    Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы." , text.trim());
}
@Test
    void wrongNumber() {

    driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иванов Илья");
    driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("89099999990");
    driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
    driver.findElement(By.cssSelector(".button__text")).click();
    String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
    Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678." , text.trim());
}

@Test
    void notNumberInRegistration() {
    driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иванов Илья");
    driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
    driver.findElement(By.cssSelector(".button__text")).click();
    String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
    Assertions.assertEquals("Поле обязательно для заполнения" , text.trim());

}

@Test
    void notName() {
    driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79099999990");
    driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
    driver.findElement(By.cssSelector(".button__text")).click();
    String text = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
    Assertions.assertEquals("Поле обязательно для заполнения" , text.trim());
}

}


