package poly.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import poly.bean.Mailer;
import poly.bean.Uploader;
import poly.dao.DAOInterface.KhachHangInterface;
import poly.entity.KhachHang;

@Controller
@RequestMapping("user")
public class ControllerThongTinTaiKhoan {
	@Autowired
	private KhachHangInterface DAOKhachHang;
	@Autowired
	Mailer mailer;
	
	@RequestMapping(value = "taikhoan", params = "thongtintaikhoan")
	public String viewThongTinTaiKhoan(ModelMap model) {
		String tenTaiKhoan = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("khachHang", DAOKhachHang.findByUsername(tenTaiKhoan));
		return "user/tai_khoan";
	}
	
	@RequestMapping(value = "taikhoan", params = "capnhatthongtin")
	public String updateThongTinTaiKhoan(@RequestParam("capnhatthongtin") int maKhachHang, @RequestParam("ho") String ho,
			@RequestParam("ten") String ten, @RequestParam("gioiTinh") String gioiTinh, @RequestParam("ngaySinh") String ngaySinh, 
			@RequestParam("anhDaiDien") MultipartFile anhDaiDien, HttpServletRequest req) throws ParseException {
		DAOKhachHang.updateKhachHang(maKhachHang, ho, ten, Boolean.parseBoolean(gioiTinh), 
				ngaySinh != null ? new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh) : null, 
						Uploader.upload(anhDaiDien, "customer", req.getServletContext()));
		return "redirect:/user/taikhoan.htm?thongtintaikhoan";
	}
	
	@RequestMapping(value = "taikhoan", params = "xacthucemail")
	public String comfirmThongTinEmail(@RequestParam("xacthucemail") int maKhachHang, HttpServletRequest request)
			throws UnsupportedEncodingException, MessagingException {
		mailer.renewVerificationEmail(maKhachHang);
		mailer.sendVerificationEmail(DAOKhachHang.getKhachHang(maKhachHang), mailer.getSiteURL(request));
		return "redirect:/user/taikhoan.htm?thongtintaikhoan";
	}
	
	@RequestMapping(value = "changeemail", params = "thaydoiemail")
	public String changeEmail(ModelMap model, @RequestParam("thaydoiemail") int maKhachHang) {
		model.addAttribute("id", maKhachHang);
		return "user/doi_email";
	}
	
	@RequestMapping(value = "changeemail", params = "xacnhan")
	public String confirmEmail(ModelMap model, @RequestParam("xacnhan") int maKhachHang, @RequestParam("email") String email, 
			HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		boolean isError = false;
		if (email.isEmpty()) {
			isError = true;
			model.addAttribute("erEmail", "Email không thể trống");
		}
		if (!isError && DAOKhachHang.checkExistEmail(email)) {
			isError = true;
			model.addAttribute("erEmail", "Email đã tồn tại");
		}
		if (isError) {
			model.addAttribute("email", email);
			model.addAttribute("id", maKhachHang);
			return "user/doi_email";
		}
		
		DAOKhachHang.changeEmail(maKhachHang, email);
		mailer.renewVerificationEmail(maKhachHang);
		mailer.sendVerificationEmail(DAOKhachHang.getKhachHang(maKhachHang), mailer.getSiteURL(request));
		return "redirect:/user/taikhoan.htm?thongtintaikhoan";
	}
	
	@RequestMapping(value = "changeemail", params = "huybo")
	public String cancelEmail(@RequestParam("huybo") int maKhachHang) {
		return "redirect:/user/taikhoan.htm?thongtintaikhoan";
	}
	
	@RequestMapping(value = "taikhoan", params = "doimatkhau")
	public String changePassword(ModelMap model) {
		String tenTaiKhoan = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("id", DAOKhachHang.findByUsername(tenTaiKhoan).getMaKhachHang());
		return "user/doi_mat_khau";
	}
	
	@RequestMapping(value = "changepassword", params = "xacnhan")
	public String confirmPassword(ModelMap model, @RequestParam("xacnhan") int maKhachHang, 
			@RequestParam("matkhauhientai") String matKhauHienTai, @RequestParam("matkhaumoi") String matKhauMoi, 
			@RequestParam("matkhauxacnhan") String matKhauXacNhan) {
		KhachHang khachHang = DAOKhachHang.getKhachHang(maKhachHang);
		
		boolean isError = false;
		if (!khachHang.getTaiKhoan().getMatKhau().equals(matKhauHienTai)) {
			isError = true;
			model.addAttribute("erMatKhauHienTai", "Mật khẩu hiện tại không chính xác");
		}
		if (!isError && matKhauMoi.isEmpty()) {
			isError = true;
			model.addAttribute("erMatKhauMoi", "Mật khẩu mới không thể trống");
		}
		else if (!isError && matKhauHienTai.equals(matKhauMoi)) {
			isError = true;
			model.addAttribute("erMatKhauMoi", "Mật khẩu mới phải khác mật khẩu hiện tại");
		}
		if (!isError && matKhauXacNhan.isEmpty()) {
			isError = true;
			model.addAttribute("erMatKhauXacNhan", "Mật khẩu mới không thể trống");
		}
		else if (!isError && !matKhauMoi.equals(matKhauXacNhan)) {
			isError = true;
			model.addAttribute("erMatKhauXacNhan", "Mật khẩu xác nhận không chính xác");
		}
		if (isError) {
			model.addAttribute("matKhauHienTai", matKhauHienTai);
			model.addAttribute("matKhauMoi", matKhauMoi);
			model.addAttribute("matKhauXacNhan", matKhauXacNhan);
			model.addAttribute("id", maKhachHang);
			return "user/doi_mat_khau";
		}
		
		DAOKhachHang.changePassword(maKhachHang, matKhauMoi);
		return "redirect:/homepage.htm";
	}
	
	@RequestMapping(value = "changepassword", params = "huybo")
	public String cancelPassword() {
		return "redirect:/homepage.htm";
	}
}
