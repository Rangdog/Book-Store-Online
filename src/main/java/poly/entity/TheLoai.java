package poly.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "THELOAI")
public class TheLoai implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TL")
	private int maTheLoai;
	@Column(name = "TENTL")
	private String tenTheLoai;
	@ManyToOne
	@JoinColumn(name = "ID_DM")
	private DanhMuc danhMuc;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CT_TL", joinColumns = { @JoinColumn(name = "ID_TL") }, inverseJoinColumns = { @JoinColumn(name = "ID_SACH") })
	private Set<Sach> dsSach = new HashSet<>();

	public Set<Sach> getDsSach() {
		return dsSach;
	}

	public void setDsSach(Set<Sach> dsSach) {
		this.dsSach = dsSach;
	}

	public DanhMuc getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(DanhMuc danhMuc) {
		this.danhMuc = danhMuc;
	}

	public int getMaTheLoai() {
		return maTheLoai;
	}

	public void setMaTheLoai(int maTheLoai) {
		this.maTheLoai = maTheLoai;
	}

	public String getTenTheLoai() {
		return tenTheLoai;
	}

	public void setTenTheLoai(String tenTheLoai) {
		this.tenTheLoai = tenTheLoai;
	}
}
