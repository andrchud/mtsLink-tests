package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${envMobile}.properties"
})
public interface BrowserstackConfig extends Config {

    @Key("url")
    @DefaultValue("https://hub.browserstack.com/wd/hub")
    String getBrowserstackUrl();

    @Key("device")
    String getDevice();

    @Key("app")
    String getApp();

    @Key("name")
    String getName();

    String getBuildName();

    String getOsVersion();

    String getProjectName();
}