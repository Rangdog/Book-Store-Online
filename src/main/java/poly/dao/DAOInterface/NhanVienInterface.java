package poly.dao.DAOInterface;

import java.util.Date;
import java.util.List;

import poly.entity.TaiKhoan;
import poly.entity.NhanVien;

public interface NhanVienInterface {
	List<NhanVien> listNhanVien();
	NhanVien getNhanVien(int maNhanVien);
	Boolean insertNhanVien(NhanVien nhanVien);
	Boolean updateNhanVien(int maNhanVien, String anhDaiDien, String ho, String ten, Boolean gioiTinh, Date ngaySinh, 
			String diaChi, String sDT, String cmnd, float luong, Boolean trangThaiXoa);
	int deleteNhanVien(int maNhanVien);
	NhanVien getNhanVienByTenTaiKhoan(String tenTaiKhoan);
	List<NhanVien> listNhanVienGiao();
	List<NhanVien> listNhanVienChuaCoTaiKhoan();
	Boolean updatetkNhanVien(TaiKhoan tk, int maNhanVien);
}
