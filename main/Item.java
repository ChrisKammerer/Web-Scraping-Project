package main;


public class Item implements Comparable<Item>{
	private String title;
	private String price;
	private String url;
	
	public Item(String name, String cost, String link) {
		title = name;
		price = cost;
		url = link;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String str) {
		title = str;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String str) {
		price = str;
	}
	
	public String getURL() {
		return url;
	}
	
	public void setURL(String str) {
		url = str;
	}
	
	public String toString() {
		return title + "\n" + price + "\n" + url;
	}
	
	public double getPriceAsDouble() {
		String str = price.substring(1);
		str = str.replaceAll(",", "");
		return Double.valueOf(str);
	}
	
	public int compareTo(Item other) {
		return (int) (this.getPriceAsDouble()*100-other.getPriceAsDouble()*100);
	}
}
