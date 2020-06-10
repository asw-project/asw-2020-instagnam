package asw.instagnam.ricetteseguite.domain.model;

import lombok.*; 

/* Ricetta (in formato breve). */ 
@Data @NoArgsConstructor
public class Ricetta {

	private Long id; 
	private String autore; 
	private String titolo; 
	
}
