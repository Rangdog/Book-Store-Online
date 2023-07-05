package poly.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DANHMUC")
public class DanhMuc implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DM")
	private int maDanhMuc;
	@Column(name = "TENDM")
	private String tenDanhMuc;
	@OneToMany(mappedBy = "danhMuc")
	private Set<TheLoai> dsTheLoai = new HashSet<>();

	public Set<TheLoai> getDsTheLoai() {
		return dsTheLoai;
	}

	public void setDsTheLoai(Set<TheLoai> dsTheLoai) {
		this.dsTheLoai = dsTheLoai;
	}

	public int getMaDanhMuc() {
		return maDanhMuc;
	}

	public void setMaDanhMuc(int maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}
}
