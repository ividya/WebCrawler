import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;


public class WebCrawler {
	
	static Vector<URL> urlsToSearch; 
	static Vector<URL> urlsSearching;
	static Vector<WebCrawlerThread> open_threads;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WebCrawler w = new WebCrawler(); 
		w.init(); 
	}
	
	public static void updateUrlsToSearch(Vector<URL> newLinks) { 
		lock(urlsToSearch); 
		for (URL next : newLinks) { 
			urlsToSearch.add(next); 
		}
		unlock(urlsToSearch); 
	}
	
	public static void init() { 
		while(true) { 
			URL x = urlsSearching.remove(0); 
			if(x == null) { 
				lock(urlsSearching); 
				lock(urlsToSearch);
				for(URL next : urlsToSearch) { 
					urlsSearching.add(next);
				}
				urlsToSearch.removeAllElements(); 
				unlock(urlsSearching);
				unlock(urlsToSearch);
				continue; 
				}
			while(true) {
				WebCrawlerThread new_thread = open_threads.remove(0); 
				new_thread.my_url = x; 
				new_thread.process(); 
				open_threads.add(new_thread); 
			}
		}
	}

}
