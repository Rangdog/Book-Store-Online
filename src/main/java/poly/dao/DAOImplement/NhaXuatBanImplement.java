package poly.dao.DAOImplement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import poly.dao.DAOInterface.NhaXuatBanInterface;
import poly.entity.NhaXuatBan;


@Transactional
public class NhaXuatBanImplement implements NhaXuatBanInterface {
	private SessionFactory sessionFactory;

	protected NhaXuatBanImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NhaXuatBan> listNhaXuatBan() {
		return sessionFactory.getCurrentSession().createQuery("FROM NhaXuatBan").list();
	}

	@Override
	public Boolean insert(NhaXuatBan nhaXuatBan) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(nhaXuatBan);
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
	public Boolean update(int id, String tenNhaXuatBan) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		NhaXuatBan nhaXuatBan = (NhaXuatBan) session.get(NhaXuatBan.class, id);
		try {
			nhaXuatBan.setTenNXB(tenNhaXuatBan);
			session.update(nhaXuatBan);
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
	public NhaXuatBan getNhaXuatBan(int id) {
		return (NhaXuatBan) sessionFactory.getCurrentSession().get(NhaXuatBan.class, id);
	}

	@Override
	public int deleteNhaXuatBan(int id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		NhaXuatBan nhaXuatBan = (NhaXuatBan) session.get(NhaXuatBan.class, id);
		if (nhaXuatBan.getDsSach().isEmpty()) {
			try {
				session.delete(nhaXuatBan);
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
