package com.avibha.common.utility;

import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.common.constants.AppConstants;
import com.avibha.common.forms.ReCaptchaResponse;
import com.avibha.common.log.Loger;
import com.nxsol.bizcomposer.common.ConstValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author sarfrazmalik
 */
@Service
public class MyUtility {

    @Autowired
    private RestTemplate restTemplate;
    private static String date_Format_ddmmyyy = "dd/MM/yyyy";
    private static String date_Format_mmddyyy = "MM-dd-yyyy";

    public static String formatLocalDateForUI(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(date_Format_mmddyyy));
    }
    public static String getCurrentDate() {
//		LocalTime currentTime = localTime.plus(Duration.ofHours(1));
        return LocalDate.now().format(DateTimeFormatter.ofPattern(date_Format_mmddyyy));
    }
    public static String getDateBeforeGivenMonths(int months) {
        LocalDate localDate = LocalDate.now().minusMonths(months);
//		LocalTime currentTime = localTime.plus(Duration.ofHours(1));
        return localDate.format(DateTimeFormatter.ofPattern(date_Format_mmddyyy));
    }
    public static boolean isDatePast(final String date) {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(date_Format_mmddyyy);
        LocalDate inputDate = LocalDate.parse(date, dtf);
        return inputDate.isBefore(localDate);
    }
    public static boolean isDateToday(final String date) {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(date_Format_mmddyyy);
        LocalDate inputDate = LocalDate.parse(date, dtf);
        return inputDate.isEqual(localDate);
    }
    public static boolean isDateFuture(final String date) {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(date_Format_mmddyyy);
        LocalDate inputDate = LocalDate.parse(date, dtf);
        return inputDate.isAfter(localDate);
    }
    public static java.sql.Date string2Date(String d) {
        SimpleDateFormat sdf = new SimpleDateFormat(date_Format_mmddyyy);
        Date d1 = null;
        try {
            d1 = sdf.parse(d);
        } catch (ParseException e) {
            Loger.log(2, "ParseException" + e.getMessage());
        }
        return (d1 != null ? new java.sql.Date(d1.getTime()) : new java.sql.Date(new Date().getTime()));
    }
    public static String date2String(Date date) {
        String dateString = "";
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(date_Format_mmddyyy);
            dateString = sdf.format(date);
        }
        return dateString;
    }

    public static boolean isStringNumber(String str){
        if(str == null || str.isEmpty()){
            return false;
        }else{
            return str.matches("\\d+");
        }
    }

    public static boolean isStringOnlyAlfabets(String str){
        if(str == null){
            return false;
        }else{
            return str.matches("[a-zA-Z\\s]+");
        }
    }

    public static String checkDefaultCountryID(String str){
        if(str == null || str.isEmpty() || str.equalsIgnoreCase("0")){
            return ConstValue.countryID;
        }else{
            return str.matches("\\d+")?str:ConstValue.countryID;
        }
    }
    public static String checkDefaultStateID(String str){
        if(str == null || str.isEmpty() || str.equalsIgnoreCase("0")){
            return ConstValue.stateID;
        }else{
            return str.matches("\\d+")?str:ConstValue.stateID;
        }
    }
    public static String checkDefaultCityID(String str){
        if(str == null || str.isEmpty() || str.equalsIgnoreCase("0")){
            return ConstValue.cityID;
        }else{
            return str.matches("\\d+")?str:ConstValue.cityID;
        }
    }

    public static String getCompanyIDFromSession(HttpServletRequest request){
        String companyID = (String)request.getSession().getAttribute("CID");
        if(companyID == null || companyID.trim().isEmpty()){
            companyID = "1";
        }
        return companyID;
    }

//    @PostMapping("/ValidateCaptha")
//    public String LoginValidate(HttpServletRequest request, @RequestParam("g-recaptcha-response") String captchaResponse)
    public boolean verifyCapcha(String captchaResponse, HttpServletRequest request) {
        String capchaVerifyURL = "https://www.google.com/recaptcha/api/siteverify";
        String params = "?secret=6LdYG_YbAAAAAGR0_3TqCiXdaZCbooAiYY8lpsVL&response="+captchaResponse;
        ReCaptchaResponse capResp = restTemplate.exchange(capchaVerifyURL+params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();
        boolean status = false;
        if(capResp.isSuccess()) {
            status = true;
        }else{
            request.setAttribute("captchaResponse", "reCAPTCHA is mandatory");
        }
        return status;
    }

    public static String get3DigitNumber(String num, boolean add1){
        int number = 0;
        if(num != null && !num.isEmpty() && isStringNumber(num)){
            number = Integer.parseInt(num);
        }
        if(add1){
            number++;
        }
        if(number < 10) return "00"+number;
        else if(number < 100) return "0"+number;
        else return number+"";
    }
    public static String getOrderNumberByConfigData(String orderNo, String type, ConfigurationDto configDto, boolean add1){
        String conOrderNo = null;
        if(type.equalsIgnoreCase(AppConstants.EstType)) conOrderNo = configDto.getStartEstimationNum();
        else if(type.equalsIgnoreCase(AppConstants.SOType)) conOrderNo = configDto.getStartSalesOrderNum();
        else if(type.equalsIgnoreCase(AppConstants.POType)) conOrderNo = configDto.getStartPONum();
        else conOrderNo = configDto.getStartInvoiceNum();

        return conOrderNo.substring(0, conOrderNo.indexOf("-")+1) + get3DigitNumber(orderNo, add1);
    }

}
