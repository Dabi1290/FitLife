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

public class CarrelloDao  {
	
	private static final Logger logger = Logger.getLogger(CarrelloDao.class.getName());
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

	private static final String TABLE_NAME = "Carrello";

	



public Collection<ProductBean> doRetrieveProducts(int codiceCliente) throws SQLException{
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	Collection<ProductBean> products = new LinkedList<>();
	
	
	String selectSQL = "SELECT c.Quantità, p.*  " + "FROM "+CarrelloDao.TABLE_NAME+" c " + "JOIN prodotti p ON c.codiceProd = p.codiceProdotto " +"WHERE c.codiceCliente = ?";
	

	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);
		preparedStatement.setInt(1, codiceCliente);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			ProductBean bean = new ProductBean();

			bean.setCodice(rs.getInt("codiceProdotto"));
			bean.setNome(rs.getString("nome"));
			bean.setCategoria(rs.getInt("categoria"));
			bean.setPrezzo(rs.getDouble("prezzo"));
			bean.setImmagine(rs.getBlob("Immagine"));
			bean.setDescrizione(rs.getString("descrizione"));
			bean.setQuantita(rs.getInt("Quantità"));
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


public synchronized boolean doDelete(int code,int user) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	int result = 0;
	String deleteSQL = "DELETE FROM " + CarrelloDao.TABLE_NAME + " WHERE codiceProd = ? AND codiceCliente=?";
	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(deleteSQL);
		preparedStatement.setInt(1, code);
		preparedStatement.setInt(2, user);

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



public synchronized Boolean doSave(CarrelloBean bean) throws SQLException {

	Connection connection = null;
	PreparedStatement preparedStatement = null;

	String insertSQL = "INSERT INTO " + CarrelloDao.TABLE_NAME+ " (codiceCliente, codiceProd, Quantità) VALUES (?, ?, ?)";
	int result = 0;
	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(insertSQL);
		preparedStatement.setInt(1, bean.getCodiceCliente());
		preparedStatement.setInt(2, bean.getCodiceProdotto());
		preparedStatement.setInt(3, 1/*bean.getQuantita()*/);

		result=preparedStatement.executeUpdate();

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
	
	return (result != 0);
	
}



public synchronized Boolean doUpdate(CarrelloBean bean) throws SQLException {

	Connection connection = null;
	PreparedStatement preparedStatement = null;

	String insertSQL = "UPDATE  " + CarrelloDao.TABLE_NAME+ " SET Quantità = Quantità + 1 WHERE codiceCliente=? AND codiceProd=?";
	int result = 0;
	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(insertSQL);
		preparedStatement.setInt(1, bean.getCodiceCliente());
		preparedStatement.setInt(2, bean.getCodiceProdotto());

		result=preparedStatement.executeUpdate();

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
	
	return (result != 0);
	
}

}


