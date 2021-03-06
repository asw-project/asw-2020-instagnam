package asw.instagnam.ricetteseguite.rest;

import asw.instagnam.ricetteseguite.domain.model.Ricetta;
import asw.instagnam.ricetteseguite.domain.service.RicetteSeguiteService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger; 
import java.util.*; 

@RestController
public class RicetteSeguiteController {

	private final Logger logger = Logger.getLogger(RicetteSeguiteController.class.toString()); 

	@Autowired 
	private RicetteSeguiteService ricetteSeguiteService;

	/* Finds short ricette of user followed users. */ 
	@GetMapping("/ricetteseguite/{utente}")
	public Collection<Ricetta> getRicetteSeguite(@PathVariable String utente) {
		logger.info("REST CALL: getRicetteSeguite " + utente); 
		Collection<Ricetta> ricette = ricetteSeguiteService.getRicetteSeguite(utente); 
		logger.info("getRicetteSeguite(): " + ricette);
		return ricette; 
	}
	
}
