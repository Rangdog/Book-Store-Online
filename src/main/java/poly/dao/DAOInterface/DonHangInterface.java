package poly.dao.DAOInterface;

import java.util.List;

import poly.entity.DonHang;

public interface DonHangInterface {
	List<DonHang> listDonHang();
	DonHang getDonHang(int maDonHang);
	Boolean insertDonHang(DonHang donHang);
	List<DonHang> listDonHangShipper(int maShipper);
	Boolean updateTrangThai(int maDonHang, int trangThai);
	List<DonHang> listDonHangTheoTrangThai(int trangThai);
	public Boolean confirmDonHang(int maDonHang, int maNhanVienDuyet, int maNhanVienGiao);
	public Boolean cancelDonHang(int maDonHang, int maNhanVienDuyet);
	public Boolean refundDonHang(int maDonHang, int maNhanVienGiao, String lyDo);
}
