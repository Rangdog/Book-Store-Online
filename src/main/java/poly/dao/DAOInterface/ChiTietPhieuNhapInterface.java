package poly.dao.DAOInterface;

import java.util.List;

import poly.entity.ChiTietPhieuNhap;

public interface ChiTietPhieuNhapInterface {
	List<ChiTietPhieuNhap> listChiTietPhieuNhap();
	Boolean insertChiTietPhieuNhap(ChiTietPhieuNhap ctpn);
	Boolean updateChiTietPhieuNhap(int maPhieuNhap, int maSach, int soLuong, float giaGoc);
	Boolean deleteChiTietPhieuNhap(int maPhieuNhap, int maSach);
	ChiTietPhieuNhap getChiTietPhieuNhap(int maPhieuNhap, int maSach);
	Boolean deleteNhieuSach(int maPhieuNhap, List<Integer> maSach);
}
