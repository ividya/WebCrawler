import java.net.URL;


public class WebCrawlerThread extends Thread {

	URL my_url;

	public WebCrawlerThread(URL my_url) {
		super();
		this.my_url = my_url;
	} 
	
	public void findNextLinks() { 
		//request for url 
		//get back HTML
		//parse HTML for next links
		//add links to WebCrawler 
	}
	
}
