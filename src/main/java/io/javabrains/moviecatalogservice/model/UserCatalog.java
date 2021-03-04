package io.javabrains.moviecatalogservice.model;

import java.util.List;

public class UserCatalog {
	
	private List<CatalogItem> catalogItem;

	public List<CatalogItem> getCatalogItem() {
		return catalogItem;
	}

	public void setCatalogItem(List<CatalogItem> catalogItem) {
		this.catalogItem = catalogItem;
	}
}
