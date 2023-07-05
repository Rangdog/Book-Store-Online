package poly.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dao.DAOInterface.DanhMucInterface;
import poly.dao.DAOInterface.KhachHangInterface;
import poly.dao.DAOInterface.SachInterface;
import poly.entity.ChiTietGioHang;
import poly.entity.DanhMuc;
import poly.entity.KhachHang;

@Controller
public class ControllerMain {
	@Autowired
	private KhachHangInterface DAOKhachHang;
	@Autowired
	private DanhMucInterface DAODanhMuc;
	@Autowired
	private SachInterface DAOSach;
	
	@RequestMapping("homepage")
	public String viewHomePage(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		KhachHang khachHang = DAOKhachHang.findByUsername(authentication.getName());
		model.addAttribute("acc", khachHang);
		if (khachHang != null) {
			Set<ChiTietGioHang> gioHang = khachHang.getDsChiTietGioHang();
			model.addAttribute("soLuongChiTiet", gioHang == null ? 0 : gioHang.stream().mapToInt(e -> e.getSoLuong()).sum());
		}
		return "user/main";
	}

	@ModelAttribute("danhMucs")
	public List<DanhMuc> DanhMuc() {
		return DAODanhMuc.listDanhMuc();
	}

	@ModelAttribute("topSachKhuyenMais")
	public List<Object[]> TopSachKhuyenMai() {
		return DAOSach.listTopSachKhuyenMai();
	}

	@ModelAttribute("topSachBanChays")
	public List<Object[]> TopSachBanChay() {
		return DAOSach.listTopSachBanChay();
	}

	@ModelAttribute("topSachMois")
	public List<Object[]> TopSachMoi() {
		return DAOSach.listTopSachMoi();
	}
}
