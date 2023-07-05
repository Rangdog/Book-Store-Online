package poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.entity.*;
import poly.dao.DAOInterface.*;

@Controller
public class ControllerAdmin {
	private NhanVien logMember;
	@Autowired
	private NhanVienInterface DAONhanVien;
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
	@Autowired
	private KhachHangInterface DAOKhachHang;
	@Autowired
	private SachInterface DAOSach;
	@Autowired
	private KhuyenMaiInterface DAOKhuyenMai;
	@Autowired
	private PhieuNhapInterface DAOPhieuNhap;
	@Autowired
	private DonHangInterface DAODonHang;

	@RequestMapping(value = "admin")
	public String viewAdminPage(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String tenTaiKhoan = authentication.getName();
		logMember = DAONhanVien.getNhanVienByTenTaiKhoan(tenTaiKhoan);
		model.addAttribute("acc", logMember);
		return "admin/admin";
	}

	@ModelAttribute("nhanViens")
	public List<NhanVien> NhanVien() {
		return DAONhanVien.listNhanVien();
	}

	@ModelAttribute("danhMucs")
	public List<DanhMuc> DanhMuc() {
		return DAODanhMuc.listDanhMuc();
	}

	@ModelAttribute("theLoais")
	public List<TheLoai> TheLoai() {
		return DAOTheLoai.listTheLoai();
	}

	@ModelAttribute("nhaXuatBans")
	public List<NhaXuatBan> NhaXuatBan() {
		return DAONhaXuatBan.listNhaXuatBan();
	}

	@ModelAttribute("nhaPhatHanhs")
	public List<NhaPhatHanh> nhaPhatHanh() {
		return DAONhaPhatHanh.listNhaPhatHanh();
	}

	@ModelAttribute("tacGias")
	public List<TacGia> TacGia() {
		return DAOTacGia.listTacGia();
	}

	@ModelAttribute("khachHangs")
	public List<KhachHang> KhachHang() {
		return DAOKhachHang.listKhachHang();
	}

	@ModelAttribute("Sachs")
	public List<Sach> Sach() {
		return DAOSach.listSach();
	}

	@ModelAttribute("khuyenMais")
	public List<KhuyenMai> KhuyenMai() {
		return DAOKhuyenMai.listKhuyenMai();
	}
	
	@ModelAttribute("phieuNhaps")
	public List<PhieuNhap> PhieuNhap() {
		return DAOPhieuNhap.listPhieuNhap();
	}
	
	@ModelAttribute("donHangs")
	public List<DonHang> DonHang() {
		return DAODonHang.listDonHang();
	}
}
