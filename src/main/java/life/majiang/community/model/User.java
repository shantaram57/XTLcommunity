package life.majiang.community.model;

public class User {
	private Integer id;
	private String name;
	private String accountid;
	private String token;
	private Long gmtcreate;
	private Long gmtModified;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getGmtcreate() {
		return gmtcreate;
	}

	public void setGmtcreate(Long gmtcreate) {
		this.gmtcreate = gmtcreate;
	}

	public Long getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Long gmtModified) {
		this.gmtModified = gmtModified;
	}
}
