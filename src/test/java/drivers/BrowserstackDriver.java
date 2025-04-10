package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.AuthConfig;
import config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {
    private static final BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class);
    private static final AuthConfig AUTH_CONFIG = ConfigFactory.create(AuthConfig.class);

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("browserstack.user", AUTH_CONFIG.getUserName());
        caps.setCapability("browserstack.key", AUTH_CONFIG.getPassUser());
        caps.setCapability("app", config.getApp());
        caps.setCapability("device", config.getDevice());
        caps.setCapability("os_version", config.getOsVersion());
        caps.setCapability("project", config.getProjectName());
        caps.setCapability("build", config.getBuildName());
        caps.setCapability("name", config.getName());
        caps.setCapability("url", config.getBrowserstackUrl());
        caps.setCapability("unicodeKeyboard", true);
        caps.setCapability("resetKeyboard", true);

        try {
            return new RemoteWebDriver(new URL(config.getBrowserstackUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
