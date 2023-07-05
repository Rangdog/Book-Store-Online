package poly.dao.DAOImplement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import poly.dao.DAOInterface.PhieuNhapInterface;
import poly.entity.PhieuNhap;

@Transactional
public class PhieuNhapImplement implements PhieuNhapInterface {
	private SessionFactory sessionFactory;

	protected PhieuNhapImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhieuNhap> listPhieuNhap() {
		return sessionFactory.getCurrentSession().createQuery("FROM PhieuNhap").list();
	}

	@Override
	public Boolean insertPhieuNhap(PhieuNhap phieuNhap) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(phieuNhap);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		}
		return true;
	}

	@Override
	public Boolean deletePhieuNhap(int maPhieuNhap) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		PhieuNhap phieuNhap = (PhieuNhap) session.get(PhieuNhap.class, maPhieuNhap);
		try {
			session.delete(phieuNhap);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		}
		return true;
	}

	@Override
	public PhieuNhap getPhieuNhap(int maPhieuNhap) {
		return (PhieuNhap) sessionFactory.getCurrentSession().get(PhieuNhap.class, maPhieuNhap);
	}

}
