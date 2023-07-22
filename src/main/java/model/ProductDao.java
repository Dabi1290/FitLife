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

public class ProductDao implements BaseDao<ProductBean> {

	private static DataSource ds;
	private static final Logger logger = Logger.getLogger(ProductDao.class.getName());
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			logger.severe("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "prodotti";
	@Override
	public synchronized void doSave(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + ProductDao.TABLE_NAME
				+ " (nome, categoria, prezzo, Immagine,descrizione,quantità) VALUES (?, ?, ?, ?,?,?)";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setInt(2, product.getCategoria());
			preparedStatement.setDouble(3, product.getPrezzo());
			preparedStatement.setBlob(4, product.getImmagine());
			preparedStatement.setString(5, product.getDescrizione());
			preparedStatement.setInt(6, product.getQuantita());
			

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
	public synchronized void doUpdate(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		

		String insertSQL = "UPDATE " + ProductDao.TABLE_NAME;
		if(product.getImmagine()==null || product.getImmagine().length()==0) {
			
			insertSQL= insertSQL+" SET nome=?, categoria=?, prezzo=?,descrizione=?, quantità=? WHERE codiceProdotto= ?";
		}
		else {
			insertSQL= insertSQL	+ " SET nome=?, categoria=?, prezzo=?, descrizione=?, quantità=?,Immagine=? WHERE codiceProdotto= ?";
		}
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setInt(2, product.getCategoria());
			preparedStatement.setDouble(3, product.getPrezzo());
			preparedStatement.setString(4, product.getDescrizione());
			preparedStatement.setInt(5, product.getQuantita());
			
			if (product.getImmagine()==null || product.getImmagine().length()==0) {
			
			preparedStatement.setInt(6,product.getCodice());
			}
			else {
				preparedStatement.setBlob(6, product.getImmagine());
				preparedStatement.setInt(7,product.getCodice());
			}
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
	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductDao.TABLE_NAME + " WHERE codiceProdotto = ?";
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
	public synchronized ProductBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductDao.TABLE_NAME + " WHERE codiceProdotto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getInt("codiceProdotto"));
				bean.setNome(rs.getString("nome"));
				bean.setCategoria(rs.getInt("categoria"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setImmagine(rs.getBlob("Immagine"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setQuantita(rs.getInt("quantità"));
				
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
	

	


	public synchronized Collection<ProductBean> doRetrieveByQuery(String query) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<>();
		query=query+"%";

		String selectSQL = "SELECT * FROM " + ProductDao.TABLE_NAME + " WHERE nome LIKE ?";
		
		
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, query);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setCodice(rs.getInt("codiceProdotto"));
				bean.setNome(rs.getString("nome"));
				bean.setCategoria(rs.getInt("categoria"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setImmagine(rs.getBlob("Immagine"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setQuantita(rs.getInt("quantità"));
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
	public synchronized Collection<ProductBean> doRetrieveAll() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + ProductDao.TABLE_NAME;

		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setCodice(rs.getInt("codiceProdotto"));
				bean.setNome(rs.getString("nome"));
				bean.setCategoria(rs.getInt("categoria"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setImmagine(rs.getBlob("Immagine"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setQuantita(rs.getInt("quantità"));
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
	
	
	public synchronized Collection<ProductBean> Newest() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + ProductDao.TABLE_NAME +" ORDER BY codiceProdotto DESC LIMIT 8";

		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setCodice(rs.getInt("codiceProdotto"));
				bean.setNome(rs.getString("nome"));
				bean.setCategoria(rs.getInt("categoria"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setImmagine(rs.getBlob("Immagine"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setQuantita(rs.getInt("quantità"));
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
