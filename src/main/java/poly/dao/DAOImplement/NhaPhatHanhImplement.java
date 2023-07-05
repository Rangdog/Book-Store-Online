package poly.dao.DAOImplement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import poly.dao.DAOInterface.NhaPhatHanhInterface;
import poly.entity.NhaPhatHanh;

@Transactional
public class NhaPhatHanhImplement implements NhaPhatHanhInterface {
	private SessionFactory sessionFactory;

	protected NhaPhatHanhImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NhaPhatHanh> listNhaPhatHanh() {
		return sessionFactory.getCurrentSession().createQuery("FROM NhaPhatHanh").list();
	}

	@Override
	public Boolean insert(NhaPhatHanh nhaPhatHanh) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(nhaPhatHanh);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public Boolean update(int id, String tenNhaPhatHanh) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		NhaPhatHanh nhaPhatHanh = (NhaPhatHanh) session.get(NhaPhatHanh.class, id);
		try {
			nhaPhatHanh.setTenNPH(tenNhaPhatHanh);
			session.update(nhaPhatHanh);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public NhaPhatHanh getNhaPhatHanh(int id) {
		return (NhaPhatHanh) sessionFactory.getCurrentSession().get(NhaPhatHanh.class, id);
	}

	@Override
	public int deleteNhaPhatHanh(int id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		NhaPhatHanh nhaPhatHanh = (NhaPhatHanh) session.get(NhaPhatHanh.class, id);
		if (nhaPhatHanh.getDsSach().isEmpty()) {
			try {
				session.delete(nhaPhatHanh);
				t.commit();
			} catch (Exception e) {
				t.rollback();
				return 1;
			} finally {
				session.close();
			}
		} else {
			return 2;
		}
		return 0;
	}
}
