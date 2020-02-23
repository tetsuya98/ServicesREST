package df.asi.hello3;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Application;

@Path("/bonjour")
public class Hello extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(Hello.class);
		return s;
	}
	
	@GET
	@Produces (MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "bonjour";
	}
	
	@GET
	@Produces (MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html><body><h1> bonjour </h1></body></html>";
	}
	
	@GET
	@Produces (MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?> <hello> bonjour </hello>";
	}
	
	@Path("/qui/{untel}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String bonjourQui(@PathParam("untel") String nom) {
		return "<html><body><h1> bonjour "+nom+"</h1></body></html> ";
	}
	
	@Path("/add/{x}/plus/{y}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String bonjourAdd(@PathParam("x") int x, @PathParam("y") int y) {
		int res = x + y;
		return "<html><body><h1> bonjour "+res+"</h1></body></html> ";
	}
	
	@Path("/add/{x}/plus/{y}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String bonjourAddPlain(@PathParam("x") int x, @PathParam("y") int y) {
		int res = x + y;
		return "bonjour plain "+ res;
	}
	
	@Path("/longueur/{mot}")
	@GET
	@Produces (MediaType.TEXT_XML)
	public String bonjourLongMot(@PathParam("mot") String word) {
		int tmp = word.length();
		return "<?xml version=\"1.0\"?> <hello> bonjour "+tmp+"</hello>";
	}
}

