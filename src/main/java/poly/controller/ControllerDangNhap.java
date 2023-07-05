package poly.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.bean.Mailer;
import poly.dao.DAOInterface.KhachHangInterface;
import poly.entity.KhachHang;

@Controller
public class ControllerDangNhap {
	@Autowired
	private Mailer mailer;
	@Autowired
	private KhachHangInterface DAOKhachHang;

	@RequestMapping("login")
	public String loginfailed(Model model, @RequestParam(value = "error", required = false) String error) {
		model.addAttribute("error", error);
		return "user/dang_nhap";
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login.htm?error=logout";
	}

	@RequestMapping("register")
	public String register(Model model) {
		model.addAttribute("flag", "register-form");
		return "user/dang_nhap";
	}

	@RequestMapping("restore")
	public String restorePassword(ModelMap model, @RequestParam("confirm-email") String email,
			HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		mailer.sendConfirmationPassword(email, mailer.getSiteURL(request));
		return "user/dang_nhap";
	}
	
	@RequestMapping("confirm")
	public String viewConfirmForm(ModelMap model, @RequestParam("email") String email, @RequestParam("limit") String limit) 
			throws ParseException {
		if (new SimpleDateFormat("dd/MM/yyyy").parse(limit).before(Calendar.getInstance().getTime())) {
			model.addAttribute("erMatKhauXacNhan", "Thời gian xác nhận đã quá hạn");
		} else {
			model.addAttribute("email", email);
		}
		return "user/xac_nhan_mat_khau";
	}
	
	@RequestMapping("confirmpassword")
	public String confirmPassword(ModelMap model, @RequestParam("xacnhan") String email, 
			@RequestParam("matkhaumoi") String matKhauMoi, @RequestParam("matkhauxacnhan") String matKhauXacNhan) {
		boolean isError = false;
		if (!matKhauMoi.equals(matKhauXacNhan)) {
			isError = true;
			model.addAttribute("erMatKhauXacNhan", "Mật khẩu xác nhận không chính xác");
		}
		KhachHang khachHang = DAOKhachHang.findByEmail(email);
		if (!isError && khachHang == null) {
			isError = true;
			model.addAttribute("erMatKhauXacNhan", "Email không tồn tại trong hệ thống");
		} else {
			DAOKhachHang.changePassword(khachHang.getMaKhachHang(), matKhauMoi);
		}
		
		if (isError) {
			model.addAttribute("matKhauMoi", matKhauMoi);
			model.addAttribute("matKhauXacNhan", matKhauXacNhan);
			return "user/xac_nhan_mat_khau";
		}
		
		return "user/dang_nhap";
	}
}
