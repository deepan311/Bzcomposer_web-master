package com.CrmServices.Helper;

public class ResponseObject {

    private Object data;
    private String SuccessMessage;
    private String ErrorMessage;
    private String ErrorCode;
    private Boolean isError;

    private int statusCode;



    public ResponseObject() {
    }

    public static ResponseObject createError(String ErrorMessage, String ErrorCode, int statusCode) {
        return new ResponseObject(null, null, ErrorMessage, ErrorCode, true, statusCode);
    }

    public static ResponseObject createSuccess(Object data, String SuccessMessage, int statusCode) {
        return new ResponseObject(data, SuccessMessage, null, null, false, statusCode);
    }


    public ResponseObject(Object data, String SuccessMessage, String ErrorMessage, String ErrorCode, Boolean isError, int statusCode) {
        this.data = data;
        this.SuccessMessage = SuccessMessage;
        this.ErrorMessage = ErrorMessage;
        this.ErrorCode = ErrorCode;

        this.isError = isError;
        this.statusCode = statusCode;
    }





    public String getSuccessMessage() {
        return SuccessMessage;
    }


    public String getErrorMessage() {
        return ErrorMessage;
    }


    public void setSuccessMessage(String SuccessMessage) {
        this.SuccessMessage = SuccessMessage;
    }


    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }


    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }


    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }


    public void setIsError(Boolean isError) {
        this.isError = isError;
    }

    public Boolean getIsError() {
        return isError;
    }


    public int getStatusCode() {    
        return statusCode;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    public Object getData() {
        return data;
    }

    
    @Override

        public String toString() {
        return "ResponseObject [data=" + data + ", SuccessMessage=" + SuccessMessage + ", ErrorMessage=" + ErrorMessage + ", ErrorCode=" + ErrorCode + ", isError=" + isError + ", statusCode=" + statusCode + "]";
    }

}

