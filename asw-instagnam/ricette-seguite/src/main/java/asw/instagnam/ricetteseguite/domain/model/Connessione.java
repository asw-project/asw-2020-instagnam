package asw.instagnam.ricetteseguite.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.*; 

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Connessione {
    
	@Id
	private Long id; 
	private String follower; 
	private String followed; 
	
}
