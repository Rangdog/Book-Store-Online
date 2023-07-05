package poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.dao.DAOInterface.DanhMucInterface;
import poly.dao.DAOInterface.NhaPhatHanhInterface;
import poly.dao.DAOInterface.NhaXuatBanInterface;
import poly.dao.DAOInterface.TacGiaInterface;
import poly.dao.DAOInterface.TheLoaiInterface;
import poly.entity.DanhMuc;
import poly.entity.NhaPhatHanh;
import poly.entity.NhaXuatBan;
import poly.entity.TacGia;
import poly.entity.TheLoai;

@Controller
@RequestMapping("admin")
public class ControllerChiTietSanPham {
	@Autowired
	private DanhMucInterface DAODanhMuc;
	@Autowired
	private TheLoaiInterface DAOTheLoai;
	@Autowired
	private NhaXuatBanInterface DAONhaXuatBan;
	@Autowired
	private NhaPhatHanhInterface DAONhaPhatHanh;
	@Autowired
	private TacGiaInterface DAOTacGia;

	// DANH MỤC.
	@RequestMapping("themdanhmuc")
	public String themDanhMuc(@RequestParam("tenDanhMuc") String tenDanhMuc, RedirectAttributes red) {
		DanhMuc e = new DanhMuc();
		e.setTenDanhMuc(tenDanhMuc);
		DAODanhMuc.insertDanhMuc(e);
		red.addFlashAttribute("page", "page-category");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "", params = "xemdanhmuc")
	public String xemDanhMuc(@RequestParam("xemdanhmuc") int id, RedirectAttributes red) {
		DanhMuc e = DAODanhMuc.getDanhMuc(id);
		red.addFlashAttribute("danhMucUpdate", e);
		red.addFlashAttribute("page", "page-category");
		red.addFlashAttribute("modal", "update-category");
		return "redirect:/admin.htm";
	}

	@RequestMapping("suadanhmuc")
	public String suaDanhMuc(@RequestParam("tenDanhMuc") String tenDanhmuc, @RequestParam("id") int id,
			RedirectAttributes red) {
		DAODanhMuc.updateDanhMuc(id, tenDanhmuc);
		red.addFlashAttribute("page", "page-category");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "", params = "xoadanhmuc")
	public String xoadanhmuc(@RequestParam("xoadanhmuc") int id, RedirectAttributes red) {
		red.addFlashAttribute("delete", "BẠN CÓ MUỐN XÓA DANH MỤC NÀY KHÔNG?");
		red.addFlashAttribute("act", "xoadanhmuc");
		red.addFlashAttribute("idDelete", id);
		red.addFlashAttribute("page", "page-category");
		red.addFlashAttribute("modal", "delete");
		return "redirect:/admin.htm";
	}

	@RequestMapping("xoadanhmuc")
	public String deletedanhmuc(@RequestParam("id") int id, RedirectAttributes red) {
		if (DAODanhMuc.deleteDanhMuc(id) == 0) {
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
		red.addFlashAttribute("page", "page-category");
		return "redirect:/admin.htm";
	}

	// THỂ LOẠI.
	@RequestMapping("themtheloai")
	public String themTheLoai(@RequestParam("tenTheLoai") String tenTheLoai, @RequestParam("maDanhMuc") int maDanhMuc,
			RedirectAttributes red) {
		TheLoai e = new TheLoai();
		e.setDanhMuc(DAODanhMuc.getDanhMuc(maDanhMuc));
		e.setTenTheLoai(tenTheLoai);
		DAOTheLoai.insertTheLoai(e);
		red.addFlashAttribute("page", "page-genre");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "", params = "xemtheloai")
	public String xemtheloai(@RequestParam("xemtheloai") int id, RedirectAttributes red) {
		TheLoai e = DAOTheLoai.getTheLoai(id);
		red.addFlashAttribute("theLoaiUpdate", e);
		red.addFlashAttribute("page", "page-genre");
		red.addFlashAttribute("modal", "update-genre");
		return "redirect:/admin.htm";
	}

	@RequestMapping("suatheloai")
	public String suaTheLoai(@RequestParam("id") int id, @RequestParam("tenTheLoai") String tenTheLoai,
			@RequestParam("maDanhMuc") int maDanhMuc, RedirectAttributes redirectAttributes) {
		DAOTheLoai.updateTheLoai(id, maDanhMuc, tenTheLoai);
		redirectAttributes.addFlashAttribute("page", "page-genre");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "", params = "xoatheloai")
	public String xoatheloai(@RequestParam("xoatheloai") int id, RedirectAttributes red) {
		red.addFlashAttribute("delete", "BẠN CÓ MUỐN XÓA THỂ LOẠI NÀY KHÔNG?");
		red.addFlashAttribute("act", "xoatheloai");
		red.addFlashAttribute("idDelete", id);
		red.addFlashAttribute("page", "page-genre");
		red.addFlashAttribute("modal", "delete");
		return "redirect:/admin.htm";
	}

	@RequestMapping("xoatheloai")
	public String deletetheloai(@RequestParam("id") int id, RedirectAttributes red) {
		if (DAOTheLoai.deleteTheLoai(id) == 0) {
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
		red.addFlashAttribute("page", "page-genre");
		return "redirect:/admin.htm";
	}

	// NHÀ XUẤT BẢN.
	@RequestMapping("themnhaxuatban")
	public String themnhaxuatban(@RequestParam("tenNhaXuatBan") String tenNhaXuatBan, RedirectAttributes red) {
		NhaXuatBan e = new NhaXuatBan();
		e.setTenNXB(tenNhaXuatBan);
		DAONhaXuatBan.insert(e);
		red.addFlashAttribute("page", "page-publisher");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "", params = "xemnhaxuatban")
	public String xemnhaxuatban(@RequestParam("xemnhaxuatban") int id, RedirectAttributes red) {
		NhaXuatBan e = DAONhaXuatBan.getNhaXuatBan(id);
		red.addFlashAttribute("nhaXuatBanUpdate", e);
		red.addFlashAttribute("page", "page-publisher");
		red.addFlashAttribute("modal", "update-publisher");
		return "redirect:/admin.htm";
	}

	@RequestMapping("suanhaxuatban")
	public String suanhaxuatban(@RequestParam("id") int id, @RequestParam("tenNhaXuatBan") String tenNhaXuatBan,
			RedirectAttributes red) {
		DAONhaXuatBan.update(id, tenNhaXuatBan);
		red.addFlashAttribute("page", "page-publisher");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "", params = "xoanhaxuatban")
	public String xoanhaxuatban(@RequestParam("xoanhaxuatban") int id, RedirectAttributes red) {
		red.addFlashAttribute("delete", "BẠN CÓ MUỐN XÓA NHÀ XUẤT BẢN NÀY KHÔNG?");
		red.addFlashAttribute("act", "xoanhaxuatban");
		red.addFlashAttribute("idDelete", id);
		red.addFlashAttribute("page", "page-publisher");
		red.addFlashAttribute("modal", "delete");
		return "redirect:/admin.htm";
	}

	@RequestMapping("xoanhaxuatban")
	public String deletenhaxuatban(@RequestParam("id") int id, RedirectAttributes red) {
		if (DAONhaXuatBan.deleteNhaXuatBan(id) == 0) {
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
		red.addFlashAttribute("page", "page-publisher");
		return "redirect:/admin.htm";
	}

	// NHÀ PHÁT HÀNH.
	@RequestMapping("themnhaphathanh")
	public String themnhaphathanh(@RequestParam("tenNhaPhatHanh") String tenNhaPhatHanh, RedirectAttributes red) {
		NhaPhatHanh e = new NhaPhatHanh();
		e.setTenNPH(tenNhaPhatHanh);
		DAONhaPhatHanh.insert(e);
		red.addFlashAttribute("page", "page-issuer");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "", params = "xemnhaphathanh")
	public String xemnhaphathanh(@RequestParam("xemnhaphathanh") int id, RedirectAttributes red) {
		NhaPhatHanh e = DAONhaPhatHanh.getNhaPhatHanh(id);
		red.addFlashAttribute("nhaPhatHanhUpdate", e);
		red.addFlashAttribute("page", "page-issuer");
		red.addFlashAttribute("modal", "update-issuer");
		return "redirect:/admin.htm";
	}

	@RequestMapping("suanhaphathanh")
	public String suanhaphathanh(@RequestParam("maNhaPhatHanh") int id,
			@RequestParam("tenNhaPhatHanh") String tenNhaPhatHanh, RedirectAttributes red) {
		DAONhaPhatHanh.update(id, tenNhaPhatHanh);
		red.addFlashAttribute("page", "page-issuer");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "", params = "xoanhaphathanh")
	public String xoanhaphathanh(@RequestParam("xoanhaphathanh") int id, RedirectAttributes red) {
		red.addFlashAttribute("delete", "BẠN CÓ MUỐN XÓA NHÀ PHÁT HÀNH NÀY KHÔNG?");
		red.addFlashAttribute("act", "xoanhaphathanh");
		red.addFlashAttribute("idDelete", id);
		red.addFlashAttribute("page", "page-issuer");
		red.addFlashAttribute("modal", "delete");
		return "redirect:/admin.htm";
	}

	@RequestMapping("xoanhaphathanh")
	public String deletenhaphathanh(@RequestParam("id") int id, RedirectAttributes red) {
		if (DAONhaPhatHanh.deleteNhaPhatHanh(id) == 0) {
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
		red.addFlashAttribute("page", "page-issuer");
		return "redirect:/admin.htm";
	}

	// TÁC GIẢ.
	@RequestMapping(value = "themtacgia")
	public String themTacGia(@RequestParam("tenTacGia") String tenTacGia, RedirectAttributes red) {
		TacGia e = new TacGia();
		e.setTenTacGia(tenTacGia);
		DAOTacGia.insertTacGia(e);
		red.addFlashAttribute("page", "page-author");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "", params = "xemtacgia")
	public String xemTacGia(@RequestParam("xemtacgia") int id, RedirectAttributes red) {
		TacGia e = DAOTacGia.getTacGia(id);
		red.addFlashAttribute("tacgiaUpdate", e);
		red.addFlashAttribute("page", "page-author");
		red.addFlashAttribute("modal", "update-author");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "suatacgia")
	public String suaTacGia(@RequestParam("tenTacGia") String tenTacGia, @RequestParam("id") int id,
			RedirectAttributes red) {
		DAOTacGia.updateTacGia(id, tenTacGia);
		red.addFlashAttribute("page", "page-author");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "", params = "xoatacgia")
	public String xoaTacGia(@RequestParam("xoatacgia") int id, RedirectAttributes red) {
		red.addFlashAttribute("delete", "BẠN CÓ MUỐN XÓA TÁC GIẢ NÀY KHÔNG?");
		red.addFlashAttribute("act", "xoatacgia");
		red.addFlashAttribute("idDelete", id);
		red.addFlashAttribute("page", "page-author");
		red.addFlashAttribute("modal", "delete");
		return "redirect:/admin.htm";
	}

	@RequestMapping("xoatacgia")
	public String deletetacgia(@RequestParam("id") int id, RedirectAttributes red) {
		if (DAOTacGia.deleteTacGia(id) == 0) {
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
		red.addFlashAttribute("page", "page-author");
		return "redirect:/admin.htm";
	}
}
