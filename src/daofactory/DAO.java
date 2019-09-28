package daofactory;

import java.util.ArrayList;


public interface DAO<T> {
	
	Connexion connect = null;
	
	public abstract T getById(int id);
	public abstract void ajout(T object);
	public abstract void modifier(T object);
	public abstract void supprimer(T object);
	public abstract ArrayList<T> tout();

}
