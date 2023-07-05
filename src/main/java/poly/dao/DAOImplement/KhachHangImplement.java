package poly.dao.DAOImplement;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import poly.dao.DAOInterface.KhachHangInterface;
import poly.entity.KhachHang;

@Transactional
public class KhachHangImplement implements KhachHangInterface {
	private SessionFactory sessionFactory;
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;

	protected KhachHangImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KhachHang> listKhachHang() {
		return sessionFactory.getCurrentSession().createQuery("FROM KhachHang").list();
	}

	@Override
	public KhachHang getKhachHang(int maKhachHang) {
		return (KhachHang) sessionFactory.getCurrentSession().get(KhachHang.class, maKhachHang);
	}

	@Override
	public Boolean insertKhachHang(KhachHang khachHang) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(khachHang);
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
	public KhachHang findByVerificationCode(String code) {
		return (KhachHang) sessionFactory.getCurrentSession()
				.createQuery(String.format("FROM KhachHang e WHERE e.verificationCode = '%s'", code)).uniqueResult();
	}

	@Override
	public Boolean confirmVerification(int maKhachHang) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		KhachHang khachHang = (KhachHang) session.get(KhachHang.class, maKhachHang);
		try {
			khachHang.setVerificationCode(null);
			khachHang.setVerificationTime(null);
			khachHang.setTrangThaiEmail(true);
			session.update(khachHang);
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
	public Boolean blockKhachHang(int maKhachHang) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		KhachHang khachHang = (KhachHang) session.get(KhachHang.class, maKhachHang);
		try {
			khachHang.getTaiKhoan().setTrangThai(!khachHang.getTaiKhoan().getTrangThai());
			session.update(khachHang);
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
	public KhachHang findByUsername(String tenTaiKhoan) {
		return (KhachHang) sessionFactory.getCurrentSession()
				.createQuery(String.format("FROM KhachHang e WHERE e.taiKhoan.tenTaiKhoan = '%s'", tenTaiKhoan)).uniqueResult();
	}

	@Override
	public Boolean updateKhachHang(int maKhachHang, String ho, String ten, Boolean gioiTinh, Date ngaySinh, String anhDaiDien) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		KhachHang khachHang = (KhachHang) session.get(KhachHang.class, maKhachHang);
		try {
			if (anhDaiDien != null) khachHang.setAnhDaiDien(anhDaiDien);
			khachHang.setHo(ho);
			khachHang.setTen(ten);
			khachHang.setGioiTinh(gioiTinh);
			khachHang.setNgaySinh(ngaySinh);
			session.update(khachHang);
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
	public Boolean renewVerificationEmail(int maKhachHang, String code, Date time) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		KhachHang khachHang = (KhachHang) session.get(KhachHang.class, maKhachHang);
		try {
			khachHang.setVerificationCode(code);
			khachHang.setVerificationTime(time);
			session.update(khachHang);
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
	public Boolean changeEmail(int maKhachHang, String email) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		KhachHang khachHang = (KhachHang) session.get(KhachHang.class, maKhachHang);
		if (khachHang.getEmail().equals(email)) return false;
		try {
			khachHang.setEmail(email);
			khachHang.setTrangThaiEmail(false);
			session.update(khachHang);
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
	public Boolean changePassword(int maKhachHang, String matKhau) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		KhachHang khachHang = (KhachHang) session.get(KhachHang.class, maKhachHang);
		if (khachHang.getTaiKhoan().getMatKhau().equals(matKhau)) return false;
		try {
			String encodedPassword = passwordEncoder.encode(matKhau);
			khachHang.getTaiKhoan().setMatKhau(encodedPassword);
			session.update(khachHang);
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
	public Boolean checkExistEmail(String email) {
		return sessionFactory.getCurrentSession()
				.createQuery(String.format("FROM KhachHang e WHERE e.email = '%s'", email)).uniqueResult() != null;
	}

	@Override
	public KhachHang findByEmail(String email) {
		return (KhachHang) sessionFactory.getCurrentSession()
				.createQuery(String.format("FROM KhachHang e WHERE e.email = '%s'", email)).uniqueResult();
	}
}
