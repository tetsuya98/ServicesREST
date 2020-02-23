package df.asi.nirvana1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import df.asi.nirvana1.Prophetie;


@Path("/prophetie")
public class Prophetie extends Application{
	
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(Prophetie.class);
		return s;
	}
	
  static final String NEXISTEPAS = "${#@[?%";
    static List<String> donnees = new LinkedList<String>(Arrays.asList(
		"vive le surhumain", "Nietzsche est mort aussi", "Dieu est mort",
		"Si la matière grise était plus rose, le monde aurait moins les idées noires.",
		"Il faut une infinie patience pour attendre toujours ce qui n'arrive jamais."
	    ));
  @Context
  UriInfo uriInfo;
	
  @Path("/uriInfo")
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String contextUriInfo() {
    return "<html><body>Context uriInfo getAbsolutePath(): "
       + uriInfo.getAbsolutePath().toString() + "<br />Context uriInfo getBaseUri(): "
       + uriInfo.getBaseUri().toString() +"</body></html> ";
	}
	
	
  @Path("/list")
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String list() {
    String baseUri = uriInfo.getBaseUri().toString()+"prophetie/";
    StringBuffer result = new StringBuffer("<html><body><ul>");
    for (int i=0; i < donnees.size() ; ++i )
      result.append("<li><a href=\"" + baseUri + i +"\">prophétie numéro " + i +"</a></li> ");
    return result.toString() + "</ul></body></html> ";
  }

  @Path("/{numero}")
  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response showIeme(@PathParam("numero") int i) {
    try {
      return Response.status(200).entity("<html><body>prophétie numéro "
		              + i + " : " + donnees.get(i) +"</body></html> ").build();
    } catch (IndexOutOfBoundsException ioobe) { 	
      return Response.status(400).entity("<html><body>impossible trouver prophetie<br />"
		   		+ "erreur : "  + ioobe.getMessage() + "</body></html>").build();
    }
  }

  @Path("/search")
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String search(@DefaultValue(NEXISTEPAS) @QueryParam("mot") String mot) {
    String baseUri = uriInfo.getBaseUri().toString()+"prophetie/";
    StringBuffer result = new StringBuffer("<html><body><ul>");
    for (int i=0; i < donnees.size() ; ++i )
      if ((donnees.get(i)).contains(mot))
	 result.append("<li><a href=\""+baseUri + i +"\">prophétie numéro "+ i +"</a></li> ");
    return result.toString() + "</ul></body></html> ";
  }
	
  @Path("/{numero}")
  @DELETE
  @Produces(MediaType.TEXT_HTML)
  public Response delete(@PathParam("numero") int i) {
    try {
      String texte = donnees.remove(i);
      return Response.status(200).entity("<html><body>prophétie de texte  " + texte
                                         +" supprimée</body></html> ").build();
    } catch (IndexOutOfBoundsException ioobe) { 	
      return Response.status(400).entity("<html><body>impossible trouver prophetie<br />"
				 + "erreur : " + ioobe.getMessage() + "</body></html>").build();
    }
  }

  @Path("/searchfordelete")
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String searchForDelete(@DefaultValue(NEXISTEPAS) @QueryParam("mot") String mot) {
    String baseUri = uriInfo.getBaseUri().toString()+"prophetie/";
    StringBuffer result = new StringBuffer("<html><body><ul>");
    for (int i=0; i < donnees.size() ; ++i )
      if ((donnees.get(i)).contains(mot))
	 result.append("<li><a href=\"" + baseUri + i
                 +"\" method=\"delete\" >supprimer prophétie numéro " + i +"de texte : "
                 + donnees.get(i) + "</a></li> ");
    return result.toString() + "</ul></body></html> ";
  }
}
