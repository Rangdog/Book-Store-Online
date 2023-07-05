package poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.dao.DAOInterface.DonHangInterface;
import poly.dao.DAOInterface.NhanVienInterface;
import poly.entity.DonHang;
import poly.entity.NhanVien;

@Controller
@RequestMapping("admin")
public class ControllerDonHang {
	@Autowired
	private DonHangInterface DAODonHang;
	@Autowired
	private NhanVienInterface DAONhanVien;

	@ModelAttribute("donHangs")
	public List<DonHang> DonHang() {
		return DAODonHang.listDonHang();
	}

	@RequestMapping(value = "", params = "view", method = RequestMethod.POST)
	public String xemDonHang(@RequestParam("view") int id, RedirectAttributes red) {
		DonHang donHang = DAODonHang.getDonHang(id);
		red.addFlashAttribute("donHang", donHang);
		red.addFlashAttribute("modal", "view-order");
		red.addFlashAttribute("page", "page-order");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "", params = "insert", method = RequestMethod.POST)
	public String addShipper(@RequestParam("insert") int id, RedirectAttributes red) {
		DonHang donHang = DAODonHang.getDonHang(id);
		List<NhanVien> nv = DAONhanVien.listNhanVienGiao();
		red.addFlashAttribute("nhanVienGiao", nv);
		red.addFlashAttribute("donHangDuyet", donHang);
		red.addFlashAttribute("modal", "insert-order");
		red.addFlashAttribute("page", "page-order");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "duyetdonhang")
	public String duyetDonHang(@RequestParam("id") int id, @RequestParam("maNhanVien") int maNhanVienGiao,
			RedirectAttributes red) {
		int maNhanVienDuyet = DAONhanVien.getNhanVienByTenTaiKhoan(
				SecurityContextHolder.getContext().getAuthentication().getName()).getMaNhanVien();
		DAODonHang.confirmDonHang(id, maNhanVienDuyet , maNhanVienGiao);
		red.addFlashAttribute("page", "page-order");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "", params = "huydonhang")
	public String xacNhanHuyDonHang(@RequestParam("huydonhang") int id, RedirectAttributes red) {
		red.addFlashAttribute("delete", "BẠN CÓ MUỐN HỦY ĐƠN HÀNG NÀY KHÔNG?");
		red.addFlashAttribute("act", "huydonhang");
		red.addFlashAttribute("idDelete", id);
		red.addFlashAttribute("page", "page-order");
		red.addFlashAttribute("modal", "delete");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "huydonhang")
	public String huyDonHang(@RequestParam("id") int id, RedirectAttributes red) {
		int maNhanVienDuyet = DAONhanVien.getNhanVienByTenTaiKhoan(
				SecurityContextHolder.getContext().getAuthentication().getName()).getMaNhanVien();
		if (DAODonHang.cancelDonHang(id, maNhanVienDuyet) == true) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thành Công");
			red.addFlashAttribute("message", "Hủy thành công");
			red.addFlashAttribute("type", "success");
			red.addFlashAttribute("duration", 5000);
		} else {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Hủy thất bại");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
		}
		red.addFlashAttribute("page", "page-order");
		return "redirect:/admin.htm";
	}
}
