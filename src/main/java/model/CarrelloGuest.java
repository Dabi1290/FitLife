package model;

import java.util.ArrayList;
import java.util.List;

public class CarrelloGuest {

	List<ProductBean> prodotti;

	public List<ProductBean> getProdotti() {
		return prodotti;
	}



	public void setProdotti(List<ProductBean> prodotti) {
		this.prodotti = prodotti;
	}



	public CarrelloGuest() {
		super();
		this.prodotti= new ArrayList<>();
	}
	
}
