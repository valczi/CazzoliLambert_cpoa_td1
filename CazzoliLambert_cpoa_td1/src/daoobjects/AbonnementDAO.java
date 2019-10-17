package daoobjects;

import daofactory.*;
import metiers.*;

public interface AbonnementDAO extends DAO<AbonnementM> {
	public abstract AbonnementM getById(int id,int id2);
}
