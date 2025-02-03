package com.nxsol.bizcomposer.global.clientvendor;

import com.nxsol.bizcomposer.common.ClientVendorHasService;

public class ClientVendor {

private int cvID = -1;
    
    private String Name = "";
    
    private String Detail = "";
    
    private String CustomerTitle = "";
    
    private int CustomerTitleID = -1;
    
    private String FirstName = "";
    
    private String LastName = "";
    
    private String BillName = "";
    
    private String Address1 = "";
    
    private String Address2= "";
    
    private String City = "";
    
    private String State ="";
    
    private String Province = "";
    
    private String Country = "";
    
    private String ZipCode = "";
    
    private String Phone = "";
    
    private String CellPhone = "";
    
    private String Fax = "";
    
    private String Email  = "";
    
    private String HomePage = "";
    
    private String ResellerTaxID = "";
    
    private int taxable = -1;
    
    private int CVTypeID = -1;
    
    private int CVCategoryID = -1;
    
    private String CVCategoryName =  "";
    
    private int shipCarrierID =  -1;
    
    private int paymentTypeID = -1;
    
    private int salesRepID = -1;
    
    private int termID = -1;

    private int lineOfCreditTermID = -1;

    private int ccTypeID = -1;

    private double customerOpenDebit = 0.0;
    
    private double customerCreditLine = 0.0;

    private double remainingCreditAmount = 0.0;
    
    private double vendorOpenDebit = 0.0;
    
    private double vendorAllowedCredit = 0.0;
    
    private java.util.Date DateAdded = null;
    
    private int active = 1;
    
    private String status ="N";
    
    private int deleted = 0;  
   
    // updated from anil 
    private int priceLevelId=0;
    
    //FinanceCharges
    private boolean isUseIndividual = false;
    
    private double minimumFianceCharge = 0.0;
    private double annualInterestRate = 0.0;
    private int gracePeriod = 0;
    //this is not used
    private boolean assessFinanceCharge = false;
    
    final public static int PRIORITY_BACKUP = 0; //default:transferable
    final public static int PRIORITY_DONOTBACKUP = 1;
    
    //priority is determined when the customer/vendor should be stored/backed up
    private int priority = PRIORITY_BACKUP;
    private ClientVendorHasService hasServices = null;
    
//    private ClientVendorHasService hasServices = null;
    
    public ClientVendorHasService getHasServices() {
		return hasServices;
	}

	public void setHasServices(ClientVendorHasService hasServices) {
		this.hasServices = hasServices;
	}

	//not in database, for finalcial charge
    private double overdueAmount = 0.0;
    private double overdueFinanceChargeAmount = 0.0;
    
    private int itemPriceLevel = 0;
    
    /**default category and pay from bank*/
    private int categoryId = -1;
    private int payFromId = -1;
    private boolean useSpecialMessage = false;
    private String message = "";
    
    private long totalPaidSales = 0;    
    
    private long totalPaidPOs = 0;
    
    private long totalUnPaidSales = 0;    
    
    private long totalUnPaidPOs = 0;
     
    private long totalPaidSalesServices = 0;
    
    private long totalUnPaidSalesServices = 0;
    
    private long totalPOsPaidServices = 0;
    
    private long totalPOsUnPaidServices = 0;
    
    private static boolean isPlevelActive = true;
    
    private int customerGroupID = 0;

    private int form1099Selected = 0;

    private int referenceCustomerID = 0;

    private String lastContactOn = "";

    private char[] pasword;

    private String smcPassword = "";

    private int bankAccountID = -1; //$PJ
   
    
    public ClientVendor() {
    }
    
    public int getPriceLevelID(){
        return priceLevelId;
        
    }
    public void setPriceLevelID(int id)
    {
        priceLevelId=id;
    }
    
    public int getCvID() {
        return cvID;
    }
    
    public void setCvID(int cvID) {
        this.cvID = cvID;
    }
    
    public String getName() {
        return Name;
    }

    public String getFullName() {
        return FirstName+" "+LastName;
    }
    
    public void setName(String Name) {       
        this.Name = Name;
    }
    
    public String getDetail() {
        return Detail;
    }
    
    public void setDetail(String Detail) {
        this.Detail = Detail;
    }
    
    public String getCustomerTitle() {
        return CustomerTitle;
    }
    
    public void setCustomerTitle(String CustomerTitle) {
        this.CustomerTitle = CustomerTitle;
    }
    
    public int getCustomerTitleID() {
        return CustomerTitleID;
    }
    
    public void setCustomerTitleID(int CustomerTitleID) {
        this.CustomerTitleID = CustomerTitleID;
    }
    
    public String getFirstName() {
        return FirstName;
    }
    
    public void setFirstName(String FirstName) {
        //this.FirstName = FirstName;
        this.FirstName = getFirstCharCap(FirstName);
    }
    
    public String getLastName() {
        return LastName;
    }
    
    public void setLastName(String LastName) {
        //this.LastName = LastName;
        this.LastName = getFirstCharCap(LastName);
    }
    
    public String getBillName() {
        return BillName;
    }
    
    public void setBillName(String BillName) {
        this.BillName = BillName;
    }
    
    public String getAddress1() {
        return Address1;
    }
    
    public void setAddress1(String Address1) {
        this.Address1 = Address1;
    }
    
    public String getAddress2() {
        return Address2;
    }
    
    public void setAddress2(String Address2) {
        this.Address2 = Address2;
    }
    
    public String getCity() {
        return City;
    }
    
    public void setCity(String City) {
        this.City = City;
    }
    
    public String getState() {
        return State;
    }
    
    public void setState(String State) {
        this.State = State;
    }
    
    public String getProvince() {
        return Province;
    }
    
    public void setProvince(String Province) {
        this.Province = Province;
    }
    
    public String getCountry() {
        return Country;
    }
    
    public void setCountry(String Country) {
        this.Country = Country;
    }
    
    public String getZipCode() {         
        return ZipCode;
    }
    
    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }
    
    public String getPhone() {
        return Phone;
    }
    
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
    
    public String getCellPhone() {
        return CellPhone;
    }
    
    public void setCellPhone(String CellPhone) {
        this.CellPhone = CellPhone;
    }
    
    public String getFax() {
        return Fax;
    }
    
    public void setFax(String Fax) {
        this.Fax = Fax;
    }
    
    public String getEmail() {
        return Email;
    }
    
    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public String getHomePage() {
        return HomePage;
    }
    
    public void setHomePage(String HomePage) {
        this.HomePage = HomePage;
    }
    
    public String getResellerTaxID() {
        return ResellerTaxID;
    }
    
    public void setResellerTaxID(String resellerTaxID) {
        this.ResellerTaxID = resellerTaxID;
    }
    
    public int getTaxable() {
        return taxable;
    }
    
    public void setTaxable(int taxable) {
        this.taxable = taxable;
    }
    
    public int getCVTypeID() {
        return CVTypeID;
    }
    
    public void setCVTypeID(int CVTypeID) {
        this.CVTypeID = CVTypeID;
    }
    
    public int getCVCategoryID() {
        return CVCategoryID;
    }
    
    public void setCVCategoryID(int CVCategoryID) {
        this.CVCategoryID = CVCategoryID;
    }
    
    public String getCVCategoryName() {
        return CVCategoryName;
    }
    
    public void setCVCategoryName(String CVCategoryName) {
        this.CVCategoryName = CVCategoryName;
    }
    
    public int getShipCarrierID() {
        return shipCarrierID;
    }
    
    public void setShipCarrierID(int shipCarrierID) {
        this.shipCarrierID = shipCarrierID;
    }
    
    public int getPaymentTypeID() {
        return paymentTypeID;
    }
    
    public void setPaymentTypeID(int paymentTypeID) {
        this.paymentTypeID = paymentTypeID;
    }
    
    public int getSalesRepID() {
        return salesRepID;
    }
    
    public void setSalesRepID(int salesRepID) {
        this.salesRepID = salesRepID;
    }
    
    public int getTermID() {
        return termID;
    }
    
    public void setTermID(int termID) {
        this.termID = termID;
    }
    
    public int getCcTypeID() {
        return ccTypeID;
    }
    
    public void setCcTypeID(int ccTypeID) {
        this.ccTypeID = ccTypeID;
    }
    
    public double getCustomerOpenDebit() {
        return customerOpenDebit;
    }
    
    public void setCustomerOpenDebit(double customerOpenDebit) {
        this.customerOpenDebit = customerOpenDebit;
    }
    
    public double getCustomerCreditLine() {
        return customerCreditLine;
    }
    
    public void setCustomerCreditLine(double customerCreditLine) {
        this.customerCreditLine = customerCreditLine;
    }
    
    public double getVendorOpenDebit() {
        return vendorOpenDebit;
    }
    
    public void setVendorOpenDebit(double vendorOpenDebit) {
        this.vendorOpenDebit = vendorOpenDebit;
    }
    
    public double getVendorAllowedCredit() {
        return vendorAllowedCredit;
    }
    
    public void setVendorAllowedCredit(double vendorAllowedCredit) {
        this.vendorAllowedCredit = vendorAllowedCredit;
    }
    
    public java.util.Date getDateAdded() {
        return DateAdded;
    }
    
    public void setDateAdded(java.util.Date DateAdded) {
        this.DateAdded = DateAdded;
    }
    
    public boolean isUseIndividual() {
        return isUseIndividual;
    }
    
    public void setUseIndividual(boolean isUseIndividual) {       
        this.isUseIndividual = isUseIndividual;
    }
    
    public double getMinimumFianceCharge() {
        return minimumFianceCharge;
    }
    
    public void setMinimumFianceCharge(double minimumFianceCharge) {
        this.minimumFianceCharge = minimumFianceCharge;
    }
    
    public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getPriceLevelId() {
		return priceLevelId;
	}

	public void setPriceLevelId(int priceLevelId) {
		this.priceLevelId = priceLevelId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public double getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(double overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public double getOverdueFinanceChargeAmount() {
		return overdueFinanceChargeAmount;
	}

	public void setOverdueFinanceChargeAmount(double overdueFinanceChargeAmount) {
		this.overdueFinanceChargeAmount = overdueFinanceChargeAmount;
	}

	public String getSmcPassword() {
		return smcPassword;
	}

	public void setSmcPassword(String smcPassword) {
		this.smcPassword = smcPassword;
	}

	public static int getPriorityBackup() {
		return PRIORITY_BACKUP;
	}

	public static int getPriorityDonotbackup() {
		return PRIORITY_DONOTBACKUP;
	}

	public int getForm1099Selected() {
		return form1099Selected;
	}

	public static void setPlevelActive(boolean isPlevelActive) {
		ClientVendor.isPlevelActive = isPlevelActive;
	}

	public double getAnnualInterestRate() {
        return annualInterestRate;
    }
    
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }
    
    public int getGracePeriod() {
        return gracePeriod;
    }
    
    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }
    
    public boolean isAssessFinanceCharge() {
        return assessFinanceCharge;
    }
    
    public void setAssessFinanceCharge(boolean assessFinanceCharge) {
        this.assessFinanceCharge = assessFinanceCharge;
    }
    public String getFirstCharCap(String Name){

        if (Name == null || Name.equals("")) {
            return "";
        } else {
            if(Character.isLowerCase(Name.charAt(0))){
                String str= Character.toString(Character.toUpperCase(Name.charAt(0)));
                Name=str+Name.substring(1, Name.length());
            }
            return Name;
        }
    }
    
    public String getPersonName() {
        
        if (getCvID()<0)
            return "";
        else
            return LastName + (LastName.equals("")?"":", ") + FirstName;
    }
    
    @Override
//    public String toString() {
//        String cvName= "";
//        /**
//         * Changes made by $RG because we need to show the client vendor according to the sorting optio
//         *
//         */
////        String fullName = getLastName()+(getLastName().equals("")?"":", ")+getFirstName();
////        return getName()+(fullName.equals("")?"":(" ("+fullName +")"));
// //        ClientVendor clientVendor = (ClientVendor) cv;
//        /**
//         * This format is needed for displaying the customer/vendor name according
//         * to the sort option
//         */
//        if (getName().equals("") && getLastName().equals("") && getFirstName().equals("")) {
//            return "";
//        } else if (getName().equals("") && ConstValue.sortListBy.equals("Company Name")) {
//            cvName = "(" + getLastName() + " " + getFirstName() + ")" + "" + getLastName() + " " + getFirstName();
//            return cvName;
//        } else if (ConstValue.sortListBy.equals("Company Name")) {
//            cvName = getName() + "(" + getLastName() + " " + getFirstName() + ")";
//            return cvName;
//        } else if (getName().equals("") && ConstValue.sortListBy.equals("Last Name")) {
//            return getLastName() + " " + getFirstName() + "(" + getLastName() + " " + getFirstName() + ")";
//        } else if (ConstValue.sortListBy.equals("Last Name")) {
//            return getLastName() + " " + getFirstName() + "(" + getName() + ")";
//        } else if (getName().equals("") && ConstValue.sortListBy.equals("First Name")) {
//            return getFirstName() + " " + getLastName() + "(" + getFirstName() + " " + getLastName() + ")";
//        } else if (ConstValue.sortListBy.equals("First Name")) {
//            return getFirstName() + " " + getLastName() + "(" + getName() + ")";
//        } else {
//            return cvName;
//        }
//    }
    
//    public Addr getAddress() {
//        Addr addr = new Addr();
//        addr.companyName = getName();
//        addr.firstName = getFirstName();
//        addr.lastName = getLastName();
//        addr.address1 = getAddress1();
//        addr.address2 = getAddress2();
//        addr.city = getCity();
//        addr.state = getState();
//        addr.province = getProvince();
//        addr.zipCode = getZipCode();
//        addr.companyName = getName();
//        
//        return addr;
//    }



    
//    public int getActive() {
//        return active;
//    }
//    
//    public void setActive(int active) {
//        this.active = active;
//    }
    
//    public String getStatus() {
//        return status;
//    }
    
//    public void setStatus(String status) {
//        this.status = status;
//    }
    
//    public int getDeleted() {
//        return deleted;
//    }
    
//    public void setDeleted(int deleted) {
//        this.deleted = deleted;
//    }
    
//    public int getPriority() {
//        return priority;
//    }
    
//    public void setPriority(int priority) {
//        this.priority = priority;
//        
//    }
    
//    public ClientVendorHasService getHasServices() {
//        return hasServices;
//    }
//    
//    public void setHasServices(ClientVendorHasService services) {
//        
//        this.hasServices = services;
//    }
    
//    public double getOverdueAmount() {
//        return overdueAmount;
//    }
    
//    public void setOverdueAmount(double overdueAmount) {
//        this.overdueAmount = overdueAmount;
//    }
    
//    public double getOverdueFinanceChargeAmount() {
//        return overdueFinanceChargeAmount;
//    }
    
//    public void setOverdueFinanceChargeAmount(double overdueFinanceChargeAmount) {
//        this.overdueFinanceChargeAmount = overdueFinanceChargeAmount;
//    }
    
    public boolean equals(Object obj) {
        if ( this == obj ) return true;
        if ( !(obj instanceof ClientVendor) ) return false;        
        ClientVendor other = (ClientVendor)obj;
        if (this.getCvID()!=other.getCvID()) return false;           
        return true;
    }

    public int getItemPriceLevel() {
        return itemPriceLevel;
    }

    public void setItemPriceLevel(int itemPriceLevel) {
        this.itemPriceLevel = itemPriceLevel;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPayFromId() {
        return payFromId;
    }

    public void setPayFromId(int payFromId) {
        this.payFromId = payFromId;
    }
    
     public boolean isPlevelActive() {
        return isPlevelActive;
    }

    public void setIsPlevelActive(boolean isPlevelActive) {
        this.isPlevelActive = isPlevelActive;
    }

    public boolean isUseSpecialMessage() {
        return useSpecialMessage;
    }

    public void setUseSpecialMessage(boolean useSpecialMessage) {
        this.useSpecialMessage = useSpecialMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTotalPaidSales() {
        return totalPaidSales;
    }

    public void setTotalPaidSales(long totalPaidSales) {
        this.totalPaidSales = totalPaidSales;
    }

    public long getTotalPaidPOs() {
        return totalPaidPOs;
    }

    public void setTotalPaidPOs(long totalPaidPOs) {
        this.totalPaidPOs = totalPaidPOs;
    }

    public long getTotalUnPaidSales() {
        return totalUnPaidSales;
    }

    public void setTotalUnPaidSales(long totalUnPaidSales) {
        this.totalUnPaidSales = totalUnPaidSales;
    }

    public long getTotalUnPaidPOs() {
        return totalUnPaidPOs;
    }

    public void setTotalUnPaidPOs(long totalUnPaidPOs) {
        this.totalUnPaidPOs = totalUnPaidPOs;
    }

    public long getTotalPaidSalesServices() {
        return totalPaidSalesServices;
    }

    public void setTotalPaidSalesServices(long totalPaidSalesServices) {
        this.totalPaidSalesServices = totalPaidSalesServices;
    }

    public long getTotalUnPaidSalesServices() {
        return totalUnPaidSalesServices;
    }

    public void setTotalUnPaidSalesServices(long totalUnPaidSalesServices) {
        this.totalUnPaidSalesServices = totalUnPaidSalesServices;
    }

    public long getTotalPOsPaidServices() {
        return totalPOsPaidServices;
    }

    public void setTotalPOsPaidServices(long totalPOsPaidServices) {
        this.totalPOsPaidServices = totalPOsPaidServices;
    }

    public long getTotalPOsUnPaidServices() {
        return totalPOsUnPaidServices;
    }

    public void setTotalPOsUnPaidServices(long totalUnPOsPaidServices) {
        this.totalPOsUnPaidServices = totalUnPOsPaidServices;
    }

    public int getCustomerGroupID() {
        return customerGroupID;
    }

    public void setCustomerGroupID(int customerGroupID) {
        this.customerGroupID = customerGroupID;
    }

    /**
     * @return the form1099Selected
     */
    public int isForm1099Selected() {
        return form1099Selected;
    }

    /**
     * @param form1099Selected the form1099Selected to set
     */
    public void setForm1099Selected(int form1099Selected) {
        this.form1099Selected = form1099Selected;
    }

    /**
     * @return the referenceCustomerID
     */
    public int getReferenceCustomerID() {
        return referenceCustomerID;
    }

    /**
     * @param referenceCustomerID the referenceCustomerID to set
     */
    public void setReferenceCustomerID(int referenceCustomerID) {
        this.referenceCustomerID = referenceCustomerID;
    }

    /**
     * @return the lastContactOn
     */
    public String getLastContactOn() {
        return lastContactOn;
    }

    /**
     * @param lastContactOn the lastContactOn to set
     */
    public void setLastContactOn(String lastContactOn) {
        this.lastContactOn = lastContactOn;
    }

    /**
     * @return the pasword
     */
    public char[] getPasword() {
        return pasword;
    }

    /**
     * @param pasword the pasword to set
     */
    public void setPasword(char[] pasword) {
        this.pasword = pasword;
    }

    /**
     * @return the lineOfCreditTermID
     */
    public int getLineOfCreditTermID() {
        return lineOfCreditTermID;
    }

    /**
     * @param lineOfCreditTermID the lineOfCreditTermID to set
     */
    public void setLineOfCreditTermID(int lineOfCreditTermID) {
        this.lineOfCreditTermID = lineOfCreditTermID;
    }

    /**
     * @return the remainingCreditAmount
     */
    public double getRemainingCreditAmount() {
        return remainingCreditAmount;
    }

    /**
     * @param remainingCreditAmount the remainingCreditAmount to set
     */
    public void setRemainingCreditAmount(double remainingCreditAmount) {
        this.remainingCreditAmount = remainingCreditAmount;
    }

    public void setSMCPasword(String smcPassword) {
        this.smcPassword = smcPassword;
    }

    public String getSMCPasword() {
        return smcPassword;
    }

    public int getBankAccountID() { 
        return bankAccountID;
    }

    public void setBankAccountID(int bankAccountID) {
        this.bankAccountID = bankAccountID;
    }
}
