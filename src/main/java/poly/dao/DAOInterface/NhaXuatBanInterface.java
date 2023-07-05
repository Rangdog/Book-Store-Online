package poly.dao.DAOInterface;

import java.util.List;

import poly.entity.NhaXuatBan;

public interface NhaXuatBanInterface {
	List<NhaXuatBan> listNhaXuatBan();
	NhaXuatBan getNhaXuatBan(int id);
	Boolean insert(NhaXuatBan nhaXuatBan);
	Boolean update(int id, String tenNhaXuatBan);
	int deleteNhaXuatBan(int id);
}
