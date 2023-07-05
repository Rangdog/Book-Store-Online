package poly.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.dao.DAOInterface.DiaChiGiaoHangInterface;
import poly.dao.DAOInterface.KhachHangInterface;
import poly.entity.DiaChiGiaoHang;
import poly.entity.KhachHang;

@Controller
public class ControllerDiaChi {
	@Autowired
	private DiaChiGiaoHangInterface DAODiaChiGiaoHang;
	@Autowired
	private KhachHangInterface DAOKhachHang;

	@RequestMapping("address")
	public String address(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		KhachHang khachHang = DAOKhachHang.findByUsername(authentication.getName());
		model.addAttribute("diaChis",  DAODiaChiGiaoHang.listDCGH(khachHang.getMaKhachHang()));
		return "user/dia_chi";
	}

	@RequestMapping(value = "themdiachi", method = RequestMethod.POST)
	public String themDiaChi(@RequestParam("tenNN") String tenNN, @RequestParam("sdtNN") String sdtNN,
			@RequestParam("diaChi") String diaChi,
			@RequestParam(value = "trangThai", required = false) Integer trangThai) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		KhachHang khachHang = DAOKhachHang.findByUsername(authentication.getName());
		DiaChiGiaoHang dcgh = new DiaChiGiaoHang();
		dcgh.setDiaChi(diaChi);
		dcgh.setTenNN(tenNN);
		dcgh.setSdtNN(sdtNN);
		Set<DiaChiGiaoHang> list =  khachHang.getDsDiaChiGiaoHang();
		if (list.isEmpty()) {
			trangThai = 1;
		} else {
			if (trangThai == null) {
				trangThai = 0;
			} else {
				for (DiaChiGiaoHang diaChiGiaoHang : list) {
					diaChiGiaoHang.setTrangThai(0);
					DAODiaChiGiaoHang.updateDCGH(diaChiGiaoHang.getMaDiaChi(), diaChiGiaoHang.getTenNN(),
							diaChiGiaoHang.getSdtNN(), diaChiGiaoHang.getDiaChi(), diaChiGiaoHang.getTrangThai());
				}
			}
		}
		dcgh.setTrangThai(trangThai);
		dcgh.setKhachHang(khachHang);
		DAODiaChiGiaoHang.insertDCGH(dcgh);

		return "redirect:address.htm";
	}

	@RequestMapping(value = "xemdiachi")
	public String xemDiaChi(@RequestParam("id") int id, RedirectAttributes red) {
		DiaChiGiaoHang dcgh = DAODiaChiGiaoHang.getDCGH(id);

		red.addFlashAttribute("diaChiUpdate", dcgh);
		red.addFlashAttribute("modal", "modal-update");
		return "redirect:address.htm";
	}

	@RequestMapping(value = "suadiachi", method = RequestMethod.POST)
	public String suaDiaChi(@RequestParam("tenNN") String tenNN, @RequestParam("sdtNN") String sdtNN,
			@RequestParam("diaChi") String diaChi, @RequestParam("id") int maDiaChi) {
		DAODiaChiGiaoHang.updateDCGH(maDiaChi, tenNN, sdtNN, diaChi, DAODiaChiGiaoHang.getDCGH(maDiaChi).getTrangThai());
		return "redirect:address.htm";
	}

	@RequestMapping(value = "thietlapmacdinh")
	public String diaChiMacDinh(@RequestParam("id") int id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		KhachHang khachHang = DAOKhachHang.findByUsername(authentication.getName());
		DiaChiGiaoHang dcgh = DAODiaChiGiaoHang.getDCGH(id);
		Set<DiaChiGiaoHang> list =  khachHang.getDsDiaChiGiaoHang();
		for (DiaChiGiaoHang diaChiGiaoHang : list) {
			diaChiGiaoHang.setTrangThai(0);
			DAODiaChiGiaoHang.updateDCGH(diaChiGiaoHang.getMaDiaChi(), diaChiGiaoHang.getTenNN(),
					diaChiGiaoHang.getSdtNN(), diaChiGiaoHang.getDiaChi(), diaChiGiaoHang.getTrangThai());
		}
		dcgh.setTrangThai(1);
		DAODiaChiGiaoHang.updateDCGH(id, dcgh.getTenNN(), dcgh.getSdtNN(), dcgh.getDiaChi(), dcgh.getTrangThai());
		return "redirect:address.htm";
	}

	@RequestMapping(value = "xoadiachi")
	public String xoaDiaChi(@RequestParam("id") int id, RedirectAttributes red) {
		if (DAODiaChiGiaoHang.deleteDCGH(id)) {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thành Công");
			red.addFlashAttribute("message", "Xóa thành công");
			red.addFlashAttribute("type", "success");
			red.addFlashAttribute("duration", 5000);
		} else {
			red.addFlashAttribute("toaston", "1");
			red.addFlashAttribute("title", "Thất bại");
			red.addFlashAttribute("message", "Xóa thất bại");
			red.addFlashAttribute("type", "error");
			red.addFlashAttribute("duration", 5000);
		}
		return "redirect:address.htm";
	}

	@RequestMapping(value = "deletediachi")
	public String deleteDiaChi(@RequestParam("id") int id, RedirectAttributes red) {
		red.addFlashAttribute("delete", "BẠN CÓ MUỐN XÓA ĐỊA CHỈ NÀY KHÔNG?");
		red.addFlashAttribute("act", "xoadiachi");
		red.addFlashAttribute("idDelete", id);
		red.addFlashAttribute("modal", "delete");
		return "redirect:address.htm";
	}
}
