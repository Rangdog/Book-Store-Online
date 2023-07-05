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
@Table(name = "NHAPHATHANH")
public class NhaPhatHanh implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_NPH")
	private int maNPH;
	@Column(name = "TENNPH")
	private String tenNPH;
	@OneToMany(mappedBy = "nhaPhatHanh")
	private Set<Sach> dsSach = new HashSet<>();

	public Set<Sach> getDsSach() {
		return dsSach;
	}

	public void setDsSach(Set<Sach> dsSach) {
		this.dsSach = dsSach;
	}

	public int getMaNPH() {
		return maNPH;
	}

	public void setMaNPH(int maNPH) {
		this.maNPH = maNPH;
	}

	public String getTenNPH() {
		return tenNPH;
	}

	public void setTenNPH(String tenNPH) {
		this.tenNPH = tenNPH;
	}
}
