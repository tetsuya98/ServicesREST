package df.asi.nirvana;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Prophete {
	
	
	public Prophete() {
		super();
		this.nom = null;
		this.id = null;
	}	
	public Prophete(String id, String nom) {
		super();
		this.nom = nom;
		this.id = id;
	}
	private String nom;
	private String id;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String toString() {
		return "Prophete : "+this.nom+" (id:"+this.id+")";
	}
}
