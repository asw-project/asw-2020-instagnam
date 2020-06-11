package asw.instagnam.ricetteseguite.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class RicettaSeguita {
	
	@Id
	@GeneratedValue
	private Long id;
	private String follower; 
	private Long idRicetta; 
	private String autoreRicetta; 
	private String titoloRicetta;
	
	public RicettaSeguita(String follower, Long idRicetta, String autoreRicetta, String titoloRicetta) {
		
		this.follower = follower;
		this.idRicetta = idRicetta;
		this.autoreRicetta = autoreRicetta;
		this.titoloRicetta = titoloRicetta;
	}
	
	
	 
}