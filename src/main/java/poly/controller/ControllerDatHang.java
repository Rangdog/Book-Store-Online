package poly.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import poly.dao.DAOInterface.ChiTietDonHangInterface;
import poly.dao.DAOInterface.ChiTietGioHangInterface;
import poly.dao.DAOInterface.DiaChiGiaoHangInterface;
import poly.dao.DAOInterface.DonHangInterface;
import poly.dao.DAOInterface.KhachHangInterface;
import poly.dao.DAOInterface.SachInterface;
import poly.entity.KhachHang;
import poly.entity.Sach;
import poly.entity.ChiTietDonHang;
import poly.entity.ChiTietGioHang;
import poly.entity.DiaChiGiaoHang;
import poly.entity.DonHang;

@Controller
@RequestMapping("user")
public class ControllerDatHang {
	@Autowired
	private KhachHangInterface DAOKhachHang;
	@Autowired
	private SachInterface DAOSach;
	@Autowired
	private DonHangInterface DAODonHang;
	@Autowired
	private DiaChiGiaoHangInterface DAODiaChiGiaoHang;
	@Autowired
	private ChiTietDonHangInterface DAOChiTietDonHang;
	@Autowired
	private ChiTietGioHangInterface DAOChiTietGioHang;

	@RequestMapping(value = "order", params = "muaNgay")
	public String muaNgay(ModelMap model, @RequestParam("muaNgay") int maSach) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		KhachHang khachHang = DAOKhachHang.findByUsername(authentication.getName());
		if (!khachHang.getTrangThaiEmail()) return "redirect:/user/taikhoan.htm?thongtintaikhoan";
		if (khachHang.getDsDiaChiGiaoHang().isEmpty()) return "redirect:/address.htm";

		Set<DiaChiGiaoHang> diaChis = khachHang.getDsDiaChiGiaoHang();
		model.addAttribute("diaChis", diaChis);
		DiaChiGiaoHang diaChi = (DiaChiGiaoHang) diaChis.stream().filter(e -> e.getTrangThai() == 1).toList().get(0);
		model.addAttribute("diaChi", diaChi);

		List<ChiTietGioHang> sanPhams = new ArrayList<>();
		ChiTietGioHang chiTietGioHang = new ChiTietGioHang();
		chiTietGioHang.setKhachHang(khachHang);
		Sach sach = DAOSach.getSach(maSach);
		chiTietGioHang.setSach(sach);
		chiTietGioHang.setSoLuong(1);
		sanPhams.add(chiTietGioHang);
		model.addAttribute("sanPhams", sanPhams);
		model.addAttribute("tongTien", sach.getGiaSach());
		model.addAttribute("phiVanChuyen", 32000);

		return "user/thanh_toan";
	}

	@RequestMapping(value = "payment", method = RequestMethod.POST)
	public String thanhToan(@RequestParam("id") int maDiaChi, @RequestParam("phiVanChuyen") float phiVanChuyen,
			@RequestParam("sanPham[]") int[] sanPhams, @RequestParam("soLuong[]") int[] soLuongs,
			@RequestParam("giaTien[]") float[] giaTiens) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		KhachHang khachHang = DAOKhachHang.findByUsername(authentication.getName());
		DonHang donHang = new DonHang();
		donHang.setDiaChiGiaoHang(DAODiaChiGiaoHang.getDCGH(maDiaChi));
		donHang.setNgay(Calendar.getInstance().getTime());
		donHang.setPhiVanChuyen(phiVanChuyen);
		donHang.setTrangThai(0);
		DAODonHang.insertDonHang(donHang);

		for (int i = 0; i < sanPhams.length; i++) {
			ChiTietDonHang e = new ChiTietDonHang();
			e.setDonHang(donHang);
			Sach sach = DAOSach.getSach(sanPhams[i]);
			e.setSach(sach);
			e.setSoLuong(soLuongs[i]);
			e.setGiaTien(giaTiens[i]);
			DAOChiTietDonHang.insertChiTietDonHang(e);
			// LÀM TRỐNG GIỎ HÀNG KHI ĐẶT HÀNG BẰNG GIỎ HÀNG.
			if (sanPhams.length > 2) DAOChiTietGioHang.deleteChiTietGioHang(khachHang.getMaKhachHang(), sach.getMaSach());
		}

		// REDIRECT VỀ ĐƠN HÀNG CỦA TÔI.
		return "redirect:/user/myorder.htm";
	}

	@RequestMapping("cart")
	public String viewCartPage(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Set<ChiTietGioHang> gioHang = DAOKhachHang.findByUsername(authentication.getName()).getDsChiTietGioHang();
		model.addAttribute("gioHang", gioHang);
		model.addAttribute("tongTien", gioHang.stream().mapToDouble(e -> e.getSoLuong() * e.getSach().getGiaSach()).sum());
		return "user/gio_hang";
	}

	@RequestMapping(value = "cart", params = "datHang")
	public String datHang(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		KhachHang khachHang = DAOKhachHang.findByUsername(authentication.getName());
		if (!khachHang.getTrangThaiEmail()) return "redirect:/user/taikhoan.htm?thongtintaikhoan";
		if (khachHang.getDsDiaChiGiaoHang().isEmpty()) return "redirect:/address.htm";

		Set<DiaChiGiaoHang> diaChis = khachHang.getDsDiaChiGiaoHang();
		model.addAttribute("diaChis", diaChis);
		DiaChiGiaoHang diaChi = (DiaChiGiaoHang) diaChis.stream().filter(e -> e.getTrangThai() == 1).toList().get(0);
		model.addAttribute("diaChi", diaChi);

		Set<ChiTietGioHang> sanPhams = khachHang.getDsChiTietGioHang();
		model.addAttribute("sanPhams", sanPhams);
		model.addAttribute("tongTien", sanPhams.stream().mapToDouble(e -> e.getSoLuong() * e.getSach().getGiaSach()).sum());
		model.addAttribute("phiVanChuyen", 32000);

		return "user/thanh_toan";
	}

	@RequestMapping(value = "cart", params = "giamSoLuong")
	public String giamSoLuongChiTiet(@RequestParam("giamSoLuong") String maChiTiet) {
		List<Integer> cumMa = getMaChiTietGioHang(maChiTiet);
		DAOChiTietGioHang.updateSoLuong(cumMa.get(0), cumMa.get(1), -1);
		return "redirect:/user/cart.htm";
	}

	@RequestMapping(value = "cart", params = "tangSoLuong")
	public String tangSoLuongChiTiet(@RequestParam("tangSoLuong") String maChiTiet) {
		List<Integer> cumMa = getMaChiTietGioHang(maChiTiet);
		DAOChiTietGioHang.updateSoLuong(cumMa.get(0), cumMa.get(1), 1);
		return "redirect:/user/cart.htm";
	}

	@RequestMapping(value = "cart", params = "xoaChiTietGioHang")
	public String xoaChiTietGioHang(@RequestParam("xoaChiTietGioHang") String maChiTiet) {
		List<Integer> cumMa = getMaChiTietGioHang(maChiTiet);
		DAOChiTietGioHang.deleteChiTietGioHang(cumMa.get(0), cumMa.get(1));
		return "redirect:/user/cart.htm";
	}

	private List<Integer> getMaChiTietGioHang(String maChiTiet) {
		return Arrays.asList(maChiTiet.split("_")).stream().map(e -> Integer.parseInt(e)).toList();
	}
}
