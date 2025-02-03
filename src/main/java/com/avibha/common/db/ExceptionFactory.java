/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 *  
 */
package com.avibha.common.db;
import java.sql.SQLException;
/**
 * @author avibha
 * 
 */
/**
 * Given a databaseType (e.g. DatabaseType.ORACLE), message, and SQLException, generate the 
 * appropriate DatabaseException subclass object 
 * 
 * 
 */
public class ExceptionFactory
{
    /**
     * Generate the correct exception subclass (of DatabaseException). For example, generate an
     * OracleException or a MySQLException.
     * @param databaseType
     * @param msg
     * @param e
     * @return DatabaseException (subclass)
     */
    public static DatabaseException getException(int databaseType, String msg, SQLException e)
    {
        switch (databaseType)
        {
            case DatabaseType.ORACLE : return(new OracleException(msg, e));
            case DatabaseType.MYSQL  : return(new MySQLException(msg, e));   
            case DatabaseType.POSTGRESQL  : return(new PostgreSQLException(msg, e));
        }
        return(null);
    }
    
    /**
     * Generate an object of the correct exception subclass (of DatabaseException). For example, 
     * generate an OracleException or a MySQLException.
     * @param databaseType
     * @param msg
     * @return DatabaseException (subclass)
     */
    public static DatabaseException getException(int databaseType, String msg)
    {
        switch (databaseType)
        {
            case DatabaseType.ORACLE : return(new OracleException(msg));
            case DatabaseType.MYSQL  : return(new MySQLException(msg));   
            case DatabaseType.POSTGRESQL  : return(new PostgreSQLException(msg));
        }
        return(null);
    }
}
