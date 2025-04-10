package tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import drivers.BrowserstackDriver;
import drivers.EmulatorDriver;
import helpers.Attach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        if (System.getProperty("envMobile")==null) {
            Configuration.browser = BrowserstackDriver.class.getName();
        }
        else if (System.getProperty("envMobile").equals("emulator")) {
            Configuration.browser = EmulatorDriver.class.getName();
        } else {
            Configuration.browser = BrowserstackDriver.class.getName();
        }
        Configuration.browserSize = null;
        Configuration.timeout = 30000;

    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        Attach.pageSource();
        if (System.getProperty("envMobile") != null) {
            if (System.getProperty("envMobile").equals("browserstack")) {
                Attach.addVideo(sessionId().toString());
            }
        }
        closeWebDriver();
    }
}