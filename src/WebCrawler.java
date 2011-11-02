import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;


public class WebCrawler {
	
	static Vector<URL> urlsToSearch; 
	static Vector<URL> urlsSearching;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WebCrawler w = new WebCrawler(); 
		w.init();
		w.crawl(); 

	}
	
	public void init() { 
		//Load initial set of URLS
		urlsToSearch = new Vector<URL>();
		urlsSearching = new Vector<URL>(); 
		URL init_url = null; 
		try { 
			init_url = new URL("http://google.com"); 
		} catch (Exception e)
		{
			System.out.println(e.getMessage()); 
		}
		urlsToSearch.add(init_url); 		
	}

	public void addURL(URL my_url) { 
		synchronized(urlsToSearch) { 
			urlsToSearch.add(my_url); 
		}
	}
	
	public URL popURL() { 
		URL returned_url = null; 
		synchronized(urlsToSearch) { 
			returned_url = urlsToSearch.remove(0); 
		}
		return returned_url; 
	}
	
	public void crawl() { 
		//urlsToSearch has been loaded
		//for each of the urlsToSearch, span a new thread
		while(true) { 
			//new thread created
			URL my_url = popURL(); 
			if(my_url == null) { 
				break; 
			}
			WebCrawlerThread wct = new WebCrawlerThread(my_url); 
			wct.findNextLinks(); 
		}
		/**
		int i = 0; 
		for(URL my_url : urlsToSearch) { 
			try {
				Vector<String> newLinks = (Vector<String>) SaveURL.extractLinks(my_url);
				for(String new_url : newLinks) { 
					if(new_url.startsWith("http")) { 
						urlsSearching.add(new URL(new_url)); 
						i += 1;
					} else { 
						new_url = my_url.toString() + new_url; 
						urlsSearching.add(new URL(new_url)); 
						i += 1; 
					}
				}
				if(i >= 100) { 
					System.out.println("reached 100"); 
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		System.out.println(urlsSearching); 
		for(URL my_url : urlsSearching) { 
			System.out.println("------------------"); 
			System.out.println(my_url); 
			System.out.println("------------------"); 
			try {
				Vector<String> newLinks = (Vector<String>) SaveURL.extractLinks(my_url);
				for(String new_url : newLinks) { 
					if(new_url.startsWith("http")) { 
						System.out.println(new_url); 
						urlsToSearch.add(new URL(new_url)); 
						i += 1;
					} else { 
						new_url = my_url.toString() + new_url; 
						System.out.println(new_url); 
						urlsToSearch.add(new URL(new_url)); 
						i += 1; 
					}
				}
				if(i >= 100) { 
					System.out.println("reached 100"); 
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}**/
	}

}
