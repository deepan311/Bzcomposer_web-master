package com.nxsol.bizcomposer.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrintFormProperties
{
private int billingPrintFormNo = 0;
    
    private String[] billingPrintClassName = {"com.bzcomposer.ar.billing.ArBillingPrintableForm1",
    "com.bzcomposer.ar.billing.ArBillingPrintableForm2"};
    
    private int invoicePrintFormNo = 0;
    
    private String[] invoicePrintClassName = {"com.bzcomposer.modules.invoice.InvoicePrintable"};
    
    private static PrintFormProperties instance = null;
    
    private PrintFormProperties_SQL sql = null;

    private boolean printBills = false;

    private boolean mailToCustomer = false;
    
    private int startBillNumber = -1;

    protected PrintFormProperties() {
        
        sql = new PrintFormProperties_SQL(this);
        
        sql.SQL_read();
    }
    
    public int getBillingPrintFormNo() {
        return billingPrintFormNo;
    }
    
    public int getInvoicePrintFormNo() {
        return invoicePrintFormNo;
    }
    
    /*public ArPrintable getBillingPrintable() {
        
        String printable = getBillingClassName(getBillingPrintFormNo());
        
        ArPrintable arPrintable = null;
        try {
            arPrintable = (ArPrintable) Class.forName(printable).newInstance();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        return arPrintable;
    }
    
    public ArPrintable getInvoicePrintable() {
        
        String printable = getInvoiceClassName(getBillingPrintFormNo());
        
        ArPrintable arPrintable = null;
        try {
            arPrintable = (ArPrintable) Class.forName(printable).newInstance();
        } catch (InstantiationException ex) {
            //ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            //ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            //ex.printStackTrace();
        }
        
        return arPrintable;
    }*/
    
    /**
     *update property and database
     */
    public void setBillingPrintFormNo(int billingPrintFormNo) {
        this.billingPrintFormNo = billingPrintFormNo;        
    }
    
    /**
     *update property and database
     */
    public void setInvoicePrintFormNo(int invoicePrintFormNo) {
        this.invoicePrintFormNo = invoicePrintFormNo;
        sql.SQL_updateInvoicePrintFormNo();
    }
    
    
    public String getBillingClassName(int formNo) {
        if (formNo>billingPrintClassName.length-1)
            return billingPrintClassName[0];//return default
        return billingPrintClassName[formNo];
    }
    
    public String getInvoiceClassName(int formNo) {
        if (formNo>invoicePrintClassName.length-1)
            return invoicePrintClassName[0];
        return invoicePrintClassName[formNo];
    }

    /**
     * @return the printBills
     */
    public boolean isPrintBills() {
        return printBills;
    }

    /**
     * @param printBills the printBills to set
     */
    public void setPrintBills(boolean printBills) {
        this.printBills = printBills;
    }

    /**
     * @return the mailToCustomer
     */
    public boolean isMailToCustomer() {
        return mailToCustomer;
    }

    /**
     * @param mailToCustomer the mailToCustomer to set
     */
    public void setMailToCustomer(boolean mailToCustomer) {
        this.mailToCustomer = mailToCustomer;
    }

    public void savePrintFormProperties(){
        sql.SQL_updateBillingPrintFormNo();
    }

    /**
     * @return the startBillNumber
     */
    public int getStartBillNumber() {
        return startBillNumber;
    }

    /**
     * @param startBillNumber the startBillNumber to set
     */
    public void setStartBillNumber(int startBillNumber) {
        this.startBillNumber = startBillNumber;
    }

}
class PrintFormProperties_SQL {
    
    private PrintFormProperties printFormProperties = null;
    
    public PrintFormProperties_SQL(PrintFormProperties printFormProperties ) {
        
        this.printFormProperties = printFormProperties;
    }
    
    public void SQL_read() {
        
        String sql =  " SELECT PrintFormNo_Billing,PrintFormNo_Invoice,PrintBills,MailToCustomer,StartingBillNumber " +
                " FROM bca_preference " +
                " WHERE CompanyID = " + ConstValue.companyId;
        
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
           
            //stmt = ConstValue.gConnection.createStatement();
            rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                printFormProperties.setBillingPrintFormNo(rs.getInt("PrintFormNo_Billing"));
                printFormProperties.setPrintBills(rs.getInt("PrintBills")==1?true:false);
                printFormProperties.setMailToCustomer(rs.getInt("MailToCustomer")==1?true:false);
                printFormProperties.setInvoicePrintFormNo(rs.getInt("PrintFormNo_Invoice"));
                printFormProperties.setStartBillNumber(rs.getInt("StartingBillNumber"));

                
            }
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        } finally {
            try {
                if (rs!=null) rs.close();
                if (stmt!=null) stmt.close();
            } catch (SQLException e) {
            }
        }
    }
    
    public void SQL_updateBillingPrintFormNo() {
        
        String sql = " UPDATE bca_preference " +
                " SET PrintFormNo_Billing = " + printFormProperties.getBillingPrintFormNo() +
                " ,PrintBills="+(printFormProperties.isPrintBills()?1:0)+
                " ,MailToCustomer="+(printFormProperties.isMailToCustomer()?1:0)+
                " ,StartingBillNumber="+printFormProperties.getStartBillNumber()+
                " WHERE CompanyID = " + ConstValue.companyId;
        
        Statement stmt = null;
        
        try {
            
            //stmt = ConstValue.gConnection.createStatement();
            stmt.executeUpdate(sql);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt!=null) stmt.close();
            } catch (SQLException e) {}
        }
    }
    
    public void SQL_updateInvoicePrintFormNo() {
        String sql = " UPDATE bca_preference SET PrintFormNo_Invoice = " + printFormProperties.getInvoicePrintFormNo()+
                " WHERE CompanyID = " + ConstValue.companyId;
        
        Statement stmt = null;
        
        try {
            
            //stmt = ConstValue.gConnection.createStatement();
            stmt.executeUpdate(sql);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt!=null) stmt.close();
            } catch (SQLException e) {}
        }
    }
}
