package com.avibha.bizcomposer.File.actions;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.purchase.dao.PurchaseInfoDao;
import com.avibha.bizcomposer.purchase.forms.VendorDto;
import com.avibha.bizcomposer.sales.dao.CustomerInfoDao;
import com.avibha.bizcomposer.sales.dao.SalesDetailsDao;
import com.avibha.bizcomposer.sales.forms.CustomerDto;
import com.avibha.bizcomposer.sales.forms.ItemDto;

/**
 * @author sarfrazmalik
 */
@Service
public class DataImportExportUtils {
	
	 private ConfigurationInfo configInfo;
	 	@Autowired
	    private SalesDetailsDao salesDetails;
	    @Autowired
	    public DataImportExportUtils(ConfigurationInfo configInfo) {
			super();
			this.configInfo = configInfo;
		}
	    
	    public DataImportExportUtils() {
			super();
		}

    private final static String NEW_LINE_SEPARATOR = "\n";
    private final static String COMMA_DELIMITER = ",";

//  =================================== Configuration-Import-Export ===================================
    public boolean exportConfigurationInfo(ConfigurationDto configDto, String type, HttpServletResponse response) {
        boolean b = false;
        String csvFilePath = System.getProperty("user.home") + "/Test/BCA_Configuration.csv";
        String excelFilePath = System.getProperty("user.home") + "/Test/BCA_Configuration.xls";
        File sourcefile = null;
        FileOutputStream fileOutputStream = null;
        if (type.equals("csv")) {
            try {
                sourcefile = new File(csvFilePath);
                checkFileExistance(sourcefile);
                fileOutputStream = new FileOutputStream(csvFilePath);

                FileWriter fileWriter = null;
                fileWriter = new FileWriter(csvFilePath);
                fileWriter.append(ConfigurationDto.ConfigColumns);
                fileWriter.append(NEW_LINE_SEPARATOR);
                fileWriter.append(configDto.getPoboard()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getItemReceivedBoard()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getItemShippedBoard()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getSalesOrderBoard()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getShowReorderPointList()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getShowReorderPointWarning()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getReservedQuantity()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getSalesOrderQty()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getProductTaxable()).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getCurrencyID()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getStartInvoiceNum(), 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getStartEstimationNum(), 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getStartSalesOrderNum(), 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getStartPONum(), 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getInvStyleID()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getEstimationStyleID()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getSoStyleID()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getPoStyleID()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getFilterOption()).append(COMMA_DELIMITER);   //CVTypeID
                fileWriter.append(configDto.getPassword()).append(COMMA_DELIMITER);       //CVCategoryID
                fileWriter.append(configDto.getCustTaxable()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getAddressSettings()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getSalesTaxCode()).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getSaleTaxRate()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getSaleTaxRate2()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getHowOftenSalesTax()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getDropShipCharge()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getExtraChargeApplicable()).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getChargeAmount()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getOrderAmount()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getIsRefundAllowed()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getPoShowCountry()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getPoShowTelephone()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getIsPurchasePrefix()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getShowReminder()).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getAnnualInterestRate()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getMinCharge()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getGracePeriod()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getAssessFinanceCharge()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getMarkFinanceCharge()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getMailServer()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getMailUserName()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getMailPassword()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getMailAuth()).append(COMMA_DELIMITER);
                fileWriter.append(configDto.getSenderEmail()).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getCustomerType()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getPriceLevelPriority()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getPriceLevelDealer()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getPriceLevelCustomer()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.getPriceLevelGeneral()+"", 1)).append(COMMA_DELIMITER);
                fileWriter.append(parseColumnValue(configDto.isShowUSAInBillShipAddress()+"", 2)).append(COMMA_DELIMITER);

                fileWriter.flush();
                fileWriter.close();
                fileOutputStream.close();
                b = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                sourcefile = new File(excelFilePath);
                if (!sourcefile.exists()) {
                    sourcefile.createNewFile();
                    fileOutputStream = new FileOutputStream(excelFilePath);
                } else {
                    fileOutputStream = new FileOutputStream(excelFilePath);
                }
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("ConfigurationInfo");
                setHSSFSheetColumnHeader(sheet, ConfigurationDto.ConfigColumns.split(COMMA_DELIMITER));
                HSSFRow row = sheet.createRow(1);
                row.createCell(0).setCellValue(configDto.getPoboard());
                row.createCell(1).setCellValue(configDto.getItemReceivedBoard());
                row.createCell(2).setCellValue(configDto.getItemShippedBoard());
                row.createCell(3).setCellValue(configDto.getSalesOrderBoard());
                row.createCell(4).setCellValue(configDto.getShowReorderPointList());
                row.createCell(5).setCellValue(configDto.getShowReorderPointWarning());
                row.createCell(6).setCellValue(configDto.getReservedQuantity());
                row.createCell(7).setCellValue(configDto.getSalesOrderQty());
                row.createCell(8).setCellValue(configDto.getProductTaxable());
                row.createCell(9).setCellValue(parseColumnValue(configDto.getCurrencyID()+"", 1));
                row.createCell(10).setCellValue(parseColumnValue(configDto.getStartInvoiceNum(), 1));
                row.createCell(11).setCellValue(parseColumnValue(configDto.getStartEstimationNum(), 1));
                row.createCell(12).setCellValue(parseColumnValue(configDto.getStartSalesOrderNum(), 1));
                row.createCell(13).setCellValue(parseColumnValue(configDto.getStartPONum(), 1));
                row.createCell(14).setCellValue(parseColumnValue(configDto.getInvStyleID()+"", 1));
                row.createCell(15).setCellValue(parseColumnValue(configDto.getEstimationStyleID()+"", 1));
                row.createCell(16).setCellValue(parseColumnValue(configDto.getSoStyleID()+"", 1));
                row.createCell(17).setCellValue(parseColumnValue(configDto.getPoStyleID()+"", 1));
                row.createCell(18).setCellValue(configDto.getFilterOption());
                row.createCell(19).setCellValue(configDto.getPassword());
                row.createCell(20).setCellValue(configDto.getCustTaxable());
                row.createCell(21).setCellValue(configDto.getAddressSettings());
                row.createCell(22).setCellValue(configDto.getSalesTaxCode());
                row.createCell(23).setCellValue(parseColumnValue(configDto.getSaleTaxRate()+"", 1));
                row.createCell(24).setCellValue(parseColumnValue(configDto.getSaleTaxRate2()+"", 1));
                row.createCell(25).setCellValue(parseColumnValue(configDto.getHowOftenSalesTax()+"", 1));
                row.createCell(26).setCellValue(parseColumnValue(configDto.getDropShipCharge()+"", 1));
                row.createCell(27).setCellValue(configDto.getExtraChargeApplicable());
                row.createCell(28).setCellValue(parseColumnValue(configDto.getChargeAmount()+"", 1));
                row.createCell(29).setCellValue(parseColumnValue(configDto.getOrderAmount()+"", 1));
                row.createCell(30).setCellValue(configDto.getIsRefundAllowed());
                row.createCell(31).setCellValue(configDto.getPoShowCountry());
                row.createCell(32).setCellValue(configDto.getPoShowTelephone());
                row.createCell(33).setCellValue(configDto.getIsPurchasePrefix());
                row.createCell(34).setCellValue(configDto.getShowReminder());
                row.createCell(35).setCellValue(parseColumnValue(configDto.getAnnualInterestRate()+"", 1));
                row.createCell(36).setCellValue(parseColumnValue(configDto.getMinCharge()+"", 1));
                row.createCell(37).setCellValue(parseColumnValue(configDto.getGracePeriod()+"", 1));
                row.createCell(38).setCellValue(configDto.getAssessFinanceCharge());
                row.createCell(39).setCellValue(configDto.getMarkFinanceCharge());
                row.createCell(40).setCellValue(configDto.getMailServer());
                row.createCell(41).setCellValue(configDto.getMailUserName());
                row.createCell(42).setCellValue(configDto.getMailPassword());
                row.createCell(43).setCellValue(configDto.getMailAuth());
                row.createCell(44).setCellValue(configDto.getSenderEmail());
                row.createCell(45).setCellValue(parseColumnValue(configDto.getCustomerType()+"", 1));
                row.createCell(46).setCellValue(parseColumnValue(configDto.getPriceLevelPriority()+"", 1));
                row.createCell(47).setCellValue(parseColumnValue(configDto.getPriceLevelDealer()+"", 1));
                row.createCell(48).setCellValue(parseColumnValue(configDto.getPriceLevelCustomer()+"", 1));
                row.createCell(49).setCellValue(parseColumnValue(configDto.getPriceLevelGeneral()+"", 1));
                row.createCell(50).setCellValue(parseColumnValue(configDto.isShowUSAInBillShipAddress()+"", 2));

                workbook.write(fileOutputStream);
                workbook.close();
                fileOutputStream.close();
                b = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        writeFileToResponse(response, sourcefile);
        return b;
    }

    public boolean uploadConfigurationFile(MultipartFile attachedFile, HttpServletRequest request) {
        boolean status = false;
        String compId = (String) request.getSession().getAttribute("CID");
        File file = new File(attachedFile.getOriginalFilename());
        String[] fileName = file.getName().split("\\.");
        try{
            OutputStream os = new FileOutputStream(file);
            InputStream is = new BufferedInputStream(attachedFile.getInputStream());
            ConfigurationDto configDto = new ConfigurationDto();
            int count;
            byte buf[] = new byte[4096];
            while ((count = is.read(buf)) > -1) {
                os.write(buf, 0, count);
            }
            is.close();
            os.close();
            if(fileName[1].equals("csv"))
            {
                BufferedReader bfReader = new BufferedReader(new FileReader(file));
                String line = "";
                int index = 0;
                while ((line = bfReader.readLine())!=null) {
                    if (index == 0){
                        index++;
                        continue;
                    }
                    String[] data = line.split(",");
                    configDto.setPoboard(data[0]);
                    configDto.setItemReceivedBoard(data[1]);
                    configDto.setItemShippedBoard(data[2]);
                    configDto.setSalesOrderBoard(data[3]);
                    configDto.setShowReorderPointList(data[4]);
                    configDto.setShowReorderPointWarning(data[5]);
                    configDto.setReservedQuantity(data[6]);
                    configDto.setSalesOrderQty(data[7]);
                    configDto.setProductTaxable(data[8]);
                    configDto.setCurrencyID(Integer.parseInt(data[9]));
                    configDto.setStartInvoiceNum(data[10]);
                    configDto.setStartEstimationNum(data[11]);
                    configDto.setStartSalesOrderNum(data[12]);
                    configDto.setStartPONum(data[13]);
                    configDto.setInvStyleID(Integer.parseInt(data[14]));
                    configDto.setEstimationStyleID(Integer.parseInt(data[15]));
                    configDto.setSoStyleID(Integer.parseInt(data[16]));
                    configDto.setPoStyleID(Integer.parseInt(data[17]));
                    configDto.setFilterOption(data[18]);
                    configDto.setPassword(data[19]);
                    configDto.setCustTaxable(data[20]);
                    configDto.setAddressSettings(data[21]);
                    configDto.setSalesTaxCode(data[22]);
                    configDto.setSaleTaxRate(Double.parseDouble(data[23]));
                    configDto.setSaleTaxRate2(Double.parseDouble(data[24]));
                    configDto.setHowOftenSalesTax(Integer.parseInt(data[25]));
                    configDto.setDropShipCharge(Integer.parseInt(data[26]));
                    configDto.setExtraChargeApplicable(data[27]);
                    configDto.setChargeAmount(Integer.parseInt(data[28]));
                    configDto.setOrderAmount(Integer.parseInt(data[29]));
                    configDto.setIsRefundAllowed(data[30]);
                    configDto.setPoShowCountry(data[31]);
                    configDto.setPoShowTelephone(data[32]);
                    configDto.setIsPurchasePrefix(data[33]);
                    configDto.setShowReminder(data[34]);
                    configDto.setAnnualInterestRate(Double.parseDouble(data[35]));
                    configDto.setMinCharge(Double.parseDouble(data[36]));
                    configDto.setGracePeriod(Integer.parseInt(data[37]));
                    configDto.setAssessFinanceCharge(data[38]);
                    configDto.setMarkFinanceCharge(data[39]);
                    configDto.setMailServer(data[40]);
                    configDto.setMailUserName(data[41]);
                    configDto.setMailPassword(data[42]);
                    configDto.setMailAuth(data[43]);
                    configDto.setSenderEmail(data[44]);
                    configDto.setCustomerType(Integer.parseInt(data[45]));
                    configDto.setPriceLevelPriority(Integer.parseInt(data[46]));
                    configDto.setPriceLevelDealer(Integer.parseInt(data[47]));
                    configDto.setPriceLevelCustomer(Integer.parseInt(data[48]));
                    configDto.setPriceLevelGeneral(Integer.parseInt(data[49]));
                    configDto.setShowUSAInBillShipAddress(Boolean.parseBoolean(data[50]));
                }
                bfReader.close();
            }
            else{
                FileInputStream inputStream = new FileInputStream(file);
                HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                HSSFSheet firstSheet = workbook.getSheetAt(0);
                Iterator<Row> iterator = firstSheet.rowIterator();
                int index = 0;
                while(iterator.hasNext()) {
                    if (index == 0) {
                        index++;
                        iterator.next();
                        continue;
                    }
                    int count2 = 0;
                    HSSFRow nextRow = (HSSFRow) iterator.next();
                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    while (cellIterator.hasNext()) {
                        HSSFCell cell = (HSSFCell) cellIterator.next();
                        String data = parseColumnValue2(cell, 0);
                        if(count2 == 0) configDto.setPoboard(data);
                        else if(count2 == 1) configDto.setItemReceivedBoard(data);
                        else if(count2 == 2) configDto.setItemShippedBoard(data);
                        else if(count2 == 3) configDto.setSalesOrderBoard(data);
                        else if(count2 == 4) configDto.setShowReorderPointList(data);
                        else if(count2 == 5) configDto.setShowReorderPointWarning(data);
                        else if(count2 == 6) configDto.setReservedQuantity(data);
                        else if(count2 == 7) configDto.setSalesOrderQty(data);
                        else if(count2 == 8) configDto.setProductTaxable(data);
                        else if(count2 == 9) configDto.setCurrencyID(Integer.parseInt(data));
                        else if(count2 == 10) configDto.setStartInvoiceNum(data);
                        else if(count2 == 11) configDto.setStartEstimationNum(data);
                        else if(count2 == 12) configDto.setStartSalesOrderNum(data);
                        else if(count2 == 13) configDto.setStartPONum(data);
                        else if(count2 == 14) configDto.setInvStyleID(Integer.parseInt(data));
                        else if(count2 == 15) configDto.setEstimationStyleID(Integer.parseInt(data));
                        else if(count2 == 16) configDto.setSoStyleID(Integer.parseInt(data));
                        else if(count2 == 17) configDto.setPoStyleID(Integer.parseInt(data));
                        else if(count2 == 18) configDto.setFilterOption(data);
                        else if(count2 == 19) configDto.setPassword(data);
                        else if(count2 == 20) configDto.setCustTaxable(data);
                        else if(count2 == 21) configDto.setAddressSettings(data);
                        else if(count2 == 22) configDto.setSalesTaxCode(data);
                        else if(count2 == 23) configDto.setSaleTaxRate(Double.parseDouble(data));
                        else if(count2 == 24) configDto.setSaleTaxRate2(Double.parseDouble(data));
                        else if(count2 == 25) configDto.setHowOftenSalesTax(Integer.parseInt(data));
                        else if(count2 == 26) configDto.setDropShipCharge(Integer.parseInt(data));
                        else if(count2 == 27) configDto.setExtraChargeApplicable(data);
                        else if(count2 == 28) configDto.setChargeAmount(Integer.parseInt(data));
                        else if(count2 == 29) configDto.setOrderAmount(Integer.parseInt(data));
                        else if(count2 == 30) configDto.setIsRefundAllowed(data);
                        else if(count2 == 31) configDto.setPoShowCountry(data);
                        else if(count2 == 32) configDto.setPoShowTelephone(data);
                        else if(count2 == 33) configDto.setIsPurchasePrefix(data);
                        else if(count2 == 34) configDto.setShowReminder(data);
                        else if(count2 == 35) configDto.setAnnualInterestRate(Double.parseDouble(data));
                        else if(count2 == 36) configDto.setMinCharge(Double.parseDouble(data));
                        else if(count2 == 37) configDto.setGracePeriod(Integer.parseInt(data));
                        else if(count2 == 38) configDto.setAssessFinanceCharge(data);
                        else if(count2 == 39) configDto.setMarkFinanceCharge(data);
                        else if(count2 == 40) configDto.setMailServer(data);
                        else if(count2 == 41) configDto.setMailUserName(data);
                        else if(count2 == 42) configDto.setMailPassword(data);
                        else if(count2 == 43) configDto.setMailAuth(data);
                        else if(count2 == 44) configDto.setSenderEmail(data);
                        else if(count2 == 45) configDto.setCustomerType(Integer.parseInt(data));
                        else if(count2 == 46) configDto.setPriceLevelPriority(Integer.parseInt(data));
                        else if(count2 == 47) configDto.setPriceLevelDealer(Integer.parseInt(data));
                        else if(count2 == 48) configDto.setPriceLevelCustomer(Integer.parseInt(data));
                        else if(count2 == 49) configDto.setPriceLevelGeneral(Integer.parseInt(data));
                        else if(count2 == 50) configDto.setShowUSAInBillShipAddress(Boolean.parseBoolean(data));
                        count2++;
                    }
                }
                workbook.close();
                inputStream.close();
            }
            ConfigurationInfo cinfo = this.configInfo;
            cinfo.saveConfigurationRecordGeneral(configDto, request);
            cinfo.saveConfigurationRecordInventorySettting(configDto, Integer.parseInt(compId));
            cinfo.saveConfigurationRecord(configDto, Integer.parseInt(compId) ,request);
            cinfo.saveCustomerInvoice(configDto, Integer.parseInt(compId));
            cinfo.saveVendorPurchaseValuesInConfigInfo(configDto, Integer.parseInt(compId));
            request.getSession().setAttribute("successMessage", "Success");
            status = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

//  =================================== Customer-Import-Export ===================================
    public boolean exportCustomerList(ArrayList<CustomerDto> customerList, String type, HttpServletResponse response) {
        boolean b = false;
        String csvFilePath = System.getProperty("user.home") + "/Test/BCA_CustomerList.csv";
        String excelFilePath = System.getProperty("user.home") + "/Test/BCA_CustomerList.xls";
        File sourcefile = null;
        FileOutputStream fileOutputStream = null;
        if (type.equals("csv")) {
            try {
                sourcefile = new File(csvFilePath);
                checkFileExistance(sourcefile);
                fileOutputStream = new FileOutputStream(csvFilePath);

                FileWriter fileWriter = null;
                fileWriter = new FileWriter(csvFilePath);
                fileWriter.append(CustomerDto.customerColumns);
                for (CustomerDto customer: customerList){
                    fileWriter.append(NEW_LINE_SEPARATOR);
                    fileWriter.append(customer.getCname()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getDbaName()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getTitle()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getFirstName()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getMiddleName()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getLastName()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getAddress1()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getAddress2()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getCity()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getState()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getCountry()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getZipCode()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getPhone()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getCellPhone()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getFax()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getEmail()).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getTexID(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getTaxAble(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getIsclient()).append(COMMA_DELIMITER);   //CVTypeID
                    fileWriter.append(parseColumnValue(customer.getType(), 1)).append(COMMA_DELIMITER);       //CVCategoryID
                    fileWriter.append(parseColumnValue(customer.getOpeningUB(), 2)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getExtCredit(), 2)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getTerm(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getRep(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getShipping(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getPaymentType(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getFsUseIndividual(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getAnnualIntrestRate(), 2)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getMinFCharges(), 2)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getGracePrd(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getFsAssessFinanceCharge()).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getFsMarkFinanceCharge(), 1)).append(COMMA_DELIMITER);
                }
                fileWriter.flush();
                fileWriter.close();
                fileOutputStream.close();
                b = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                sourcefile = new File(excelFilePath);
                if (!sourcefile.exists()) {
                    sourcefile.createNewFile();
                    fileOutputStream = new FileOutputStream(excelFilePath);
                } else {
                    fileOutputStream = new FileOutputStream(excelFilePath);
                }
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("CustomerList");
                setHSSFSheetColumnHeader(sheet, CustomerDto.customerColumns.split(COMMA_DELIMITER));
                HSSFRow row = null;
                int rowIndex = 1;
                for (CustomerDto customer: customerList){
                    row = sheet.createRow(rowIndex++);
                    row.createCell(0).setCellValue(customer.getCname());
                    row.createCell(1).setCellValue(customer.getDbaName());
                    row.createCell(2).setCellValue(customer.getTitle());
                    row.createCell(3).setCellValue(customer.getFirstName());
                    row.createCell(4).setCellValue(customer.getMiddleName());
                    row.createCell(5).setCellValue(customer.getLastName());
                    row.createCell(6).setCellValue(customer.getAddress1());
                    row.createCell(7).setCellValue(customer.getAddress2());
                    row.createCell(8).setCellValue(customer.getCity());
                    row.createCell(9).setCellValue(customer.getState());
                    row.createCell(10).setCellValue(customer.getCountry());
                    row.createCell(11).setCellValue(customer.getZipCode());
                    row.createCell(12).setCellValue(customer.getPhone());
                    row.createCell(13).setCellValue(customer.getCellPhone());
                    row.createCell(14).setCellValue(customer.getFax());
                    row.createCell(15).setCellValue(customer.getEmail());
                    row.createCell(16).setCellValue(parseColumnValue(customer.getTexID(), 1));
                    row.createCell(17).setCellValue(parseColumnValue(customer.getTaxAble(), 1));
                    row.createCell(18).setCellValue(parseColumnValue(customer.getIsclient(), 1));
                    row.createCell(19).setCellValue(parseColumnValue(customer.getType(), 1));
                    row.createCell(20).setCellValue(parseColumnValue(customer.getOpeningUB(), 2));
                    row.createCell(21).setCellValue(parseColumnValue(customer.getExtCredit(), 2));
                    row.createCell(22).setCellValue(parseColumnValue(customer.getTerm(), 1));
                    row.createCell(23).setCellValue(parseColumnValue(customer.getRep(), 1));
                    row.createCell(24).setCellValue(parseColumnValue(customer.getShipping(), 1));
                    row.createCell(25).setCellValue(parseColumnValue(customer.getPaymentType(), 1));
                    row.createCell(26).setCellValue(parseColumnValue(customer.getFsUseIndividual(), 1));
                    row.createCell(27).setCellValue(parseColumnValue(customer.getAnnualIntrestRate(), 2));
                    row.createCell(28).setCellValue(parseColumnValue(customer.getMinFCharges(), 2));
                    row.createCell(29).setCellValue(parseColumnValue(customer.getGracePrd(), 1));
                    row.createCell(30).setCellValue(customer.getFsAssessFinanceCharge());
                    row.createCell(31).setCellValue(parseColumnValue(customer.getFsMarkFinanceCharge(), 1));
                }
                b = true;
                workbook.write(fileOutputStream);
                workbook.close();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        writeFileToResponse(response, sourcefile);
        return b;
    }

    public boolean uploadCustomerFile(MultipartFile attachedFile, HttpServletRequest request) {
        boolean status = false;
        File file = new File(attachedFile.getOriginalFilename());
        String[] fileName = file.getName().split("\\.");
        ArrayList al = new ArrayList();
        CustomerInfoDao customerInfoDao = new CustomerInfoDao();
        String compId = (String) request.getSession().getAttribute("CID");
        try{
            OutputStream os = new FileOutputStream(file);
            InputStream is = new BufferedInputStream(attachedFile.getInputStream());
            int count;
            byte buf[] = new byte[4096];
            while ((count = is.read(buf)) > -1) {
                os.write(buf, 0, count);
            }
            is.close();
            os.close();
            if(fileName[1].equals("csv"))
            {
                BufferedReader bfReader = new BufferedReader(new FileReader(file));
                String line = "";
                int index = 0;
                while ((line = bfReader.readLine())!=null) {
                    if (index == 0){
                        index++;
                        continue;
                    }
                    String[] data = line.split(",");
                    CustomerDto customer = new CustomerDto();
                    customer.setCname(data[0]);
                    customer.setDbaName(data[1]);
                    customer.setTitle(data[2]);
                    customer.setFirstName(data[3]);
                    customer.setMiddleName(data[4]);
                    customer.setLastName(data[5]);
                    customer.setAddress1(data[6]);
                    customer.setAddress2(data[7]);
                    customer.setCity(data[8]);
                    customer.setState(data[9]);
                    customer.setCountry(data[10]);
                    customer.setZipCode(data[11]);
                    customer.setPhone(data[12]);
                    customer.setCellPhone(data[13]);
                    customer.setFax(data[14]);
                    customer.setEmail(data[15]);
                    customer.setTexID(data[16]);
                    customer.setTaxAble(data[17]);
                    customer.setIsclient(data[18]);
                    customer.setType(data[19]);
                    customer.setOpeningUB(data[20]);
                    customer.setExtCredit(data[21]);
                    customer.setTerm(data[22]);
                    customer.setRep(data[23]);
                    customer.setShipping(data[24]);
                    customer.setPaymentType(data[25]);
                    customer.setFsUseIndividual(data[26]);
                    customer.setAnnualIntrestRate(data[27]);
                    customer.setMinFCharges(data[28]);
                    customer.setGracePrd(data[29]);
                    customer.setFsAssessFinanceCharge(data[30]);
                    customer.setFsMarkFinanceCharge(data[31]);

//                  b = insertdataintodatabase(al, request, type);
                    customerInfoDao.insertCustomer(customer, compId);
                }
                bfReader.close();
            }
            else{
                if(fileName[1].equals("xlsx")) {
				    	/*FileInputStream inputStream=null;
				        XSSFWorkbook  workbook=null;
				        inputStream = new FileInputStream(file);
				        workbook=new XSSFWorkbook(inputStream);
				        XSSFSheet firstSheet = workbook.getSheetAt(0);
				        Iterator<Row> iterator = firstSheet.iterator();                //row
				        int count1=0;*/
                }
                else{
                    FileInputStream inputStream = new FileInputStream(file);
                    HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                    HSSFSheet firstSheet = workbook.getSheetAt(0);
                    Iterator<Row> iterator = firstSheet.rowIterator();
                    int index = 0;
                    while(iterator.hasNext())
                    {
                        if (index == 0){
                            index++;
                            iterator.next();
                            continue;
                        }
                        int count2 = 0;
                        CustomerDto customer = new CustomerDto();
                        HSSFRow nextRow = (HSSFRow) iterator.next();
                        Iterator<Cell> cellIterator = nextRow.cellIterator();
                        while(cellIterator.hasNext())
                        {
                            HSSFCell cell = (HSSFCell) cellIterator.next();
                            String data = parseColumnValue2(cell, 0);
//                            try{ data = cell.getStringCellValue(); }
//                            catch (Exception e) { }
//                            al.add(count2, data);
                            if(count2 == 0) customer.setCname(data);
                            else if(count2 == 1) customer.setDbaName(data);
                            else if(count2 == 2) customer.setTitle(data);
                            else if(count2 == 3) customer.setFirstName(data);
                            else if(count2 == 4) customer.setMiddleName(data);
                            else if(count2 == 5) customer.setLastName(data);
                            else if(count2 == 6) customer.setAddress1(data);
                            else if(count2 == 7) customer.setAddress2(data);
                            else if(count2 == 8) customer.setCity(data);
                            else if(count2 == 9) customer.setState(data);
                            else if(count2 == 10) customer.setCountry(data);
                            else if(count2 == 11) customer.setZipCode(data);
                            else if(count2 == 12) customer.setPhone(data);
                            else if(count2 == 13) customer.setCellPhone(data);
                            else if(count2 == 14) customer.setFax(data);
                            else if(count2 == 15) customer.setEmail(data);
                            else if(count2 == 16) customer.setTexID(data);
                            else if(count2 == 17) customer.setTaxAble(data);
                            else if(count2 == 18) customer.setIsclient(data);
                            else if(count2 == 19) customer.setType(data);
                            else if(count2 == 20) customer.setOpeningUB(data);
                            else if(count2 == 21) customer.setExtCredit(data);
                            else if(count2 == 22) customer.setTerm(data);
                            else if(count2 == 23) customer.setRep(data);
                            else if(count2 == 24) customer.setShipping(data);
                            else if(count2 == 25) customer.setPaymentType(data);
                            else if(count2 == 26) customer.setFsUseIndividual(data);
                            else if(count2 == 27) customer.setAnnualIntrestRate(data);
                            else if(count2 == 28) customer.setMinFCharges(data);
                            else if(count2 == 29) customer.setGracePrd(data);
                            else if(count2 == 30) customer.setFsAssessFinanceCharge(data);
                            else if(count2 == 31) customer.setFsMarkFinanceCharge(data);
                            count2++;
                        }
//                        b = insertdataintodatabase(al, request, type);
                        customerInfoDao.insertCustomer(customer, compId);
                    }
                    workbook.close();
                    inputStream.close();
                }
            }
            status = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

//  =================================== Vendor-Import-Export ===================================
    public boolean exportVendorList(ArrayList<VendorDto> vendorList, String type, HttpServletResponse response) {
        boolean status = false;
        String csvFilePath = System.getProperty("user.home") + "/Test/BCA_VendorList.csv";
        String excelFilePath = System.getProperty("user.home") + "/Test/BCA_VendorList.xls";
        File sourcefile = null;
        FileOutputStream fileOutputStream = null;
        if (type.equals("csv")) {
            try {
                sourcefile = new File(csvFilePath);
                checkFileExistance(sourcefile);
                fileOutputStream = new FileOutputStream(csvFilePath);

                FileWriter fileWriter = null;
                fileWriter = new FileWriter(csvFilePath);
                fileWriter.append(VendorDto.vendorColumns);
                for (VendorDto customer: vendorList){
                    fileWriter.append(NEW_LINE_SEPARATOR);
                    fileWriter.append(customer.getCname()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getDbaName()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getTitle()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getFirstName()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getMiddleName()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getLastName()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getAddress1()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getAddress2()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getCity()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getState()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getCountry()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getZipCode()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getPhone()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getCellPhone()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getFax()).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getEmail()).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getTexID(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getTaxAble(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getIsclient()).append(COMMA_DELIMITER);   //CVTypeID
                    fileWriter.append(parseColumnValue(customer.getType(), 1)).append(COMMA_DELIMITER);       //CVCategoryID
                    fileWriter.append(parseColumnValue(customer.getOpeningUB(), 2)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getExtCredit(), 2)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getTerm(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getRep(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getShipping(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getPaymentType(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getFsUseIndividual(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getAnnualIntrestRate(), 2)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getMinFCharges(), 2)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getGracePrd(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(customer.getFsAssessFinanceCharge()).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(customer.getFsMarkFinanceCharge(), 1)).append(COMMA_DELIMITER);
                }
                fileWriter.flush();
                fileWriter.close();
                fileOutputStream.close();
                status = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                sourcefile = new File(excelFilePath);
                if (!sourcefile.exists()) {
                    sourcefile.createNewFile();
                    fileOutputStream = new FileOutputStream(excelFilePath);
                } else {
                    fileOutputStream = new FileOutputStream(excelFilePath);
                }
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("VendorList");
                setHSSFSheetColumnHeader(sheet, VendorDto.vendorColumns.split(COMMA_DELIMITER));
                HSSFRow row = null;
                int rowIndex = 1;
                for (VendorDto customer: vendorList){
                    row = sheet.createRow(rowIndex++);
                    row.createCell(0).setCellValue(customer.getCname());
                    row.createCell(1).setCellValue(customer.getDbaName());
                    row.createCell(2).setCellValue(customer.getTitle());
                    row.createCell(3).setCellValue(customer.getFirstName());
                    row.createCell(4).setCellValue(customer.getMiddleName());
                    row.createCell(5).setCellValue(customer.getLastName());
                    row.createCell(6).setCellValue(customer.getAddress1());
                    row.createCell(7).setCellValue(customer.getAddress2());
                    row.createCell(8).setCellValue(customer.getCity());
                    row.createCell(9).setCellValue(customer.getState());
                    row.createCell(10).setCellValue(customer.getCountry());
                    row.createCell(11).setCellValue(customer.getZipCode());
                    row.createCell(12).setCellValue(customer.getPhone());
                    row.createCell(13).setCellValue(customer.getCellPhone());
                    row.createCell(14).setCellValue(customer.getFax());
                    row.createCell(15).setCellValue(customer.getEmail());
                    row.createCell(16).setCellValue(parseColumnValue(customer.getTexID(), 1));
                    row.createCell(17).setCellValue(parseColumnValue(customer.getTaxAble(), 1));
                    row.createCell(18).setCellValue(parseColumnValue(customer.getIsclient(), 1));
                    row.createCell(19).setCellValue(parseColumnValue(customer.getType(), 1));
                    row.createCell(20).setCellValue(parseColumnValue(customer.getOpeningUB(), 2));
                    row.createCell(21).setCellValue(parseColumnValue(customer.getExtCredit(), 2));
                    row.createCell(22).setCellValue(parseColumnValue(customer.getTerm(), 1));
                    row.createCell(23).setCellValue(parseColumnValue(customer.getRep(), 1));
                    row.createCell(24).setCellValue(parseColumnValue(customer.getShipping(), 1));
                    row.createCell(25).setCellValue(parseColumnValue(customer.getPaymentType(), 1));
                    row.createCell(26).setCellValue(parseColumnValue(customer.getFsUseIndividual(), 1));
                    row.createCell(27).setCellValue(parseColumnValue(customer.getAnnualIntrestRate(), 2));
                    row.createCell(28).setCellValue(parseColumnValue(customer.getMinFCharges(), 2));
                    row.createCell(29).setCellValue(parseColumnValue(customer.getGracePrd(), 1));
                    row.createCell(30).setCellValue(customer.getFsAssessFinanceCharge());
                    row.createCell(31).setCellValue(parseColumnValue(customer.getFsMarkFinanceCharge(), 1));
                }
                status = true;
                workbook.write(fileOutputStream);
                workbook.close();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        writeFileToResponse(response, sourcefile);
        return status;
    }

    public boolean uploadVendorFile(MultipartFile attachedFile, HttpServletRequest request) {
        boolean status = false;
        File file = new File(attachedFile.getOriginalFilename());
        String[] fileName = file.getName().split("\\.");
        ArrayList al = new ArrayList();
        PurchaseInfoDao purchase = new PurchaseInfoDao();
        String compId = (String) request.getSession().getAttribute("CID");
        try{
            OutputStream os = new FileOutputStream(file);
            InputStream is = new BufferedInputStream(attachedFile.getInputStream());
            int count;
            byte buf[] = new byte[4096];
            while ((count = is.read(buf)) > -1) {
                os.write(buf, 0, count);
            }
            is.close();
            os.close();
            if(fileName[1].equals("csv"))
            {
                BufferedReader bfReader = new BufferedReader(new FileReader(file));
                String line = "";
                int index = 0;
                while ((line = bfReader.readLine())!=null) {
                    if (index == 0){
                        index++;
                        continue;
                    }
                    String[] data = line.split(",");
                    VendorDto customer = new VendorDto();
                    customer.setCname(data[0]);
                    customer.setDbaName(data[1]);
                    customer.setTitle(data[2]);
                    customer.setFirstName(data[3]);
                    customer.setMiddleName(data[4]);
                    customer.setLastName(data[5]);
                    customer.setAddress1(data[6]);
                    customer.setAddress2(data[7]);
                    customer.setCity(data[8]);
                    customer.setState(data[9]);
                    customer.setCountry(data[10]);
                    customer.setZipCode(data[11]);
                    customer.setPhone(data[12]);
                    customer.setCellPhone(data[13]);
                    customer.setFax(data[14]);
                    customer.setEmail(data[15]);
                    customer.setTexID(data[16]);
                    customer.setTaxAble(data[17]);
                    customer.setIsclient(data[18]);
                    customer.setType(data[19]);
                    customer.setOpeningUB(data[20]);
                    customer.setExtCredit(data[21]);
                    customer.setTerm(data[22]);
                    customer.setRep(data[23]);
                    customer.setShipping(data[24]);
                    customer.setPaymentType(data[25]);
                    customer.setFsUseIndividual(data[26]);
                    customer.setAnnualIntrestRate(data[27]);
                    customer.setMinFCharges(data[28]);
                    customer.setGracePrd(data[29]);
                    customer.setFsAssessFinanceCharge(data[30]);
                    customer.setFsMarkFinanceCharge(data[31]);

//                  b = insertdataintodatabase(al, request, type);
                    purchase.insertVendor(customer, compId);
                }
                bfReader.close();
            }
            else{
                if(fileName[1].equals("xlsx")) { }
                else{
                    FileInputStream inputStream = new FileInputStream(file);
                    HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                    HSSFSheet firstSheet = workbook.getSheetAt(0);
                    Iterator<Row> iterator = firstSheet.rowIterator();
                    int index = 0;
                    while(iterator.hasNext())
                    {
                        if (index == 0){
                            index++;
                            iterator.next();
                            continue;
                        }
                        int count2 = 0;
                        VendorDto customer = new VendorDto();
                        HSSFRow nextRow = (HSSFRow) iterator.next();
                        Iterator<Cell> cellIterator = nextRow.cellIterator();
                        while(cellIterator.hasNext())
                        {
                            HSSFCell cell = (HSSFCell) cellIterator.next();
                            String data = parseColumnValue2(cell, 0);
//                            try{ data = cell.getStringCellValue(); }
//                            catch (Exception e) { }
//                            al.add(count2, data);
                            if(count2 == 0) customer.setCname(data);
                            else if(count2 == 1) customer.setDbaName(data);
                            else if(count2 == 2) customer.setTitle(data);
                            else if(count2 == 3) customer.setFirstName(data);
                            else if(count2 == 4) customer.setMiddleName(data);
                            else if(count2 == 5) customer.setLastName(data);
                            else if(count2 == 6) customer.setAddress1(data);
                            else if(count2 == 7) customer.setAddress2(data);
                            else if(count2 == 8) customer.setCity(data);
                            else if(count2 == 9) customer.setState(data);
                            else if(count2 == 10) customer.setCountry(data);
                            else if(count2 == 11) customer.setZipCode(data);
                            else if(count2 == 12) customer.setPhone(data);
                            else if(count2 == 13) customer.setCellPhone(data);
                            else if(count2 == 14) customer.setFax(data);
                            else if(count2 == 15) customer.setEmail(data);
                            else if(count2 == 16) customer.setTexID(data);
                            else if(count2 == 17) customer.setTaxAble(data);
                            else if(count2 == 18) customer.setIsclient(data);
                            else if(count2 == 19) customer.setType(data);
                            else if(count2 == 20) customer.setOpeningUB(data);
                            else if(count2 == 21) customer.setExtCredit(data);
                            else if(count2 == 22) customer.setTerm(data);
                            else if(count2 == 23) customer.setRep(data);
                            else if(count2 == 24) customer.setShipping(data);
                            else if(count2 == 25) customer.setPaymentType(data);
                            else if(count2 == 26) customer.setFsUseIndividual(data);
                            else if(count2 == 27) customer.setAnnualIntrestRate(data);
                            else if(count2 == 28) customer.setMinFCharges(data);
                            else if(count2 == 29) customer.setGracePrd(data);
                            else if(count2 == 30) customer.setFsAssessFinanceCharge(data);
                            else if(count2 == 31) customer.setFsMarkFinanceCharge(data);
                            count2++;
                        }
//                        b = insertdataintodatabase(al, request, type);
                        purchase.insertVendor(customer, compId);
                    }
                    workbook.close();
                    inputStream.close();
                }
            }
            status = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

//  =================================== Item-Import-Export ===================================
    public boolean exportItemList(ArrayList<ItemDto> itemList, String type, HttpServletResponse response) {
        boolean status = false;
        String csvFilePath = System.getProperty("user.home") + "/Test/BCA_ItemList.csv";
        String excelFilePath = System.getProperty("user.home") + "/Test/BCA_ItemList.xls";
        File sourcefile = null;
        FileOutputStream fileOutputStream = null;
        if (type.equals("csv")) {
            try {
                sourcefile = new File(csvFilePath);
                checkFileExistance(sourcefile);
                fileOutputStream = new FileOutputStream(csvFilePath);

                FileWriter fileWriter = null;
                fileWriter = new FileWriter(csvFilePath);
                fileWriter.append(ItemDto.ItemColumns);
                for (ItemDto itemDto: itemList){
                    fileWriter.append(NEW_LINE_SEPARATOR);
                    fileWriter.append(parseColumnValue(itemDto.getCategoryName(), 0)).append(COMMA_DELIMITER);
                    fileWriter.append(itemDto.getItemCode()).append(COMMA_DELIMITER);
                    fileWriter.append(itemDto.getItemName()).append(COMMA_DELIMITER);
                    fileWriter.append(itemDto.getItemType()).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(itemDto.getSerialNum(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(itemDto.getQty(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(itemDto.getAvailableQty(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(itemDto.getReorderPoint()+"", 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(itemDto.getWeight(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(itemDto.getProductSKU(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(itemDto.getPurchasePrice(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(itemDto.getSalePrice(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(itemDto.getDealerPrice(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(itemDto.getTaxable(), 1)).append(COMMA_DELIMITER);
                    fileWriter.append(parseColumnValue(itemDto.getTextAreaContent(), 1)).append(COMMA_DELIMITER);
                }
                fileWriter.flush();
                fileWriter.close();
                fileOutputStream.close();
                status = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                sourcefile = new File(excelFilePath);
                if (!sourcefile.exists()) {
                    sourcefile.createNewFile();
                    fileOutputStream = new FileOutputStream(excelFilePath);
                } else {
                    fileOutputStream = new FileOutputStream(excelFilePath);
                }
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("ItemList");
                setHSSFSheetColumnHeader(sheet, ItemDto.ItemColumns.split(COMMA_DELIMITER));
                HSSFRow row = null;
                int rowIndex = 1;
                for (ItemDto itemDto: itemList){
                    row = sheet.createRow(rowIndex++);
                    row.createCell(0).setCellValue(parseColumnValue(itemDto.getCategoryName(), 0));
                    row.createCell(1).setCellValue(itemDto.getItemCode());
                    row.createCell(2).setCellValue(itemDto.getItemName());
                    row.createCell(3).setCellValue(itemDto.getItemType());
                    row.createCell(4).setCellValue(itemDto.getSerialNum());
                    row.createCell(5).setCellValue(parseColumnValue(itemDto.getQty(), 1));
                    row.createCell(6).setCellValue(parseColumnValue(itemDto.getAvailableQty(), 1));
                    row.createCell(7).setCellValue(parseColumnValue(itemDto.getReorderPoint()+"", 1));
                    row.createCell(8).setCellValue(parseColumnValue(itemDto.getWeight(), 1));
                    row.createCell(9).setCellValue(parseColumnValue(itemDto.getProductSKU(), 1));
                    row.createCell(10).setCellValue(parseColumnValue(itemDto.getPurchasePrice(), 1));
                    row.createCell(11).setCellValue(parseColumnValue(itemDto.getSalePrice(), 1));
                    row.createCell(12).setCellValue(parseColumnValue(itemDto.getDealerPrice(), 1));
                    row.createCell(13).setCellValue(parseColumnValue(itemDto.getTaxable(), 1));
                    row.createCell(14).setCellValue(parseColumnValue(itemDto.getTextAreaContent(), 1));
                }
                status = true;
                workbook.write(fileOutputStream);
                workbook.close();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        writeFileToResponse(response, sourcefile);
        return status;
    }

    public boolean uploadItemFile(MultipartFile attachedFile, HttpServletRequest request) {
        boolean status = false;
        File file = new File(attachedFile.getOriginalFilename());
        String[] fileName = file.getName().split("\\.");
       // SalesDetailsDao sdetails = new SalesDetailsDao();
        try{
            OutputStream os = new FileOutputStream(file);
            InputStream is = new BufferedInputStream(attachedFile.getInputStream());
            int count;
            byte buf[] = new byte[4096];
            while ((count = is.read(buf)) > -1) {
                os.write(buf, 0, count);
            }
            is.close();
            os.close();
            if(fileName[1].equals("csv"))
            {
                BufferedReader bfReader = new BufferedReader(new FileReader(file));
                String line = "";
                int index = 0;
                while ((line = bfReader.readLine())!=null) {
                    if (index == 0){
                        index++;
                        continue;
                    }
                    String[] data = line.split(",");
                    ItemDto itemDto = new ItemDto();
                    itemDto.setCategoryName(data[0]);
                    itemDto.setItemCode(data[1]);
                    itemDto.setItemName(data[2]);
                    itemDto.setItemType(data[3]);
                    itemDto.setSerialNum(data[4]);
                    itemDto.setQty(data[5]);
                    itemDto.setAvailableQty(data[6]);
                    itemDto.setReorderPoint(Integer.parseInt(data[7]));
                    itemDto.setWeight(data[8]);
                    itemDto.setProductSKU(data[9]);
                    itemDto.setPurchasePrice(data[10]);
                    itemDto.setSalePrice(data[11]);
                    itemDto.setDealerPrice(data[12]);
                    itemDto.setTaxable(data[13]);
                    itemDto.setTextAreaContent(data[14]);

                    salesDetails.AddItem(request, itemDto);
                }
                bfReader.close();
            }
            else{
                if(fileName[1].equals("xlsx")) { }
                else{
                    FileInputStream inputStream = new FileInputStream(file);
                    HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                    HSSFSheet firstSheet = workbook.getSheetAt(0);
                    Iterator<Row> iterator = firstSheet.rowIterator();
                    int index = 0;
                    while(iterator.hasNext())
                    {
                        if (index == 0){
                            index++;
                            iterator.next();
                            continue;
                        }
                        int count2 = 0;
                        ItemDto itemDto = new ItemDto();
                        HSSFRow nextRow = (HSSFRow) iterator.next();
                        Iterator<Cell> cellIterator = nextRow.cellIterator();
                        while(cellIterator.hasNext())
                        {
                            HSSFCell cell = (HSSFCell) cellIterator.next();
                            String data = parseColumnValue2(cell, 0);
                            if(count2 == 0) itemDto.setCategoryName(data);
                            else if(count2 == 1) itemDto.setItemCode(data);
                            else if(count2 == 2) itemDto.setItemName(data);
                            else if(count2 == 3) itemDto.setItemType(data);
                            else if(count2 == 4) itemDto.setSerialNum(data);
                            else if(count2 == 5) itemDto.setQty(data);
                            else if(count2 == 6) itemDto.setAvailableQty(data);
                            else if(count2 == 7) itemDto.setReorderPoint(Integer.parseInt(data));
                            else if(count2 == 8) itemDto.setWeight(data);
                            else if(count2 == 9) itemDto.setProductSKU(data);
                            else if(count2 == 10) itemDto.setPurchasePrice(data);
                            else if(count2 == 11) itemDto.setSalePrice(data);
                            else if(count2 == 12) itemDto.setDealerPrice(data);
                            else if(count2 == 13) itemDto.setTaxable(data);
                            else if(count2 == 14) itemDto.setTextAreaContent(data);
                            count2++;
                        }
                        salesDetails.AddItem(request, itemDto);
                    }
                    workbook.close();
                    inputStream.close();
                }
            }
            status = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    private String parseColumnValue(String data, int dataType){
        if(data == null || data.isEmpty()){  //String: 0, Integer: 1, Double: 2, Boolean: 3
            if(dataType == 1) data = "0";
            else if(dataType == 2) data = "0.0";
            else if(dataType == 3) data = "false";
            else data = "";
        }
        return data;
    }
    private String parseColumnValue2(HSSFCell cell, int dataType){
        String data = "";
        CellType cellType = cell.getCellTypeEnum();
        if(cellType == CellType.NUMERIC) data = String.valueOf(cell.getNumericCellValue());
        else if(cellType == CellType.BOOLEAN) data = String.valueOf(cell.getBooleanCellValue());
        else data = cell.getStringCellValue();
        if(data == null || data.isEmpty()){  //String: 0, Integer: 1, Double: 2, Boolean: 3
            if(dataType == 1) data = "0";
            else if(dataType == 2) data = "0.0";
            else if(dataType == 3) data = "false";
            else data = "";
        }
        return data;
    }
    private void setHSSFSheetColumnHeader(HSSFSheet sheet, String columns[]){
        HSSFRow row = sheet.createRow(0);
        for(int x=0; x<columns.length; x++){
            sheet.setColumnWidth(x, 20*256);
            row.createCell(x).setCellValue(columns[x]);
        }
    }

    private void checkFileExistance(File outFile){
        try {
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdir();
            }
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private void writeFileToResponse(HttpServletResponse response, File outFile){
        try {
            if(outFile!=null && outFile.exists()) {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=" + outFile.getName());
                FileCopyUtils.copy(new FileInputStream(outFile), response.getOutputStream());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
