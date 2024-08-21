package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:samsung.properties"
})
public interface BrowserStackConfig extends Config {

    String bsApp();

    String bsDevice();

    String bsVersion();

    String bsProject();

    String bsBuild();

    String bsName();

    String bsUrl();
}