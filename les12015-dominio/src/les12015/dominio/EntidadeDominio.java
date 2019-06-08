package les12015.dominio;

public class EntidadeDominio implements IEntidade{
	
	private Integer id;
	private String stat;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	
}
