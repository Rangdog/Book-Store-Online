package poly.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KHUYENMAI")
public class KhuyenMai implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_KM")
	private int maKhuyenMai;
	@Column(name = "NGAYBATDAU")
	private Date ngayBatDau;
	@Column(name = "NGAYKETTHUC")
	private Date ngayKetThuc;
	@Column(name = "PHANTRAM")
	private float phanTram;
	@ManyToOne
	@JoinColumn(name = "ID_NV")
	private NhanVien nhanVien;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "CT_KM", joinColumns = @JoinColumn(name = "ID_KM"), inverseJoinColumns = @JoinColumn(name = "ID_SACH"))
	private Set<Sach> dsSach = new HashSet<>();

	public Set<Sach> getDsSach() {
		return dsSach;
	}

	public void setDsSach(Set<Sach> dsSach) {
		this.dsSach = dsSach;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public int getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public void setMaKhuyenMai(int maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public float getPhanTram() {
		return phanTram;
	}

	public void setPhanTram(float phanTram) {
		this.phanTram = phanTram;
	}

	public String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dfm = formatter.format(date);
		return dfm;
	}

	public Boolean checkexistSach(int maSach) {
		for (Sach sach : this.dsSach) {
			if (sach.getMaSach() == maSach) {
				return true;
			}
		}
		return false;
	}
}
