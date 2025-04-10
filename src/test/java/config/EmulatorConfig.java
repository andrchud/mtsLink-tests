package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${envMobile}.properties"
})
public interface EmulatorConfig extends Config {

    @Key("deviceModel")
    @DefaultValue("Google Pixel 4")
    String getDeviceModel();

    @Key("systemVersion")
    @DefaultValue("11.0")
    String getSystemVersion();

    @Key("appVersion")
    String getAppVersion();

    @Key("appPackage")
    String getAppPackage();

    @Key("appActivity")
    String getAppActivity();
}