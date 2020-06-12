package asw.instagnam.ricetteseguite.domain.service;

import org.springframework.stereotype.Service;

import asw.instagnam.ricetteseguite.domain.model.Ricetta;
import asw.instagnam.ricetteseguite.domain.model.RicettaSeguita;

import asw.instagnam.ricetteseguite.domain.repository.RicetteSeguiteRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


@Service 
public class RicetteSeguiteService {


	@Autowired
	private RicetteSeguiteRepository ricetteSeguiteRepository;


	public Collection<Ricetta> getRicetteSeguite(String utente) {

		/*Now ricette-seguite has new table ricette-seguite on DB*/	
		Collection<Ricetta> ricetteSeguite = new ArrayList<>();
		Collection<RicettaSeguita> ricette=this.ricetteSeguiteRepository.findAllByFollower(utente);
        for(RicettaSeguita rs: ricette) {
        	Ricetta r = new Ricetta(rs.getIdRicetta(),
        			rs.getAutoreRicetta(), rs.getTitoloRicetta());
        	ricetteSeguite.add(r);
        }
        return ricetteSeguite;
	}
}
