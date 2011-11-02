import java.net.URL;
import java.util.Vector;


public class WebCrawlerThread extends Thread {

	URL my_url;
	Vector<URL> myNextLinks; 

	public WebCrawlerThread(URL my_url) {
		super();
		this.my_url = my_url;
	} 
	
	public WebCrawlerThread() {
		super();
	} 
	
	public void findNextLinks() { 
		//request for url 
		//get back HTML
		//parse HTML for next links
		//add links to myNextLinks 
	}

	public void process() {
		openInBrowser(); 
		findNextLinks(); 
		WebCrawler.updateUrlsToSearch(myNextLinks);  
	}
	
	public void openInBrowser() {
		//open url in browser with special headers
	}
	
}
