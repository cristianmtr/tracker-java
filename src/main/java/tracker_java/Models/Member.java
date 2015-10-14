package tracker_java.Models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the member database table.
 * 
 */
@Entity
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"memberId\"")
	private Integer memberId;

	private String activation;

	@Column(name="\"authorId\"")
	private Integer authorId;

	@Column(name="\"autoLogin\"")
	private Integer autoLogin;

	@Column(name="\"badAccess\"")
	private Integer badAccess;

	private String city;

	@Column(name="\"countryId\"")
	private String countryId;

	@Column(name="\"creationDate\"")
	private Timestamp creationDate;

	private String email;

	private Integer enabled;

	@Column(name="\"expirationDate\"")
	private Timestamp expirationDate;

	@Column(name="\"firstName\"")
	private String firstName;

	@Column(name="\"lastChangeDate\"")
	private Timestamp lastChangeDate;

	@Column(name="\"lastLoginAddress\"")
	private String lastLoginAddress;

	@Column(name="\"lastLoginDate\"")
	private Timestamp lastLoginDate;

	@Column(name="\"lastName\"")
	private String lastName;

	private Integer level;

	private String password;

	private String phone;

	private String salt;

	@Column(name="\"timeZone\"")
	private Integer timeZone;

	private String title;

	private String username;

	private Integer visits;

	public Member() {
	}

	public Integer getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getActivation() {
		return this.activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	public Integer getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getAutoLogin() {
		return this.autoLogin;
	}

	public void setAutoLogin(Integer autoLogin) {
		this.autoLogin = autoLogin;
	}

	public Integer getBadAccess() {
		return this.badAccess;
	}

	public void setBadAccess(Integer badAccess) {
		this.badAccess = badAccess;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryId() {
		return this.countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Timestamp getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Timestamp getLastChangeDate() {
		return this.lastChangeDate;
	}

	public void setLastChangeDate(Timestamp lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public String getLastLoginAddress() {
		return this.lastLoginAddress;
	}

	public void setLastLoginAddress(String lastLoginAddress) {
		this.lastLoginAddress = lastLoginAddress;
	}

	public Timestamp getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getTimeZone() {
		return this.timeZone;
	}

	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getVisits() {
		return this.visits;
	}

	public void setVisits(Integer visits) {
		this.visits = visits;
	}

}