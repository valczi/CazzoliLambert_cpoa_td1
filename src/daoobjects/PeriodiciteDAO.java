package daoobjects;

import daofactory.*;
import metiers.*;

public interface PeriodiciteDAO extends DAO<PeriodiciteM> {
	public abstract PeriodiciteM getById(int id);
}
