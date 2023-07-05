package poly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SACH")
public class Sach implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SACH")
	private int maSach;
	@Column(name = "TENSACH")
	private String tenSach;
	@Column(name = "KICHTHUOC")
	private int kichThuoc;
	@Column(name = "HINHTHUC")
	private String hinhThuc;
	@Column(name = "NGONNGU")
	private String ngonNgu;
	@Column(name = "SOTRANG")
	private int soTrang;
	@Column(name = "TRONGLUONG")
	private int trongLuong;
	@Column(name = "ANHDAIDIEN")
	private String anhDaiDien;
	@Column(name = "TOMTAT")
	private String tomTat;
	@Column(name = "GIABAN")
	private float giaBan;
	@Column(name = "GIAKM")
	private float giaKhuyenMai;
	@Column(name = "SOLUONGTON")
	private int soLuongTon;
	@Column(name = "LUOTMUA")
	private int luotMua;
	@Column(name = "TRANGTHAI")
	private int trangThai;
	@ManyToOne
	@JoinColumn(name = "ID_NXB")
	private NhaXuatBan nhaXuatBan;
	@ManyToOne
	@JoinColumn(name = "ID_NPH")
	private NhaPhatHanh nhaPhatHanh;
	@ManyToMany(mappedBy = "dsSach", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<TacGia> dsTacGia = new HashSet<>();
	@ManyToMany(mappedBy = "dsSach", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<TheLoai> dsTheLoai = new HashSet<>();
	@OneToMany(mappedBy = "sach")
	private Set<ChiTietGioHang> dsChiTietGioHang = new HashSet<>();
	@OneToMany(mappedBy = "sach")
	private Set<ChiTietDonHang> dsChiTietDonHang = new HashSet<>();
	@ManyToMany(mappedBy = "dsSach", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<KhuyenMai> dsKhuyenMai = new HashSet<>();
	@OneToMany(mappedBy = "sach", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ChiTietPhieuNhap> dsChiTietPhieuNhap = new HashSet<>();

	public Set<ChiTietPhieuNhap> getDsChiTietPhieuNhap() {
		return dsChiTietPhieuNhap;
	}

	public void setDsChiTietPhieuNhap(Set<ChiTietPhieuNhap> dsChiTietPhieuNhap) {
		this.dsChiTietPhieuNhap = dsChiTietPhieuNhap;
	}

	public Set<KhuyenMai> getDsKhuyenMai() {
		return dsKhuyenMai;
	}

	public void setDsKhuyenMai(Set<KhuyenMai> dsKhuyenMai) {
		this.dsKhuyenMai = dsKhuyenMai;
	}

	public Set<ChiTietGioHang> getDsChiTietGioHang() {
		return dsChiTietGioHang;
	}

	public void setDsChiTietGioHang(Set<ChiTietGioHang> dsChiTietGioHang) {
		this.dsChiTietGioHang = dsChiTietGioHang;
	}

	public Set<ChiTietDonHang> getDsChiTietDonHang() {
		return dsChiTietDonHang;
	}

	public void setDsChiTietDonHang(Set<ChiTietDonHang> dsChiTietDonHang) {
		this.dsChiTietDonHang = dsChiTietDonHang;
	}

	public Set<TheLoai> getDsTheLoai() {
		return dsTheLoai;
	}

	public void setDsTheLoai(Set<TheLoai> dsTheLoai) {
		this.dsTheLoai = dsTheLoai;
	}

	public Set<TacGia> getDsTacGia() {
		return dsTacGia;
	}

	public void setDsTacGia(Set<TacGia> dsTacGia) {
		this.dsTacGia = dsTacGia;
	}

	public NhaXuatBan getNhaXuatBan() {
		return nhaXuatBan;
	}

	public void setNhaXuatBan(NhaXuatBan nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}

	public NhaPhatHanh getNhaPhatHanh() {
		return nhaPhatHanh;
	}

	public void setNhaPhatHanh(NhaPhatHanh nhaPhatHanh) {
		this.nhaPhatHanh = nhaPhatHanh;
	}

	public int getMaSach() {
		return maSach;
	}

	public void setMaSach(int maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public int getKichThuoc() {
		return kichThuoc;
	}

	public void setKichThuoc(int kichThuoc) {
		this.kichThuoc = kichThuoc;
	}

	public String getHinhThuc() {
		return hinhThuc;
	}

	public void setHinhThuc(String hinhThuc) {
		this.hinhThuc = hinhThuc;
	}

	public String getNgonNgu() {
		return ngonNgu;
	}

	public void setNgonNgu(String ngonNgu) {
		this.ngonNgu = ngonNgu;
	}

	public int getSoTrang() {
		return soTrang;
	}

	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}

	public int getTrongLuong() {
		return trongLuong;
	}

	public void setTrongLuong(int trongLuong) {
		this.trongLuong = trongLuong;
	}

	public String getAnhDaiDien() {
		return anhDaiDien;
	}

	public void setAnhDaiDien(String anhDaiDien) {
		this.anhDaiDien = anhDaiDien;
	}

	public String getTomTat() {
		return tomTat;
	}

	public void setTomTat(String tomTat) {
		this.tomTat = tomTat;
	}

	public float getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}

	public float getGiaKhuyenMai() {
		return giaKhuyenMai;
	}

	public void setGiaKhuyenMai(float giaKhuyenMai) {
		this.giaKhuyenMai = giaKhuyenMai;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public int getLuotMua() {
		return luotMua;
	}

	public void setLuotMua(int luotMua) {
		this.luotMua = luotMua;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	
	public Float getGiaSach() {
		return giaKhuyenMai == -1 ? giaBan : giaKhuyenMai;
	}

	public Float tinhKhuyenMaiThucTeMotNgay() {
		float ketQua = 0;
		if (this.dsKhuyenMai.isEmpty()) {
			return ketQua;
		}
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		for (KhuyenMai khuyenMai : this.dsKhuyenMai) {
			if (date.after(khuyenMai.getNgayBatDau()) && date.before(khuyenMai.getNgayKetThuc())) {
				ketQua += khuyenMai.getPhanTram();
			}
		}
		return ketQua;
	}

	public Boolean checkTheLoai(TheLoai theLoai) {
		if (this.getDsTheLoai() == null) {
			return false;
		}
		for (TheLoai tl : this.getDsTheLoai()) {
			if (tl.getMaTheLoai() == theLoai.getMaTheLoai()) {
				return true;
			}
		}
		return false;
	}

	public Boolean checkTacGia(TacGia tacGia) {
		if (this.getDsTheLoai() == null) {
			return false;
		}
		for (TacGia tg : this.getDsTacGia()) {
			if (tg.getMaTacGia() == tacGia.getMaTacGia()) {
				return true;
			}
		}
		return false;
	}
}
