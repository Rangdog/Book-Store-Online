package poly.dao.DAOInterface;

import java.util.List;

import poly.entity.PhieuNhap;

public interface PhieuNhapInterface {
	List<PhieuNhap> listPhieuNhap();
	Boolean insertPhieuNhap(PhieuNhap phieuNhap);
	Boolean deletePhieuNhap(int maPhieuNhap);
	PhieuNhap getPhieuNhap(int maPhieuNhap);
}
