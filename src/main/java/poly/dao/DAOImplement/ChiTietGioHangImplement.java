package poly.dao.DAOImplement;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import poly.dao.DAOInterface.ChiTietGioHangInterface;
import poly.entity.ChiTietGioHang;

@Transactional
public class ChiTietGioHangImplement implements ChiTietGioHangInterface {
	private SessionFactory sessionFactory;

	protected ChiTietGioHangImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ChiTietGioHang getChiTietGioHang(int maKhachHang, int maSach) {
		return (ChiTietGioHang) sessionFactory.getCurrentSession()
				.createQuery(String.format(
						"FROM ChiTietGioHang e " + "WHERE e.khachHang.maKhachHang = %d AND e.sach.maSach = %d",
						maKhachHang, maSach))
				.uniqueResult();
	}

	@Override
	public Boolean deleteChiTietGioHang(int maKhachHang, int maSach) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		ChiTietGioHang chiTiet = getChiTietGioHang(maKhachHang, maSach);
		try {
			session.delete(chiTiet);
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
	public Boolean updateSoLuong(int maKhachHang, int maSach, int soLuong) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		ChiTietGioHang chiTiet = getChiTietGioHang(maKhachHang, maSach);
		try {
			int soLuongMoi = chiTiet.getSoLuong() + soLuong;
			if (soLuongMoi == 0) {
				session.delete(chiTiet);
			} else {
				chiTiet.setSoLuong(chiTiet.getSoLuong() + soLuong);
				session.update(chiTiet);
			}
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
	public List<ChiTietGioHang> listCTGH() {
		return sessionFactory.getCurrentSession().createQuery("FROM ChiTietGioHang").list();
	}

	@Override
	public Boolean insertCTGH(ChiTietGioHang CTGH) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(CTGH);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public Boolean checkExistCTGH(int maKhachHang, int maSach) {
		return sessionFactory.getCurrentSession()
				.createQuery(String.format("FROM ChiTietGioHang e "
						+ "WHERE e.khachHang.maKhachHang = '%d' and e.sach.maSach = '%d'", maKhachHang, maSach))
				.uniqueResult() != null;
	}
}
