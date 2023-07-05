package poly.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PHIEUXUAT")
public class PhieuXuat implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PX")
	private int maPhieuXuat;
	@Column(name = "NGAY")
	@Temporal(TemporalType.DATE)
	private Date ngay;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_DH")
	private DonHang donHang;
	@ManyToOne
	@JoinColumn(name = "ID_NV")
	private NhanVien nhanVien;
	@OneToOne(mappedBy = "phieuXuat")
	private PhieuTra phieuTra;

	public PhieuTra getPhieuTra() {
		return phieuTra;
	}

	public void setPhieuTra(PhieuTra phieuTra) {
		this.phieuTra = phieuTra;
	}

	public DonHang getDonHang() {
		return donHang;
	}

	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public int getMaPhieuXuat() {
		return maPhieuXuat;
	}

	public void setMaPhieuXuat(int maPhieuXuat) {
		this.maPhieuXuat = maPhieuXuat;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
}
