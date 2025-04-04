package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class test {

    @Test
    void Test(){
        open("https://ostrovok.ru/");
        sleep(15000);
    }
}
