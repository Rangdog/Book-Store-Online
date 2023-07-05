package poly.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import poly.bean.Mailer;
import poly.dao.DAOInterface.KhachHangInterface;
import poly.dao.DAOInterface.TaiKhoanInterface;
import poly.entity.KhachHang;
import poly.entity.TaiKhoan;

@Controller
public class ControllerDangKy {
	@Autowired
	private TaiKhoanInterface DAOTaiKhoan;
	@Autowired
	private KhachHangInterface DAOKhachHang;
	@Autowired
	Mailer mailer;

	@RequestMapping(value = "process_register", method = RequestMethod.POST)
	public String processRegister(ModelMap model, @RequestParam("tenTaiKhoan") String tenTaiKhoan, 
			@RequestParam("matKhau") String matKhau, @RequestParam("email") String email, 
			@RequestParam("matKhauXacNhan") String matKhauXacNhan, HttpServletRequest request)
			throws UnsupportedEncodingException, MessagingException {
		boolean isError = false;
		if (DAOTaiKhoan.getTaiKhoan(tenTaiKhoan) != null) {
			isError = true;
			model.addAttribute("erDangKy", "Tên đăng nhập đã tồn tại");
		}
		
		if (!isError && DAOKhachHang.checkExistEmail(email)) {
			isError = true;
			model.addAttribute("erDangKy", "Email đã tồn tại");
		}
		
		if (!isError && !matKhau.equals(matKhauXacNhan)) {
			isError = true;
			model.addAttribute("erDangKy", "Mật khẩu xác nhận không chính xác");
		}
		
		if (isError) {
			model.addAttribute("tenTaiKhoan", tenTaiKhoan);
			model.addAttribute("matKhau", matKhau);
			model.addAttribute("email", email);
			model.addAttribute("matKhauXacNhan", matKhauXacNhan);
			model.addAttribute("flag", "register-form");
			return "user/dang_nhap";
		}
		
		TaiKhoan taiKhoan = new TaiKhoan();
		taiKhoan.setTenTaiKhoan(tenTaiKhoan);
		taiKhoan.setMatKhau(matKhau);
		taiKhoan.setTrangThai(true);
		taiKhoan.setQuyen("ROLE_USER");
		DAOTaiKhoan.insertTaiKhoan(taiKhoan);
		
		KhachHang khachHang = new KhachHang();
		khachHang.setEmail(email);
		khachHang.setTrangThaiEmail(false);
		khachHang.setTaiKhoan(DAOTaiKhoan.getTaiKhoan(tenTaiKhoan));
		mailer.register(khachHang, mailer.getSiteURL(request));
		return "user/dang_nhap";
	}


	@RequestMapping("/verify")
	public String verifyUser(@RequestParam("code") String code) {
		return "user/" + (mailer.verify(code) ? "verify_success" : "verify_fail");
	}
}
