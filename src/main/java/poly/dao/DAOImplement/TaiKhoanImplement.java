package poly.dao.DAOImplement;


import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import poly.dao.DAOInterface.TaiKhoanInterface;
import poly.entity.TaiKhoan;

@Transactional
public class TaiKhoanImplement implements TaiKhoanInterface {
	
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;
	 
	 
	 
	 private SessionFactory sessionFactory;

	protected TaiKhoanImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Boolean insertTaiKhoan(TaiKhoan taiKhoan) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			String encodedPassword = passwordEncoder.encode(taiKhoan.getMatKhau());
			taiKhoan.setMatKhau(encodedPassword);
			session.save(taiKhoan);
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
	public TaiKhoan getTaiKhoan(String tenTaiKhoan) {
		return (TaiKhoan) sessionFactory.getCurrentSession().get(TaiKhoan.class, tenTaiKhoan);
	}
	
	@Override
	public Boolean checkExistTenTaiKhoan(String tenTaiKhoan) {
		return sessionFactory.getCurrentSession().createQuery("FROM TaiKhoan where tenTaiKhoan = (?)").setParameter(0, tenTaiKhoan).list().size() != 0;
	}

	@Override
	public Boolean doiMatKhau(String tenTaiKhoan, String matKhau) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		TaiKhoan tk  = (TaiKhoan) session.get(TaiKhoan.class, tenTaiKhoan);
		try {
			String encodedPassword = passwordEncoder.encode(matKhau);
			tk.setMatKhau(encodedPassword);
			session.save(tk);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public Boolean deleteTaiKhoan(String tenTaiKhoan) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		TaiKhoan tk  = (TaiKhoan) session.get(TaiKhoan.class, tenTaiKhoan);
		try {
			session.delete(tk);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
}
