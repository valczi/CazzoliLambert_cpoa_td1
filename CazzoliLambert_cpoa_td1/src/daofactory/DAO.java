package daofactory;

import java.util.ArrayList;


public interface DAO<T> {
	
	Connexion connect = null;
	
	//public abstract T getById(int id);
	public abstract boolean ajout(T object);
	public abstract boolean modifier(T object);
	public abstract boolean supprimer(T object);
	public abstract ArrayList<T> tout();

}
