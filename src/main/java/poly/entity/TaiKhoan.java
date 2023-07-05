package poly.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TAIKHOAN")
public class TaiKhoan implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "TENTAIKHOAN")
	private String tenTaiKhoan;
	@Column(name = "MATKHAU")
	private String matKhau;
	@Column(name = "TRANGTHAI")
	private Boolean trangThai;
	@Column(name = "QUYEN")
	private String quyen;
	@OneToMany(mappedBy = "taiKhoan")
	private Set<NhanVien> dsNhanVien = new HashSet<>();
	@OneToOne(mappedBy = "taiKhoan")
	private KhachHang khachHang;

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Set<NhanVien> getDsNhanVien() {
		return dsNhanVien;
	}

	public void setDsNhanVien(Set<NhanVien> dsNhanVien) {
		this.dsNhanVien = dsNhanVien;
	}

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}

	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}
}
