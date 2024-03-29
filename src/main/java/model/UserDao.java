package model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.logging.Logger;

public class UserDao{
	private static DataSource ds;
	 private static final Logger logger = Logger.getLogger(UserDao.class.getName());

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			logger.severe("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "clienti";
	private static final String SELECTALL="SELECT * FROM ";
	private static final String UPDATE="UPDATE ";
	private static final String CLIENTE="codiceCliente";
	private static final String COGNOME="cognome";
	private static final String TELEFONO="telefono";
	private static final String ADDR="indirizzo";
	private static final String EMAIL="email";
	private static final String PASS="password";

	public synchronized int doSave(UserBean user) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UserDao.TABLE_NAME
				+ " (nome, cognome, telefono, indirizzo, email, password) VALUES (?, ?, ?, ?, ?, ?)";
		int lastInsertedId=-1;
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getNome());
			preparedStatement.setString(2, user.getCognome());
			preparedStatement.setString(3, user.getTelefono());
			preparedStatement.setString(4, user.getIndirizzo());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, toHash(user.getPassword()));

			preparedStatement.executeUpdate();
			connection.setAutoCommit(false); 
			
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			 if (generatedKeys.next()) {
	                lastInsertedId = generatedKeys.getInt(1);
	                // Now lastInsertedId holds the primary key value of the inserted row
	            }
			 generatedKeys.close();
			connection.commit();
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return lastInsertedId;
		
	}


	public synchronized void doUpdate(UserBean user) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = UPDATE + UserDao.TABLE_NAME
				+ " SET nome=?, cognome=?, telefono=?, indirizzo=?, email=? WHERE codiceCliente=?";
		try {
			
			connection = ds.getConnection();
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, user.getNome());
			preparedStatement.setString(2, user.getCognome());
			preparedStatement.setString(3, user.getTelefono());
			preparedStatement.setString(4, user.getIndirizzo());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setInt(6, user.getCodice());

			preparedStatement.executeUpdate();
			connection.setAutoCommit(false); 
			connection.commit();
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}
	
public synchronized void doUpdateAfterOrder(UserBean user) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = UPDATE + UserDao.TABLE_NAME
				+ " SET nome=?, cognome=?, telefono=?, indirizzo=?  WHERE codiceCliente=?";
		
		try {
			connection = ds.getConnection();
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, user.getNome());
			preparedStatement.setString(2, user.getCognome());
			preparedStatement.setString(3, user.getTelefono());
			preparedStatement.setString(4, user.getIndirizzo());
			preparedStatement.setInt(5, user.getCodice());
			
			preparedStatement.executeUpdate();
			
			connection.setAutoCommit(false); 
			
			connection.commit();
			
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}
public synchronized void doPass(String pass,int code) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = UPDATE + UserDao.TABLE_NAME
				+ " SET password=? WHERE codiceCliente=?";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, toHash(pass));
			preparedStatement.setInt(2,code);
			preparedStatement.executeUpdate();
			connection.setAutoCommit(false); 
			connection.commit();
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}
	
	
	
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + UserDao.TABLE_NAME + " WHERE codiceCliente = ?";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	
	public synchronized UserBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UserBean bean = new UserBean();

		String selectSQL = SELECTALL + UserDao.TABLE_NAME + " WHERE codiceCliente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getInt(CLIENTE));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString(COGNOME));
				bean.setTelefono(rs.getString(TELEFONO));
				bean.setIndirizzo(rs.getString(ADDR));
				bean.setEmail(rs.getString(EMAIL));
				bean.setPassword(rs.getString(PASS));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}
	public synchronized UserBean doRetrieveByEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UserBean bean = new UserBean();

		String selectSQL = SELECTALL + UserDao.TABLE_NAME + " WHERE email = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getInt(CLIENTE));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString(COGNOME));
				bean.setTelefono(rs.getString(TELEFONO));
				bean.setIndirizzo(rs.getString(ADDR));
				bean.setEmail(rs.getString(EMAIL));
				bean.setPassword(rs.getString(PASS));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}
	
	public Collection<UserBean> doRetrieveAll() throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<UserBean> products = new LinkedList<>();

		String selectSQL = SELECTALL + UserDao.TABLE_NAME;

		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UserBean bean = new UserBean();

				bean.setCodice(rs.getInt(CLIENTE));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString(COGNOME));
				bean.setTelefono(rs.getString(TELEFONO));
				bean.setIndirizzo(rs.getString(ADDR));
				bean.setEmail(rs.getString(EMAIL));
				bean.setPassword(rs.getString(PASS));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}
	
	private String toHash(String password) throws SQLException {
		StringBuilder hashString = null;
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
             hashString = new StringBuilder();
            
            for (int i = 0; i < hash.length; i++) {
                hashString.append(Integer.toHexString( 
                        (hash[i] & 0xFF) | 0x100 
                    ).toLowerCase().substring(1,3))   ;
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new SQLException();
        }
        return hashString.toString();
    }
}
