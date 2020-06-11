package asw.instagnam.ricetteseguite.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class RicettaSeguitaBreve {
	
	@Id
	@GeneratedValue
	private Long id; 
	private Long idRicetta; 
	private String autoreRicetta; 
	private String titoloRicetta;
	
	public RicettaSeguitaBreve(Long idRicetta, String autoreRicetta, String titoloRicetta) {
		
		this.idRicetta = idRicetta;
		this.autoreRicetta = autoreRicetta;
		this.titoloRicetta = titoloRicetta;
	}
	
	
	 
}