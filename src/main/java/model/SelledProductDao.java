package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SelledProductDao implements BaseDao<SelledProductBean> {
	private static final Logger logger = Logger.getLogger(SelledProductDao.class.getName());
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			logger.severe("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "prodottiVenduti";
	
	public synchronized void doSave(SelledProductBean product,OrdineBean order) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + SelledProductDao.TABLE_NAME
				+ " (nome, categoria, prezzo, codiceOrdine) VALUES (?, ?, ?, ?)";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setString(2, product.getCategoria());
			preparedStatement.setDouble(3, product.getPrezzo());
			preparedStatement.setInt(4, order.getCodice());

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

	@Override
	public boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + SelledProductDao.TABLE_NAME + " WHERE CODE = ?";
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
	public SelledProductBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		SelledProductBean bean = new SelledProductBean();

		String selectSQL = "SELECT * FROM " + SelledProductDao.TABLE_NAME + " WHERE CODE = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getInt("codiceProdotto"));
				bean.setNome(rs.getString("nome"));
				bean.setCategoria(rs.getString("categoria"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setOrdine(rs.getInt("codiceOrdine"));
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
	public Collection<SelledProductBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<SelledProductBean> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + SelledProductDao.TABLE_NAME;

		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				SelledProductBean bean = new SelledProductBean();

				bean.setCodice(rs.getInt("codiceProdotto"));
				bean.setNome(rs.getString("nome"));
				bean.setCategoria(rs.getString("categoria"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setOrdine(rs.getInt("codiceOrdine"));
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
	public void doSave(SelledProductBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
}
