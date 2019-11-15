package com.JoL.JRPG.Inventory.Item;

public class Filter {
	private Filters filter;
	private Object value;
	
	public enum Filters {
		ID("ID"), COUNT("Count"), PRICE("Price");
		String tag;
		
		Filters(String tag) {
			this.tag = tag;
		}
		
		public String getTag() {
			return tag;
		}
	}
	
	public Filter(Filters filter, double[] value) {
		this.filter = filter;
		this.value = value;
	}
	
	public Filter(Filters filter, String value) {
		this.filter = filter;
		this.value = value;
	}

	public Filters getFilter() {
		return filter;
	}

	public Object getValue() {
		return value;
	}
}