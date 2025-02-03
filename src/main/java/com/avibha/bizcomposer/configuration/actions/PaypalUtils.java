package com.avibha.bizcomposer.configuration.actions;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class PaypalUtils {

    @Autowired
    private HttpServletRequest request;

    private APIContext apiContext;
    private String mode = "sandbox";    //live
    private String clientID = "AQ1NP-8e5MAbIlHod7B0BD8POz6dLOrYnkvFzO-ae5iWq4Hs8XyoQ4TiVmLiVVP2L_qKwiOJ5OjxPiTi";
    private String clientSecret = "EDtBfGSzqAq85Cvic1wn0nafouGpdqAJ87RlnggRLdVfskTloiMgbO5KAfyL4DrgaXT6O0RSe6roxJNp";
//    email: sb-9wbxt8560122@personal.example.com   pwd: ejN_)F90

    public APIContext getApiContext() {
        if (apiContext == null)
            apiContext = new APIContext(clientID, clientSecret, mode);
        apiContext.setRequestId(null);
        return apiContext;
    }

    /**
     * Payment from Paypal-Accout
     */
    public Payment createdPaymentFromPayalAccount(String totalAmount, String desc) {
        Payment createdPayment = null;
        APIContext apiContext = getApiContext();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(totalAmount);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(desc);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        Payer payer = new Payer();
        payer.setPaymentMethod("PAYPAL");
        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(getServerBaseURL() + "/paypal-payment-cancel");
        redirectUrls.setReturnUrl(getServerBaseURL() + "/paypal-payment-success");
        payment.setRedirectUrls(redirectUrls);
        try {
            createdPayment = payment.create(apiContext);
        } catch (PayPalRESTException ex) {
            System.out.println("Payment: " + Payment.getLastRequest() + ": " + ex.getMessage());
        }
        return createdPayment;
    }

    public Payment executePaymentByPaymentIdAndPayerID(String paymentID, String payerID) throws PayPalRESTException {
        APIContext apiContext = getApiContext();
        Payment payment = new Payment();
        payment.setId(paymentID);
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerID);
        return payment.execute(apiContext, paymentExecution);
    }

    private String getServerBaseURL() {
        String url = request.getRequestURL().toString();
        int colonIndex = url.indexOf(":", url.indexOf(":")+1);
        String portNo = url.substring(colonIndex+1, url.indexOf("/", colonIndex+1));
        if(url.contains(portNo)) {
            url = url.substring(0, url.indexOf(portNo)+4);
        }else {
            url = url.substring(0, url.indexOf(".com")+4);
        }
        return url;
    }
}
