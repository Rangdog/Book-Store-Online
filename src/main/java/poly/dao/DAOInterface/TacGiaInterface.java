package poly.dao.DAOInterface;

import java.util.List;

import poly.entity.TacGia;

public interface TacGiaInterface {
	List<TacGia> listTacGia();
	TacGia getTacGia(int maTacGia);
	Boolean insertTacGia(TacGia tacGia);
	Boolean updateTacGia(int maTacGia, String tenTacGia);
	int deleteTacGia(int maTacGia);
	Boolean addSach(int maTacGia, int maSach);
	Boolean deleteSach(int maTacGia, int maSach);
}
