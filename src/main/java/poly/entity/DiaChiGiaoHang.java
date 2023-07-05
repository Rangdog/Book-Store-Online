package poly.entity;

import java.io.Serializable;
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

@Entity
@Table(name = "DIACHIGIAOHANG")
public class DiaChiGiaoHang implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DC")
	private int maDiaChi;
	@Column(name = "DIACHI")
	private String diaChi;
	@Column(name = "TENN")
	private String tenNN;
	@Column(name = "SDTNN")
	private String sdtNN;
	@Column(name = "TRANGTHAI")
	private int trangThai;
	@ManyToOne
	@JoinColumn(name = "ID_KH")
	private KhachHang khachHang;
	@OneToMany(mappedBy = "diaChiGiaoHang")
	private Set<DonHang> dsDonHang = new HashSet<>();

	public Set<DonHang> getDsDonHang() {
		return dsDonHang;
	}

	public void setDsDonHang(Set<DonHang> dsDonHang) {
		this.dsDonHang = dsDonHang;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public int getMaDiaChi() {
		return maDiaChi;
	}

	public void setMaDiaChi(int maDiaChi) {
		this.maDiaChi = maDiaChi;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getTenNN() {
		return tenNN;
	}

	public void setTenNN(String tenNN) {
		this.tenNN = tenNN;
	}

	public String getSdtNN() {
		return sdtNN;
	}

	public void setSdtNN(String sdtNN) {
		this.sdtNN = sdtNN;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
}
