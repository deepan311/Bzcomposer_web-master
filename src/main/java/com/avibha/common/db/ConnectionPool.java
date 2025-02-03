package com.avibha.common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.InitialContext;

public class ConnectionPool {
	// Singleton instance
	private static ConnectionPool cp = null;

	// Database driver name
	private String driverName;

	// Database connection URL
	private String conURL;

	// Database connection username
	private String username;

	// Database connection password
	private String password;

	// Database type (e.g. DatabaseType.ORACLE)
	private int dbType;

	// Connection pool
	ArrayList<PooledConnection> conPool;

	// Pooled connection object
	private class PooledConnection {
		// Database connection
		private Connection con = null;
		// Availability flag
		private boolean available;

		/**
		 * Constructor for PooledConnection
		 *
		 * @param con       the JDBC connection
		 * @param available whether the connection is available
		 */
		PooledConnection(Connection con, boolean available) {
			this.con = con;
			this.available = available;
		}

		/**
		 * Gets the underlying JDBC connection.
		 *
		 * @return the JDBC Connection
		 */
		Connection getConnection() {
			return con;
		}

		/**
		 * Checks if this connection is available.
		 *
		 * @return true if available, false otherwise
		 */
		boolean isAvailable() {
			return available;
		}

		/**
		 * Sets the availability status of this connection.
		 *
		 * @param available the new availability status
		 */
		void setAvailable(boolean available) {
			this.available = available;
		}
	}

	/**
	 * Private constructor for the singleton.
	 * Creates JDBC connections using configuration from JNDI.
	 */
	private ConnectionPool() {
		try {
			InitialContext ic = new InitialContext();
			this.dbType = DatabaseType.getDbType((String) ic.lookup("java:comp/env/poolsize"));
			this.driverName = (String) ic.lookup("java:comp/env/DriverClass");
			this.conURL = (String) ic.lookup("java:comp/env/URL");
			this.username = (String) ic.lookup("java:comp/env/UID");
			this.password = (String) ic.lookup("java:comp/env/password");
			conPool = new ArrayList<PooledConnection>();
			addConnectionsToPool(Integer.parseInt((String) ic.lookup("java:comp/env/poolsize")));
		} catch (Exception e) {
			System.out.println("Error in Initializing Database Connection: " + e);
		}
	}

	/**
	 * Returns the singleton instance of ConnectionPool.
	 *
	 * @return the ConnectionPool instance
	 */
	public static ConnectionPool getInstance() {
		if (cp != null) {
			return cp;
		} else {
			cp = new ConnectionPool();
			return cp;
		}
	}

	/**
	 * Constructor that uses a given connection.
	 * Use this if you want to supply your own connection rather than use the pool.
	 *
	 * @param con the provided connection
	 */
	public ConnectionPool(Connection con) {
		conPool = new ArrayList<PooledConnection>();
		PooledConnection pc = new PooledConnection(con, true);
		conPool.add(pc);
		this.dbType = DatabaseType.getDbType(con);
	}

	/**
	 * Creates database connections and adds them to the pool.
	 *
	 * @param numPooledCon the number of connections to add
	 */
	private void addConnectionsToPool(int numPooledCon) {
		try {
			Class.forName(driverName).newInstance();
			for (int i = 0; i < numPooledCon; i++) {
				Connection con = DriverManager.getConnection(conURL, username, password);
				PooledConnection pc = new PooledConnection(con, true);
				conPool.add(pc);
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage() + "\nSQL State: " + sqle.getSQLState());
			throw ExceptionFactory.getException(dbType,
					sqle.toString() + "\nSQL State: " + sqle.getSQLState(), sqle);
		} catch (ClassNotFoundException cnfe) {
			throw ExceptionFactory.getException(dbType, cnfe.getMessage());
		} catch (InstantiationException ie) {
			throw ExceptionFactory.getException(dbType, ie.getMessage());
		} catch (Exception e) {
			throw ExceptionFactory.getException(dbType, e.getMessage());
		}
	}

	/**
	 * Gets the number of connections currently in the pool.
	 *
	 * @return the number of connections in the pool
	 */
	public int getNumConInPool() {
		return conPool.size();
	}

	/**
	 * Removes any closed connections from the pool.
	 */
	private void removeAnyClosedConnections() {
		try {
			boolean done = false;
			while (!done) {
				done = true;
				for (int i = 0; i < conPool.size(); i++) {
					PooledConnection pc = conPool.get(i);
					if (pc.getConnection().isClosed()) {
						conPool.remove(i);
						done = false;
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets an available connection from the pool.
	 * If none are available, a new connection is added.
	 *
	 * @return an available JDBC Connection
	 */
	public Connection getConnection() {
		removeAnyClosedConnections();
		for (int i = 0; i < conPool.size(); i++) {
			PooledConnection pc = conPool.get(i);
			if (pc.isAvailable()) {
				pc.setAvailable(false);
				return pc.getConnection();
			}
		}
		// No available connection; add one to the pool.
		addConnectionsToPool(1);
		PooledConnection pc = conPool.get(conPool.size() - 1);
		return pc.getConnection();
	}

	/**
	 * Returns the database type.
	 *
	 * @return the database type as an integer
	 */
	public int getDbType() {
		return dbType;
	}

	/**
	 * Releases a connection, marking it as available for reuse.
	 *
	 * @param con the connection to release
	 */
	void releaseConnection(Connection con) {
		for (int i = 0; i < conPool.size(); i++) {
			PooledConnection pc = conPool.get(i);
			if (pc.getConnection().equals(con)) {
				pc.setAvailable(true);
			}
		}
	}

	/**
	 * Closes all connections in the connection pool.
	 */
	public void closeAllConnections() {
		for (int i = 0; i < conPool.size(); i++) {
			PooledConnection pc = conPool.get(i);
			closeConnection(pc.getConnection(), dbType);
		}
		conPool.clear();
	}

	/**
	 * Attempts to resize the connection pool to the new size.
	 * If reducing the size, only available connections are removed.
	 *
	 * @param newSize the desired pool size
	 * @return the new size of the connection pool
	 */
	public int resizeConnectionPool(int newSize) {
		if ((newSize < 0) || (newSize > 999))
			throw ExceptionFactory.getException(dbType, "Connection pool size must be between 0 and 999");

		removeAnyClosedConnections();

		if (newSize > conPool.size()) {
			// Add new connections to the pool.
			int conToAdd = newSize - conPool.size();
			addConnectionsToPool(conToAdd);
		} else if (newSize < conPool.size()) {
			// Remove available connections.
			boolean done = false;
			while ((newSize < conPool.size()) && !done) {
				done = true;
				for (int i = 0; i < conPool.size(); i++) {
					PooledConnection pc = conPool.get(i);
					if (pc.isAvailable()) {
						closeConnection(pc.getConnection(), dbType);
						conPool.remove(i);
						done = false;
						break;
					}
				}
			}
		}
		return conPool.size();
	}

	/**
	 * Releases a connection, marking it as available.
	 *
	 * @param con    the connection to release
	 * @param dbType the database type
	 */
	public void releaseConnection(Connection con, int dbType) {
		for (int i = 0; i < conPool.size(); i++) {
			PooledConnection pc = conPool.get(i);
			if (pc.getConnection().equals(con)) {
				pc.setAvailable(true);
			}
		}
	}

	/**
	 * Closes the given connection.
	 *
	 * @param con    the connection to close
	 * @param dbType the database type
	 */
	void closeConnection(Connection con, int dbType) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			throw ExceptionFactory.getException(dbType, e.getMessage(), e);
		}
	}
}
