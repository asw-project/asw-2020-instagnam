package asw.instagnam.ricetteseguite.domain.service;

import org.springframework.stereotype.Service;

import asw.instagnam.ricetteseguite.domain.model.Ricetta;
import asw.instagnam.ricetteseguite.domain.model.RicettaSeguita;
import asw.instagnam.ricetteseguite.domain.repository.ConnessioniRepository;
import asw.instagnam.ricetteseguite.domain.repository.RicetteRepository;
import asw.instagnam.ricetteseguite.domain.repository.RicetteSeguiteRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream; 

@Service 
public class RicetteSeguiteService {

	/*Old Version@Autowired  
	private ConnessioniRepository connessioniRepository;

	@Autowired 
	private RicetteRepository ricetteRepository;
    */
	@Autowired
	private RicetteSeguiteRepository ricetteSeguiteRepository;


	public Collection<Ricetta> getRicetteSeguite(String utente) {
		/*Old version
		//Find utente connections
		return connessioniRepository.findAllByFollower(utente).stream()
				//Find ricette created by followed users
				.map(connessione -> ricetteRepository.findAllByAutore(connessione.getFollowed()))
				         .flatMap(Collection::stream).collect(Collectors.toList());
	} */

		//Now ricette-seguite has new table ricette-seguite on DB	
		Collection<Ricetta> ricetteSeguite = new ArrayList<>();
        for(RicettaSeguita rs: this.ricetteSeguiteRepository.findAllByFollower(utente)) {
        	Ricetta r = new Ricetta(rs.getIdRicetta(),
        			rs.getAutoreRicetta(), rs.getTitoloRicetta());
        	ricetteSeguite.add(r);
        }
        return ricetteSeguite;
	}
}
