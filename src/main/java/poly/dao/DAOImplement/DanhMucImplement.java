package poly.dao.DAOImplement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import poly.dao.DAOInterface.DanhMucInterface;
import poly.entity.DanhMuc;

@Transactional
public class DanhMucImplement implements DanhMucInterface {
	private SessionFactory sessionFactory;

	protected DanhMucImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DanhMuc> listDanhMuc() {
		return sessionFactory.getCurrentSession().createQuery("FROM DanhMuc").list();
	}

	@Override
	public Boolean insertDanhMuc(DanhMuc danhMuc) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(danhMuc);
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
	public Boolean updateDanhMuc(int maDanhMuc, String tenDanhMuc) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		DanhMuc danhMuc = (DanhMuc) session.get(DanhMuc.class, maDanhMuc);
		try {
			danhMuc.setTenDanhMuc(tenDanhMuc);
			session.update(danhMuc);
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
	public DanhMuc getDanhMuc(int maDanhMuc) {
		return (DanhMuc) sessionFactory.getCurrentSession().get(DanhMuc.class, maDanhMuc);
	}

	@Override
	public int deleteDanhMuc(int maDanhMuc) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		DanhMuc danhMuc = (DanhMuc) session.get(DanhMuc.class, maDanhMuc);
		if (danhMuc.getDsTheLoai().isEmpty()) {
			try {
				session.delete(danhMuc);
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
