package asw.instagnam.ricetteseguite.domain.service;

import java.util.*;

import asw.instagnam.ricetteseguite.domain.model.Ricetta; 

public interface RicetteService {

	public Collection<Ricetta> getRicetteByAutore(String autore); 
	
}
