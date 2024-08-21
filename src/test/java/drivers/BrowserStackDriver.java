package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import config.BrowserStackConfig;

public class BrowserStackDriver implements WebDriverProvider {

    private static final BrowserStackConfig config =
            ConfigFactory.create(
                    BrowserStackConfig.class,
                    System.getProperties()
            );
    private static final AuthConfig authConfig = ConfigFactory.create(AuthConfig.class);

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("browserstack.user", authConfig.bsUser());
        caps.setCapability("browserstack.key", authConfig.bsPassword());
        caps.setCapability("app", config.bsApp());
        caps.setCapability("device", config.bsDevice());
        caps.setCapability("os_version", config.bsVersion());
        caps.setCapability("project", config.bsProject());
        caps.setCapability("build", config.bsBuild());
        caps.setCapability("name", config.bsName());

        try {
            return new RemoteWebDriver(
                    new URL(config.bsUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
