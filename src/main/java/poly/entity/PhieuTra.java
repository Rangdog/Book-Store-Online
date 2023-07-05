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
@Table(name = "PHIEUTRA")
public class PhieuTra implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PT")
	private int maPhieuTra;
	@Column(name = "NGAY")
	@Temporal(TemporalType.DATE)
	private Date ngay;
	@Column(name = "LYDO")
	private String lyDo;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_PX")
	private PhieuXuat phieuXuat;
	@ManyToOne
	@JoinColumn(name = "ID_NV")
	private NhanVien nhanVien;

	public PhieuXuat getPhieuXuat() {
		return phieuXuat;
	}

	public void setPhieuXuat(PhieuXuat phieuXuat) {
		this.phieuXuat = phieuXuat;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public int getMaPhieuTra() {
		return maPhieuTra;
	}

	public void setMaPhieuTra(int maPhieuTra) {
		this.maPhieuTra = maPhieuTra;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public String getLyDo() {
		return lyDo;
	}

	public void setLyDo(String lyDo) {
		this.lyDo = lyDo;
	}

}
