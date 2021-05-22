package main;

import java.io.IOException;
import java.net.MalformedURLException;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Test {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {

		// initialize a headless browser
		WebClient webClient = new WebClient(BrowserVersion.CHROME);

		// configuring options
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);

		// fetching the web page
		HtmlPage page = webClient.getPage("https://time.is/");
		System.out.println("connected to time.is");
		HtmlPage page2 = webClient.getPage("https://www.fool.com/quote/nasdaq/nvidia/nvda/");
		System.out.println("connected to fool.com");
		
		// fetching Stock Price
		DomNode node = page.querySelector("div#clock0_bg");
		DomNode node2 = page2.querySelector("span.current-price");
		System.out.println(node.asNormalizedText());
		System.out.println(node2.asNormalizedText());
	}
}