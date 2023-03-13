package com.chrstnnsl.pages;

import com.chrstnnsl.Base.Remote;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Checkout extends Remote {

    public String cardNumber;

    public String email;

    @FindBy(xpath="//button[contains(text(),'Donate $5.00 once')]")
    WebElement _button;

    @FindBy(id="email")
	WebElement _email;

    @FindBy(id="cardNumber")
    WebElement _cardNumber;

    @FindBy(id="cardExpiry")
    WebElement _cardExpiration;

    @FindBy(id="cardCvc")
    WebElement _cvc;

    @FindBy(id="billingName")
    WebElement _billingName;

    @FindBy(id="enableStripePass")
    WebElement _tickCheckout;

    @FindBy(id="phoneNumber")
    WebElement _phoneNumber;

    @FindBy(xpath="//div[@class='SubmitButton-IconContainer']")
    WebElement _payBtn; 

    @FindBy(id="challengeFrame")
    WebElement _iframe;

    @FindBy(xpath="//button[@class='Button Button--default']")
    WebElement _complete;

    @FindBy(id="session")
    WebElement _sessionId;


    public Checkout() throws MalformedURLException {
		PageFactory.initElements(this.driver, this);
	}

    public Checkout OneTimeDonation() {
        this.driver.get("https://stripe-samples.github.io/github-pages-stripe-checkout/");

        _button.click();

        return this;
    }

    public Checkout isCheckoutPageLoaded() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@data-testid='checkout-container']")));

        return this;
    }

    public Checkout submitCardDetails(String details) {
        if (details.equals("With 3D secure Verfication")) {
            this.email = "With3DsecureVerfication@gmail.com";
            this.cardNumber = "4000 0000 0000 3220";
        } else {
            this.email = "Without3DsecureVerfication@gmail.com";
            this.cardNumber = "4242 4242 4242 4242";
        }
        _email.sendKeys(this.email);
        _cardNumber.sendKeys(this.cardNumber);
        _cardExpiration.sendKeys("12 31");
        _cvc.sendKeys("1231");
        _billingName.sendKeys("christian fernandez");
        _tickCheckout.click();
        _phoneNumber.sendKeys("0912 312 3123");
        _payBtn.click();

        return this;
    }

    public Checkout completeVerification() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
				By.xpath("//div//iframe[contains(@name, 'privateStripeFrame')]")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
				By.xpath("//iframe[@id='challengeFrame']")));

        wait.until(ExpectedConditions.elementToBeClickable(
            By.id("test-source-authorize-3ds"))).click();

        return this;
    }

    public Checkout paymentSucceeded() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//h1[contains(text(),'Your test payment succeeded')]")));

        return this;
    }

    public String sessionIdFromResponse() throws MalformedURLException {

        String currentUrl = this.driver.getCurrentUrl();
        URL url = new URL(currentUrl);
        String[] sessionId = url.getQuery().split("=");

        return sessionId[1];
    }

    public String checkoutSessionId() {
        String sessionId = _sessionId.getText();
        return sessionId;
    }
}