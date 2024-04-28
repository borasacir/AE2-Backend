package com.appliedenergetics2.model;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "modpackitem")
public class Modpacks {
	
	public static class Item{
		
		@Id
		private String _id;
		private String title;
		private String description;
		private String imageURL;
		private String recipeURL;
		public String getId() {
			return _id;
		}
		public String getTitle() {
			return title;
		}
		public String getDescription() {
			return description;
		}
		public String getImageURL() {
			return imageURL;
		}
		public String getRecipeURL() {
			return recipeURL;
		}
	}
	
	@Id
	private String id;
	private String itemTypeName;
	private List<Item> items;
	public String get_id() {
		return id;
	}
	public String getItemTypeName() {
		return itemTypeName;
	}
	public List<Item> getItems() {
		return items;
	}
	
	
}
