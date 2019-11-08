package com.JoL.JRPG.SQL;

import com.JoL.JRPG.Inventory.Item.Item;

public class Entry {
	private Item item;
	private int price;
	
	public Entry(Item item, int price) {
		this.item = item;
		this.price = price;
	}
	
	public Entry(int itemID, int count, int price) {
		this(new Item(itemID, count), price);
	}
	
	public Item getItem() {
		return item;
	}
	
	public int getPrice() {
		return price;
	}
}