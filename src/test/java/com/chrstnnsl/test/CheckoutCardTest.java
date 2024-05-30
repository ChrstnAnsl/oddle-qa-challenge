package com.chrstnnsl.test;

import java.net.MalformedURLException;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.chrstnnsl.pages.Checkout;

public class CheckoutCardTest {

    @Test
    public void without3DSecureVerfication() throws MalformedURLException{
        // Arrange
        Checkout checkout = new Checkout();
        checkout.OneTimeDonation();

        // Act
        checkout.isCheckoutPageLoaded().submitCardDetails("Without 3D secure Verfication");

        // Assert
        checkout.paymentSucceeded();
        Assert.assertEquals(checkout.sessionIdFromResponse(), checkout.checkoutSessionId());
    }

    @Test
    public void with3DSecureVerfication() throws MalformedURLException {
        // Arrange
        Checkout checkout = new Checkout();
        checkout.OneTimeDonation();

        // Act
        checkout.isCheckoutPageLoaded().submitCardDetails("With 3D secure Verfication").completeVerification();

        // Assert
        checkout.paymentSucceeded();
        Assert.assertEquals(checkout.sessionIdFromResponse(), checkout.checkoutSessionId());
    }

}
