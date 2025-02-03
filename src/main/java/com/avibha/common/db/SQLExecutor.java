/*
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * www.avibha.com
 *  
 */
package com.avibha.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.avibha.common.log.Loger;

/**
 * @author avibha
 * 
 */

@Configuration
@EnableTransactionManagement
public class SQLExecutor  {

	/** database connection */
	//private Connection con = null;
	/** connection pool */
	//private ConnectionPool conPool = null;

	@Autowired
	private Environment env;
	

	/** Database type (for example, DatabaseType.ORACLE) */
	private int dbType;

	/** prepared statement used in calls to runQuery() and runQueryKeepConnOpen() */
	private PreparedStatement prepStatement = null;

	/** maximum rows to return in a SELECT statement */
	private int maxRows = 1000;

	/** timeout interval for long running queries */
	private int timeoutInSec = 0; // 0 = no timeout

	/**
	 * remember the last SQL statement (so we don't re-prepare a statement if
	 * SQL doesn't change
	 */
	private String lastSQL = null;

	/** number of rows updated in last runQuery() */
	private int numRecordsUpdated = 0;

	/** ArrayList which stores the SQL parameters (?) */
	private ArrayList<Object> params = null;
	
	private static DriverManagerDataSource dataSource ;
	
	/**
	 * Constructor uses provided connection pool
	 * @throws InterruptedException 
	 */
	public SQLExecutor() {
		
		params = new ArrayList<Object>();
	}

	/**
	 * Getter for connection
	 * @return Connection
	 */
	/*
	 * public Connection getConnection1() { return (con); }
	 */
	
	
	/*static {
		try {
			// InitialContext ic = new InitialContext();
	            //dataSource.setDriverClassName((String) ic.lookup("java:comp/env/DriverClass"));
	            //dataSource.setUrl((String) ic.lookup("java:comp/env/URL"));
	           // dataSource.setUsername((String) ic.lookup("java:comp/env/UID"));
	            //dataSource.setPassword((String) ic.lookup("java:comp/env/password"));
				dataSource.setDriverClassName(env.getProperty("DriverClass"));
				dataSource.setUrl(env.getProperty("spring.datasource.url"));
				dataSource.setUsername(env.getProperty("spring.datasource.username"));
				dataSource.setPassword(env.getProperty("spring.datasource.password"));

				dataSource.setMaxIdle(10);
				dataSource.setMaxTotal(100);
				dataSource.setInitialSize(5);
				dataSource.setValidationQuery("select 1");
				dataSource.setTestOnBorrow(true);
		} catch (Exception ex) {
			System.out.println("***Not able to create Datasource: " + ex.getMessage());
			Loger.log(Loger.DEBUG,"***Not able to create Datasource: " + ex.getMessage());
			ex.printStackTrace();
		}
	}*/


    public Connection getConnection() {
    	Connection connection =null;
        try {
             connection = dataSource.getConnection();
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bzc","root","root");
		} catch (Exception ex) {
			System.out.println("Not able to create DB connection " + ex.getMessage());
			Loger.log(Loger.DEBUG,"Not able to create DB connection " + ex.getMessage());
			ex.printStackTrace();
		}
        return connection;
    }


	/**
	 * Clears out the query parameter list
	 */
	public void clearParams() {
		if (params != null) {
			params.clear();
		}
	}

	/**
	 * Adds a query parameter to the list
	 * @param param parameter to add to list
	 */
	public void addParam(Object param) {
		try{
			params.add(param);
		}catch(NullPointerException npe){
			Loger.log(Loger.DEBUG,"Exception While Adding Parameters");
		}
	}

	/**
	 * Adds an int query parameter to the list
	 * @param param parameter to add to list
	 */
	public void addParam(int param) {
		addParam(new Integer(param));
	}

	/**
	 * Adds a long query parameter to the list
	 * @param param parameter to add to list
	 */
	public void addParam(long param) {
		addParam(new Long(param));
	}

	/**
	 * Adds a double query parameter to the list
	 * @param param parameter to add to list
	 */
	public void addParam(double param) {
		addParam(new Double(param));
	}

	/**
	 * Adds a boolean query parameter to the list
	 * @param param parameter to add to list
	 */
	public void addParam(boolean param) {
		addParam(new Boolean(param));
	}

	/**
	 * Adds a float query parameter to the list
	 * @param param parameter to add to list
	 */
	public void addParam(float param) {
		addParam(new Float(param));
	}

	/**
	 * Adds a short query parameter to the list
	 * @param param parameter to add to list
	 */
	public void addParam(short param) {
		addParam(new Short(param));
	}

	/**
	 * Runs the sql and does NOT close the connection
	 * @param sql command to run
	 * @return SQLResults if it's a query, null otherwise
	 */
	/*
	 * public SQLResults runQuery(String sql) { SQLResults results = null; ResultSet
	 * rs = null; numRecordsUpdated = 0;
	 * 
	 * try { if ((sql.equalsIgnoreCase(lastSQL) == false) || (prepStatement ==
	 * null)) // if sql has changed, then // prepare stmt prepStatement =
	 * con.prepareStatement(sql); lastSQL = sql;
	 * 
	 * setPrepStatementParameters();
	 * 
	 * boolean isSelectStatement = isSelectStatement(sql); if ((dbType !=
	 * DatabaseType.ORACLE) && (isSelectStatement == false)) {
	 * prepStatement.setMaxRows(maxRows); numRecordsUpdated =
	 * prepStatement.executeUpdate(); } else { if (timeoutInSec > 0)
	 * prepStatement.setQueryTimeout(timeoutInSec);
	 * 
	 * if (isSelectStatement(sql)) { prepStatement.setMaxRows(maxRows); rs =
	 * prepStatement.executeQuery(); } else { numRecordsUpdated =
	 * prepStatement.executeUpdate(); } }
	 * 
	 * if (isSelectStatement) { // SELECT statement, so get results
	 * ResultSetMetaData rsmd = rs.getMetaData(); int columnCount =
	 * rsmd.getColumnCount(); results = new SQLResults(dbType, columnCount);
	 * 
	 * // add field values to ResultSet object while (rs.next()) { for (int i = 0; i
	 * < columnCount; i++) results.add(rs.getObject(i + 1)); } // add column names
	 * to ResultSet object for (int i = 0; i < columnCount; i++)
	 * results.addColumnName(rsmd.getColumnName(i + 1)); } } catch (SQLException e)
	 * { throw ExceptionFactory.getException(dbType, e.getMessage() +
	 * "\nSQL Failed: " + sql, e); } finally { clearParams(); }
	 * 
	 * return (results); }
	 */

	private void setPrepStatementParameters() throws SQLException {
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				Object param = params.get(i);
				if (param instanceof Integer) {
					int value = ((Integer) param).intValue();
					prepStatement.setInt(i + 1, value);
				} else if (param instanceof Short) {
					short sh = ((Short) param).shortValue();
					prepStatement.setShort(i + 1, sh);
				} else if (param instanceof String) {
					String s = (String) param;
					prepStatement.setString(i + 1, s);
				} else if (param instanceof Double) {
					double d = ((Double) param).doubleValue();
					prepStatement.setDouble(i + 1, d);
				} else if (param instanceof Float) {
					float f = ((Float) param).floatValue();
					prepStatement.setFloat(i + 1, f);
				} else if (param instanceof Long) {
					long l = ((Long) param).longValue();
					prepStatement.setFloat(i + 1, l);
				} else if (param instanceof Boolean) {
					boolean b = ((Boolean) param).booleanValue();
					prepStatement.setBoolean(i + 1, b);
				} else if (param instanceof java.sql.Date) {
					prepStatement.setDate(i + 1, (java.sql.Date) param);
				} else if (param instanceof java.sql.Time) {
					prepStatement.setTime(i + 1, (java.sql.Time) param);
				} else if (param instanceof java.sql.Timestamp) {
					prepStatement.setTimestamp(i + 1,
							(java.sql.Timestamp) param);
				} else {
					clearParams();
					throw ExceptionFactory.getException(dbType,
							"Unknown parameter type: param " + i);
				}
			}
		}
	}

	/**
	 * Runs the sql and does NOT close the connection. Returns a standard JDBC
	 * ResultSet object which can be scrolled through using rs.next(). This
	 * method is preferable to runQuery() when your ResultSet is too large to
	 * fit into memory (a SQLResults object).
	 * 
	 * @param sql
	 *            sql command to run
	 * @return ResultSet if it's a query, null otherwise
	 */
	/*
	 * public ResultSet runQueryStreamResults(String sql) { ResultSet rs = null;
	 * numRecordsUpdated = 0;
	 * 
	 * try { if ((sql.equalsIgnoreCase(lastSQL) == false) || (prepStatement ==
	 * null)) // if sql has changed, then // prepare stmt prepStatement =
	 * con.prepareStatement(sql); lastSQL = sql;
	 * 
	 * setPrepStatementParameters();
	 * 
	 * boolean isSelectStatement = isSelectStatement(sql); if ((dbType !=
	 * DatabaseType.ORACLE) && (isSelectStatement == false)) {
	 * prepStatement.setMaxRows(maxRows); numRecordsUpdated =
	 * prepStatement.executeUpdate(); } else { if (timeoutInSec > 0)
	 * prepStatement.setQueryTimeout(timeoutInSec);
	 * 
	 * if (isSelectStatement(sql)) { prepStatement.setMaxRows(maxRows); rs =
	 * prepStatement.executeQuery(); } else { numRecordsUpdated =
	 * prepStatement.executeUpdate(); } } } catch (SQLException e) { throw
	 * ExceptionFactory.getException(dbType, e.getMessage() + "\nSQL Failed: " +
	 * sql, e); } finally { clearParams(); }
	 * 
	 * return (rs); }
	 */

	/**
	 * Runs the sql and automatically closes the connection when done
	 * 
	 * @param sql
	 *            sql command to execute
	 * @return SQLResults if it's a query, null otherwise
	 */
	/*
	 * public SQLResults runQueryCloseCon(String sql) { try { return
	 * (runQuery(sql)); } finally { conPool.closeConnection(con, dbType); } }
	 */

	/**
	 * Is this SQL statement a select statement (returns rows?)
	 * 
	 * @param sql
	 *            String
	 * @return boolean
	 */
	private boolean isSelectStatement(String sql) {
		StringBuffer sb = new StringBuffer(sql.trim());
		String s = (sb.substring(0, 6));
		return (s.equalsIgnoreCase("SELECT"));
	}

	/**
	 * Cancels the currently running query if both the database and driver
	 * support aborting an SQL statement. This method can be used by one thread
	 * to cancel a statement that is being executed by another thread.
	 */
	public void cancelQuery() {
		try {
			if (prepStatement != null)
				prepStatement.cancel();
		} catch (SQLException e) {
			throw ExceptionFactory.getException(dbType, e.getMessage());
		}
	}

	/**
	 * Closes the SQLExecutor's statement object (releasing database and JDBC
	 * resources immediately instead of waiting for this to happen when the
	 * SQLExecutor object is garbage collected).
	 */
	public void closeQuery() {
		try {
			if (prepStatement != null) {
				prepStatement.close();
				prepStatement = null;
			}
		} catch (SQLException e) {
			throw ExceptionFactory.getException(dbType, e.getMessage());
		}
	}

	/**
	 * Gets the auto-commit status
	 * 
	 * @return true if in auto-commit mode, false otherwise
	 */
	/*
	 * public boolean getAutoCommit() { try { return (con.getAutoCommit()); } catch
	 * (SQLException e) { throw ExceptionFactory.getException(dbType,
	 * "Unable to get the auto commit status", e); } }
	 */

	/**
	 * Sets the auto-commit status
	 * 
	 * @param autoCommit
	 *            boolean
	 */
	/*
	 * public void setAutoCommit(boolean autoCommit) { try {
	 * con.setAutoCommit(autoCommit); } catch (SQLException e) { throw
	 * ExceptionFactory.getException(dbType,
	 * "Unable to set the auto commit status = " + autoCommit, e); } }
	 */

	/**
	 * Sets the transaction isolation level
	 * 
	 * @param level
	 *            int
	 */
	/*
	 * public void setTransactionIsolation(int level) { try {
	 * con.setTransactionIsolation(level); } catch (SQLException e) { throw
	 * ExceptionFactory.getException(dbType,
	 * "Attempted to set an invalid transaction isolation level", e); } }
	 */

	/**
	 * Commits the current transaction
	 */
	/*
	 * public void commitTrans() { try { con.commit(); } catch (SQLException e) {
	 * throw ExceptionFactory.getException(dbType,
	 * "Failure during transaction commit", e); } }
	 */

	/**
	 * Rolls back the current transaction
	 */
	/*
	 * public void rollbackTrans() { try { con.rollback(); } catch (SQLException e)
	 * { throw ExceptionFactory.getException(dbType,
	 * "Failure during transaction rollback", e); } }
	 */

	/**
	 * Sets the current connection readOnly status
	 * 
	 * @param readOnly
	 *            boolean
	 */
	/*
	 * public void setReadOnly(boolean readOnly) { try { con.setReadOnly(readOnly);
	 * } catch (SQLException e) { throw ExceptionFactory.getException(dbType,
	 * "Exception during setReadOnly", e); } }
	 */

	/**
	 * Is the current connection read only?
	 * 
	 * @return boolean
	 */
	/*
	 * public boolean isReadOnly() { try { return (con.isReadOnly()); } catch
	 * (SQLException e) { throw ExceptionFactory.getException(dbType,
	 * "Failure while accessing readOnly status of connection", e); } }
	 */

	/**
	 * Setter for the maximum number of rows that a query can return
	 * 
	 * @param maxRow
	 *            int
	 */
	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	/**
	 * Getter for the maximum number of rows that a query can return
	 * 
	 * @return int
	 */
	public int getMaxRows() {
		return (maxRows);
	}

	/**
	 * Getter for numRecordsUpdated. Call this method to find out how many rows
	 * were updated in the last runQuery() call that did an INSERT, UPDATE, or
	 * DELETE.
	 * 
	 * @return int
	 */
	public int getNumRecordsUpdated() {
		return (numRecordsUpdated);
	}

	/**
	 * Getter for timeoutInSec
	 * 
	 * @return int query timeout in seconds
	 */
	public int getTimeoutInSec() {
		return (timeoutInSec);
	}

	/**
	 * Setter for timeoutInSec
	 * 
	 * @param timeoutInSec
	 *            int sets the query timeout in seconds
	 */
	public void setTimeoutInSec(int timeoutInSec) {
		this.timeoutInSec = timeoutInSec;
	}

	/**
	 * Closes this connection.
	 */
	/*
	 * public void closeConnection() { conPool.closeConnection(con, dbType); }
	 */

	/**
	 * Releases this connection (sets its available status = true). It does not
	 * close the connection. It merely makes the connection available for
	 * re-use.
	 * 
	 * @param conPool
	 *            ConnectionPool
	 */
	/*
	 * public void releaseConnection() { conPool.close(con)(con, dbType); }
	 */
	public void close(ResultSet c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(Statement c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public int getDatatabaseType(){
		return dbType;
	}

	/**
	 * @author SarfrazMalik
	 * To avoid error on execution on group by query
	 */
	public static void dissable_ONLY_FULL_GROUP_BY() {
		SQLExecutor db = new SQLExecutor();
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));";
		try {
			con = db.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {  db.close(pstmt); }
				if (con != null) { db.close(con); }
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	@Bean
	public DataSource dataSource() {

	    dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(env.getProperty("DriverClass"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
	    return dataSource;
	}
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
	
}
