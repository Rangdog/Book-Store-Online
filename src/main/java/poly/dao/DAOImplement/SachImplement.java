package poly.dao.DAOImplement;

import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import poly.dao.DAOInterface.NhaPhatHanhInterface;
import poly.dao.DAOInterface.NhaXuatBanInterface;
import poly.dao.DAOInterface.SachInterface;
import poly.entity.Sach;
import poly.entity.TacGia;
import poly.entity.TheLoai;

@Transactional
public class SachImplement implements SachInterface {
	@Autowired
	private NhaPhatHanhInterface DAONhaPhatHanh;
	@Autowired
	private NhaXuatBanInterface DAONhaXuatBan;
	@Autowired
	DataSource dataSource;

	private SessionFactory sessionFactory;

	protected SachImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sach> listSach() {
		return sessionFactory.getCurrentSession().createQuery("FROM Sach").list();
	}

	@Override
	public Sach getSach(int maSach) {
		return (Sach) sessionFactory.getCurrentSession().get(Sach.class, maSach);
	}

	@Override
	public Boolean insertSach(Sach sach, int maTacGia, int maTheLoai) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		TheLoai theLoai = (TheLoai) session.get(TheLoai.class, maTheLoai);
		TacGia tacGia = (TacGia) session.get(TacGia.class, maTacGia);
		try {
			tacGia.getDsSach().add(sach);
			theLoai.getDsSach().add(sach);
			session.save(sach);
			session.update(tacGia);
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
	public Boolean updateSach(int maSach, String tenSach, int maNPH, int maNXB, int soTrang, int kichThuoc,
			int trongLuong, String hinhThuc, String ngonNgu, int trangThai, Float giaBan, String tomTat,
			String photoPath) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		Sach sach = (Sach) session.get(Sach.class, maSach);

		try {
			if (photoPath != null) {
				if (sach.getAnhDaiDien() != photoPath) {
					sach.setAnhDaiDien(photoPath);
				}
			}
			sach.setTenSach(tenSach);
			sach.setNhaPhatHanh(DAONhaPhatHanh.getNhaPhatHanh(maNPH));
			sach.setNhaXuatBan(DAONhaXuatBan.getNhaXuatBan(maNXB));
			sach.setSoTrang(soTrang);
			sach.setKichThuoc(kichThuoc);
			sach.setTrongLuong(trongLuong);
			sach.setNgonNgu(ngonNgu);
			sach.setHinhThuc(hinhThuc);
			sach.setTomTat(tomTat);
			sach.setGiaBan(giaBan);
			sach.setTrangThai(trangThai);
			session.update(sach);
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
	public Boolean deleteSach(Sach sach, Set<TheLoai> dsTheLoai, Set<TacGia> dsTacGia) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			for (TheLoai tl : dsTheLoai) {
				tl.getDsSach().remove(sach);
				session.update(tl);
			}
			for (TacGia tg : dsTacGia) {
				tg.getDsSach().remove(sach);
				session.update(tg);
			}
			session.delete(sach);
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sach> listSachKhongCoTrongKM(int maKhuyenMai) {
		return sessionFactory.getCurrentSession().createSQLQuery(String.format("SELECT * FROM SACH "
				+ "WHERE SACH.SOLUONGTON > 0 AND SACH.ID_SACH NOT IN (SELECT ID_SACH FROM CT_KM WHERE ID_KM = '%s')",
				maKhuyenMai)).addEntity(Sach.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sach> listSachKhongCoTrongPN(int maPhieuNhap) {
		return sessionFactory.getCurrentSession()
				.createSQLQuery(String.format("SELECT * FROM SACH "
						+ "WHERE SACH.ID_SACH NOT IN (SELECT ID_SACH FROM CT_PN WHERE ID_PN = '%s')", maPhieuNhap))
						.addEntity(Sach.class).list();
	}

	@Override
	public Boolean updateSoLuongTon(int maSach, int soluong) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		Sach sach = (Sach) session.get(Sach.class, maSach);
		try {
			int soLuongTon = sach.getSoLuongTon() + soluong;
			sach.setSoLuongTon(soLuongTon);
			sach.setTrangThai(soLuongTon > 0 ? 1 : 0);
			session.update(sach);
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
	public List<Object[]> listTopSachKhuyenMai() {
		return sessionFactory.getCurrentSession()
				.createQuery("SELECT e.maSach, e.anhDaiDien, e.tenSach, e.giaKhuyenMai, e.giaBan "
						+ "FROM Sach e WHERE e.soLuongTon > 0 ORDER BY e.giaKhuyenMai DESC")
				.setMaxResults(5).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listTopSachBanChay() {
		return sessionFactory.getCurrentSession()
				.createQuery("SELECT e.maSach, e.anhDaiDien, e.tenSach, e.giaKhuyenMai, e.giaBan "
						+ "FROM Sach e WHERE e.soLuongTon > 0  ORDER BY e.luotMua DESC")
				.setMaxResults(5).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listTopSachMoi() {
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT SACH.ID_SACH, ANHDAIDIEN, TENSACH, GIAKM, GIABAN\r\n"
						+ "FROM  SACH, (SELECT ID_PN, ID_SACH FROM CT_PN) AS CT_PN, (SELECT ID_PN,NGAY FROM PHIEUNHAP ) AS PN\r\n"
						+ "WHERE SACH.SOLUONGTON > 0 AND SACH.ID_SACH = CT_PN.ID_SACH AND CT_PN.ID_PN = PN.ID_PN \r\n"
						+ "ORDER BY PN.NGAY DESC");
		return query.addScalar("ID_SACH", IntegerType.INSTANCE).addScalar("ANHDAIDIEN", StringType.INSTANCE)
				.addScalar("TENSACH", StringType.INSTANCE).addScalar("GIAKM", FloatType.INSTANCE)
				.addScalar("GIABAN", FloatType.INSTANCE).setMaxResults(5).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> cungTacGia(int maSach) {
		Sach sach = (Sach) sessionFactory.getCurrentSession().get(Sach.class, maSach);
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT SACH.ID_SACH, ANHDAIDIEN, TENSACH, GIAKM, GIABAN \r\n"
						+ "FROM (SELECT ID_SACH, ANHDAIDIEN, TENSACH, GIABAN,GIAKM, SOLUONGTON FROM SACH) AS SACH, CT_TG, (SELECT ID_TG FROM TACGIA) AS TACGIA\r\n"
						+ " WHERE SACH.SOLUONGTON > 0 AND SACH.ID_SACH = CT_TG.ID_SACH AND CT_TG.ID_TG = TACGIA.ID_TG AND TACGIA.ID_TG = (?)");
		return query.addScalar("ID_SACH", IntegerType.INSTANCE).addScalar("ANHDAIDIEN", StringType.INSTANCE)
				.addScalar("TENSACH", StringType.INSTANCE).addScalar("GIAKM", FloatType.INSTANCE)
				.addScalar("GIABAN", FloatType.INSTANCE).setParameter(0, sach.getDsTacGia().toArray()[0])
				.setMaxResults(5).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> cungTheLoai(int maSach) {

		Sach sach = (Sach) sessionFactory.getCurrentSession().get(Sach.class, maSach);
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT SACH.ID_SACH, ANHDAIDIEN, TENSACH, GIAKM, GIABAN \r\n"
						+ "FROM (SELECT ID_SACH, ANHDAIDIEN, TENSACH, GIABAN,GIAKM, SOLUONGTON FROM SACH) AS SACH, CT_TL, (SELECT ID_TL FROM THELOAI) AS THELOAI\r\n"
						+ "WHERE SACH.SOLUONGTON > 0 AND SACH.ID_SACH = CT_TL.ID_SACH AND CT_TL.ID_TL = THELOAI.ID_TL AND THELOAI.ID_TL = (?)");
		return query.addScalar("ID_SACH", IntegerType.INSTANCE).addScalar("ANHDAIDIEN", StringType.INSTANCE)
				.addScalar("TENSACH", StringType.INSTANCE).addScalar("GIAKM", FloatType.INSTANCE)
				.addScalar("GIABAN", FloatType.INSTANCE).setParameter(0, sach.getDsTheLoai().toArray()[0])
				.setMaxResults(5).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> sortDanhMuc(int maDanhMuc, float giaTienbd, float giaTienkt, int maTheLoai, int maNhaXuatBan, String timKiem) {
		SQLQuery query;
		String danhmuc = "";
		String tien = "";
		String theloai = "";
		String nhaxuatban = "";
		String timKiemsql = "";
		if(maDanhMuc != -1) {
			danhmuc = " DANHMUC.ID_DM = (:danhmuc) AND ";
		}
		if(giaTienbd != -1) {
			tien = " SACH.GIABAN BETWEEN (:bd) AND (:kt) OR SACH.GIAKM BETWEEN (:bd) AND (:kt) AND ";
		}
		if(maTheLoai != -1) {
			theloai = " THELOAI.ID_TL = (:tl) AND ";
		}
		if(maNhaXuatBan != -1) {
			nhaxuatban = " NHAXUATBAN.ID_NXB = (:nxb) AND ";
		}
		if(!timKiem.isEmpty()) {
			timKiemsql = "UPPER(SACH.TENSACH) LIKE (:tk) OR UPPER(SACH.TOMTAT) LIKE (:tk) OR UPPER(TACGIA.TENTG) LIKE  (:tk) AND ";
		}
		query = (SQLQuery) sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT SACH.ID_SACH, ANHDAIDIEN, TENSACH, GIAKM, GIABAN, LUOTMUA FROM SACH, (SELECT THELOAI.ID_DM ,THELOAI.ID_TL FROM THELOAI) AS THELOAI, (SELECT DANHMUC.ID_DM FROM DANHMUC) AS DANHMUC, (SELECT NHAXUATBAN.ID_NXB FROM NHAXUATBAN) AS NHAXUATBAN, CT_TL, CT_TG, TACGIA\r\n"
				+ " WHERE SACH.SOLUONGTON > 0 AND " + (giaTienbd!=-1 ? tien : " ").toString() + (maDanhMuc!=-1 ? danhmuc : " ").toString() + (maTheLoai!=-1 ? theloai : " ").toString() + (maNhaXuatBan!=-1 ? nhaxuatban : " ").toString() + (!timKiem.isEmpty()?timKiemsql:" ").toString() +"\r\n"
				+ "SACH.ID_SACH = CT_TL.ID_SACH AND CT_TL.ID_TL = THELOAI.ID_TL AND THELOAI.ID_DM = DANHMUC.ID_DM AND SACH.ID_NXB = NHAXUATBAN.ID_NXB AND SACH.ID_SACH = CT_TG.ID_SACH AND CT_TG.ID_TG = TACGIA.ID_TG");
		if(maDanhMuc != -1) {
			query.setParameter("danhmuc", maDanhMuc);
		}
		if(giaTienbd != -1) {
			query.setParameter("bd", giaTienbd);
			query.setParameter("kt", giaTienkt);
		}
		if(maTheLoai != -1) {
			query.setParameter("tl", maTheLoai);
		}
		if(maNhaXuatBan != -1) {
			query.setParameter("nxb", maNhaXuatBan);
		}
		if(!timKiem.isEmpty()) {
			query.setParameter("tk","%" + timKiem.toUpperCase().toString() + "%");
		}
		return query.addScalar("ID_SACH", IntegerType.INSTANCE).addScalar("ANHDAIDIEN", StringType.INSTANCE)
				.addScalar("TENSACH", StringType.INSTANCE).addScalar("GIAKM", FloatType.INSTANCE)
				.addScalar("GIABAN", FloatType.INSTANCE).addScalar("LUOTMUA", IntegerType.INSTANCE).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listSachNhanh() {
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT ID_SACH, ANHDAIDIEN, TENSACH, GIAKM, GIABAN, LUOTMUA FROM SACH WHERE SOLUONGTON > 0");
		return  query.addScalar("ID_SACH", IntegerType.INSTANCE).addScalar("ANHDAIDIEN", StringType.INSTANCE)
				.addScalar("TENSACH", StringType.INSTANCE).addScalar("GIAKM", FloatType.INSTANCE)
				.addScalar("GIABAN", FloatType.INSTANCE).addScalar("LUOTMUA", IntegerType.INSTANCE).list();
	}
}
