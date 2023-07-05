package poly.controller;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.bean.Uploader;
import poly.dao.DAOInterface.NhaPhatHanhInterface;
import poly.dao.DAOInterface.NhaXuatBanInterface;
import poly.dao.DAOInterface.SachInterface;
import poly.dao.DAOInterface.TacGiaInterface;
import poly.dao.DAOInterface.TheLoaiInterface;
import poly.entity.Sach;
import poly.entity.TacGia;
import poly.entity.TheLoai;

@Controller
@RequestMapping("admin")
public class ControllerSanPham {
	@Autowired
	private NhaXuatBanInterface DAONhaXuatBan;
	@Autowired
	private NhaPhatHanhInterface DAONhaPhatHanh;
	@Autowired
	private SachInterface DAOSach;
	@Autowired
	private TacGiaInterface DAOTacGia;
	@Autowired
	private TheLoaiInterface DAOTheLoai;

	@RequestMapping(value = "sach", params = "insert", method = RequestMethod.POST)
	public String themSach(@RequestParam(value = "tenSach", required = false) String tenSach,
			@RequestParam("anhDaiDien") MultipartFile anhDaiDien, @RequestParam("tacGia") int tacGia,
			@RequestParam("nhaPhatHanh") int nhaPhatHanh, @RequestParam("nhaXuatBan") int nhaXuatBan,
			@RequestParam("tomTat") String tomTat, @RequestParam("theLoai") int theLoai,
			@RequestParam("soTrang") int soTrang, @RequestParam("kichThuoc") int kichThuoc,
			@RequestParam("ngonNgu") String ngonNgu, @RequestParam("trongLuong") int trongLuong,
			@RequestParam("hinhThuc") String hinhThuc, RedirectAttributes re, HttpServletRequest req) {
		Sach sach = new Sach();
		if (anhDaiDien != null) sach.setAnhDaiDien(Uploader.upload(anhDaiDien, "product", req.getServletContext()));
		sach.setTenSach(tenSach);
		sach.setNhaPhatHanh(DAONhaPhatHanh.getNhaPhatHanh(nhaPhatHanh));
		sach.setNhaXuatBan(DAONhaXuatBan.getNhaXuatBan(nhaXuatBan));
		sach.setSoTrang(soTrang);
		sach.setKichThuoc(0);
		sach.setTrongLuong(trongLuong);
		sach.setNgonNgu(ngonNgu);
		sach.setHinhThuc(hinhThuc);
		sach.setTomTat(tomTat);
		sach.setGiaBan(0);
		sach.setGiaKhuyenMai(-1);
		sach.setTrangThai(0);
		DAOSach.insertSach(sach, tacGia, theLoai);
		re.addFlashAttribute("page", "page-product");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "sach", params = "xemsanpham", method = RequestMethod.GET)
	public String xemSanPham(@RequestParam("xemsanpham") int id, RedirectAttributes re) {
		Sach sach = DAOSach.getSach(id);
		re.addFlashAttribute("sachUpdate", sach);
		re.addFlashAttribute("page", "page-product");
		re.addFlashAttribute("modal", "view-product");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "suasanpham", method = RequestMethod.POST)
	public String suaSanPham(@RequestParam("tenSach") String tenSach, @RequestParam("id") int id,
			@RequestParam("anhDaiDien") MultipartFile anhDaiDien, @RequestParam("nhaPhatHanh") int maNPH,
			@RequestParam("nhaXuatBan") int maNXB, @RequestParam("tomTat") String tomTat,
			@RequestParam("hinhThuc") String hinhThuc, @RequestParam("soTrang") int soTrang,
			@RequestParam("kichThuoc") int kichThuoc, @RequestParam("ngonNgu") String ngonNgu,
			@RequestParam("trongLuong") int trongLuong, @RequestParam("trangThai") int trangThai,
			@RequestParam("giaBan") Float giaBan, @RequestParam("giaKhuyenMai") Float giaKhuyenMai,
			RedirectAttributes re, HttpServletRequest req) {
		String photoPath = "";
		if (anhDaiDien != null) photoPath = Uploader.upload(anhDaiDien, "product", req.getServletContext());
		DAOSach.updateSach(id, tenSach, maNPH, maNXB, soTrang, kichThuoc, trongLuong, hinhThuc, ngonNgu, trangThai,
				giaBan, tomTat, photoPath);
		re.addFlashAttribute("page", "page-product");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "sach", params = "theloaicuasach")
	public String theLoaiCuaSach(@RequestParam("theloaicuasach") int id, RedirectAttributes re) {
		Sach sach = DAOSach.getSach(id);
		re.addFlashAttribute("sachUpdate", sach);
		re.addFlashAttribute("page", "page-view-genre");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "sach", params = "themtheloaivaosach", method = RequestMethod.POST)
	public String themTheLoaiVaoSach(@RequestParam(value = "theLoai[]") int[] listMaTheLoai,
			@RequestParam("id") int id) {
		Sach sach = DAOSach.getSach(id);
		for (int maTheLoai : listMaTheLoai) {
			if (!sach.checkTheLoai(DAOTheLoai.getTheLoai(maTheLoai))) {
				DAOTheLoai.addSach(maTheLoai, id);
			}
		}
		return "redirect:sach.htm?theloaicuasach=" + id;
	}

	public Boolean checkExist(int[] list, int id) {
		for (int i : list) {
			if (id == i) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping(value = "sach", params = "xoatheloaikhoisach", method = RequestMethod.POST)
	public String xoaTheLoaiKhoiSach(@RequestParam(value = "theLoai[]", required = false) int[] listMaTheLoai,
			@RequestParam("id") int id, RedirectAttributes red) {
		if (listMaTheLoai == null) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Xóa thất bại");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			return "redirect:sach.htm?theloaicuasach=" + id;
		}
		Sach sach = DAOSach.getSach(id);
		Set<TheLoai> dsTL = sach.getDsTheLoai();
		for (TheLoai tl : dsTL) {
			if (!checkExist(listMaTheLoai, tl.getMaTheLoai())) {
				DAOTheLoai.deleteSach(tl.getMaTheLoai(), id);
			}
		}
		return "redirect:sach.htm?theloaicuasach=" + id;
	}

	@RequestMapping(value = "sach", params = "tacgiacuasach")
	public String tacGiaCuaSach(@RequestParam("tacgiacuasach") int id, RedirectAttributes re) {
		Sach sach = DAOSach.getSach(id);
		re.addFlashAttribute("sachUpdate", sach);
		re.addFlashAttribute("page", "page-view-author");
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "sach", params = "themtacgiavaosach", method = RequestMethod.POST)
	public String themTacGiaVaoSach(@RequestParam(value = "tacGia[]") int[] listMaTacGia, @RequestParam("id") int id) {

		Sach sach = DAOSach.getSach(id);
		for (int maTacGia : listMaTacGia) {
			if (!sach.checkTacGia(DAOTacGia.getTacGia(maTacGia))) {
				DAOTacGia.addSach(maTacGia, id);
			}
		}
		return "redirect:sach.htm?tacgiacuasach=" + id;
	}

	@RequestMapping(value = "sach", params = "xoatacgiakhoisach", method = RequestMethod.POST)
	public String xoaTacGiaKhoiSach(@RequestParam(value = "tacGia[]", required = false) int[] listMaTacGia,
			@RequestParam("id") int id, RedirectAttributes red) {
		if (listMaTacGia == null) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Xóa thất bại");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
			return "redirect:sach.htm?tacgiacuasach=" + id;
		}
		Sach sach = DAOSach.getSach(id);
		Set<TacGia> dsTG = sach.getDsTacGia();
		for (TacGia tg : dsTG) {
			if (!checkExist(listMaTacGia, tg.getMaTacGia())) {
				DAOTacGia.deleteSach(tg.getMaTacGia(), id);
			}
		}
		return "redirect:sach.htm?tacgiacuasach=" + id;
	}
	
	@RequestMapping(value = "sach", params = "delete")
	public String batModalDelete(@RequestParam("delete") int id, RedirectAttributes red) {
		red.addFlashAttribute("delete", "BẠN CÓ CHẮC XÓA SÁCH NÀY KHÔNG??");
		red.addFlashAttribute("idDelete", id);
		red.addFlashAttribute("act", "xoasach");
		red.addFlashAttribute("modal", "delete");
		red.addFlashAttribute("page", "page-product");
		return "redirect:/admin.htm";
	}
	
	@RequestMapping(value = "xoasach")
	public String xoaSach(@RequestParam("id") int id,RedirectAttributes red) {
		Sach sach = DAOSach.getSach(id);
		if(sach.getDsChiTietPhieuNhap().isEmpty()) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Sách này đã có phiếu nhập");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
		}
		if(DAOSach.deleteSach(sach,sach.getDsTheLoai(),sach.getDsTacGia())) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thành Công");
			red.addFlashAttribute("message", "Xóa thành công");
			red.addFlashAttribute("type", "success");
			red.addFlashAttribute("duration", 5000);
		}
		else {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất Bại");
			red.addFlashAttribute("message", "Xóa thất bại");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
		}
		red.addFlashAttribute("page", "page-product");
		return "redirect:/admin.htm";
	}
}
