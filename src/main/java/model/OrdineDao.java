package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import java.util.Collection;
import java.sql.Date;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdineDao  {

	private static DataSource ds;
	private static final Logger logger = Logger.getLogger(OrdineDao.class.getName());
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			logger.severe("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "ordini";
	private static final String selectAll="SELECT * FROM ";
	private static final String codiceOrdine="codiceOrdine";
	private static final String codiceClienti="codiceClienti";
	private static final String isProcessed="isProcessed";
	private static final String codiceGuest="codiceGuest";
	
	
	public int doSave(OrdineBean bean) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + OrdineDao.TABLE_NAME+ " (isProcessed, data, codiceClienti) VALUES (0, ?, ?)";
		
		LocalDate currentDate = LocalDate.now();
		
		Date sqlDate = Date.valueOf(currentDate);
		int lastInsertedId=-1;
		

		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setDate(1,sqlDate);
			preparedStatement.setInt(2, bean.getCodCliente());
			connection.setAutoCommit(false); 
			preparedStatement.executeUpdate();
			
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
	public void doUpdate(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;	
		
		String insertSQL = "UPDATE "+ OrdineDao.TABLE_NAME+ " SET isProcessed = true"+ " WHERE codiceOrdine=?";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, code);
			connection.setAutoCommit(false);
			preparedStatement.executeUpdate();
			connection.commit();
		} 
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}}
	}
	
	public boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		

		String deleteSQL = "DELETE FROM " + OrdineDao.TABLE_NAME + " WHERE codiceOrdine = ?";
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

	
	public OrdineBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrdineBean bean = new OrdineBean();
		
		String selectSQL = selectAll + OrdineDao.TABLE_NAME + " WHERE codiceOrdine = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getInt(codiceOrdine));
				bean.setIsProcessed(rs.getBoolean(isProcessed));
				
				bean.setData(rs.getString("data"));
				bean.setCodCliente(rs.getInt(codiceClienti));
				bean.setCodGuest(rs.getInt(codiceGuest));
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

	
	public Collection<OrdineBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + OrdineDao.TABLE_NAME;

		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();

				bean.setCodice(rs.getInt(codiceOrdine));
				bean.setIsProcessed(rs.getBoolean(isProcessed));
				bean.setData(rs.getString("data"));
				bean.setCodCliente(rs.getInt(codiceClienti));
				bean.setCodGuest(rs.getInt(codiceGuest));
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
	
	
	public Collection<OrdineBean> doRetrievebyUser(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> products = new LinkedList<>();
		

		String selectSQL = selectAll + OrdineDao.TABLE_NAME +" WHERE codiceClienti=?";

		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();

				bean.setCodice(rs.getInt(codiceOrdine));
				bean.setIsProcessed(rs.getBoolean(isProcessed));
				bean.setData(rs.getString("data"));
				bean.setCodCliente(rs.getInt(codiceClienti));
				bean.setCodGuest(rs.getInt(codiceGuest));
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
	
	   
	
	

}
