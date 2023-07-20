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

public class GuestDao implements BaseDao<GuestBean> {

	private static DataSource ds;
	private static final Logger logger = Logger.getLogger(GuestDao.class.getName());
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			logger.severe("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "clienteGuest";
	@Override
	public synchronized void doSave(GuestBean user) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + GuestDao.TABLE_NAME
				+ " (nome, cognome, telefono, indirizzo) VALUES (?, ?, ?, ?)";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, user.getNome());
			preparedStatement.setString(2, user.getCognome());
			preparedStatement.setString(3, user.getTelefono());
			preparedStatement.setString(4, user.getIndirizzo());

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
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + GuestDao.TABLE_NAME + " WHERE CODE = ?";
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
	public synchronized GuestBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		GuestBean bean = new GuestBean();

		String selectSQL = "SELECT * FROM " + GuestDao.TABLE_NAME + " WHERE CODE = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getInt("codiceGuest"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setIndirizzo(rs.getString("indirizzo"));
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
	public Collection<GuestBean> doRetrieveAll() throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<GuestBean> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + GuestDao.TABLE_NAME;

		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				GuestBean bean = new GuestBean();

				bean.setCodice(rs.getInt("codiceGuest"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setIndirizzo(rs.getString("indirizzo"));
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

