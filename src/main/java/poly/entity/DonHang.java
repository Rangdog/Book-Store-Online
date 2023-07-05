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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "DONHANG")
public class DonHang implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String[] statusNames = { "Chờ duyệt", "Đã duyệt", "Đã hủy", "Đang giao", "Hoàn thành", "Hoàn trả" };
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DH")
	private int maDonHang;
	@Column(name = "NGAY")
	@Temporal(TemporalType.DATE)
	private Date ngay;
	@Column(name = "PHIVANCHUYEN")
	private float phiVanChuyen;
	@Column(name = "TRANGTHAI")
	private int trangThai;
	@ManyToOne
	@JoinColumn(name = "ID_DC")
	private DiaChiGiaoHang diaChiGiaoHang;
	@ManyToOne
	@JoinColumn(name = "ID_NV_DUYET")
	private NhanVien nhanVienDuyet;
	@ManyToOne
	@JoinColumn(name = "ID_NV_GIAO")
	private NhanVien nhanVienGiao;
	@OneToOne(mappedBy = "donHang")
	private PhieuXuat phieuXuat;
	@OneToMany(mappedBy = "donHang", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ChiTietDonHang> dsChiTietDonHang = new HashSet<>();

	public Set<ChiTietDonHang> getDsChiTietDonHang() {
		return dsChiTietDonHang;
	}

	public void setDsChiTietDonHang(Set<ChiTietDonHang> dsChiTietDonHang) {
		this.dsChiTietDonHang = dsChiTietDonHang;
	}

	public PhieuXuat getPhieuXuat() {
		return phieuXuat;
	}

	public void setPhieuXuat(PhieuXuat phieuXuat) {
		this.phieuXuat = phieuXuat;
	}

	public DiaChiGiaoHang getDiaChiGiaoHang() {
		return diaChiGiaoHang;
	}

	public void setDiaChiGiaoHang(DiaChiGiaoHang diaChiGiaoHang) {
		this.diaChiGiaoHang = diaChiGiaoHang;
	}

	public NhanVien getNhanVienDuyet() {
		return nhanVienDuyet;
	}

	public void setNhanVienDuyet(NhanVien nhanVienDuyet) {
		this.nhanVienDuyet = nhanVienDuyet;
	}

	public NhanVien getNhanVienGiao() {
		return nhanVienGiao;
	}

	public void setNhanVienGiao(NhanVien nhanVienGiao) {
		this.nhanVienGiao = nhanVienGiao;
	}

	public int getMaDonHang() {
		return maDonHang;
	}

	public void setMaDonHang(int maDonHang) {
		this.maDonHang = maDonHang;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public float getPhiVanChuyen() {
		return phiVanChuyen;
	}

	public void setPhiVanChuyen(float phiVanChuyen) {
		this.phiVanChuyen = phiVanChuyen;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public String getTenTrangThai() {
		return statusNames[trangThai];
	}
	
	public float getTongTienHang() {
		return (float) dsChiTietDonHang.stream().mapToDouble(e -> e.getSoLuong() * e.getSach().getGiaSach()).sum();
	}
}
