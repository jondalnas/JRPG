package com.JoL.JRPG.Inventory.Item;

public class Item {
	private Items item;
	private int count;
	
	public enum Items {
		healthPotion();
		
		public int id;
	}
	
	public Item(int itemID, int count) {
		this.item = Items.values()[itemID];
		this.count = count;
	}
	
	public Items getItemEntry() {
		return item;
	}
	
	public int getCount() {
		return count;
	}
	
	//Update all ID's of all items in list, so they can be used with the SQL database
	public static void initItems() {
		for (int i = 0; i < Items.values().length; i++) {
			Items item = Items.values()[i];
			item.id = i;
		}
	}
}