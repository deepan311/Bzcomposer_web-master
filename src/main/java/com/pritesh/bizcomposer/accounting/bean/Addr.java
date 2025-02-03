package com.pritesh.bizcomposer.accounting.bean;

public class Addr {

	public int addressID = -1;
    public int cvID = -1;
    public int addressType = -1;
    
    public String companyName = "";
    public String firstName = "";
    public String lastName = "";
    public String address1 = "";
    public String address2 = "";
    public String city = "";
    public String state = "";
    public String zipCode = "";
    public String email = "";
    public String country = "";
    public String province = "";    
    public String cellphone ="";
    public String fax = "";
    public String telephone = "";
    
    public Addr() {
    }
    
    public Addr(TblBSAddress2 bsAddress) {
        
        companyName = bsAddress.getName();
        firstName = bsAddress.getFirstName();
        lastName = bsAddress.getLastName();
        address1 = bsAddress.getAddress1();
        address2 = bsAddress.getAddress2();
        city = bsAddress.getCity();
        state = bsAddress.getState();
        zipCode = bsAddress.getZipCode();        
        email = "";
        country = bsAddress.getCountry();
        province = bsAddress.getProvince();
        cellphone = bsAddress.getCellPhone();
        telephone = bsAddress.getPhone();
        fax = bsAddress.getFax();
    }
    
    public String getAddress(boolean showCountry,boolean showPhone) {
        
        trim();        
        StringBuffer address = new StringBuffer();        
        address.append(companyName+"\n");
        address.append(firstName + " ");
        address.append(lastName + "\n");
        address.append(address1 + "\n");
        address.append(address2 + "\n");
        
        address.append(city);
        if (!state.equals(""))
            address.append(", ");        
        address.append(state);
        address.append(zipCode);        
        if (showCountry) {
            address.append("\n"+country);
        }
        if (showPhone) {
            address.append("\n");
            address.append("Tel: "+telephone+", Fax: "+fax);            
        }
        
        return address.toString();
    }
    
    private void trim() {
        companyName.trim();
        firstName.trim();
        lastName.trim();
        address1.trim();
        address2.trim();
        city.trim();
        state.trim();
        zipCode.trim();
        telephone.trim();
        fax.trim();
        email.trim();
        country.trim();
        province.trim();
        telephone.trim();
        fax.trim();
        cellphone.trim();
    }
    
    public void clear() {
        addressID=  -1;
        cvID = -1;
        companyName = "";
        firstName = "";
        lastName = "";
        address1 = "";
        address2 = "";
        city = "";
        state = "";
        zipCode = "";
        telephone = "";
        fax = "";
        email = "";
        country = "";
        province = "";
        cellphone="";
        fax="";
        telephone="";        
    }
}
