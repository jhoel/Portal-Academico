package is2.repository.jpa;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import is2.domain.Admin;
import is2.repository.AdminDao;

@Repository
public class JpaAdminDao extends JpaGenericDao<Admin,Long> implements AdminDao {
		
	
	
	@Override
	protected Class<Admin> getClase() {
		return Admin.class;
	}

	@Override
	public Admin findByUsername(String usuario) {
		TypedQuery<Admin> query = entityManager.createNamedQuery(Admin.FIND_BY_USER, Admin.class);
		query.setParameter("usuario", usuario);
		return getFirstResult(query);
	}
	
	@Override
	public boolean existsAdministrator(String username, String password) {
		Admin test = findByUsername(username);
		if (test!=null && test.getPassword().equals(password)) return true;
		else return false;
	}

	@Override
	public boolean existsByUsername(String username) {
		Admin test = findByUsername(username);
		if (test != null)return true;
		else return false;
	}

}
