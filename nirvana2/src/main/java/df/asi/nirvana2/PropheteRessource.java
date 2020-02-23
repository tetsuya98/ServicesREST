package df.asi.nirvana2;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import df.asi.nirvana.Prophete;

@Path("/prophete/unique")
public class PropheteRessource extends Application{
	
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(PropheteRessource.class);
		return s;
	}
  @Context
  UriInfo uriInfo;

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getProphete1() {
    Prophete prophete = new Prophete("0", "Zarathoustra");
    return Response.status(200).entity("<html><body>prophete : "
		         + prophete.toString() + "</body></html> ").build();
  }

  @GET
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public Prophete getProphete2() {
    Prophete prophete = new Prophete("0", "Zarathoustra");
    return prophete;
  }

  @Path("/complete")
  @GET
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public Response getProphete3() {
    Prophete prophete = new Prophete("0", "Zarathoustra");
    return Response.status(200).link(uriInfo.getAbsolutePath(),"self")
                  .entity(prophete).build();
  }
  
  @POST
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  @Produces(MediaType.TEXT_HTML)
  public Response postProphete(JAXBElement<Prophete> propheteParam) {
	    Prophete prophete = propheteParam.getValue();
	    return Response.status(200).entity("<html><body>prophete : "
		         + prophete.toString() + "</body></html> ").build();
  }
}
