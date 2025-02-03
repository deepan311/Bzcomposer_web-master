package com.avibha.bizcomposer.sales.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class EstimationBoardForm extends ActionForm {
	
	private static final long serialVersionUID = 0;
	
	private String filterMarket;

	private String sortType1;

	private String sortType2;

	private String searchType;

	private String invoiceID;

	private String orderNum;

	private String pONum;

	private String rcvNum;

	private String estNum;

	private String clientVendorID;

	private String bSAddressID;

	private String dateAdded;

	private String orderid;

	private String dateConfirmed;

	private String isPrinted;

	private String shipped;

	private String isEmailed;

	private String lastName;

	private String firstName;

	private String email;

	private String address1;

	private String address2;

	private String city;

	private String state;

	private String country;

	private String zipCode;

	private String inventoryName;

	private String qty;

	private int count_kind_items;

	private String orderDate1;

	private String orderDate2;

	private String saleDate1;

	private String saleDate2;

	private String searchTxt;

	private String marketPlace;
	
	private String datesCombo;		//Added on 23-07-2019
	
	private String fromDate;		//Added on 23-07-2019
	
	private String toDate;			//Added on 23-07-2019
	
	private int sortBy;				//Added on 23-07-2019
	
	public int getSortBy() {
		return sortBy;
	}

	public void setSortBy(int sortBy) {
		this.sortBy = sortBy;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getDatesCombo() {
		return datesCombo;
	}

	public void setDatesCombo(String datesCombo) {
		this.datesCombo = datesCombo;
	}

	/**
	 * @return Returns the marketPlace.
	 */
	public String getMarketPlace() {
		return marketPlace;
	}

	/**
	 * @param marketPlace
	 *            The marketPlace to set.
	 */
	public void setMarketPlace(String marketPlace) {
		this.marketPlace = marketPlace;
	}

	/**
	 * @return Returns the address1.
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1
	 *            The address1 to set.
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return Returns the address2.
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            The address2 to set.
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return Returns the bSAddressID.
	 */
	public String getBSAddressID() {
		return bSAddressID;
	}

	/**
	 * @param addressID
	 *            The bSAddressID to set.
	 */
	public void setBSAddressID(String addressID) {
		bSAddressID = addressID;
	}

	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return Returns the clientVendorID.
	 */
	public String getClientVendorID() {
		return clientVendorID;
	}

	/**
	 * @param clientVendorID
	 *            The clientVendorID to set.
	 */
	public void setClientVendorID(String clientVendorID) {
		this.clientVendorID = clientVendorID;
	}

	/**
	 * @return Returns the count_kind_items.
	 */
	public int getCount_kind_items() {
		return count_kind_items;
	}

	/**
	 * @param count_kind_items
	 *            The count_kind_items to set.
	 */
	public void setCount_kind_items(int count_kind_items) {
		this.count_kind_items = count_kind_items;
	}

	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return Returns the dateAdded.
	 */
	public String getDateAdded() {
		return dateAdded;
	}

	/**
	 * @param dateAdded
	 *            The dateAdded to set.
	 */
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	/**
	 * @return Returns the dateConfirmed.
	 */
	public String getDateConfirmed() {
		return dateConfirmed;
	}

	/**
	 * @param dateConfirmed
	 *            The dateConfirmed to set.
	 */
	public void setDateConfirmed(String dateConfirmed) {
		this.dateConfirmed = dateConfirmed;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Returns the estNum.
	 */
	public String getEstNum() {
		return estNum;
	}

	/**
	 * @param estNum
	 *            The estNum to set.
	 */
	public void setEstNum(String estNum) {
		this.estNum = estNum;
	}

	/**
	 * @return Returns the filterMarket.
	 */
	public String getFilterMarket() {
		return filterMarket;
	}

	/**
	 * @param filterMarket
	 *            The filterMarket to set.
	 */
	public void setFilterMarket(String filterMarket) {
		this.filterMarket = filterMarket;
	}

	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return Returns the inventoryName.
	 */
	public String getInventoryName() {
		return inventoryName;
	}

	/**
	 * @param inventoryName
	 *            The inventoryName to set.
	 */
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	/**
	 * @return Returns the invoiceID.
	 */
	public String getInvoiceID() {
		return invoiceID;
	}

	/**
	 * @param invoiceID
	 *            The invoiceID to set.
	 */
	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	/**
	 * @return Returns the isEmailed.
	 */
	public String getIsEmailed() {
		return isEmailed;
	}

	/**
	 * @param isEmailed
	 *            The isEmailed to set.
	 */
	public void setIsEmailed(String isEmailed) {
		this.isEmailed = isEmailed;
	}

	/**
	 * @return Returns the isPrinted.
	 */
	public String getIsPrinted() {
		return isPrinted;
	}

	/**
	 * @param isPrinted
	 *            The isPrinted to set.
	 */
	public void setIsPrinted(String isPrinted) {
		this.isPrinted = isPrinted;
	}

	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return Returns the orderDate1.
	 */
	public String getOrderDate1() {
		return orderDate1;
	}

	/**
	 * @param orderDate1
	 *            The orderDate1 to set.
	 */
	public void setOrderDate1(String orderDate1) {
		this.orderDate1 = orderDate1;
	}

	/**
	 * @return Returns the orderDate2.
	 */
	public String getOrderDate2() {
		return orderDate2;
	}

	/**
	 * @param orderDate2
	 *            The orderDate2 to set.
	 */
	public void setOrderDate2(String orderDate2) {
		this.orderDate2 = orderDate2;
	}

	/**
	 * @return Returns the orderid.
	 */
	public String getOrderid() {
		return orderid;
	}

	/**
	 * @param orderid
	 *            The orderid to set.
	 */
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	/**
	 * @return Returns the orderNum.
	 */
	public String getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum
	 *            The orderNum to set.
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return Returns the pONum.
	 */
	public String getPONum() {
		return pONum;
	}

	/**
	 * @param num
	 *            The pONum to set.
	 */
	public void setPONum(String num) {
		pONum = num;
	}

	/**
	 * @return Returns the qty.
	 */
	public String getQty() {
		return qty;
	}

	/**
	 * @param qty
	 *            The qty to set.
	 */
	public void setQty(String qty) {
		this.qty = qty;
	}

	/**
	 * @return Returns the rcvNum.
	 */
	public String getRcvNum() {
		return rcvNum;
	}

	/**
	 * @param rcvNum
	 *            The rcvNum to set.
	 */
	public void setRcvNum(String rcvNum) {
		this.rcvNum = rcvNum;
	}

	/**
	 * @return Returns the saleDate1.
	 */
	public String getSaleDate1() {
		return saleDate1;
	}

	/**
	 * @param saleDate1
	 *            The saleDate1 to set.
	 */
	public void setSaleDate1(String saleDate1) {
		this.saleDate1 = saleDate1;
	}

	/**
	 * @return Returns the saleDate2.
	 */
	public String getSaleDate2() {
		return saleDate2;
	}

	/**
	 * @param saleDate2
	 *            The saleDate2 to set.
	 */
	public void setSaleDate2(String saleDate2) {
		this.saleDate2 = saleDate2;
	}

	/**
	 * @return Returns the searchTxt.
	 */
	public String getSearchTxt() {
		return searchTxt;
	}

	/**
	 * @param searchTxt
	 *            The searchTxt to set.
	 */
	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}

	/**
	 * @return Returns the searchType.
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType
	 *            The searchType to set.
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	 * @return Returns the shipped.
	 */
	public String getShipped() {
		return shipped;
	}

	/**
	 * @param shipped
	 *            The shipped to set.
	 */
	public void setShipped(String shipped) {
		this.shipped = shipped;
	}

	/**
	 * @return Returns the sortType1.
	 */
	public String getSortType1() {
		return sortType1;
	}

	/**
	 * @param sortType1
	 *            The sortType1 to set.
	 */
	public void setSortType1(String sortType1) {
		this.sortType1 = sortType1;
	}

	/**
	 * @return Returns the sortType2.
	 */
	public String getSortType2() {
		return sortType2;
	}

	/**
	 * @param sortType2
	 *            The sortType2 to set.
	 */
	public void setSortType2(String sortType2) {
		this.sortType2 = sortType2;
	}

	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return Returns the zipCode.
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            The zipCode to set.
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);

		filterMarket = null;
		searchType = null;
		sortType1 = null;
		sortType2 = null;

		invoiceID = null;
		orderNum = null;
		pONum = null;
		rcvNum = null;
		estNum = null;
		clientVendorID = null;
		bSAddressID = null;
		dateAdded = null;
		orderid = null;
		dateConfirmed = null;
		isPrinted = null;
		shipped = null;
		isEmailed = null;
		lastName = null;
		firstName = null;
		email = null;
		address1 = null;
		address2 = null;
		city = null;
		state = null;
		country = null;
		zipCode = null;
		inventoryName = null;
		qty = null;
		count_kind_items = 0;
		orderDate1 = null;
		orderDate2 = null;
		saleDate1 = null;
		saleDate2 = null;
		searchTxt = null;

	}

}
