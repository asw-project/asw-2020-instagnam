package asw.instagnam.ricetteseguite.domain.model;

import lombok.Data;

//Class used to respond Get call in ricette-seguite
@Data
public class RicettaSeguitaBreve {
	

	private Long idRicetta; 
	private String autoreRicetta; 
	private String titoloRicetta;
	
	public RicettaSeguitaBreve(Long idRicetta, String autoreRicetta, String titoloRicetta) {
		
		this.idRicetta = idRicetta;
		this.autoreRicetta = autoreRicetta;
		this.titoloRicetta = titoloRicetta;
	}
	
	
	 
}