package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;


import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.util.Arrays.asList;

public class TestBaseJunit5 extends BaseTest {

    //Аннотация @CsvSource
    @CsvSource({"TestUser99, 1q2w3e4r5t"})
    @ParameterizedTest(name = "Авторизация в ресурсе software-testing.ru name = {0} password = {1}")
    void softwareTestingAuthorization(String nameUser, String passwordUser){

        $("#sign_in").click();
        $("#ips_username").setValue(nameUser);
        $("#ips_password").setValue(passwordUser);
        $(".ipsButton").click();
    }



    //Аннотация @CsvFileSource
    @CsvFileSource(resources = "testData/CsvTestFile.csv")
    @ParameterizedTest(name =  "Поиск блока с названием и переход = {0} поиск и переход по результату  = {1}")
    void softwareTestingCsvFileSource(String nameData, String valueData){
        $$("#category_197").find(text(nameData)).shouldBe(visible).click();
        $$(".ipb_table a").find(text(valueData)).shouldBe(visible).click();
        //back();
    }



    //Аннотация @Disabled
    @Disabled
    @ValueSource(strings = {"Как правильно задавать вопросы + ЧаВо", "Учебник по TestComplete на русском"})
    @ParameterizedTest(name =  "При поиске в ресурсе software-testing.ru по блоку {0}")
    void softwareTestingDisabled(String searchData){
        $$(".ipb_table").find(text(searchData))
                .shouldBe(visible);
    }

    //Аннотация @EnumSource
    @EnumSource(PortalNav.class)
    @ParameterizedTest(name =  "Поиск совпадение названия в главной верхней навигации в ресурсе software-testing.ru " +
            "по блоку {0}")
    void enumTest(PortalNav portalNav) {
        $$("#portal-nav a").find(text(portalNav.desc)).shouldBe(visible);

    }


    //Аннотация @MethodSource
    static Stream<Arguments> VeryComplexDataProvider() {
        return Stream.of(
                Arguments.of("Selenium", asList("Конференция Heisenbug")));
    }

    @MethodSource(value = "VeryComplexDataProvider")
    @ParameterizedTest(name = "При поиске в яндексе по запросу {0} в результатах отображается текст {1}")
    void TestVeryComplex(String searchData, List<String> expectedResult) {
        $("#adv_search").click();
        $("#query").setValue(searchData).pressEnter();
        $$("#forum_table").shouldHave(CollectionCondition.texts(expectedResult));

    }

    //Аннотация @ValueSource
    @ValueSource(strings = {"Тестирование от лица сотни авторизованных пользователей",
            "Помогите разобраться, очень прошу)"})
    @ParameterizedTest(name =  "При поиске в ресурсе software-testing.ru по блоку {0}")
    void softwareTestingValueSource(String searchData){
        $$(".ipb_table")
                .find(text(searchData)).shouldBe(visible);
    }

}
