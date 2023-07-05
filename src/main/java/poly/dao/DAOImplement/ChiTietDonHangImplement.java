package poly.dao.DAOImplement;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import poly.dao.DAOInterface.ChiTietDonHangInterface;
import poly.entity.ChiTietDonHang;

@Transactional
public class ChiTietDonHangImplement implements ChiTietDonHangInterface {
	private SessionFactory sessionFactory;
	
	protected ChiTietDonHangImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Boolean insertChiTietDonHang(ChiTietDonHang e) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(e);
			t.commit();
		} catch (Exception ex) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
}
