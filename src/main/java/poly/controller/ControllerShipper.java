package poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.dao.DAOInterface.DonHangInterface;
import poly.dao.DAOInterface.NhanVienInterface;
import poly.entity.DonHang;
import poly.entity.NhanVien;

@Controller
public class ControllerShipper {
	@Autowired
	private NhanVienInterface DAONhanVien;
	@Autowired
	private DonHangInterface DAODonHang;
	
	@RequestMapping(value = "shipper", method = RequestMethod.GET)
	public String viewShipperPage(ModelMap model) {
		String tenTaiKhoan = SecurityContextHolder.getContext().getAuthentication().getName();
		NhanVien shipper = DAONhanVien.getNhanVienByTenTaiKhoan(tenTaiKhoan);
		// LẤY DANH SÁCH ĐƠN HÀNG CỦA SHIPPER.
		model.addAttribute("shipper", shipper);
		model.addAttribute("donHangs", DAODonHang.listDonHangShipper(shipper.getMaNhanVien()));
		return "shipper/shipper";
	}

	@RequestMapping(value = "shipper", params = "view", method = RequestMethod.POST)
	public String viewOrder(@RequestParam("view") int maDonHang, RedirectAttributes red) {
		// LẤY DỮ LIỆU ĐƠN HÀNG ĐƯỢC CHỌN.
		DonHang donHang = DAODonHang.getDonHang(maDonHang);
		red.addFlashAttribute("donHang", donHang);
		red.addFlashAttribute("openModal", "modal-view");
		return "redirect:/shipper.htm";
	}

	@RequestMapping(value = "shipper", params = "deliver", method = RequestMethod.POST)
	public String deliverOrder(@RequestParam("deliver") int maDonHang) {
		// CẬP NHẬT TRẠNG THÁI ĐƠN HÀNG SANG 'ĐANG GIAO' (3)
		DAODonHang.updateTrangThai(maDonHang, 3);
		return "redirect:/shipper.htm";
	}

	@RequestMapping(value = "shipper", params = "complete", method = RequestMethod.POST)
	public String completeOrder(@RequestParam("complete") int maDonHang) {
		// CẬP NHẬT TRẠNG THÁI ĐƠN HÀNG SANG 'HOÀN THÀNH' (4)
		DAODonHang.updateTrangThai(maDonHang, 4);
		return "redirect:/shipper.htm";
	}

	@RequestMapping(value = "shipper", params = "refund", method = RequestMethod.POST)
	public String openModalRefund(@RequestParam("refund") int maDonHang, RedirectAttributes red) {
		red.addFlashAttribute("maDonHang", maDonHang);
		red.addFlashAttribute("openModal", "modal-refund");
		return "redirect:/shipper.htm";
	}
	
	@RequestMapping(value = "refund")
	public String refundOrder(@RequestParam("confirm") int maDonHang, @RequestParam("lyDo") String lyDo) {
		String tenTaiKhoan = SecurityContextHolder.getContext().getAuthentication().getName();
		NhanVien shipper = DAONhanVien.getNhanVienByTenTaiKhoan(tenTaiKhoan);
		// CẬP NHẬT TRẠNG THÁI ĐƠN HÀNG SANG 'HOÀN TRẢ' (5)
		DAODonHang.updateTrangThai(maDonHang, 5);
		DAODonHang.refundDonHang(maDonHang, shipper.getMaNhanVien(), lyDo);
		return "redirect:/shipper.htm";
	}
}
