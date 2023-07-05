package poly.dao.DAOImplement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import poly.dao.DAOInterface.TheLoaiInterface;
import poly.entity.DanhMuc;
import poly.entity.Sach;
import poly.entity.TheLoai;

@Transactional
public class TheLoaiImplement implements TheLoaiInterface {

	private SessionFactory sessionFactory;

	protected TheLoaiImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TheLoai> listTheLoai() {
		return sessionFactory.getCurrentSession().createQuery("FROM TheLoai").list();
	}

	@Override
	public Boolean insertTheLoai(TheLoai theLoai) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(theLoai);
			t.commit();
			;
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public TheLoai getTheLoai(int id) {
		return (TheLoai) sessionFactory.getCurrentSession().get(TheLoai.class, id);
	}

	@Override
	public Boolean updateTheLoai(int id, int maDanhMuc, String tenTheLoai) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		TheLoai theLoai = (TheLoai) session.get(TheLoai.class, id);
		try {
			theLoai.setTenTheLoai(tenTheLoai);
			theLoai.setDanhMuc((DanhMuc) session.get(DanhMuc.class, maDanhMuc));
			session.update(theLoai);
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
	public int deleteTheLoai(int id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		TheLoai theLoai = (TheLoai) session.get(TheLoai.class, id);
		if (theLoai.getDsSach().isEmpty()) {
			try {
				session.delete(theLoai);
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
	public Boolean addSach(int maTheLoai, int maSach) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		Sach sach = (Sach) session.get(Sach.class, maSach);
		TheLoai theLoai = (TheLoai) session.get(TheLoai.class, maTheLoai);
		try {
			theLoai.getDsSach().add(sach);
			session.update(theLoai);
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
	public Boolean deleteSach(int maTheLoai, int maSach) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		Sach sach = (Sach) session.get(Sach.class, maSach);
		TheLoai theLoai = (TheLoai) session.get(TheLoai.class, maTheLoai);
		if (theLoai.getDsSach() == null) {
			return false;
		}
		try {
			theLoai.getDsSach().remove(sach);
			session.update(theLoai);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TheLoai> listTheLoaitheoDanhMuc(int maDanhMuc) {
		return sessionFactory.getCurrentSession()
				.createQuery("Select e FROM TheLoai e inner join e.danhMuc d where d.maDanhMuc = (?)").setParameter(0, maDanhMuc).list();
	}
}
