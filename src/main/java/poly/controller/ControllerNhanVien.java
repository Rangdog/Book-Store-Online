package poly.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.bean.Uploader;
import poly.dao.DAOInterface.NhanVienInterface;
import poly.dao.DAOInterface.TaiKhoanInterface;
import poly.entity.NhanVien;
import poly.entity.TaiKhoan;

@Controller
@RequestMapping("admin")
public class ControllerNhanVien {
	@Autowired
	private TaiKhoanInterface DAOTaiKhoan;
	@Autowired
	private NhanVienInterface DAONhanVien;

	@RequestMapping(value = "themnhanvien", method = RequestMethod.POST)
	public String themNhanVien(@RequestParam("anhDaiDien") MultipartFile anhDaiDien, @RequestParam("ho") String ho,
			@RequestParam("ten") String ten, @RequestParam("gioiTinh") String gioiTinh,
			@RequestParam("ngaySinh") String ngaySinh, @RequestParam("diaChi") String diaChi,
			@RequestParam("sDT") String sDT, @RequestParam("cmnd") String cmnd, @RequestParam("luong") float luong,
			RedirectAttributes red, HttpServletRequest req) throws ParseException {
		NhanVien e = new NhanVien();
		e.setTrangThaiXoa(false);
		if (anhDaiDien != null) e.setAnhDaiDien(Uploader.upload(anhDaiDien, "staff", req.getServletContext()));
		if (!ngaySinh.isEmpty()) e.setNgaySinh(new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh));
		e.setHo(ho);
		e.setTen(ten);
		e.setGioiTinh(Boolean.parseBoolean(gioiTinh));
		e.setDiaChi(diaChi);
		e.setsDT(sDT);
		e.setCmnd(cmnd);
		e.setLuong(luong);
		DAONhanVien.insertNhanVien(e);
		red.addFlashAttribute("page", "page-staff");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "", params = "xemnhanvien")
	public String xemNhanVien(@RequestParam("xemnhanvien") int id, RedirectAttributes red) {
		NhanVien e = DAONhanVien.getNhanVien(id);
		red.addFlashAttribute("nhanVien", e);
		red.addFlashAttribute("page", "page-staff");
		red.addFlashAttribute("modal", "update-staff");
		return "redirect:/admin.htm";
	}

	@RequestMapping("suanhanvien")
	public String suaNhanVien(@RequestParam("id") int id, @RequestParam("anhDaiDien") MultipartFile anhDaiDien,
			@RequestParam("ho") String ho, @RequestParam("ten") String ten, @RequestParam("gioiTinh") String gioiTinh,
			@RequestParam("ngaySinh") String ngaySinh, @RequestParam("diaChi") String diaChi,
			@RequestParam("sDT") String sDT, @RequestParam("cmnd") String cmnd, @RequestParam("luong") float luong,
			@RequestParam("trangThaiXoa") String trangThaiXoa, RedirectAttributes red, HttpServletRequest req)
			throws ParseException {
		DAONhanVien.updateNhanVien(id, Uploader.upload(anhDaiDien, "staff", req.getServletContext()), ho, ten,
				Boolean.parseBoolean(gioiTinh), ngaySinh.isEmpty() ? null : new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh),
				diaChi, sDT, cmnd, luong, Boolean.parseBoolean(trangThaiXoa));
		red.addFlashAttribute("page", "page-staff");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "", params = "xoanhanvien")
	public String xoadanhmuc(@RequestParam("xoanhanvien") int id, RedirectAttributes red) {
		red.addFlashAttribute("delete", "BẠN CÓ MUỐN XÓA NHÂN VIÊN NÀY KHÔNG?");
		red.addFlashAttribute("act", "xoanhanvien");
		red.addFlashAttribute("idDelete", id);
		red.addFlashAttribute("page", "page-staff");
		red.addFlashAttribute("modal", "delete");
		return "redirect:/admin.htm";
	}

	@RequestMapping("xoanhanvien")
	public String deletedanhmuc(@RequestParam("id") int id, RedirectAttributes red) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (DAONhanVien.getNhanVienByTenTaiKhoan(authentication.getName()).getMaNhanVien() == id) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Bạn không thể xóa bản thân");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
		} else {
			if (DAONhanVien.deleteNhanVien(id) == 0) {
				red.addFlashAttribute("toaston", "1");
				red.addFlashAttribute("title", "Thành Công");
				red.addFlashAttribute("message", "Xóa thành công");
				red.addFlashAttribute("type", "success");
				red.addFlashAttribute("duration", 5000);
			} else {
				red.addFlashAttribute("toaston", "1");
				red.addFlashAttribute("title", "Thất Bại");
				red.addFlashAttribute("message", "Xóa thất bại");
				red.addFlashAttribute("type", "error");
				red.addFlashAttribute("duration", 5000);
			}
		}
		red.addFlashAttribute("page", "page-staff");
		return "redirect:/admin.htm";
	}
	
	@RequestMapping(value = "", params = "addAccount")
	public String xemTaoTaiKhoan(RedirectAttributes red) {
		red.addFlashAttribute("nhanVienChuaCoTK",DAONhanVien.listNhanVienChuaCoTaiKhoan());
		red.addFlashAttribute("modal", "add-staff");
		red.addFlashAttribute("page", "page-staff");
		return "redirect:/admin.htm";
	}
	
	@RequestMapping("taotaikhoan")
	public String taoTaiKhoan(@RequestParam("maNhanVien") int maNhanVien, @RequestParam("tenTaiKhoan") String tenTaiKhoan,
			@RequestParam("matKhau") String matKhau, @RequestParam("role")int role,
			@RequestParam("xacminh") String matKhauXacMinh, RedirectAttributes red) {
		if(DAOTaiKhoan.checkExistTenTaiKhoan(tenTaiKhoan)) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Tên tài Khoản bị trùng");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-staff");
			return "redirect:/admin.htm";
		}
		if(!matKhau.equals(matKhauXacMinh)) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Mật khẩu không khớp");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-staff");
			return "redirect:/admin.htm";
		}
		TaiKhoan tk = new TaiKhoan();
		tk.setTenTaiKhoan(tenTaiKhoan);
		tk.setMatKhau(matKhau);
		if(role == 0) {
			tk.setQuyen("ROLE_ADMIN");
		}
		else {
			tk.setQuyen("ROLE_SHIPPER");
		}
		tk.setTrangThai(true);
		DAOTaiKhoan.insertTaiKhoan(tk);
		if(!DAONhanVien.updatetkNhanVien(tk, maNhanVien)) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Tạo tài khoản thất bại");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-staff");
			return "redirect:/admin.htm";
		}
		red.addFlashAttribute("toaston", "1");
		red.addFlashAttribute("title", "Thành Công");
		red.addFlashAttribute("message", "Tạo tài khoản thành công");
		red.addFlashAttribute("type", "success");
		red.addFlashAttribute("duration", 5000);
		red.addFlashAttribute("page", "page-staff");
		return "redirect:/admin.htm";
	}
	@RequestMapping(value = "doimk")
	public String doimk(@RequestParam("matKhau") String matKhau, @RequestParam("xacminh") String matKhauXacMinh,RedirectAttributes red) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!matKhau.equals(matKhauXacMinh)) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Mật khẩu không khớp");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-staff");
			return "redirect:/admin.htm";
		}
		if(DAOTaiKhoan.doiMatKhau(authentication.getName(), matKhau))
		red.addFlashAttribute("toaston", "1");
		red.addFlashAttribute("title", "Thành Công");
		red.addFlashAttribute("message", "Đổi mật khẩu thành công");
		red.addFlashAttribute("type", "success");
		red.addFlashAttribute("duration", 5000);
		return "redirect:/logout.htm";
	}
}
