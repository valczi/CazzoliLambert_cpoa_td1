package daoobjects;

import daofactory.*;
import metiers.*;

public interface ClientDAO extends DAO<ClientM> {
	public abstract ClientM getById(int id);
}
