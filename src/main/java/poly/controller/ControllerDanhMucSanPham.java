package poly.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.dao.DAOInterface.DanhMucInterface;
import poly.dao.DAOInterface.NhaXuatBanInterface;
import poly.dao.DAOInterface.SachInterface;
import poly.dao.DAOInterface.TheLoaiInterface;
import poly.entity.DanhMuc;
import poly.entity.NhaXuatBan;
import poly.entity.TheLoai;

@Controller
public class ControllerDanhMucSanPham {
	@Autowired
	private DanhMucInterface DAODanhMuc;
	@Autowired
	private TheLoaiInterface DAOTheLoai;
	@Autowired
	private NhaXuatBanInterface DAONhaXuatBan;
	@Autowired
	private SachInterface DAOSach;
	
	@RequestMapping("danhmuc")
	public String DanhMuc(ModelMap model, @RequestParam(value = "maDanhMuc", required = false, defaultValue = "-1") int maDanhMuc,
			@RequestParam(value = "gia", required = false,defaultValue ="-1") int gia,@RequestParam(value = "tenTheLoai", required = false, defaultValue = "-1") int maTheLoai,
			@RequestParam(value = "nhaXuatBan", required = false, defaultValue = "-1") int maNXB,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "timKiem",required = false, defaultValue = "") String timKiem,
			@RequestParam(value = "arrange", required = false, defaultValue = "-1") int sortby
			) {
		Float bd;
		Float kt;
		if(gia == 0) {
			bd = (float) 0;
			kt = (float) 50000;
		}
		else if(gia == 1) {
			bd = (float) 50000;
			kt = (float) 200000;
		}
		else if(gia == 2) {
			bd = (float) 200000;
			kt = (float) 500000;
		}
		else if(gia == 3){
			bd = (float) 500000;
			kt = (float) 99999999;
		}
		else {
			bd = kt = (float) -1;
		}
		List<DanhMuc> danhMucs = DAODanhMuc.listDanhMuc();
		List<TheLoai> theLoais = new ArrayList<>();
		List<Object[]> sachs =  new ArrayList<>();
		if(maDanhMuc == -1) {
			theLoais = DAOTheLoai.listTheLoai();
		}
		else {
			theLoais = DAOTheLoai.listTheLoaitheoDanhMuc(maDanhMuc);
		}
		List<NhaXuatBan> nhaXuatBans = DAONhaXuatBan.listNhaXuatBan();
		if(maDanhMuc == -1 && maNXB == -1 && maTheLoai == -1 && gia == -1 && timKiem.isEmpty()) {
			sachs = DAOSach.listSachNhanh();
		}else {
			sachs = DAOSach.sortDanhMuc(maDanhMuc, bd, kt, maTheLoai, maNXB,timKiem);
		}
		int pageSize = 0;
		if(sachs.size()%16 == 0) {
			pageSize = (sachs.size()/16);
		}
		else {
			pageSize = (sachs.size()/16) + 1;
		}
		List<Object[]> sachOfPage =  new ArrayList<>();
		for(int i = (16*(page-1));  i < (16*(page)); i++) {
			if(i < sachs.size()) {
				sachOfPage.add(sachs.get(i));
			}
		}
		//Sắp Xếp
		if(sortby == 0) {
			Collections.sort(sachOfPage,Comparator.comparing(e->(String)e[2]));
		}
		else if (sortby == 1) {
			Collections.sort(sachOfPage,Comparator.comparingInt(e->(int)e[5]));
		}
		model.addAttribute("sachs", sachOfPage);
		model.addAttribute("danhMucs", danhMucs);
		if(!theLoais.isEmpty()) {
			model.addAttribute("theLoais", theLoais);
		}
		model.addAttribute("nhaXuatBans", nhaXuatBans);
		model.addAttribute("pageSize", pageSize);
		if(maDanhMuc != -1) {
			model.addAttribute("maDM", String.valueOf(maDanhMuc));
			model.addAttribute("cagetory","danhmuc"+String.valueOf(maDanhMuc));			
		}
		if(gia != -1) {
			model.addAttribute("mucgia",String.valueOf(gia));
			model.addAttribute("price",String.valueOf(gia));			
		}
		if(maTheLoai != -1) {
			model.addAttribute("maTL", String.valueOf(maTheLoai));
			model.addAttribute("genner","theloai"+String.valueOf(maTheLoai));			
		}
		if(maNXB != -1 ) {
			model.addAttribute("maNXB", String.valueOf(maNXB));
			model.addAttribute("NXB","nhaxuatban"+String.valueOf(maNXB));	
		}
		if(!timKiem.isEmpty()) {
			model.addAttribute("timKiemvalue", timKiem);
		}
		if(sortby != -1) {
			model.addAttribute("sortby", sortby);
		}
		model.addAttribute("page", page);
		if(pageSize == 0 || pageSize == 1) {
			model.addAttribute("huong", "leftright");
		}
		else if (page == pageSize){
			model.addAttribute("huong", "right");
		}
		else if(page == 1) {
			model.addAttribute("huong", "left");
		}
		return "user/danh_muc";
	}
}
