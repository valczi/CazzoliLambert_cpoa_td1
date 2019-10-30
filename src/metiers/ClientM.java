package metiers;

public class ClientM {
	
	private int id_client;
	private String nom;
	private String prenom;
	private String no_rue;
	private String voie;
	private String code_postal;
	private String ville;
	private String pays;

	public ClientM(int id_client, String nom, String prenom, String no_rue, String voie, String code_postal,
			String ville, String pays) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.no_rue = no_rue;
		this.voie = voie;
		this.code_postal = code_postal;
		this.ville = ville;
		this.pays = pays;
		this.id_client=id_client;
	}
	
	public ClientM(String nom, String prenom, String no_rue, String voie, String code_postal,
			String ville, String pays) {
		super();
		this.id_client=-1;
		this.nom = nom;
		this.prenom = prenom;
		this.no_rue = no_rue;
		this.voie = voie;
		this.code_postal = code_postal;
		this.ville = ville;
		this.pays = pays;
	}
	
	public ClientM(String nom, String prenom,int id_client) {
		super();
		this.id_client = id_client;
		this.nom = nom;
		this.prenom = prenom;
		this.no_rue = null;
		this.voie = null;
		this.code_postal = null;
		this.ville = null;
		this.pays = null;
	}
	public ClientM(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.no_rue = null;
		this.voie = null;
		this.code_postal = null;
		this.ville = null;
		this.pays = null;
		this.id_client=-1;
	}
	
	public ClientM() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id_client;
	}
	public void setId(int id_client) {
		this.id_client = id_client;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	} 
	public String getNo_rue() {
		return no_rue;
	}
	public void setNo_rue(String no_rue) {
		this.no_rue = no_rue;
	}
	public String getVoie() {
		return voie;
	}
	public void setVoie(String voie) {
		this.voie = voie;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	
	@Override
	public String toString() {
		return "id_client=" + id_client + "\n nom=" + nom + "\n prenom=" + prenom + "\n no_rue=" + no_rue
				+ "\n voie=" + voie + "\n code_postal=" + code_postal + "\n ville=" + ville + "\n pays=" + pays;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code_postal == null) ? 0 : code_postal.hashCode());
		result = prime * result + id_client;
		result = prime * result + ((no_rue == null) ? 0 : no_rue.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((pays == null) ? 0 : pays.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		result = prime * result + ((voie == null) ? 0 : voie.hashCode());
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
		ClientM other = (ClientM) obj;
		if (code_postal == null) {
			if (other.code_postal != null)
				return false;
		} else if (!code_postal.equals(other.code_postal))
			return false;
		if (id_client != other.id_client)
			return false;
		if (no_rue == null) {
			if (other.no_rue != null)
				return false;
		} else if (!no_rue.equals(other.no_rue))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (pays == null) {
			if (other.pays != null)
				return false;
		} else if (!pays.equals(other.pays))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		if (voie == null) {
			if (other.voie != null)
				return false;
		} else if (!voie.equals(other.voie))
			return false;
		return true;
	}
	
}
