package asw.instagnam.ricetteseguite.domain.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import asw.instagnam.ricetteseguite.domain.model.RicettaSeguita;

public interface RicetteSeguiteRepository extends CrudRepository<RicettaSeguita,Long> {
	
	public Collection<RicettaSeguita> findAllByFollower(String follower);

}
