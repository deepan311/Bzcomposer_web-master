package com.nxsol.bizcomposer.clientvendorService.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.avibha.common.db.SQLExecutor;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.common.TblClientVendorService;
import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;

public class ClientVendorServiceDao {

	public ArrayList<TblClientVendorService> getService(ClientVendor person)
	{
		SQLExecutor db = new SQLExecutor();
		Connection con=null;
		con=db.getConnection();
		Statement stmt = null,stmt2 = null;
		ResultSet rs = null,rs2 = null;
		ArrayList<TblClientVendorService> vService = null;
		 try{
				stmt = con.createStatement();	  
				String sql =
		                "SELECT ClientVendorID,ServiceID,DateAdded,InvoiceStyleID,DefaultService,ServiceTypeID," +
		                "SalePrice,BillDate,JobCategoryID,startDate,terminateDate,Active" +
		                " FROM bca_clientvendorservice " +
		                "WHERE CompanyID = " + ConstValue.companyId +
		                " AND ClientVendorID = " + person.getCvID();
				
				stmt = con.createStatement();
				stmt2 = con.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{	
				if (vService == null) {
	                    vService = new ArrayList<>();
	                }
				 TblClientVendorService service = new TblClientVendorService(); 
				 service.setCv(person);
	                service.setClientVendorID(rs.getInt("ClientVendorID"));
	                //service.setServiceName(ConstValue.hateNull(rs.getString("ServiceName")));
	                service.setDateAdded(rs.getDate("DateAdded"));
	                service.setInvoiceStyleID(rs.getInt("InvoiceStyleID"));
	                service.setDefaultService(rs.getBoolean("DefaultService"));
	                service.setServiceTypeID(rs.getInt("ServiceTypeID"));
	                service.setServiceID(rs.getLong("ServiceID"));
	                service.setSalePrice(rs.getDouble("SalePrice"));
	                service.setBillDate(rs.getString("BillDate"));

	                service.setJobCategoryID(rs.getInt("JobCategoryID"));
	                service.setStartDate(rs.getDate("StartDate"));
	                service.setTerminateDate(rs.getDate("TerminateDate"));
//	                service.setUsage(rs.getInt("Usage"));
	                service.setActive(rs.getInt("Active"));
				}  
	                
			 }catch (Exception e) {
					// TODO: handle exception
					  e.printStackTrace();
				}finally {
					try {
						if (rs != null) {
							db.close(rs);
							}
						if (stmt != null) {
							db.close(stmt);
							}
						if (rs2 != null) {
							db.close(rs2);
							}
						if (stmt2 != null) {
							db.close(stmt2);
							}
							if(con != null){
							db.close(con);
							}
						} catch (Exception e) {
						e.printStackTrace();
					}
				}
		 return vService;
	}
}
