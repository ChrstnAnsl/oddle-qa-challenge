package com.chrstnnsl.Base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Remote {
    
    public WebDriver driver;

    public Remote() throws MalformedURLException {
        
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        caps.setPlatform(Platform.ANY);
        this.driver = new RemoteWebDriver(new URL("http://192.168.1.17:4444"), caps);
    }
}
