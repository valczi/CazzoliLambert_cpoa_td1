package metiers;

public class RevueM {

	private int id_revue;
	private int id_periodicite;
	private String titre;
	private String description;
	private String visuel;
	private double tarif_numero;

	public int getId_revue() {
		return id_revue;
	} 

	public RevueM(int id_revue, String titre, String description, double tarif_numero, String visuel,
			int id_periodicite) {
		super();
		this.id_revue = id_revue;
		this.titre = titre;
		this.description = description;
		this.tarif_numero = tarif_numero;
		this.visuel = visuel;
		this.id_periodicite = id_periodicite;

	}
	
	public RevueM( String titre, String description, double tarif_numero,
			int id_periodicite) {
		super();
		this.id_revue = -1;
		this.titre = titre;
		this.description = description;
		this.tarif_numero = tarif_numero;
		this.visuel = titre+".jpg";
		this.id_periodicite = id_periodicite;

	}
	
	public RevueM(String titre, String description, double tarif_numero, String visuel,
			int id_periodicite) {
		super();
		this.id_revue = -1;
		this.titre = titre;
		this.description = description;
		this.tarif_numero = tarif_numero;
		this.visuel = visuel;
		this.id_periodicite = id_periodicite;
	}

	public RevueM() {
		// TODO Auto-generated constructor stub
	}

	public void setId_revue(int id_revue) {
		this.id_revue = id_revue;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
		this.visuel=titre+".jpg";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTarif_numero() {
		return tarif_numero;
	}

	public void setTarif_numero(double tarif_numero) {
		this.tarif_numero = tarif_numero;
	}

	public String getVisuel() {
		return visuel;
	}

	public void setVisuel(String visuel) {
		this.visuel = visuel;
	}

	public int getId_periodicite() {
		return id_periodicite;
	}

	public void setId_periodicite(int id_periodicite) {
		this.id_periodicite = id_periodicite;
	}

	@Override
	public String toString() {
		return "RevueM [id_revue=" + id_revue + ", titre=" + titre + ", description=" + description + ", tarif_numero="
				+ tarif_numero + ", visuel=" + visuel + ", id_periodicite=" + id_periodicite + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id_periodicite;
		result = prime * result + id_revue;
		long temp;
		temp = Double.doubleToLongBits(tarif_numero);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		result = prime * result + ((visuel == null) ? 0 : visuel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RevueM other = (RevueM) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id_periodicite != other.id_periodicite)
			return false;
		if (id_revue != other.id_revue)
			return false;
		if (Double.doubleToLongBits(tarif_numero) != Double.doubleToLongBits(other.tarif_numero))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		if (visuel == null) {
			if (other.visuel != null)
				return false;
		} else if (!visuel.equals(other.visuel))
			return false;
		return true;
	}

	public String affichage() {
		// TODO Auto-generated method stub
		return titre +"("+tarif_numero +"€)";
	}

}
