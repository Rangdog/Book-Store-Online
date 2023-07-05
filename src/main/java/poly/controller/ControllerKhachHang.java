package poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.dao.DAOInterface.KhachHangInterface;
import poly.entity.KhachHang;

@Controller
@RequestMapping("admin")
public class ControllerKhachHang {
	@Autowired
	private KhachHangInterface DAOKhachHang;
	
	@RequestMapping(value = "", params = "xemkhachhang")
	public String xemKhachHang(@RequestParam("xemkhachhang") int id, RedirectAttributes red) {
		KhachHang e = DAOKhachHang.getKhachHang(id);
		red.addFlashAttribute("khachHang", e);
		red.addFlashAttribute("page", "page-customer");
		red.addFlashAttribute("modal", "view-customer");
		return "redirect:/admin.htm";
	}
	
	@RequestMapping(value = "", params = "khoakhachhang")
	public String khoaKhachHang(@RequestParam("khoakhachhang") int id, RedirectAttributes red) {
		DAOKhachHang.blockKhachHang(id);
		red.addFlashAttribute("page", "page-customer");
		return "redirect:/admin.htm";
	}
}
