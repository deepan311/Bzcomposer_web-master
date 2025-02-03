package com.avibha.bizcomposer.configuration.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.authorize.api.contract.v1.ANetApiResponse;
import net.authorize.api.contract.v1.CreateTransactionResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RelatedResources;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

import java.util.Iterator;

@Controller
public class PaymentController {

    @Autowired
    private PaypalUtils paypalUtils;
    @Autowired
    private AuthorizeNetUtils authorizeNetUtils;

    @GetMapping("/authorize-payment-process")
    @ResponseBody
    public Object authorizeNetPayment(HttpServletRequest request) {
        ANetApiResponse response = authorizeNetUtils.checkoutPayment(1.0);
        return response;
    }

    @GetMapping("/paypal-payment-process/{plan}")
    public String paypalPayment(@PathVariable String plan, HttpServletRequest request) {
        String totalAmount = null;
        if(plan.equalsIgnoreCase("professional")) {
            totalAmount = "39.95";
        }else if(plan.equalsIgnoreCase("enterprise")) {
            totalAmount = "89.00";
        }else {
            totalAmount = "9.95";
        }
        String desc = "Testing payment from Paypal Account";
        Payment createdPayment = paypalUtils.createdPaymentFromPayalAccount(totalAmount, desc);
        Iterator<Links> links = createdPayment.getLinks().iterator();
        while (links.hasNext()) {
            Links link = (Links) links.next();
            if (link.getRel().equalsIgnoreCase("approval_url"))
                return "redirect:" + link.getHref();
        }
        return "redirect:/paypal-payment-cancel";
    }

    @GetMapping("/paypal-payment-cancel")
    public String cancel(Model model) throws JSONException {
        System.out.println("--------------payment-cancel-------------");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", 0);
        jsonResponse.put("error_message", "Something went wrong to process the payment!");
      //  model.addAttribute("paymentStatus", 0);
        model.addAttribute("paymentDetails", jsonResponse.names());
        return "/sales/paymentResponse";
    }

    @GetMapping("/paypal-payment-success")
    public String success(@RequestParam String paymentId, @RequestParam String PayerID, Model model) throws JSONException {
        System.out.println("--------------payment-success-------------");
        JSONObject jsonResponse = new JSONObject();
        int paymentStatus = 0;
        try {
            Payment createdPayment = paypalUtils.executePaymentByPaymentIdAndPayerID(paymentId, PayerID);
            System.out.println(Payment.getLastResponse());

            jsonResponse.put("status", 1);
            jsonResponse.put("error_message", "Your payment has been recieved successfully!");
            jsonResponse.put("state", createdPayment.getState());
            jsonResponse.put("payment_id", createdPayment.getId());
            jsonResponse.put("sale_id", createdPayment.getTransactions().get(0).getRelatedResources().get(0).getSale().getId());
            jsonResponse.put("amount", createdPayment.getTransactions().get(0).getRelatedResources().get(0).getSale().getAmount().getTotal());
            paymentStatus = 1;
        } catch (PayPalRESTException ex) {
            paymentStatus = 0;
            jsonResponse.put("status", 0);
            jsonResponse.put("error_code", ex.getResponsecode());
            jsonResponse.put("error_name", ex.getDetails().getName());
            jsonResponse.put("error_message", ex.getDetails().getMessage());
        }
        model.addAttribute("paymentStatus", paymentStatus);
        model.addAttribute("paymentDetails", jsonResponse. names());
        return "/sales/paymentResponse";
    }

}
