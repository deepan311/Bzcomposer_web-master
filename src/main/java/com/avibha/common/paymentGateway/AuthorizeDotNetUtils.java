package com.avibha.common.paymentGateway;

import net.authorize.Environment;
import net.authorize.api.contract.v1.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import net.authorize.api.controller.*;
import net.authorize.api.controller.base.ApiOperationBase;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author sarfrazmalik
 */
public class AuthorizeDotNetUtils {

    private final static String apiLoginId = "7RGW3ywneB9t";
    private final static String transactionKey = "8S2u9Xg4PVgg57j5";
    private final static String clientKey = "93YtUnZqv5g2A37x4huKkm2N7N3vdKCj7frq53r8HknEwj4yE2tNPa5CSY92H3Cn";
    private final static ValidationModeEnum validationMode = ValidationModeEnum.TEST_MODE;

    static {
        // Set the request to operate in either the sandbox or production environment
        ApiOperationBase.setEnvironment(Environment.SANDBOX);
    }

    // Create object with merchant authentication details
    private MerchantAuthenticationType getMerchantAuthDetails(){
        MerchantAuthenticationType merchantAuthType  = new MerchantAuthenticationType() ;
        merchantAuthType.setName(apiLoginId);
        merchantAuthType.setTransactionKey(transactionKey);
        return merchantAuthType;
    }

    /**
     * Create Customer
     */
    public ANetApiResponse createCustomerProfile(String customerEmail, String cardNo, String expYYYY_MM, String cvv) {
        CreditCardType creditCard = new CreditCardType();
        creditCard.setCardNumber(cardNo);
        creditCard.setExpirationDate(expYYYY_MM);
        creditCard.setCardCode(cvv);
        PaymentType paymentType = new PaymentType();
        paymentType.setCreditCard(creditCard);

        // Set payment profile data
        CustomerPaymentProfileType customerPaymentProfileType = new CustomerPaymentProfileType();
        customerPaymentProfileType.setCustomerType(CustomerTypeEnum.INDIVIDUAL);
        customerPaymentProfileType.setPayment(paymentType);

        // Set customer profile data
        CustomerProfileType customerProfileType = new CustomerProfileType();
//        customerProfileType.setMerchantCustomerId("M_" + eMail);
//        customerProfileType.setDescription("Profile description for " + eMail);
        customerProfileType.setEmail(customerEmail);
        customerProfileType.getPaymentProfiles().add(customerPaymentProfileType);

        // Create the API request and set the parameters for this specific request
        CreateCustomerProfileRequest apiRequest = new CreateCustomerProfileRequest();
        apiRequest.setMerchantAuthentication(getMerchantAuthDetails());
        apiRequest.setProfile(customerProfileType);
        apiRequest.setValidationMode(validationMode);

        CreateCustomerProfileController controller = new CreateCustomerProfileController(apiRequest);
        controller.execute();
        CreateCustomerProfileResponse response = controller.getApiResponse();
        if (response != null) {
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                System.out.println("getCustomerProfileId: "+ response.getCustomerProfileId());
                if (!response.getCustomerPaymentProfileIdList().getNumericString().isEmpty()) {
                    System.out.println(response.getCustomerPaymentProfileIdList().getNumericString());
                    System.out.println(response.getCustomerPaymentProfileIdList().getNumericString().get(0));
                }
                if (!response.getCustomerShippingAddressIdList().getNumericString().isEmpty()) {
                    System.out.println(response.getCustomerShippingAddressIdList().getNumericString().get(0));
                }
                if (!response.getValidationDirectResponseList().getString().isEmpty()) {
                    System.out.println(response.getValidationDirectResponseList().getString().get(0));
                }
            }
            else {
                System.out.println("Failed to create customer profile:  " + response.getMessages().getResultCode());
                for(MessagesType.Message msg: response.getMessages().getMessage()){
                    System.out.println("Message:  " + msg.getCode()+" : "+msg.getText());
                }
            }
        } else {
            ANetApiResponse errorResponse = controller.getErrorResponse();
            System.out.println("Failed to get response");
            if (!errorResponse.getMessages().getMessage().isEmpty()) {
                System.out.println("Error: "+errorResponse.getMessages().getMessage().get(0).getCode()+" \n"+ errorResponse.getMessages().getMessage().get(0).getText());
            }
        }
        return response;
    }

    /**
     * Update Customer
     */
    public ANetApiResponse updateCustomerProfile(String customerProfileId, String customerEmail) {
        ApiOperationBase.setMerchantAuthentication(getMerchantAuthDetails());
        CustomerProfileInfoExType customer =  new CustomerProfileInfoExType();
        customer.setEmail(customerEmail);
        customer.setCustomerProfileId(customerProfileId);
        customer.setProfileType(CustomerProfileTypeEnum.REGULAR);

        UpdateCustomerProfileRequest apiRequest = new UpdateCustomerProfileRequest();
        apiRequest.setProfile(customer);
        UpdateCustomerProfileController controller = new UpdateCustomerProfileController(apiRequest);
        controller.execute();
        UpdateCustomerProfileResponse response = controller.getApiResponse();
        if (response!=null) {
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                System.out.println(response.getMessages().getMessage().get(0).getCode());
                System.out.println(response.getMessages().getMessage().get(0).getText());
            }
            else {
                System.out.println("Failed to update customer profile:  " + response.getMessages().getResultCode());
            }
        }
        return response;
    }

    /**
     * get Customer details
     */
    public ANetApiResponse getCustomerProfileDetails(String customerProfileId) {
        ApiOperationBase.setMerchantAuthentication(getMerchantAuthDetails());
        GetCustomerProfileRequest apiRequest = new GetCustomerProfileRequest();
        apiRequest.setCustomerProfileId(customerProfileId);

        GetCustomerProfileController controller = new GetCustomerProfileController(apiRequest);
        controller.execute();
        GetCustomerProfileResponse response = controller.getApiResponse();
        if (response!=null) {
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                System.out.println(response.getMessages().getMessage().get(0).getCode());
                System.out.println(response.getMessages().getMessage().get(0).getText());

                System.out.println(response.getProfile().getMerchantCustomerId());
                System.out.println(response.getProfile().getDescription());
                System.out.println(response.getProfile().getEmail());
                System.out.println(response.getProfile().getCustomerProfileId());

                if (!response.getProfile().getPaymentProfiles().isEmpty()) {
                    System.out.println(response.getProfile().getPaymentProfiles().get(0).getCustomerPaymentProfileId());
                    System.out.println(response.getProfile().getPaymentProfiles().get(0).getPayment().getCreditCard().getCardNumber());
                    System.out.println(response.getProfile().getPaymentProfiles().get(0).getPayment().getCreditCard().getExpirationDate());
                }
                if ((response.getSubscriptionIds() != null) && (response.getSubscriptionIds().getSubscriptionId() != null) &&
                        (!response.getSubscriptionIds().getSubscriptionId().isEmpty())) {
                    System.out.println("List of subscriptions:");
                    for (String subscriptionid : response.getSubscriptionIds().getSubscriptionId())
                        System.out.println(subscriptionid);
                }

            } else {
                System.out.println("Failed to get customer profile:  " + response.getMessages().getResultCode());
            }
        }
        return response;
    }

    /**
     * Delete Customer details
     */
    public ANetApiResponse deleteCustomerProfileDetails(String customerProfileId) {
        ApiOperationBase.setMerchantAuthentication(getMerchantAuthDetails());
        DeleteCustomerProfileRequest apiRequest = new DeleteCustomerProfileRequest();
        apiRequest.setCustomerProfileId(customerProfileId);

        DeleteCustomerProfileController controller = new DeleteCustomerProfileController(apiRequest);
        controller.execute();
        DeleteCustomerProfileResponse response = controller.getApiResponse();
        if (response!=null) {
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                System.out.println(response.getMessages().getMessage().get(0).getCode());
                System.out.println(response.getMessages().getMessage().get(0).getText());
            }
            else {
                System.out.println("Failed to delete customer profile:  " + response.getMessages().getResultCode());
            }
        }
        return response;
    }

    /**
     * Add Customer-Payment-Profile or Credit-Card
     */
    public ANetApiResponse addCreditCard(String customerProfileId, String cardNo, String expYYYY_MM, String cvv) {
        ApiOperationBase.setMerchantAuthentication(getMerchantAuthDetails());
        CreateCustomerPaymentProfileRequest apiRequest = new CreateCustomerPaymentProfileRequest();
        apiRequest.setMerchantAuthentication(getMerchantAuthDetails());
        apiRequest.setCustomerProfileId(customerProfileId);
        apiRequest.setValidationMode(validationMode);

//        CustomerAddressType customerAddress = new CustomerAddressType();
//        customerAddress.setFirstName("test");
//        customerAddress.setLastName("scenario");
//        customerAddress.setAddress("123 Main Street");
//        customerAddress.setCity("Bellevue");
//        customerAddress.setState("WA");
//        customerAddress.setZip("98004");
//        customerAddress.setCountry("USA");
//        customerAddress.setPhoneNumber("000-000-0000");

        //credit card details
        CreditCardType creditCard = new CreditCardType();
        creditCard.setCardNumber(cardNo);
        creditCard.setExpirationDate(expYYYY_MM);
        creditCard.setCardCode(cvv);

        CustomerPaymentProfileType profile = new CustomerPaymentProfileType();
//        profile.setBillTo(customerAddress);
        PaymentType payment = new PaymentType();
        payment.setCreditCard(creditCard);
        profile.setPayment(payment);

        apiRequest.setPaymentProfile(profile);
        CreateCustomerPaymentProfileController controller = new CreateCustomerPaymentProfileController(apiRequest);
        controller.execute();
        CreateCustomerPaymentProfileResponse response = controller.getApiResponse();
        if (response != null) {
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                System.out.println(response.getCustomerPaymentProfileId());
                System.out.println(response.getMessages().getMessage().get(0).getCode());
                System.out.println(response.getMessages().getMessage().get(0).getText());
                if (response.getValidationDirectResponse() != null)
                    System.out.println(response.getValidationDirectResponse());
            }
            else {
                System.out.println("Failed to create customer payment profile:  " + response.getMessages().getResultCode());
            }
        }

        return response;
    }

    /**
     * Get Credit-Card-details
     */
    public ANetApiResponse getCreditCardDetails(String customerProfileId, String customerPaymentProfileId) {
        ApiOperationBase.setMerchantAuthentication(getMerchantAuthDetails());
        GetCustomerPaymentProfileRequest apiRequest = new GetCustomerPaymentProfileRequest();
        apiRequest.setCustomerProfileId(customerProfileId);
        apiRequest.setCustomerPaymentProfileId(customerPaymentProfileId);

        GetCustomerPaymentProfileController controller = new GetCustomerPaymentProfileController(apiRequest);
        controller.execute();
        GetCustomerPaymentProfileResponse response = controller.getApiResponse();
        if (response!=null) {
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                System.out.println(response.getMessages().getMessage().get(0).getCode());
                System.out.println(response.getMessages().getMessage().get(0).getText());
                System.out.println(response.getPaymentProfile().getCustomerPaymentProfileId());

                System.out.println(response.getPaymentProfile().getPayment().getCreditCard().getCardNumber());
                System.out.println(response.getPaymentProfile().getPayment().getCreditCard().getExpirationDate());

                if ((response.getPaymentProfile().getSubscriptionIds() != null) && (response.getPaymentProfile().getSubscriptionIds().getSubscriptionId() != null) &&
                        (!response.getPaymentProfile().getSubscriptionIds().getSubscriptionId().isEmpty())) {
                    System.out.println("List of subscriptions:");
                    for (String subscriptionid : response.getPaymentProfile().getSubscriptionIds().getSubscriptionId())
                        System.out.println(subscriptionid);
                }
            } else {
                System.out.println("Failed to get customer payment profile:  " + response.getMessages().getResultCode());
            }
        }
        return response;
    }

    /**
     * Validate-Customer-Payment-Profile or Credit-Card
     */
    public ANetApiResponse validateCreditCardDetails(String customerProfileId, String customerPaymentProfileId, String cardCVV) {
        ApiOperationBase.setMerchantAuthentication(getMerchantAuthDetails());
        ValidateCustomerPaymentProfileRequest apiRequest = new ValidateCustomerPaymentProfileRequest();
        apiRequest.setCustomerProfileId(customerProfileId);
        apiRequest.setCustomerPaymentProfileId(customerPaymentProfileId);
        apiRequest.setValidationMode(validationMode);
        apiRequest.setCardCode(cardCVV);
        //apiRequest.setCustomerShippingAddressId("1");

        ValidateCustomerPaymentProfileController  controller = new ValidateCustomerPaymentProfileController(apiRequest);
        controller.execute();
        ValidateCustomerPaymentProfileResponse response = controller.getApiResponse();
        if (response!=null) {
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                System.out.println(response.getMessages().getMessage().get(0).getCode());
                System.out.println(response.getMessages().getMessage().get(0).getText());
                System.out.println(response.getDirectResponse());
            }
            else {
                System.out.println("Failed to validate customer payment profile:  " + response.getMessages().getResultCode());
            }
        }
        return response;
    }

    /**
     * Delete-Credit-Card
     */
    public ANetApiResponse deleteCreditCardDetails(String customerProfileId, String customerPaymentProfileId) {
        ApiOperationBase.setMerchantAuthentication(getMerchantAuthDetails());
        DeleteCustomerPaymentProfileRequest apiRequest = new DeleteCustomerPaymentProfileRequest();
        apiRequest.setCustomerProfileId(customerProfileId);
        apiRequest.setCustomerPaymentProfileId(customerPaymentProfileId);

        DeleteCustomerPaymentProfileController controller = new DeleteCustomerPaymentProfileController(apiRequest);
        controller.execute();
        DeleteCustomerPaymentProfileResponse response = controller.getApiResponse();
        if (response!=null) {
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                System.out.println(response.getMessages().getMessage().get(0).getCode());
                System.out.println(response.getMessages().getMessage().get(0).getText());
            }
            else {
                System.out.println("Failed to delete customer payment profile:  " + response.getMessages().getResultCode());
            }
        }
        return response;
    }

    /**
     * chargeCreditCard
     */
    public ANetApiResponse chargeCreditCard(String cardNo, String expYYYY_MM, Double amount) {

        // Populate the payment data
        PaymentType paymentType = new PaymentType();
        CreditCardType creditCard = new CreditCardType();
        creditCard.setCardNumber(cardNo);
        creditCard.setExpirationDate(expYYYY_MM);
        paymentType.setCreditCard(creditCard);

        // Set email address (optional)
//        CustomerDataType customer = new CustomerDataType();
//        customer.setEmail("test@test.test");

        // Create the payment transaction object
        TransactionRequestType txnRequest = new TransactionRequestType();
        txnRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
        txnRequest.setPayment(paymentType);
//        txnRequest.setCustomer(customer);
        txnRequest.setAmount(new BigDecimal(amount).setScale(2, RoundingMode.CEILING));

        // Create the API request and set the parameters for this specific request
        CreateTransactionRequest apiRequest = new CreateTransactionRequest();
        apiRequest.setMerchantAuthentication(getMerchantAuthDetails());
        apiRequest.setTransactionRequest(txnRequest);

        // Call the controller
        CreateTransactionController controller = new CreateTransactionController(apiRequest);
        controller.execute();
        CreateTransactionResponse response = controller.getApiResponse();
        if (response!=null) {
            // If API Response is OK, go ahead and check the transaction response
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                TransactionResponse result = response.getTransactionResponse();
                if (result.getMessages() != null) {
                    System.out.println("Successfully created transaction with Transaction ID: " + result.getTransId());
                    System.out.println("Response Code: " + result.getResponseCode());
                    System.out.println("Message Code: " + result.getMessages().getMessage().get(0).getCode());
                    System.out.println("Description: " + result.getMessages().getMessage().get(0).getDescription());
                    System.out.println("Auth Code: " + result.getAuthCode());
                } else {
                    System.out.println("Failed Transaction.");
                    if (response.getTransactionResponse().getErrors() != null) {
                        System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                        System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                    }
                }
            } else {
                System.out.println("Failed Transaction.");
                if (response.getTransactionResponse() != null && response.getTransactionResponse().getErrors() != null) {
                    System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                    System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                } else {
                    System.out.println("Error Code: " + response.getMessages().getMessage().get(0).getCode());
                    System.out.println("Error message: " + response.getMessages().getMessage().get(0).getText());
                }
            }
        } else {
            // Display the error code and message when response is null
            ANetApiResponse errorResponse = controller.getErrorResponse();
            System.out.println("Failed to get response");
            if (!errorResponse.getMessages().getMessage().isEmpty()) {
                System.out.println("Error: "+errorResponse.getMessages().getMessage().get(0).getCode()+" \n"+ errorResponse.getMessages().getMessage().get(0).getText());
            }
        }
        return response;
    }

    /**
     * Charge-Customer-Profile that saved into our database
     */
    public ANetApiResponse ChargeCustomerProfile(String customerProfileId, String customerPaymentProfileId, Double amount) {
        ApiOperationBase.setMerchantAuthentication(getMerchantAuthDetails());
        // Set the profile ID to charge
        CustomerProfilePaymentType profileToCharge = new CustomerProfilePaymentType();
        profileToCharge.setCustomerProfileId(customerProfileId);
        PaymentProfile paymentProfile = new PaymentProfile();
        paymentProfile.setPaymentProfileId(customerPaymentProfileId);
        profileToCharge.setPaymentProfile(paymentProfile);

        // Create the payment transaction request
        TransactionRequestType txnRequest = new TransactionRequestType();
        txnRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
        txnRequest.setProfile(profileToCharge);
        txnRequest.setAmount(new BigDecimal(amount).setScale(2, RoundingMode.CEILING));

        CreateTransactionRequest apiRequest = new CreateTransactionRequest();
        apiRequest.setTransactionRequest(txnRequest);
        CreateTransactionController controller = new CreateTransactionController(apiRequest);
        controller.execute();
        CreateTransactionResponse response = controller.getApiResponse();
        if (response!=null) {
            // If API Response is ok, go ahead and check the transaction response
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                TransactionResponse result = response.getTransactionResponse();
                if (result.getMessages() != null) {
                    System.out.println("Successfully created transaction with Transaction ID: " + result.getTransId());
                    System.out.println("Response Code: " + result.getResponseCode());
                    System.out.println("Message Code: " + result.getMessages().getMessage().get(0).getCode());
                    System.out.println("Description: " + result.getMessages().getMessage().get(0).getDescription());
                    System.out.println("Auth Code: " + result.getAuthCode());
                }
                else {
                    System.out.println("Failed Transaction.");
                    if (response.getTransactionResponse().getErrors() != null) {
                        System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                        System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                    }
                }
            }
            else {
                System.out.println("Failed Transaction.");
                if (response.getTransactionResponse() != null && response.getTransactionResponse().getErrors() != null) {
                    System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                    System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                }
                else {
                    System.out.println("Error Code: " + response.getMessages().getMessage().get(0).getCode());
                    System.out.println("Error message: " + response.getMessages().getMessage().get(0).getText());
                }
            }
        }
        else {
            System.out.println("Null Response.");
        }
        return response;
    }

    /**
     * refundTransaction
     */
    public ANetApiResponse refundTransaction(String cardLast4Digit, String expYYYY_MM, Double transactionAmount, String transactionID) {
        ApiOperationBase.setMerchantAuthentication(getMerchantAuthDetails());
        // Create a payment object, last 4 of the credit card and expiration date are required
        PaymentType paymentType = new PaymentType();
        CreditCardType creditCard = new CreditCardType();
        creditCard.setCardNumber(cardLast4Digit);
        creditCard.setExpirationDate(expYYYY_MM);
        paymentType.setCreditCard(creditCard);

        // Create the payment transaction request
        TransactionRequestType txnRequest = new TransactionRequestType();
        txnRequest.setTransactionType(TransactionTypeEnum.REFUND_TRANSACTION.value());
        txnRequest.setRefTransId(transactionID);
        txnRequest.setAmount(new BigDecimal(transactionAmount.toString()));
        txnRequest.setPayment(paymentType);

        // Make the API Request
        CreateTransactionRequest apiRequest = new CreateTransactionRequest();
        apiRequest.setTransactionRequest(txnRequest);
        CreateTransactionController controller = new CreateTransactionController(apiRequest);
        controller.execute();
        CreateTransactionResponse response = controller.getApiResponse();
        if (response!=null) {
            // If API Response is ok, go ahead and check the transaction response
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                TransactionResponse result = response.getTransactionResponse();
                if (result.getMessages() != null) {
                    System.out.println("Successfully created transaction with Transaction ID: " + result.getTransId());
                    System.out.println("Response Code: " + result.getResponseCode());
                    System.out.println("Message Code: " + result.getMessages().getMessage().get(0).getCode());
                    System.out.println("Description: " + result.getMessages().getMessage().get(0).getDescription());
                    System.out.println("Auth Code: " + result.getAuthCode());
                } else {
                    System.out.println("Failed Transaction.");
                    if (response.getTransactionResponse().getErrors() != null) {
                        System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                        System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                    }
                }
            } else {
                System.out.println("Failed Transaction.");
                if (response.getTransactionResponse() != null && response.getTransactionResponse().getErrors() != null) {
                    System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                    System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                } else {
                    System.out.println("Error Code: " + response.getMessages().getMessage().get(0).getCode());
                    System.out.println("Error message: " + response.getMessages().getMessage().get(0).getText());
                }
            }
        } else {
            System.out.println("Null Response.");
        }
        return response;
    }

    /**
     *paypalExpressCheckoutForAuthoriseAndCapture
     */
    public ANetApiResponse paypalExpressCheckoutForAuthoriseAndCapture(Double amount) {
        ApiOperationBase.setMerchantAuthentication(getMerchantAuthDetails());
        PayPalType payPalType = new PayPalType();
        payPalType.setSuccessUrl("http://www.merchanteCommerceSite.com/Success/TC25262");
        payPalType.setCancelUrl("http://www.merchanteCommerceSite.com/Success/TC25262");

        //standard api call to retrieve response
        PaymentType paymentType = new PaymentType();
        paymentType.setPayPal(payPalType);

        // Create the payment transaction request
        TransactionRequestType transactionRequest = new TransactionRequestType();
        transactionRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
        transactionRequest.setPayment(paymentType);
        transactionRequest.setAmount(new BigDecimal(amount).setScale(2, RoundingMode.CEILING));

        // Make the API Request
        CreateTransactionRequest apiRequest = new CreateTransactionRequest();
        apiRequest.setTransactionRequest(transactionRequest);
        CreateTransactionController controller = new CreateTransactionController(apiRequest);
        controller.execute();
        CreateTransactionResponse response = controller.getApiResponse();
        if (response!=null) {
            // If API Response is ok, go ahead and check the transaction response
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                TransactionResponse result = response.getTransactionResponse();
                if (result.getMessages() != null) {
                    System.out.println("Successfully created transaction with Transaction ID: " + result.getTransId());
                    System.out.println("Response Code: " + result.getResponseCode());
                    System.out.println("Message Code: " + result.getMessages().getMessage().get(0).getCode());
                    System.out.println("Description: " + result.getMessages().getMessage().get(0).getDescription());
                    System.out.println("Secure Acceptance URL : " + result.getSecureAcceptance().getSecureAcceptanceUrl());
                }
                else {
                    System.out.println("Failed Transaction.");
                    if (response.getTransactionResponse().getErrors() != null) {
                        System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                        System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                    }
                }
            }
            else {
                System.out.println("Failed Transaction.");
                if (response.getTransactionResponse() != null && response.getTransactionResponse().getErrors() != null) {
                    System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                    System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                }
                else {
                    System.out.println("Error Code: " + response.getMessages().getMessage().get(0).getCode());
                    System.out.println("Error message: " + response.getMessages().getMessage().get(0).getText());
                }
            }
        }
        else {
            System.out.println("Null Response.");
        }
        return response;
    }

    /**
     * createSubscriptionFromCustomerProfile
     */
    public ANetApiResponse createSubscriptionFromCustomerProfile(short intervalLength, Double amount, String profileId, String paymentProfileId, String customerAddressId) {
        ApiOperationBase.setMerchantAuthentication(getMerchantAuthDetails());
        // Set up payment schedule
        PaymentScheduleType schedule = new PaymentScheduleType();
        PaymentScheduleType.Interval interval = new PaymentScheduleType.Interval();
        interval.setLength(intervalLength);
        interval.setUnit(ARBSubscriptionUnitEnum.DAYS);
        schedule.setInterval(interval);
        try {
            XMLGregorianCalendar startDate = DatatypeFactory.newInstance().newXMLGregorianCalendar();
            startDate.setDay(30);
            startDate.setMonth(12);
            startDate.setYear(2020);
            schedule.setStartDate(startDate);
        }
        catch(Exception e) { }

        schedule.setTotalOccurrences((short)12);
        schedule.setTrialOccurrences((short)1);

        CustomerProfileIdType profile = new CustomerProfileIdType();
        profile.setCustomerProfileId(profileId);
        profile.setCustomerPaymentProfileId(paymentProfileId);
        profile.setCustomerAddressId(customerAddressId);

        ARBSubscriptionType arbSubscriptionType = new ARBSubscriptionType();
        arbSubscriptionType.setPaymentSchedule(schedule);
        arbSubscriptionType.setAmount(new BigDecimal(amount).setScale(2, RoundingMode.CEILING));
        arbSubscriptionType.setTrialAmount(new BigDecimal(0.0).setScale(2, RoundingMode.CEILING));
        arbSubscriptionType.setProfile(profile);

        // Make the API Request
        ARBCreateSubscriptionRequest apiRequest = new ARBCreateSubscriptionRequest();
        apiRequest.setSubscription(arbSubscriptionType);
        ARBCreateSubscriptionController controller = new ARBCreateSubscriptionController(apiRequest);
        controller.execute();
        ARBCreateSubscriptionResponse response = controller.getApiResponse();
        if (response!=null) {
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                System.out.println(response.getSubscriptionId());
                System.out.println(response.getMessages().getMessage().get(0).getCode());
                System.out.println(response.getMessages().getMessage().get(0).getText());
            } else {
                System.out.println("Failed to create Subscription:  " + response.getMessages().getResultCode());
                System.out.println(response.getMessages().getMessage().get(0).getText());
            }
        }
        return response;
    }

    /**
     * CancelSubscription
     */
    public ANetApiResponse CancelSubscription(String subscriptionId) {
        ApiOperationBase.setMerchantAuthentication(getMerchantAuthDetails());
        // Make the API Request
        ARBCancelSubscriptionRequest apiRequest = new ARBCancelSubscriptionRequest();
        apiRequest.setSubscriptionId(subscriptionId);
        ARBCancelSubscriptionController controller = new ARBCancelSubscriptionController(apiRequest);
        controller.execute();
        ARBCancelSubscriptionResponse response = controller.getApiResponse();
        if (response!=null) {
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                System.out.println(response.getMessages().getMessage().get(0).getCode());
                System.out.println(response.getMessages().getMessage().get(0).getText());
            } else {
                System.out.println("Failed to cancel Subscription:  " + response.getMessages().getResultCode());
            }
        }
        return response;
    }

    public static void main(String[] args) {
        String cardNoOK = "4012888818888";    //amex: 370000000000002, master: 5424000000000015, visa: 4012888818888, 4007000000027
        String cardNoError = "4222222222222";
        String expYYYY_MM = "2022-12";
        String cvv = "123";
        AuthorizeDotNetUtils authUtils = new AuthorizeDotNetUtils();
        ANetApiResponse mAuth = authUtils.createCustomerProfile( "malik@test.com", cardNoOK, expYYYY_MM, cvv);
        System.out.println(mAuth.getRefId());
        System.out.println(mAuth.getMessages().getResultCode());
    }
}
