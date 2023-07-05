package poly.dao.DAOImplement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import poly.dao.DAOInterface.DiaChiGiaoHangInterface;
import poly.entity.DiaChiGiaoHang;

@Transactional
public class DiaChiGiaoHangImplement implements DiaChiGiaoHangInterface {
	private SessionFactory sessionFactory;

	protected DiaChiGiaoHangImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiaChiGiaoHang> listDCGH(int maKhachHang) {
		return sessionFactory.getCurrentSession().createQuery(String.format("FROM DiaChiGiaoHang e where e.khachHang.maKhachHang = %d and e.trangThai != 2",  maKhachHang)).list();
	}

	@Override
	public Boolean insertDCGH(DiaChiGiaoHang DCGH) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(DCGH);
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
	public Boolean updateDCGH(int maDiaChi, String tenNN, String sdtNN, String diaChi, int trangThai) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		DiaChiGiaoHang DCGH = (DiaChiGiaoHang) session.get(DiaChiGiaoHang.class, maDiaChi);
		try {
			DCGH.setTenNN(tenNN);
			DCGH.setSdtNN(sdtNN);
			DCGH.setDiaChi(diaChi);
			DCGH.setTrangThai(trangThai);
			session.save(DCGH);
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
	public DiaChiGiaoHang getDCGH(int maDiaChi) {
		return (DiaChiGiaoHang) sessionFactory.getCurrentSession().get(DiaChiGiaoHang.class, maDiaChi);
	}

	@Override
	public Boolean deleteDCGH(int maDiaChi) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		DiaChiGiaoHang dcgh = (DiaChiGiaoHang) session.get(DiaChiGiaoHang.class, maDiaChi);
		if(dcgh.getTrangThai() == 1) return false;
		try {
			if(dcgh.getDsDonHang().isEmpty()) {
				session.delete(dcgh);
			}
			else {
				dcgh.setTrangThai(2);
				session.update(dcgh);
			}
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
			return false;
		}
		finally {
			session.close();
		}
		return true;
	}
}
