package asw.instagnam.ricetteseguite.domain.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import asw.instagnam.ricetteseguite.domain.model.Ricetta;

public interface RicetteRepository extends CrudRepository<Ricetta, Long> {
	
	public Collection<Ricetta> findAll();
	public Collection<Ricetta> findAllByAutore(String autore);

}