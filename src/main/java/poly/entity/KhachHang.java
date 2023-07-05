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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "KHACHHANG")
public class KhachHang implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_KH")
	private int maKhachHang;
	@Column(name = "HO")
	private String ho;
	@Column(name = "TEN")
	private String ten;
	@Column(name = "GIOITINH")
	private Boolean gioiTinh;
	@Column(name = "NGAYSINH")
	@Temporal(TemporalType.DATE)
	private Date ngaySinh;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "TT_EMAIL")
	private Boolean trangThaiEmail;
	@Column(name = "ANHDAIDIEN")
	private String anhDaiDien;
	@Column(name = "VERIFICATION_CODE")
	private String verificationCode;
	@Column(name = "VERIFICATION_TIME")
	@Temporal(TemporalType.DATE)
	private Date verificationTime;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TENTAIKHOAN")
	private TaiKhoan taiKhoan;
	@OneToMany(mappedBy = "khachHang", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DiaChiGiaoHang> dsDiaChiGiaoHang = new HashSet<>();
	@OneToMany(mappedBy = "khachHang", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ChiTietGioHang> dsChiTietGioHang = new HashSet<>();

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Date getVerificationTime() {
		return verificationTime;
	}

	public void setVerificationTime(Date verificationTime) {
		this.verificationTime = verificationTime;
	}

	public Set<ChiTietGioHang> getDsChiTietGioHang() {
		return dsChiTietGioHang;
	}

	public void setDsChiTietGioHang(Set<ChiTietGioHang> dsChiTietGioHang) {
		this.dsChiTietGioHang = dsChiTietGioHang;
	}


	public Set<DiaChiGiaoHang> getDsDiaChiGiaoHang() {
		return dsDiaChiGiaoHang;
	}

	public void setDsDiaChiGiaoHang(Set<DiaChiGiaoHang> dsDiaChiGiaoHang) {
		this.dsDiaChiGiaoHang = dsDiaChiGiaoHang;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public int getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getTrangThaiEmail() {
		return trangThaiEmail;
	}

	public void setTrangThaiEmail(Boolean trangThaiEmail) {
		this.trangThaiEmail = trangThaiEmail;
	}

	public String getAnhDaiDien() {
		return anhDaiDien;
	}

	public void setAnhDaiDien(String anhDaiDien) {
		this.anhDaiDien = anhDaiDien;
	}
	
	public String getHoTen() {
		return ho == null || ten == null ? String.format("Khách hàng #%d", maKhachHang) : String.format("%s %s", ho, ten);
	}
	
	public Boolean checkExistAnhDaiDien() {
		if(this.anhDaiDien == null || this.anhDaiDien.isEmpty()) {
			return false;
		}
		return true;
	}
}
