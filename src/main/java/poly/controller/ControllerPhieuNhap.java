package poly.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import poly.dao.DAOInterface.ChiTietPhieuNhapInterface;
import poly.dao.DAOInterface.NhanVienInterface;
import poly.dao.DAOInterface.PhieuNhapInterface;
import poly.dao.DAOInterface.SachInterface;
import poly.entity.ChiTietPhieuNhap;
import poly.entity.PhieuNhap;

@Controller
@RequestMapping("admin")
public class ControllerPhieuNhap {
	@Autowired
	private NhanVienInterface DAONhanVien;
	@Autowired
	private PhieuNhapInterface DAOPhieuNhap;
	@Autowired
	private SachInterface DAOSach;
	@Autowired
	ChiTietPhieuNhapInterface DAOChiTietPhieuNhap;

	@RequestMapping(value = "phieunhap", params = "insertpn")
	public String themphieunhap(@RequestParam("nhaCungCap") String nhaCungCap, RedirectAttributes red) {
		Date date = new Date(System.currentTimeMillis());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String tenTaiKhoan = authentication.getName();
		PhieuNhap phieuNhap = new PhieuNhap();
		phieuNhap.setNgay(date);
		phieuNhap.setNhanVien(DAONhanVien.getNhanVienByTenTaiKhoan(tenTaiKhoan));
		phieuNhap.setNhaCungCap(nhaCungCap);
		DAOPhieuNhap.insertPhieuNhap(phieuNhap);
		red.addFlashAttribute("page", "page-import");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "phieunhap", params = "insert")
	public String themchitietphieunhap(@RequestParam("maPhieuNhap") int maPhieuNhap, @RequestParam("maSach") int maSach,
			@RequestParam("soLuong") int soLuong, @RequestParam("donGia") float donGia, RedirectAttributes red) {
		ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
		ctpn.setGiaGoc(donGia);
		ctpn.setSoLuong(soLuong);
		ctpn.setSach(DAOSach.getSach(maSach));
		ctpn.setPhieuNhap(DAOPhieuNhap.getPhieuNhap(maPhieuNhap));
		DAOChiTietPhieuNhap.insertChiTietPhieuNhap(ctpn);
		red.addFlashAttribute("page", "page-import");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "phieunhap", params = "view")
	public String viewphienhap(@RequestParam("view") int maPhieuNhap, RedirectAttributes red) {
		PhieuNhap phieuNhap = DAOPhieuNhap.getPhieuNhap(maPhieuNhap);
		red.addFlashAttribute("phieuNhapUpdate", phieuNhap);
		red.addFlashAttribute("page", "page-view-import");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "phieunhap", params = "listAdd")
	public String xemdanhsachsanphamchuaco(@RequestParam("listAdd") int maPhieuNhap, RedirectAttributes red) {
		Date date = new Date(System.currentTimeMillis());
		Calendar c = new GregorianCalendar();
		c.setTime(DAOPhieuNhap.getPhieuNhap(maPhieuNhap).getNgay());
		c.add(Calendar.DATE, 1);
		if(date.after(c.getTime())) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Đã quá thời gian để thêm");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-import");
			return "redirect:/admin.htm";
		}
		red.addFlashAttribute("phieuNhapUpdate", DAOPhieuNhap.getPhieuNhap(maPhieuNhap));
		red.addFlashAttribute("listSachImport", DAOSach.listSachKhongCoTrongPN(maPhieuNhap));
		red.addFlashAttribute("modal", "insert-view-import");
		red.addFlashAttribute("page", "page-import");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "phieunhap", params = "addSach")
	public String addSach(@RequestParam("addSach") int maPhieuNhap, @RequestParam("maSach") int maSach,
			@RequestParam("soLuong") int soLuong, @RequestParam("donGia") int donGia) {
		ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
		ctpn.setGiaGoc(donGia);
		ctpn.setSoLuong(soLuong);
		ctpn.setPhieuNhap(DAOPhieuNhap.getPhieuNhap(maPhieuNhap));
		ctpn.setSach(DAOSach.getSach(maSach));
		DAOSach.updateSoLuongTon(maSach, soLuong);
		DAOChiTietPhieuNhap.insertChiTietPhieuNhap(ctpn);
		return "redirect:/admin/phieunhap.htm?view=" + String.valueOf(maPhieuNhap);
	}

	@RequestMapping(value = "phieunhap", params = "deleteCTPN")
	public String deleteCTPN(@RequestParam("deleteCTPN") int maPhieuNhap, @RequestParam(value = "listID[]", required = false) int[] maSach,
			RedirectAttributes red) {
		if(maSach.length == 0) {
			red.addFlashAttribute("page", "page-import");
			return "redirect:/admin.htm";
		}
		String id = String.valueOf(maPhieuNhap);
		for (int i : maSach) {
			id = id + '_' + String.valueOf(i);
		}
		red.addFlashAttribute("delete", "BẠN CÓ MUỐN XÓA CHI TIẾT PHIẾU NHẬP NÀY KHÔNG?");
		red.addFlashAttribute("act", "deleteCTPN");
		red.addFlashAttribute("idDelete", id);
		red.addFlashAttribute("page", "page-import");
		red.addFlashAttribute("modal", "delete");
		return "redirect:/admin.htm";
	}

	@RequestMapping("deleteCTPN")
	public String deleteCTPN(@RequestParam("id") String ma, RedirectAttributes red) {
		String[] listma = ma.split("_", 0);
		int maPhieuNhap = Integer.parseInt(listma[0]);
		List<Integer> maSach = new ArrayList<>();
		for (int i = 1; i < listma.length; i++) {
			maSach.add(Integer.parseInt(listma[i]));
		}
		Date date = new Date(System.currentTimeMillis());
		Calendar c = new GregorianCalendar();
		c.setTime(DAOPhieuNhap.getPhieuNhap(maPhieuNhap).getNgay());
		c.add(Calendar.DATE, 1);
		if (date.after(c.getTime())) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Đã quá thời gian để xóa");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-import");
			return "redirect:/admin.htm";
		}
		if (!DAOChiTietPhieuNhap.deleteNhieuSach(maPhieuNhap, maSach)) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Xóa thất bại");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-import");
			return "redirect:/admin.htm";
		}
		red.addFlashAttribute("toaston", "1");
		red.addFlashAttribute("title", "Thành Công");
		red.addFlashAttribute("message", "Xóa thành công");
		red.addFlashAttribute("type", "success");
		red.addFlashAttribute("duration", 5000);
		red.addFlashAttribute("page", "page-import");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "phieunhap", params = "delete")
	public String deletephieunhap(@RequestParam("delete") int maPhieuNhap, RedirectAttributes red) {
		red.addFlashAttribute("delete", "BẠN CÓ MUỐN XÓA PHIẾU NHẬP NÀY KHÔNG?");
		red.addFlashAttribute("act", "deletephieunhap");
		red.addFlashAttribute("idDelete", maPhieuNhap);
		red.addFlashAttribute("page", "page-import");
		red.addFlashAttribute("modal", "delete");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "deletephieunhap")
	public String deletePhieuNhap(@RequestParam("id") int maPhieuNhap, RedirectAttributes red) {
		Date date = new Date(System.currentTimeMillis());
		Calendar c = new GregorianCalendar();
		c.setTime(DAOPhieuNhap.getPhieuNhap(maPhieuNhap).getNgay());
		c.add(Calendar.DATE, 1);
		if (date.after(c.getTime())) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Đã quá thời gian để xóa");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-import");
			return "redirect:/admin.htm";
		} else if (!DAOPhieuNhap.getPhieuNhap(maPhieuNhap).getDsChiTietPhieuNhap().isEmpty()) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Phiếu đã nhập sản phẩm");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-import");
			return "redirect:/admin.htm";
		}
		if (DAOPhieuNhap.deletePhieuNhap(maPhieuNhap)) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thành Công");
			red.addFlashAttribute("message", "Xóa thành công");
			red.addFlashAttribute("type", "success");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-import");
			return "redirect:/admin.htm";
		} else {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Xóa thất bại");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-import");
			return "redirect:/admin.htm";
		}
	}
}
