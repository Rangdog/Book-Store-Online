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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PHIEUNHAP")
public class PhieuNhap implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PN")
	private int maPhieuNhap;
	@Column(name = "NGAY")
	@Temporal(TemporalType.DATE)
	private Date ngay;
	@Column(name = "NHACC")
	private String nhaCungCap;
	@ManyToOne
	@JoinColumn(name = "ID_NV")
	private NhanVien nhanVien;
	@OneToMany(mappedBy = "phieuNhap", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ChiTietPhieuNhap> dsChiTietPhieuNhap = new HashSet<>();

	public Set<ChiTietPhieuNhap> getDsChiTietPhieuNhap() {
		return dsChiTietPhieuNhap;
	}

	public void setDsChiTietPhieuNhap(Set<ChiTietPhieuNhap> dsChiTietPhieuNhap) {
		this.dsChiTietPhieuNhap = dsChiTietPhieuNhap;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public int getMaPhieuNhap() {
		return maPhieuNhap;
	}

	public void setMaPhieuNhap(int maPhieuNhap) {
		this.maPhieuNhap = maPhieuNhap;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public String getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
}
