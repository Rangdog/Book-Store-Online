package poly.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.dao.DAOInterface.DonHangInterface;
import poly.entity.DonHang;
import poly.entity.KhachHang;
import poly.entity.ChiTietDonHang;
import poly.entity.ChiTietGioHang;
import poly.entity.DiaChiGiaoHang;

@Controller
@RequestMapping("user")
public class ControllerDonHangCuaToi {
	@Autowired
	private DonHangInterface DAODonHang;

	@RequestMapping("myorder")
	public String viewMyOrderPage(ModelMap model,
			@RequestParam(value = "trangThai", defaultValue = "0", required = false) int trangThai) {
		model.addAttribute("trangThaiLoc", trangThai);
		model.addAttribute("donHangs", DAODonHang.listDonHangTheoTrangThai(trangThai));
		return "user/don_hang";
	}
	
	@RequestMapping("readmore")
	public String viewDetailOrderPage(ModelMap model, @RequestParam("readmore") int maDonHang) {
		model.addAttribute("donHang", DAODonHang.getDonHang(maDonHang));
		return "user/ct_dh";
	}
	@RequestMapping(value = "readmore", params = "repurchase")
	public String repurchase(@RequestParam("repurchase") int maDonHang,ModelMap model) {
		DonHang dh = DAODonHang.getDonHang(maDonHang);
		KhachHang kh = dh.getDiaChiGiaoHang().getKhachHang();
		Set<DiaChiGiaoHang> diaChis = dh.getDiaChiGiaoHang().getKhachHang().getDsDiaChiGiaoHang();
		DiaChiGiaoHang diaChi = dh.getDiaChiGiaoHang();
		model.addAttribute("diaChis", diaChis);
		model.addAttribute("diaChi", diaChi);
		List<ChiTietGioHang> sanPhams = new ArrayList<>();
		for(ChiTietDonHang i: dh.getDsChiTietDonHang()) {
			ChiTietGioHang chiTietGioHang = new ChiTietGioHang();
			chiTietGioHang.setKhachHang(kh);
			chiTietGioHang.setSach(i.getSach());
			chiTietGioHang.setSoLuong(i.getSoLuong());
			sanPhams.add(chiTietGioHang);
		}
		model.addAttribute("sanPhams", sanPhams);
		model.addAttribute("tongTien", sanPhams.stream().mapToDouble(e -> e.getSoLuong() * e.getSach().getGiaSach()).sum());
		model.addAttribute("phiVanChuyen", 32000);
		return "user/thanh_toan";
	}
}
