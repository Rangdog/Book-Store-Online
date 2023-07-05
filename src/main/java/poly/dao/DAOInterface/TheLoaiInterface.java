package poly.dao.DAOInterface;

import java.util.List;
import poly.entity.TheLoai;

public interface TheLoaiInterface {
	List<TheLoai> listTheLoai();
	TheLoai getTheLoai(int id);
	Boolean insertTheLoai(TheLoai theLoai);
	Boolean updateTheLoai(int id, int maDanhMuc, String tenTheLoai);
	int deleteTheLoai(int maTheLoai);
	Boolean addSach(int maTheLoai ,int maSach);
	Boolean deleteSach(int maTheLoai, int maSach);
	List<TheLoai> listTheLoaitheoDanhMuc(int maDanhMuc);
}
