package poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.dao.DAOInterface.ChiTietGioHangInterface;
import poly.dao.DAOInterface.KhachHangInterface;
import poly.dao.DAOInterface.SachInterface;
import poly.entity.ChiTietGioHang;
import poly.entity.KhachHang;
import poly.entity.Sach;

@Controller
public class ControllerTrinhBaySanPham {
	@Autowired
	private SachInterface DAOSach;
	@Autowired
	private ChiTietGioHangInterface DAOChiTietGioHang;
	@Autowired
	private KhachHangInterface DAOKhachHang;

	@RequestMapping("chitietsanpham")
	public String chiTietSanPham(@RequestParam("maSach") int maSach, ModelMap model) {
		Sach sach = DAOSach.getSach(maSach);
		List<Object[]> cungTacGia = DAOSach.cungTacGia(maSach);
		List<Object[]> cungTheLoai = DAOSach.cungTheLoai(maSach);
		model.addAttribute("sach", sach);
		model.addAttribute("cungTacGia", cungTacGia);
		model.addAttribute("cungTheLoai", cungTheLoai);
		return "user/ct_sp";
	}

	@RequestMapping("user/chitietsanpham")
	public String themVaoGioHang(@RequestParam("add") int maSach) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		KhachHang khachHang = DAOKhachHang.findByUsername(authentication.getName());

		if (khachHang.getDsChiTietGioHang().stream().anyMatch(e -> e.getSach().getMaSach() == maSach)) {
			DAOChiTietGioHang.updateSoLuong(khachHang.getMaKhachHang(), maSach, 1);
		} else {
			ChiTietGioHang ctgh = new ChiTietGioHang();
			ctgh.setKhachHang(khachHang);
			ctgh.setSach(DAOSach.getSach(maSach));
			ctgh.setSoLuong(1);
			DAOChiTietGioHang.insertCTGH(ctgh);
		}
		return "redirect:/user/cart.htm";
	}
}
