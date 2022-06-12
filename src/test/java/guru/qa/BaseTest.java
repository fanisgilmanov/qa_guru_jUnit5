package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeAll
    static void beforeUrl() {
       Configuration.baseUrl = "https://software-testing.ru";
        Configuration.browserSize = "1920x1080";
        Selenide.open("/forum/");
    }




}
