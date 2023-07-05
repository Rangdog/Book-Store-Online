package poly.dao.DAOInterface;

import java.util.Date;
import java.util.List;

import poly.entity.KhachHang;

public interface KhachHangInterface {
	List<KhachHang> listKhachHang();
	KhachHang getKhachHang(int maKhachHang);
	Boolean insertKhachHang(KhachHang khachHang);
    KhachHang findByVerificationCode(String code);
    Boolean confirmVerification(int maKhachHang);
    Boolean blockKhachHang(int maKhachHang);
    KhachHang findByUsername(String tenTaiKhoan);
    Boolean updateKhachHang(int maKhachHang, String ho, String ten, Boolean gioiTinh, Date ngaySinh, String anhDaiDien);
    Boolean renewVerificationEmail(int maKhachHang, String code, Date time);
    Boolean changeEmail(int maKhachHang, String email);
    Boolean changePassword(int maKhachHang, String matKhau);
    Boolean checkExistEmail(String email);
    KhachHang findByEmail(String email);
}
