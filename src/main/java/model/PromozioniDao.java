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

public class PromozioniDao implements BaseDao<PromozioniBean> {
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "Promozione";
	@Override
	public void doSave(PromozioniBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + PromozioniDao.TABLE_NAME
				+ " (codicePromozione, categoria, codiceAdministrator) VALUES (?, ?, ?)";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getCodice());
			preparedStatement.setString(2, product.getCategoria());
			preparedStatement.setInt(3, product.getCodiceAdmin());

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
	public synchronized void doUpdate(PromozioniBean product,String oldCode) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		

		String insertSQL = "UPDATE " + PromozioniDao.TABLE_NAME
				+ " SET codicePromozione=?, categoria=?, codiceAdministrator=? WHERE codicePromozione= ?";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getCodice());
			preparedStatement.setString(2, product.getCategoria());
			preparedStatement.setInt(3, product.getCodiceAdmin());
			preparedStatement.setString(4,oldCode);

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
	
	public boolean doDelete(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + PromozioniDao.TABLE_NAME + " WHERE codicePromozione = ?";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, code);

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

	
	public PromozioniBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		PromozioniBean bean = new PromozioniBean();

		String selectSQL = "SELECT * FROM " + PromozioniDao.TABLE_NAME + " WHERE codicePromozione = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getString("codicePromozione"));
				bean.setCategoria(rs.getString("categoria"));
				bean.setCodiceAdmin(rs.getInt("codiceAdministrator"));

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
	public Collection<PromozioniBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<PromozioniBean> products = new LinkedList<PromozioniBean>();

		String selectSQL = "SELECT * FROM " + PromozioniDao.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PromozioniBean bean = new PromozioniBean();

				bean.setCodice(rs.getString("codicePromozione"));
				bean.setCategoria(rs.getString("categoria"));
				bean.setCodiceAdmin(rs.getInt("codiceAdministrator"));
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
	public boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public PromozioniBean doRetrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}