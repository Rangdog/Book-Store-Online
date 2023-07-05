package poly.dao.DAOImplement;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import poly.dao.DAOInterface.NhanVienInterface;
import poly.entity.NhanVien;
import poly.entity.TaiKhoan;

@Transactional
public class NhanVienImplement implements NhanVienInterface { 
	
	private SessionFactory sessionFactory;

	protected NhanVienImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NhanVien> listNhanVien() {
		return sessionFactory.getCurrentSession().createQuery("FROM NhanVien").list();
	}

	@Override
	public NhanVien getNhanVien(int maNhanVien) {
		return (NhanVien) sessionFactory.getCurrentSession().get(NhanVien.class, maNhanVien);
	}

	@Override
	public Boolean insertNhanVien(NhanVien nhanVien) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(nhanVien);
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
	public Boolean updateNhanVien(int maNhanVien, String anhDaiDien, String ho, String ten, Boolean gioiTinh,
			Date ngaySinh, String diaChi, String sDT, String cmnd, float luong, Boolean trangThaiXoa) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		NhanVien nhanVien = (NhanVien) session.get(NhanVien.class, maNhanVien);
		try {
			if (anhDaiDien != null) nhanVien.setAnhDaiDien(anhDaiDien);
			nhanVien.setHo(ho);
			nhanVien.setTen(ten);
			nhanVien.setGioiTinh(gioiTinh);
			nhanVien.setNgaySinh(ngaySinh);
			nhanVien.setDiaChi(diaChi);
			nhanVien.setsDT(sDT);
			nhanVien.setCmnd(cmnd);
			nhanVien.setLuong(luong);
			nhanVien.setTrangThaiXoa(trangThaiXoa);
			TaiKhoan tk = nhanVien.getTaiKhoan();
			if(tk != null) {
				tk.setTrangThai(!trangThaiXoa);
				session.update(tk);
			}
			session.update(nhanVien);
			t.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public int deleteNhanVien(int maNhanVien) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		NhanVien nhanVien = (NhanVien) session.get(NhanVien.class, maNhanVien);
		if (nhanVien.getDsDonHangDuyet().isEmpty() && nhanVien.getDsDonHangGiao().isEmpty()
				&& nhanVien.getDsKhuyenMai().isEmpty() && nhanVien.getDsPhieuNhap().isEmpty()
				&& nhanVien.getDsPhieuXuat().isEmpty() && nhanVien.getDsPhieuTra().isEmpty()) {
			try {
				TaiKhoan tk = nhanVien.getTaiKhoan();
				if(tk != null) {
					session.delete(tk);
				}
				session.delete(nhanVien);
				t.commit();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
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
	public NhanVien getNhanVienByTenTaiKhoan(String tenTaiKhoan) {
		return (NhanVien) sessionFactory.getCurrentSession()
				.createQuery(String.format("FROM NhanVien e WHERE e.taiKhoan.tenTaiKhoan = '%s'", tenTaiKhoan)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NhanVien> listNhanVienGiao() {
		return sessionFactory.getCurrentSession().createQuery("FROM NhanVien e WHERE e.taiKhoan.quyen = 'ROLE_SHIPPER'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NhanVien> listNhanVienChuaCoTaiKhoan() {
		return sessionFactory.getCurrentSession().createQuery("FROM NhanVien WHERE taiKhoan IS NULL").list();
	}

	@Override
	public Boolean updatetkNhanVien(TaiKhoan tk, int maNhanVien) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		NhanVien nhanVien = (NhanVien) session.get(NhanVien.class, maNhanVien);
		try {
			nhanVien.setTaiKhoan(tk);
			session.update(nhanVien);
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
