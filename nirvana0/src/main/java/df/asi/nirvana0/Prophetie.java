package df.asi.nirvana0;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import df.asi.nirvana0.Prophetie;

@Path("/prophetie")
public class Prophetie extends Application{
	
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(Prophetie.class);
		return s;
	}
	
	static List<String> donnees = new LinkedList<String>(Arrays.asList(
		"vive le surhumain", "Nietzsche est mort aussi", "Dieu est mort",
		"Si la matière grise était plus rose, le monde aurait moins les idées noires.",
		"Il faut une infinie patience pour attendre toujours ce qui n'arrive jamais.", 
		"tu es trop beau", "ta bite est incommensurable"
	    ));
		
	@Path("/list")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String list() {
	  StringBuffer result = new StringBuffer("<html><body><ul>");
	  for (int i=0; i < donnees.size() ; ++i )
	    result.append("<li>prophétie numéro " + i + " : " + donnees.get(i) +"</li> ");
	  return result.toString() + "</ul></body></html> ";
	}
	
	@Path("/{numero}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String showIeme(@PathParam("numero") int i) {
	  return "<html><body>prophétie numéro "+i+" : " + donnees.get(i) +"</body></html> ";
	}
	
	@Path("/find1")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String findFirst(@QueryParam("mot") String mot) {
	  for (int i=0; i < donnees.size() ; ++i )
	    if ((donnees.get(i)).contains(mot))
		return "<html><body>prophétie numéro " + i + " : " + donnees.get(i)
                    +"</body></html> ";
	  return "<html><body>pas de prophétie contenant le mot " + mot + "</body></html> ";
	} 	
	
	@Path("/new")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String nouvelle(@FormParam("texte") String texte) {
	  donnees.add(texte);
         return "<html><body>prophétie numéro " + (donnees.size() -1) + " : "
               + texte +"</body></html> ";
	}
		
	@Path("/{numero}")
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	public String delete(@PathParam("numero") int i) {
	  String texte = donnees.remove(i);
	  return "<html><body>prophétie de texte  " + texte +" supprimée</body></html> ";
	}
	
	@Path("/{numero}")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String change(@PathParam("numero") int i, @FormParam("texte") String texte) {
	  donnees.set(i, texte);
	  return "<html><body>prophétie numéro "+i+" : "+donnees.get(i)+"</body></html> ";
	}
	
	@Path("/count")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String count() {
	  int res = donnees.size();
	  return "<html><body>prophétie numéro "+res+"</body></html> ";
	}
	
	@Path("/{i}/occ")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String occ(@QueryParam("mot") String mot, @PathParam("i") int i) {
		int occ = 0;
	    String texte = donnees.get(i);
	    for (int pos=0; pos <texte.length(); pos++)
	      if (texte.indexOf(mot, pos) == pos)
		  occ++;
		  return ""+occ ;

	}
	
}
