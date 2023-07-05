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
import javax.persistence.Table;

@Entity
@Table(name = "TACGIA")
public class TacGia implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TG")
	private int maTacGia;
	@Column(name = "TENTG")
	private String tenTacGia;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CT_TG", joinColumns = { @JoinColumn(name = "ID_TG") }, inverseJoinColumns = { @JoinColumn(name = "ID_SACH") })
	private Set<Sach> dsSach = new HashSet<>();

	public Set<Sach> getDsSach() {
		return dsSach;
	}

	public void setDsSach(Set<Sach> dsSach) {
		this.dsSach = dsSach;
	}

	public int getMaTacGia() {
		return maTacGia;
	}

	public void setMaTacGia(int maTacGia) {
		this.maTacGia = maTacGia;
	}

	public String getTenTacGia() {
		return tenTacGia;
	}

	public void setTenTacGia(String tenTacGia) {
		this.tenTacGia = tenTacGia;
	}
}
