package poly.dao.DAOInterface;
import java.util.List;

import poly.entity.NhaPhatHanh;
public interface NhaPhatHanhInterface {
	List<NhaPhatHanh> listNhaPhatHanh();
	NhaPhatHanh getNhaPhatHanh(int id);
	Boolean insert(NhaPhatHanh nhaPhatHanh);
	Boolean update(int id, String tenNhaPhatHanh);
	int deleteNhaPhatHanh(int id);
}
