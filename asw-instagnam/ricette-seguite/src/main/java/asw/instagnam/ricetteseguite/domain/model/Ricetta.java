package asw.instagnam.ricetteseguite.domain.model;

import javax.persistence.Entity;

import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Ricetta {

	private Long id; 
	private String autore; 
	private String titolo; 
	
}
