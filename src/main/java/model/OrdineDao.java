package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdineDao implements BaseDao<OrdineBean> {

	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			System.err.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "ordini";
	
	public void doSave(AdminBean admin, UserBean user,GuestBean guest) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + OrdineDao.TABLE_NAME
				+ " (data, codiceAdmin, codiceClienti, codiceGuests) VALUES (?, ?, ?, ?)";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setInt(2,admin.getCodice());
			preparedStatement.setInt(3, user.getCodice());
			preparedStatement.setInt(4, guest.getCodice());

			preparedStatement.executeUpdate();

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
				
				bean.setData(rs.getDate("data").toString());
				bean.setCodAdmin(rs.getInt("codiceAdmin"));
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

		Collection<OrdineBean> products = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDao.TABLE_NAME;

		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();

				bean.setCodice(rs.getInt("codiceOrdine"));
				bean.setIsProcessed(rs.getBoolean("isProcessed"));
				bean.setData(rs.getDate("data").toString());
				bean.setCodAdmin(rs.getInt("codiceAdmin"));
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
		// TODO Auto-generated method stub
		
	}

}
