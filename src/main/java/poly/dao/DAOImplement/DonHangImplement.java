package poly.dao.DAOImplement;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import poly.dao.DAOInterface.DonHangInterface;
import poly.entity.DonHang;
import poly.entity.NhanVien;
import poly.entity.PhieuTra;
import poly.entity.PhieuXuat;
import poly.entity.Sach;

@Transactional
public class DonHangImplement implements DonHangInterface {
	private SessionFactory sessionFactory;

	protected DonHangImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DonHang> listDonHang() {
		return sessionFactory.getCurrentSession().createQuery("FROM DonHang").list();
	}

	@Override
	public DonHang getDonHang(int maDonHang) {
		return (DonHang) sessionFactory.getCurrentSession().get(DonHang.class, maDonHang);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DonHang> listDonHangShipper(int maShipper) {
		return sessionFactory.getCurrentSession()
				.createQuery(String.format("FROM DonHang e WHERE e.nhanVienGiao.maNhanVien = %d", maShipper)).list();
	}

	@Override
	public Boolean updateTrangThai(int maDonHang, int trangThai) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		DonHang donHang = (DonHang) session.get(DonHang.class, maDonHang);
		try {
			donHang.setTrangThai(trangThai);
			session.update(donHang);
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
	public Boolean insertDonHang(DonHang donHang) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(donHang);
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
	public List<DonHang> listDonHangTheoTrangThai(int trangThai) {
		return sessionFactory.getCurrentSession()
				.createQuery(String.format("FROM DonHang e WHERE e.trangThai = %d ORDER BY e.ngay DESC", trangThai)).list();
	}

	@Override
	public Boolean confirmDonHang(int maDonHang, int maNhanVienDuyet, int maNhanVienGiao) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			DonHang donHang = (DonHang) session.get(DonHang.class, maDonHang);
			// KIỂM TRA CÓ THỂ ĐÁP ỨNG ĐƠN HÀNG.
			if (donHang.getDsChiTietDonHang().stream().anyMatch(e -> e.getSoLuong() > e.getSach().getSoLuongTon())) {
				return false;
			}
			// DUYỆT ĐƠN HÀNG THÀNH CÔNG VÀ GIAO CHO SHIPPER.
			NhanVien nhanVienDuyet = (NhanVien) session.get(NhanVien.class, maNhanVienDuyet);
			donHang.setNhanVienDuyet(nhanVienDuyet);
			donHang.setTrangThai(1);
			donHang.setNhanVienGiao((NhanVien) session.get(NhanVien.class, maNhanVienGiao));
			session.save(donHang);
			// LẬP PHIẾU XUẤT HÀNG.
			PhieuXuat phieuXuat = new PhieuXuat();
			phieuXuat.setDonHang(donHang);
			phieuXuat.setNgay(Calendar.getInstance().getTime());
			phieuXuat.setNhanVien(nhanVienDuyet);
			session.save(phieuXuat);
			// UPDATE SỐ LƯỢNG TỒN SẢN PHẨM.
			donHang.getDsChiTietDonHang().forEach(e -> {
				Sach sanPham = e.getSach();
				sanPham.setSoLuongTon(sanPham.getSoLuongTon() - 1);
				sanPham.setLuotMua(sanPham.getLuotMua() + 1);
				session.update(sanPham);
			});
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

	@Override
	public Boolean cancelDonHang(int maDonHang, int maNhanVienDuyet) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		DonHang donHang = (DonHang) session.get(DonHang.class, maDonHang);
		try {
			donHang.setNhanVienDuyet((NhanVien) session.get(NhanVien.class, maNhanVienDuyet));
			donHang.setTrangThai(2);
			session.update(donHang);
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
	public Boolean refundDonHang(int maDonHang, int maNhanVienGiao, String lyDo) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			DonHang donHang = (DonHang) session.get(DonHang.class, maDonHang);
			// LẬP PHIẾU TRẢ.
			PhieuTra phieuTra = new PhieuTra();
			phieuTra.setNgay(Calendar.getInstance().getTime());
			phieuTra.setLyDo(lyDo);
			phieuTra.setNhanVien((NhanVien) session.get(NhanVien.class, maNhanVienGiao));
			phieuTra.setPhieuXuat(donHang.getPhieuXuat());
			// TĂNG SỐ LƯỢNG TỒN SẢN PHẨM.
			donHang.getDsChiTietDonHang().forEach(e -> {
				Sach sanPham = e.getSach();
				sanPham.setSoLuongTon(sanPham.getSoLuongTon() + 1);
				sanPham.setLuotMua(sanPham.getLuotMua() - 1);
				session.update(sanPham);
			});
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
