package poly.dao.DAOImplement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import poly.dao.DAOInterface.TacGiaInterface;
import poly.entity.Sach;
import poly.entity.TacGia;

@Transactional
public class TacGiaImplement implements TacGiaInterface {
	private SessionFactory sessionFactory;

	protected TacGiaImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TacGia> listTacGia() {
		return sessionFactory.getCurrentSession().createQuery("FROM TacGia").list();
	}

	@Override
	public TacGia getTacGia(int maTacGia) {
		return (TacGia) sessionFactory.getCurrentSession().get(TacGia.class, maTacGia);
	}

	@Override
	public Boolean insertTacGia(TacGia tacGia) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(tacGia);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public Boolean updateTacGia(int maTacGia, String tenTacGia) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		TacGia tacGia = (TacGia) session.get(TacGia.class, maTacGia);
		try {
			tacGia.setTenTacGia(tenTacGia);
			session.update(tacGia);
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
	public int deleteTacGia(int maTacGia) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		TacGia tacGia = (TacGia) session.get(TacGia.class, maTacGia);
		if (tacGia.getDsSach().isEmpty()) {
			try {
				session.delete(tacGia);
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

	@Override
	public Boolean addSach(int maTacGia, int maSach) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		Sach sach = (Sach) session.get(Sach.class, maSach);
		TacGia tacGia = (TacGia) session.get(TacGia.class, maTacGia);
		try {
			tacGia.getDsSach().add(sach);
			session.update(tacGia);
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
	public Boolean deleteSach(int maTacGia, int maSach) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		Sach sach = (Sach) session.get(Sach.class, maSach);
		TacGia tacGia = (TacGia) session.get(TacGia.class, maTacGia);
		if (tacGia.getDsSach() == null) {
			return false;
		}
		try {
			tacGia.getDsSach().remove(sach);
			session.update(tacGia);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
}
