package daoobjects;

import daofactory.*;
import metiers.*;

public interface AbonnementDAO extends DAO<AbonnementM> {
	public abstract AbonnementM getById(int id,int id2);
	public abstract boolean revExist(int id);
	public abstract boolean cliExist(int id);
}
