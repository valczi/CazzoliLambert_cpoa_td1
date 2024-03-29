package metiers;

public class PeriodiciteM {

	private int id_periodicite;
	private String libelle;
	
	public PeriodiciteM(int id_periodicite, String libelle) {
		super();
		this.id_periodicite = id_periodicite;
		this.libelle = libelle;
	}
	
	public PeriodiciteM(String libelle) {
		super();
		this.id_periodicite =-1;
		this.libelle = libelle;
	}
	 
	public PeriodiciteM() {
	}
	
	
	public int getId() {
		return id_periodicite;
	}
	public void setId(int id_periodicite) {
		this.id_periodicite = id_periodicite;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	@Override
	public String toString() {
		return "id_periodicite=" + id_periodicite + "\n libelle=" + libelle;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeriodiciteM other = (PeriodiciteM) obj;
		if (id_periodicite != other.id_periodicite)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}
	
}
