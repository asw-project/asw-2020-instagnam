package asw.instagnam.ricetteseguite.domain.service;

import org.springframework.stereotype.Service;

import asw.instagnam.ricetteseguite.domain.model.Connessione;
import asw.instagnam.ricetteseguite.domain.model.Ricetta;
import asw.instagnam.ricetteseguite.domain.repository.ConnessioniRepository;
import asw.instagnam.ricetteseguite.domain.repository.RicetteRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors; 

@Service 
public class RicetteSeguiteService {

	@Autowired 
	private ConnessioniRepository connessioniRepository;
	
	@Autowired 
	private RicetteRepository ricetteRepository;

	
	public Collection<Ricetta> getRicetteSeguite(String utente) {
		//Find utente connections
		return connessioniRepository.findAllByFollower(utente).stream()
				//Find ricette created by followed users
				.map(connessione -> ricetteRepository.findAllByAutore(connessione.getFollowed()))
				         .flatMap(Collection::stream).collect(Collectors.toList());
	} 
	
}
