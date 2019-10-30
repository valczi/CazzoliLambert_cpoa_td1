package daoobjects;

import daofactory.*;
import metiers.*;

public interface RevueDAO extends DAO<RevueM> {
	public abstract RevueM getById(int id);
	public abstract boolean perioExist(int idp);
}

