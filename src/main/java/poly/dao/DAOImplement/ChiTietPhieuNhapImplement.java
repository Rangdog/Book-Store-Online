package poly.dao.DAOImplement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import poly.dao.DAOInterface.ChiTietPhieuNhapInterface;
import poly.dao.DAOInterface.SachInterface;
import poly.entity.ChiTietPhieuNhap;


@Transactional
public class ChiTietPhieuNhapImplement implements ChiTietPhieuNhapInterface {
	private SessionFactory sessionFactory;
	@Autowired
	private SachInterface DAOSach;

	protected ChiTietPhieuNhapImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChiTietPhieuNhap> listChiTietPhieuNhap() {
		return sessionFactory.getCurrentSession().createQuery("FROM ChiTietPhieuNhap").list();
	}

	@Override
	public Boolean insertChiTietPhieuNhap(ChiTietPhieuNhap ctpn) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ctpn);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		}
		return true;
	}

	@Override
	public Boolean updateChiTietPhieuNhap(int maPhieuNhap, int maSach, int soLuong, float GiaGoc) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		ChiTietPhieuNhap ctpn = (ChiTietPhieuNhap) sessionFactory.getCurrentSession()
				.createQuery(String.format("FROM ChiTietPhieuNhap e WHERE e.phieuNhap.maPhieuNhap = '%d' and e.sach.maSach = '%d'",
						maPhieuNhap, maSach)).uniqueResult();
		try {
			ctpn.setGiaGoc(GiaGoc);
			ctpn.setSoLuong(soLuong);
			session.update(ctpn);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		}
		return true;
	}

	@Override
	public Boolean deleteChiTietPhieuNhap(int maPhieuNhap, int maSach) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		ChiTietPhieuNhap ctpn = (ChiTietPhieuNhap) sessionFactory.getCurrentSession()
				.createQuery(String.format("FROM ChiTietPhieuNhap e WHERE e.phieuNhap.maPhieuNhap = '%d' and e.sach.maSach = '%d'",
						maPhieuNhap, maSach)).uniqueResult();
		try {
			session.delete(ctpn);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		}
		return true;
	}

	@Override
	public ChiTietPhieuNhap getChiTietPhieuNhap(int maPhieuNhap, int maSach) {
		return (ChiTietPhieuNhap) sessionFactory.getCurrentSession()
				.createQuery(String.format("FROM ChiTietPhieuNhap e WHERE e.phieuNhap.maPhieuNhap = '%d' and e.sach.maSach = '%d'",
						maPhieuNhap, maSach)).uniqueResult();
	}

	@Override
	public Boolean deleteNhieuSach(int maPhieuNhap, List<Integer> maSach) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			for(int id : maSach) {
				ChiTietPhieuNhap ctpn = (ChiTietPhieuNhap) sessionFactory.getCurrentSession()
						.createQuery(String.format("FROM ChiTietPhieuNhap e "
							+ "WHERE e.phieuNhap.maPhieuNhap = '%d' and e.sach.maSach = '%d'", maPhieuNhap, id)).uniqueResult();
				DAOSach.updateSoLuongTon(id, -ctpn.getSoLuong());
				session.delete(ctpn);
			}
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
