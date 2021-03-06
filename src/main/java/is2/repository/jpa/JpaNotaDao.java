package is2.repository.jpa;

import org.springframework.stereotype.Repository;

import is2.domain.Nota;
import is2.repository.NotaDao;

@Repository
public class JpaNotaDao extends JpaGenericDao<Nota, Long> implements NotaDao{

	@Override
	protected Class<Nota> getClase() {
		return Nota.class;
	}

}
