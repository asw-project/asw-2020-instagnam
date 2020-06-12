package asw.instagnam.ricetteseguite.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Ricetta {
	
	@Id	
	private Long id; 
	private String autore; 
	private String titolo; 
	
}
