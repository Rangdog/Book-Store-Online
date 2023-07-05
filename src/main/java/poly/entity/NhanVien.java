package poly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "NHANVIEN")
public class NhanVien implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_NV")
	private int maNhanVien;
	@Column(name = "HO")
	private String ho;
	@Column(name = "TEN")
	private String ten;
	@Column(name = "GIOITINH")
	private Boolean gioiTinh;
	@Column(name = "NGAYSINH")
	@Temporal(TemporalType.DATE)
	private Date ngaySinh;
	@Column(name = "DIACHI")
	private String diaChi;
	@Column(name = "SDT")
	private String sDT;
	@Column(name = "LUONG")
	private float luong;
	@Column(name = "CMND")
	private String cmnd;
	@Column(name = "ANHDAIDIEN")
	private String anhDaiDien;
	@Column(name = "TRANGTHAIXOA")
	private Boolean trangThaiXoa;
	@ManyToOne
	@JoinColumn(name = "TENTAIKHOAN")
	private TaiKhoan taiKhoan;
	@OneToMany(mappedBy = "nhanVienDuyet")
	private Set<DonHang> dsDonHangDuyet = new HashSet<>();
	@OneToMany(mappedBy = "nhanVienGiao")
	private Set<DonHang> dsDonHangGiao = new HashSet<>();
	@OneToMany(mappedBy = "nhanVien")
	private Set<KhuyenMai> dsKhuyenMai = new HashSet<>();
	@OneToMany(mappedBy = "nhanVien")
	private Set<PhieuNhap> dsPhieuNhap = new HashSet<>();
	@OneToMany(mappedBy = "nhanVien")
	private Set<PhieuXuat> dsPhieuXuat = new HashSet<>();
	@OneToMany(mappedBy = "nhanVien")
	private Set<PhieuTra> dsPhieuTra = new HashSet<>();

	public Set<PhieuXuat> getDsPhieuXuat() {
		return dsPhieuXuat;
	}

	public void setDsPhieuXuat(Set<PhieuXuat> dsPhieuXuat) {
		this.dsPhieuXuat = dsPhieuXuat;
	}

	public Set<PhieuTra> getDsPhieuTra() {
		return dsPhieuTra;
	}

	public void setDsPhieuTra(Set<PhieuTra> dsPhieuTra) {
		this.dsPhieuTra = dsPhieuTra;
	}

	public Set<PhieuNhap> getDsPhieuNhap() {
		return dsPhieuNhap;
	}

	public void setDsPhieuNhap(Set<PhieuNhap> dsPhieuNhap) {
		this.dsPhieuNhap = dsPhieuNhap;
	}

	public Set<KhuyenMai> getDsKhuyenMai() {
		return dsKhuyenMai;
	}

	public void setDsKhuyenMai(Set<KhuyenMai> dsKhuyenMai) {
		this.dsKhuyenMai = dsKhuyenMai;
	}

	public Set<DonHang> getDsDonHangDuyet() {
		return dsDonHangDuyet;
	}

	public void setDsDonHangDuyet(Set<DonHang> dsDonHangDuyet) {
		this.dsDonHangDuyet = dsDonHangDuyet;
	}

	public Set<DonHang> getDsDonHangGiao() {
		return dsDonHangGiao;
	}

	public void setDsDonHangGiao(Set<DonHang> dsDonHangGiao) {
		this.dsDonHangGiao = dsDonHangGiao;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public int getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Boolean getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getsDT() {
		return sDT;
	}

	public void setsDT(String sDT) {
		this.sDT = sDT;
	}

	public float getLuong() {
		return luong;
	}

	public void setLuong(float luong) {
		this.luong = luong;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getAnhDaiDien() {
		return anhDaiDien;
	}

	public void setAnhDaiDien(String anhDaiDien) {
		this.anhDaiDien = anhDaiDien;
	}

	public Boolean getTrangThaiXoa() {
		return trangThaiXoa;
	}

	public void setTrangThaiXoa(Boolean trangThaiXoa) {
		this.trangThaiXoa = trangThaiXoa;
	}

	public String hoTenNhanVien() {
		return this.ho + " " + this.ten + " - " + String.valueOf(maNhanVien);
	}

	public String hoTen() {
		return this.ho + " " + this.ten;
	}

	public Boolean checkExistAnhDaiDien() {
		if (this.anhDaiDien == null || this.anhDaiDien.isEmpty()) {
			return false;
		}
		return true;
	}
}
