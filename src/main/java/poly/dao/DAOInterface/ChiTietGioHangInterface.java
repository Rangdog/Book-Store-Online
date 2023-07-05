package poly.dao.DAOInterface;

import java.util.List;

import poly.entity.ChiTietGioHang;

public interface ChiTietGioHangInterface {
	List<ChiTietGioHang> listCTGH();
	Boolean insertCTGH(ChiTietGioHang CTGH);
	Boolean checkExistCTGH(int maKhachHang, int maSach);
	ChiTietGioHang getChiTietGioHang(int maKhachHang, int maSach);
	Boolean deleteChiTietGioHang(int maKhachHang, int maSach);
	Boolean updateSoLuong(int maKhachHang, int maSach, int soLuong);
}
