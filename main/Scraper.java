package main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Scraper {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {

		// initialize a headless browser
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		Scanner kboard = new Scanner(System.in);

		// configuring options
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);

		//Get Search Tag
		System.out.print("Input Search Tag: ");
		String url = kboard.nextLine();
		
		//Make Output File
		System.out.print("Input Name of Output File: ");
		String fileName = kboard.nextLine();
		PrintWriter outputFile = new PrintWriter(new FileWriter(fileName));
		
		outputFile.println("Search Results for " + url + ":");
		outputFile.println();
		
		url = url.replace(" ", "+"); //formatting input
		
		// fetching the web page and search results
		HtmlPage page = webClient.getPage("https://www.ebay.com/sch/i.html?_from=R40&_trksid=p2380057.m570.l1313&_nkw="
				+ url + "&_sacat=0");
		DomNodeList<DomNode> htmlItems = page.querySelectorAll("li.s-item--watch-at-corner"); //making list of nodes (item listings)
		ArrayList<Item> items = new ArrayList<Item>(); //ArrayList of items
		
		for(DomNode result: htmlItems) {
			//grabs various pieces of information by making child nodes from the parent node result
			DomNode title = result.getFirstByXPath(".//a[@class='s-item__link']"); //getting title of item
			DomNode price = result.getFirstByXPath(".//span[@class='s-item__price']"); //getting listing price
			HtmlAnchor link = result.getFirstByXPath(".//a[@class='s-item__link']"); //getting link to item
			
			items.add(new Item(title.asNormalizedText(), price.asNormalizedText(), link.getHrefAttribute())); //adding item to list
		}

		items.sort(null); //sorts items based on price from low to high
		
		for(Item item: items) { //Printing all Items in ArrayList
			outputFile.println(item + "\n");
		}
		//closing keyboard, file, and webClient
		kboard.close();
		outputFile.close();
		webClient.close();
		System.out.println("Done!");
	}

}
