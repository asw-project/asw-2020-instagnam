package asw.instagnam.ricetteseguite.domain.model;

import javax.persistence.Entity;

import lombok.*; 

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Connessione {

	private Long id; 
	private String follower; 
	private String followed; 
	
}
