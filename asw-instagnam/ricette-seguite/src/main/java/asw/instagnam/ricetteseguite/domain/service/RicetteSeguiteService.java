package asw.instagnam.ricetteseguite.domain.service;

import org.springframework.stereotype.Service;

import asw.instagnam.ricetteseguite.domain.model.Connessione;
import asw.instagnam.ricetteseguite.domain.model.Ricetta;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

@Service 
public class RicetteSeguiteService {

	@Autowired 
	private ConnessioniService connessioniService;

	@Autowired 
	private RicetteService ricetteService;

	/* Trova le ricette (in formato breve) degli utenti seguiti da utente. */ 
	public Collection<Ricetta> getRicetteSeguite(String utente) {
		Collection<Ricetta> ricette = new ArrayList<>(); 
		Collection<Connessione> connessioni = connessioniService.getConnessioniByFollower(utente); 
		for (Connessione connessione : connessioni) {
			String followed = connessione.getFollowed();
			Collection<Ricetta> ricetteByFollowed = ricetteService.getRicetteByAutore(followed);
			ricette.addAll(ricetteByFollowed);
		}
		return ricette; 
	}
	
}
