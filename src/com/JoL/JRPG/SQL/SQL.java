package com.JoL.JRPG.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JoL.JRPG.Inventory.Item.Filter;

public class SQL {

	
	/**
	 * Returns data from an SQL database
	 * 
	 * <p>
	 * loadSQL returns a list of all entries that falls within given filters.
	 * If strings are passed through the filters, then a list of entries that match the values will be passed.
	 * If double[]'s are passed, then ranges are created, with index 0 as the lower bounds and index 1 as the higher, a list of entries is then passed that falls within the ranges.
	 * The String and double[] filters can be combined at will and will work like an AND if done so
	 * </p>
	 * 
	 * @param filters - a filter that describes a set of parameters to filter 
	 * @return a list of entries that matches the given filter
	 */
	public static List<Entry> loadSQL(Filter... filters) {
		//Creates a list of beer entries
		List<Entry> entries = new ArrayList<Entry>();
		
		try {
			//Creates a connection to the SQL table with SQLite
			Connection c = DriverManager.getConnection("jdbc:sqlite::resource:SQL/Shop.db");
			
			ResultSet rs = null;
			
			//String that contains the SQL instruction
			//This is done so we can have more than one filter applied at once
			String sql = "SELECT Item.ItemID, Item.Count, Item.Price FROM Item WHERE ";
			
			if (filters == null) {
				sql = sql.substring(0, sql.length()-7);
				
				
			} else {
				//Goes through all the filters
				for (Filter filter : filters) {
					if (filter.getValue() instanceof String) {
						//If value is empty, then don't add it to the query
						if (((String) filter.getValue()).isEmpty()) continue;
						
						//SELECTS everything FROM the table called Beer, if the column name specified by the filter contains the value
						sql += filter.getFilter().getTag() + " LIKE ? ";
					} else if (filter.getValue() instanceof double[]) {
						//If both values are -1, then don't add it to the query
						if (((double[]) filter.getValue())[0] == -1 || ((double[]) filter.getValue())[1] == -1) continue;
						
						//SELECTS everything FROM a table called Beer, if the column name specified by the filter is BETWEEN value[0] and value[1]	
						sql += filter.getFilter().getTag() + " BETWEEN ? AND ? ";
					}
					
					sql += "AND ";
				}
				
				sql = sql.substring(0, sql.substring(0, sql.length()-1).lastIndexOf(' '));
			}
			
			//Creates the prepared statement
			PreparedStatement ps = c.prepareStatement(sql);
			
			int filterIndex = 1;
			for (int i = 0; i < (filters == null ? 0 : filters.length); i++) {
				Filter filter = filters[i];
				
				if (filter.getValue() instanceof String) {
					if (((String) filter.getValue()).isEmpty()) continue;
					
					//% is used so it matches anything that contains value
					ps.setString(filterIndex++, "%" + (String) filter.getValue() + "%");
				} else if (filter.getValue() instanceof double[]) {
					if (((double[]) filter.getValue())[0] == -1 || ((double[]) filter.getValue())[1] == -1) continue;
					
					ps.setDouble(filterIndex++, ((double[]) filter.getValue())[0]);
					ps.setDouble(filterIndex++, ((double[]) filter.getValue())[1]);
				}
			}

			rs = ps.executeQuery();

			//Goes through all elements and adds them to a list of entries
			while (rs.next()) {
				int itemID = rs.getInt(1);
				int numbersOfItems = rs.getInt(2);
				int price = rs.getInt(3);
				
				entries.add(new Entry(itemID, numbersOfItems, price));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return entries;
	}
}
