package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.sql.Date;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdineDao implements BaseDao<OrdineBean> {

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
	
	
	
	public int doSave(OrdineBean bean,int i) throws SQLException {

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
	@Override
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

	@Override
	public OrdineBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrdineBean bean = new OrdineBean();

		String selectSQL = "SELECT * FROM " + OrdineDao.TABLE_NAME + " WHERE codiceOrdine = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getInt("codiceOrdine"));
				bean.setIsProcessed(rs.getBoolean("isProcessed"));
				
				bean.setData(rs.getString("data"));
				bean.setCodCliente(rs.getInt("codiceClienti"));
				bean.setCodGuest(rs.getInt("codiceGuests"));
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

	@Override
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

				bean.setCodice(rs.getInt("codiceOrdine"));
				bean.setIsProcessed(rs.getBoolean("isProcessed"));
				bean.setData(rs.getString("data"));
				bean.setCodCliente(rs.getInt("codiceClienti"));
				bean.setCodGuest(rs.getInt("codiceGuests"));
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

		String selectSQL = "SELECT * FROM " + OrdineDao.TABLE_NAME +" WHERE codiceClienti=?";

		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();

				bean.setCodice(rs.getInt("codiceOrdine"));
				bean.setIsProcessed(rs.getBoolean("isProcessed"));
				bean.setData(rs.getString("data"));
				bean.setCodCliente(rs.getInt("codiceClienti"));
				bean.setCodGuest(rs.getInt("codiceGuests"));
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
	@Override
	public void doSave(OrdineBean product) throws SQLException {
		
		
	}
	
	   
	
	

}
