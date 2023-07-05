package poly.dao.DAOInterface;

import java.util.List;
import poly.entity.DanhMuc;

public interface DanhMucInterface {
	List<DanhMuc> listDanhMuc();
	DanhMuc getDanhMuc(int maDanhMuc);
	Boolean insertDanhMuc(DanhMuc danhMuc);
	Boolean updateDanhMuc(int maDanhMuc, String tenDanhMuc);
	int deleteDanhMuc(int maDanhMuc);
}
