package testJersey.client_nirvana0;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class App {
  public static void main(String[] args) {
    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target("http://localhost:8080/nirvana0");
    WebTarget prophetieTarget = webTarget.path("v1").path("prophetie");
    WebTarget countTarget =  prophetieTarget.path("count");
    Invocation.Builder invocationBuilder = countTarget.request(MediaType.TEXT_HTML_TYPE );
    Response response = invocationBuilder.get();
    System.out.println(response.getStatus());
    System.out.println(response.readEntity(String.class));
    System.out.println();

    Form monForm = new Form();
    monForm.param("texte", "Renlar");
    WebTarget newTarget =  prophetieTarget.path("new");
    Invocation.Builder invocationBuilder2 = newTarget.request();
    response = invocationBuilder2.post(Entity.form(monForm));
    System.out.println(response.getStatus());
    System.out.println(response.readEntity(String.class));
    System.out.println();

    WebTarget target2 =  prophetieTarget.path("2");
    Invocation.Builder invocationBuilder3 = target2.request();
    response = invocationBuilder3.delete();
    System.out.println(response.getStatus());
    System.out.println(response.readEntity(String.class));
    System.out.println();

    WebTarget listTarget =  prophetieTarget.path("list");
    Invocation.Builder invocationBuilder4 = listTarget.request();
    response = invocationBuilder4.get();
    System.out.println(response.getStatus());
    System.out.println(response.readEntity(String.class));
    System.out.println();
  }
}
