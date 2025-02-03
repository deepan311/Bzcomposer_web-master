package com.pritesh.bizcomposer.accounting.bean;

import java.util.Date;

public class TblBSAddress2 implements Cloneable{

public final static int BILLING_ADDR_TYPE = 1;
    
    public final static int SHIPPING_ADDR_TYPE = 0;
        
    private int id =-1;
    private int cvId=-1;
    private String name="";
    private String addressName ="Noname";
    private String firstName="";
    private String lastName="";
    private String address1="";
    private String address2="";
    private String city="";
    private String state="";
    private String province="";
    private String country="";
    private String zipCode="";
    private String status="N";
    private Date dateAdded=null;
    private String phone="";
    private String cellPhone="";
    private String fax="";
    private String Email="";
    private String active="1";
    private String DropShippAddress="";
    private String Email1="";
    private int isDefault=0;
    public static int addressId = 0;
    //This field is not related with address contain, here it added to manage the multiple billing and 
    //shipping address list on Manage Multiple Billing and Shipping tab
    private boolean isSelected=false;
    
    public boolean isSelected()
    {
        return isSelected;
    }
    public void setSelected(boolean b)
    {
        isSelected=b;
    }

    public Object clone() throws CloneNotSupportedException {
        try {
            TblBSAddress2 address = (TblBSAddress2)super.clone();

            return address;
        } catch (CloneNotSupportedException cnse) {
            throw new CloneNotSupportedException();
        }
    }
    
    /** Creates a new instance of tblBillingAddress */
    public TblBSAddress2() {
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getCvId() {
        return cvId;
    }
    
    public void setCvId(int cvId) {
        this.cvId = cvId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getAddress1() {
        return address1;
    }
    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    
    public String getAddress2() {
        return address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getProvince() {
        return province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getDateAdded() {
        return dateAdded;
    }
    
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    public String getAddressName() {
        return addressName;
    }
    
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
    
    public String toString() {
        return addressName.equals("")?zipCode:addressName;
    }
    
    public boolean equals(Object obj) {
        //check for self-comparison
        if ( this == obj ) return true;
        if ( !(obj instanceof TblBSAddress2) ) return false;
        
        TblBSAddress2 other = (TblBSAddress2)obj;
//        if (this.id!=other.id) return false;
        if (!this.addressName.equals(other.addressName)) return false;
        
        return true;
        
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }
    public String getDropShippAddress() {
        return DropShippAddress;
    }
    public void setDropShippAddress(String DropShippAddress) {
        this.DropShippAddress = DropShippAddress;
    }
    public String getEmail1() {
        return Email1;
    }
    public void setEmail1(String Email1) {
        this.Email1 = Email1;
    }

    /**
     * @return the isDefault
     */
    public int getIsDefault() {
        return isDefault;
    }

    /**
     * @param isDefault the isDefault to set
     */
    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * @return the addressId
     */
    public int getAddressId() {
        return addressId;
    }

    /**
     * @param addressId the addressId to set
     */
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
