package poly.dao.DAOImplement;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import poly.dao.DAOInterface.KhuyenMaiInterface;
import poly.entity.KhuyenMai;
import poly.entity.Sach;

@Transactional
public class KhuyenMaiImplement implements KhuyenMaiInterface {
	private SessionFactory sessionFactory;

	protected KhuyenMaiImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KhuyenMai> listKhuyenMai() {
		return sessionFactory.getCurrentSession().createQuery("FROM KhuyenMai").list();
	}

	@Override
	public Boolean insertKhuyenMai(KhuyenMai khuyenMai) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(khuyenMai);
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
	public Boolean updateKhuyenMai(int maKhuyenMai, Date ngayBatDau, Date ngayKetThuc, Float phanTram) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		KhuyenMai khuyenMai = (KhuyenMai) session.get(KhuyenMai.class, maKhuyenMai);

		try {
			khuyenMai.setNgayBatDau(ngayBatDau);
			khuyenMai.setNgayKetThuc(ngayKetThuc);
			khuyenMai.setPhanTram(phanTram);
			session.update(khuyenMai);
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
	public KhuyenMai getKhuyenMai(int maKM) {
		return (KhuyenMai) sessionFactory.getCurrentSession().get(KhuyenMai.class, maKM);
	}

	@Override
	public Boolean addSach(int maKhuyenMai, int maSach) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		Sach sach = (Sach) session.get(Sach.class, maSach);
		KhuyenMai km = (KhuyenMai) session.get(KhuyenMai.class, maKhuyenMai);
		try {
			km.getDsSach().add(sach);
			session.update(km);
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
	public Boolean deleteSach(int maKhuyenMai, int maSach) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		Sach sach = (Sach) session.get(Sach.class, maSach);
		KhuyenMai km = (KhuyenMai) session.get(KhuyenMai.class, maKhuyenMai);
		try {
			km.getDsSach().remove(sach);
			session.update(km);
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
	public Boolean deleteKhuyenMai(int maKhuyenMai) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		KhuyenMai km = (KhuyenMai) session.get(KhuyenMai.class, maKhuyenMai);
		try {
			session.delete(km);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		}
		finally {
			session.close();
		}
		return true;
	}
}
