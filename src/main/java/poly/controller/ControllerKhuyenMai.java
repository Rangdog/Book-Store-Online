package poly.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.dao.DAOInterface.KhuyenMaiInterface;
import poly.dao.DAOInterface.NhanVienInterface;
import poly.dao.DAOInterface.SachInterface;
import poly.entity.KhuyenMai;
import poly.entity.Sach;

@Controller
@RequestMapping("admin")
public class ControllerKhuyenMai {
	@Autowired
	private KhuyenMaiInterface DAOKhuyenMai;
	@Autowired
	private NhanVienInterface DAONhanVien;
	@Autowired
	private SachInterface DAOSach;

	@RequestMapping(value = "khuyenmai", params = "themkhuyenmai")
	public String themKhuyenMai(@RequestParam("ngayBatDau") Date ngayBatDau,
			@RequestParam("ngayKetThuc") Date ngayKetThuc, @RequestParam("phanTram") Float phanTram,
			RedirectAttributes red) {
		Date date = new Date(System.currentTimeMillis());
		if(ngayBatDau.before(date)) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Thêm thất bại");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-sale");
			return "redirect:/admin.htm";
		}
		if(ngayKetThuc.before(ngayBatDau)) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Thêm thất bại");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-sale");
			return "redirect:/admin.htm";
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String tenTaiKhoan = authentication.getName();
		KhuyenMai khuyenMai = new KhuyenMai();
		khuyenMai.setNgayBatDau(ngayBatDau);
		khuyenMai.setNgayKetThuc(ngayKetThuc);
		khuyenMai.setPhanTram(phanTram);
		khuyenMai.setNhanVien(DAONhanVien.getNhanVienByTenTaiKhoan(tenTaiKhoan));
		DAOKhuyenMai.insertKhuyenMai(khuyenMai);
		red.addFlashAttribute("page", "page-sale");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "khuyenmai", params = "update")
	public String xemKhuyenMai(@RequestParam("update") int maKhuyenMai, RedirectAttributes red) {
		KhuyenMai khuyenMai = DAOKhuyenMai.getKhuyenMai(maKhuyenMai);

		red.addFlashAttribute("khuyenMaiUpdate", khuyenMai);
		red.addFlashAttribute("page", "page-sale");
		red.addFlashAttribute("modal", "update-sale");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "khuyenmai", params = "view")
	public String xemKhuyenMai1(@RequestParam("view") int maKhuyenMai, RedirectAttributes red) {
		KhuyenMai khuyenMai = DAOKhuyenMai.getKhuyenMai(maKhuyenMai);
		red.addFlashAttribute("khuyenMaiUpdate", khuyenMai);
		red.addFlashAttribute("page", "page-view-sale");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "suakhuyenmai", method = RequestMethod.POST)
	public String suaKhuyenMai(@RequestParam("id") int maKhuyenMai, @RequestParam("ngayBatDau") Date ngayBatDau,
			@RequestParam("ngayKetThuc") Date ngayKetThuc, @RequestParam("phanTram") Float phanTram,
			RedirectAttributes red) {
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);

		if (ngayBatDau.before(date)) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Lưu thất bại");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-sale");
			return "redirect:/admin.htm";
		} else if (ngayKetThuc.before(ngayBatDau)) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Lưu thất bại");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-sale");
			return "redirect:/admin.htm";
		} else if (phanTram < 0) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Lưu thất bại");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page", "page-sale");
			return "redirect:/admin.htm";
		}
		DAOKhuyenMai.updateKhuyenMai(maKhuyenMai, ngayBatDau, ngayKetThuc, phanTram);
		red.addFlashAttribute("page", "page-sale");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "khuyenmai", params = "listAdd")
	public String listSachAdd(@RequestParam("listAdd") int id, RedirectAttributes red) {
		List<Sach> list = DAOSach.listSachKhongCoTrongKM(id);
		red.addFlashAttribute("listSachKM", list);
		red.addFlashAttribute("maKhuyenMai", id);
		red.addFlashAttribute("modal", "insert-view-sale");
		red.addFlashAttribute("page", "page-sale");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "khuyenmai", params = "addSach")
	public String listSachAddSach(@RequestParam("listID[]") int[] id, @RequestParam("addSach") int maKhuyenMai,
			RedirectAttributes red) {
		for (int i : id) {
			DAOKhuyenMai.addSach(maKhuyenMai, i);
		}
		return "redirect:/admin/khuyenmai.htm?view=" + String.valueOf(maKhuyenMai);
	}

	public Boolean checkExist(int[] list, int id) {
		for (int i : list) {
			if (id == i) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping(value = "khuyenmai", params = "delete")
	public String deleteSach(@RequestParam("listID[]") int[] id, @RequestParam("delete") int maKhuyenMai,
			RedirectAttributes red) {
		for (int i : id) {
			DAOKhuyenMai.deleteSach(maKhuyenMai, i);
		}
		return "redirect:/admin/khuyenmai.htm?view=" + String.valueOf(maKhuyenMai);
	}
	
	@RequestMapping(value = "khuyenmai", params = "deleteKhuyenMai")
	public String xoadanhmuc(@RequestParam("deleteKhuyenMai") int id, RedirectAttributes red) {
		red.addFlashAttribute("delete", "BẠN CÓ MUỐN XÓA KHUYẾN MÃI NÀY KHÔNG?");
		red.addFlashAttribute("act", "xoakhuyenmai");
		red.addFlashAttribute("idDelete", id);
		red.addFlashAttribute("page", "page-sale");
		red.addFlashAttribute("modal", "delete");
		return "redirect:/admin.htm";
	}
	
	@RequestMapping(value = "xoakhuyenmai")
	public String deleteKhuyenMai(@RequestParam("id") int maKhuyenMai,RedirectAttributes red) {
		KhuyenMai km = DAOKhuyenMai.getKhuyenMai(maKhuyenMai);
		Date date = new Date(System.currentTimeMillis());
		if(date.after(km.getNgayBatDau())) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Khuyến mãi đã bắt đầu");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page","page-sale");
			return "redirect:/admin.htm";	
		}
		if(DAOKhuyenMai.deleteKhuyenMai(maKhuyenMai)) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thành Công");
			red.addFlashAttribute("message", "Xóa thành công");
			red.addFlashAttribute("type", "success");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page","page-sale");
			return "redirect:/admin.htm";	
		}
		else {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Xóa thất bại");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			red.addFlashAttribute("page","page-sale");
			return "redirect:/admin.htm";	
		}
	}
}
