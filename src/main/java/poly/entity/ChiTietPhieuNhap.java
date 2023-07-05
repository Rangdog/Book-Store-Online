package poly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CT_PN")
public class ChiTietPhieuNhap implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name = "ID_PN")
	private PhieuNhap phieuNhap;
	@Id
	@ManyToOne
	@JoinColumn(name = "ID_SACH")
	private Sach sach;
	@Column(name = "SOLUONG")
	private int soLuong;
	@Column(name = "GIAGOC")
	private float giaGoc;

	public PhieuNhap getPhieuNhap() {
		return phieuNhap;
	}

	public void setPhieuNhap(PhieuNhap phieuNhap) {
		this.phieuNhap = phieuNhap;
	}

	public Sach getSach() {
		return sach;
	}

	public void setSach(Sach sach) {
		this.sach = sach;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public float getGiaGoc() {
		return giaGoc;
	}

	public void setGiaGoc(float giaGoc) {
		this.giaGoc = giaGoc;
	}
}
