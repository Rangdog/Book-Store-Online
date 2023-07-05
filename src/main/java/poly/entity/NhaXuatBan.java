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
@Table(name = "NHAXUATBAN")
public class NhaXuatBan implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_NXB")
	private int maNXB;
	@Column(name = "TENNXB")
	private String tenNXB;
	@OneToMany(mappedBy = "nhaXuatBan")
	private Set<Sach> dsSach = new HashSet<>();

	public Set<Sach> getDsSach() {
		return dsSach;
	}

	public void setDsSach(Set<Sach> dsSach) {
		this.dsSach = dsSach;
	}

	public int getMaNXB() {
		return maNXB;
	}

	public void setMaNXB(int maNXB) {
		this.maNXB = maNXB;
	}

	public String getTenNXB() {
		return tenNXB;
	}

	public void setTenNXB(String tenNXB) {
		this.tenNXB = tenNXB;
	}
}
