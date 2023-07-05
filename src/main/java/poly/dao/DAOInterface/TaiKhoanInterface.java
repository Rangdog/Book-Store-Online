package poly.dao.DAOInterface;

import poly.entity.TaiKhoan;

public interface TaiKhoanInterface {
	Boolean insertTaiKhoan(TaiKhoan taiKhoan);
	TaiKhoan getTaiKhoan(String tenTaiKhoan);
	Boolean checkExistTenTaiKhoan(String tenTaiKhoan);
	Boolean doiMatKhau(String tenTaiKhoan, String matKhau);
	Boolean deleteTaiKhoan(String tenTaiKhoan);
}
