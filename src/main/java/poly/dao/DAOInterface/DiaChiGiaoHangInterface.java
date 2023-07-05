package poly.dao.DAOInterface;

import java.util.List;

import poly.entity.DiaChiGiaoHang;

public interface DiaChiGiaoHangInterface {
	List<DiaChiGiaoHang> listDCGH(int maKhachHang);
	Boolean insertDCGH(DiaChiGiaoHang DCGH);
	Boolean updateDCGH(int maDiaChi, String tenNN, String sdtNN, String diaChi, int trangThai);
	DiaChiGiaoHang getDCGH(int maDiaChi);
	Boolean deleteDCGH(int maDiaChi);
}
