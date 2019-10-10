package metiers;

import java.time.LocalDate;

public class AbonnementM {

	private int id_client;
	private int id_revue;
	private LocalDate date_debut;
	private LocalDate date_fin; 
	 
	public AbonnementM(int id_client, int id_revue, LocalDate date_debut, LocalDate date_fin) {
		super();
		this.id_client = id_client;
		this.id_revue = id_revue;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
	}
	
	public AbonnementM(int id_client, int id_revue) {
		super();
		this.id_client = id_client;
		this.id_revue = id_revue;
		this.date_debut = LocalDate.of(1999,9,29);
		this.date_fin = LocalDate.now();
	}

	public AbonnementM() {
		// TODO Auto-generated constructor stub
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public int getId_revue() {
		return id_revue;
	}

	public void setId_revue(int id_revue) {
		this.id_revue = id_revue;
	}

	public LocalDate getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(LocalDate date_debut) {
		this.date_debut = date_debut;
	}

	public LocalDate getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(LocalDate date_fin) {
		this.date_fin = date_fin;
	}

	@Override
	public String toString() {
		return "AbonnementM [id_client=" + id_client + ", id_revue=" + id_revue + ", date_debut=" + date_debut
				+ ", date_fin=" + date_fin + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbonnementM other = (AbonnementM) obj;
		if (date_debut == null) {
			if (other.date_debut != null)
				return false;
		} else if (!date_debut.equals(other.date_debut))
			return false;
		if (date_fin == null) {
			if (other.date_fin != null)
				return false;
		} else if (!date_fin.equals(other.date_fin))
			return false;
		if (id_client != other.id_client)
			return false;
		if (id_revue != other.id_revue)
			return false;
		return true;
	}
	
}
