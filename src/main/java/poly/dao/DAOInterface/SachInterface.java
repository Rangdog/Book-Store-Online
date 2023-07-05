package poly.dao.DAOInterface;

import java.util.List;
import java.util.Set;

import poly.entity.Sach;
import poly.entity.TacGia;
import poly.entity.TheLoai;

public interface SachInterface {
	List<Sach> listSach();
	Sach getSach(int maSach);
	Boolean insertSach(Sach sach, int maTacGia, int maTheLoai);
	Boolean updateSach(int maSach, String tenSach, int maNPH, int maNXB, 
			int soTrang, int kichThuoc, int trongLuong, String hinhThuc, String ngonNgu,
			int trangThai, Float giaBan, String tomTat, String photoPath);
	Boolean deleteSach(Sach sach, Set<TheLoai> dsTheLoai, Set<TacGia> dsTacGia);
	List<Sach> listSachKhongCoTrongKM(int maKhuyenMai);
	List<Sach> listSachKhongCoTrongPN(int maPhieuNhap);
	Boolean updateSoLuongTon(int maSach, int soluong);
	List<Object[]> listTopSachKhuyenMai();
	List<Object[]> listTopSachBanChay();
	List<Object[]> listTopSachMoi();
	List<Object[]> cungTacGia(int maSach);
	List<Object[]> cungTheLoai(int maSach);
	List<Object[]> sortDanhMuc(int maDanhMuc, float giaTienbd, float giaTienkt, int maTheLoai, int maNhaXuatBan, String timKiem);
	List<Object[]> listSachNhanh();
}
