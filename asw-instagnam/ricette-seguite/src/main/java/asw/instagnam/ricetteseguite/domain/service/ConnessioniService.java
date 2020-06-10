package asw.instagnam.ricetteseguite.domain.service;

import java.util.*;

import asw.instagnam.ricetteseguite.domain.model.Connessione; 

public interface ConnessioniService {

	public Collection<Connessione> getConnessioniByFollower(String follower); 
	
}
