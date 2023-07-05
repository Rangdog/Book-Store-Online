package poly.dao.DAOInterface;

import java.sql.Date;
import java.util.List;


import poly.entity.KhuyenMai;


public interface KhuyenMaiInterface {
	List<KhuyenMai> listKhuyenMai();
	Boolean insertKhuyenMai(KhuyenMai khuyenMai);
	Boolean updateKhuyenMai(int maKhuyenMai, Date ngayBatDau, Date ngayKetThuc, Float phanTram);
	Boolean deleteKhuyenMai(int maKhuyenMai);
	KhuyenMai getKhuyenMai(int maKM);
	Boolean addSach(int maKhuyenMai,int maSach);
	Boolean deleteSach(int maKhuyenMai,int maSach);
}
