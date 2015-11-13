package tracker_java.Models;

import javax.persistence.*;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;

/**
 * Created by cristian on 11/1/15.
 */
@Entity
@javax.persistence.Table(name = "MEMBER", schema = "public", catalog = "tracker")
public class MemberEntity {
    private Integer memberid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "MEMBERID", unique=true, insertable=false)
    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    @FormField
    private String email;

    @Basic
    @javax.persistence.Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @FormField
    private String firstname;

    @Basic
    @javax.persistence.Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @FormField
    private String lastname;

    @Basic
    @javax.persistence.Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private String city;

    @Basic
    @javax.persistence.Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String countryid;

    @Basic
    @javax.persistence.Column(name = "COUNTRYID")
    public String getCountryid() {
        return countryid;
    }

    public void setCountryid(String countryid) {
        this.countryid = countryid;
    }

    private String phone;

    @Basic
    @javax.persistence.Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @FormField
    private String username;

    @Basic
    @javax.persistence.Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FormField
    private String password;

    @Basic
    @javax.persistence.Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Integer autologin;

    @Basic
    @javax.persistence.Column(name = "AUTOLOGIN")
    public Integer getAutologin() {
        return autologin;
    }

    public void setAutologin(Integer autologin) {
        this.autologin = autologin;
    }

    private Short timezone;

    @Basic
    @javax.persistence.Column(name = "TIMEZONE")
    public Short getTimezone() {
        return timezone;
    }

    public void setTimezone(Short timezone) {
        this.timezone = timezone;
    }

    private Timestamp expirationdate;

    @Basic
    @javax.persistence.Column(name = "EXPIRATIONDATE")
    public Timestamp getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Timestamp expirationdate) {
        this.expirationdate = expirationdate;
    }

    private Timestamp lastlogindate;

    @Basic
    @javax.persistence.Column(name = "LASTLOGINDATE")
    public Timestamp getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(Timestamp lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    private String lastloginaddress;

    @Basic
    @javax.persistence.Column(name = "LASTLOGINADDRESS")
    public String getLastloginaddress() {
        return lastloginaddress;
    }

    public void setLastloginaddress(String lastloginaddress) {
        this.lastloginaddress = lastloginaddress;
    }

    private Timestamp creationdate;

    @Basic
    @javax.persistence.Column(name = "CREATIONDATE")
    public Timestamp getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Timestamp creationdate) {
        this.creationdate = creationdate;
    }

    private Timestamp lastchangedate;

    @Basic
    @javax.persistence.Column(name = "LASTCHANGEDATE")
    public Timestamp getLastchangedate() {
        return lastchangedate;
    }

    public void setLastchangedate(Timestamp lastchangedate) {
        this.lastchangedate = lastchangedate;
    }

    private Integer visits;

    @Basic
    @javax.persistence.Column(name = "VISITS")
    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    private Integer badaccess;

    @Basic
    @javax.persistence.Column(name = "BADACCESS")
    public Integer getBadaccess() {
        return badaccess;
    }

    public void setBadaccess(Integer badaccess) {
        this.badaccess = badaccess;
    }

    private String activation;

    @Basic
    @javax.persistence.Column(name = "ACTIVATION")
    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    private Integer authorid;

    @Basic
    @javax.persistence.Column(name = "AUTHORID")
    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    private Integer enabled;

    @Basic
    @javax.persistence.Column(name = "ENABLED")
    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public boolean admin;

    @Basic
    @javax.persistence.Column(name = "admin")
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberEntity that = (MemberEntity) o;

        if (memberid != null ? !memberid.equals(that.memberid) : that.memberid != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (countryid != null ? !countryid.equals(that.countryid) : that.countryid != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (autologin != null ? !autologin.equals(that.autologin) : that.autologin != null) return false;
        if (timezone != null ? !timezone.equals(that.timezone) : that.timezone != null) return false;
        if (expirationdate != null ? !expirationdate.equals(that.expirationdate) : that.expirationdate != null)
            return false;
        if (lastlogindate != null ? !lastlogindate.equals(that.lastlogindate) : that.lastlogindate != null)
            return false;
        if (lastloginaddress != null ? !lastloginaddress.equals(that.lastloginaddress) : that.lastloginaddress != null)
            return false;
        if (creationdate != null ? !creationdate.equals(that.creationdate) : that.creationdate != null) return false;
        if (lastchangedate != null ? !lastchangedate.equals(that.lastchangedate) : that.lastchangedate != null)
            return false;
        if (visits != null ? !visits.equals(that.visits) : that.visits != null) return false;
        if (badaccess != null ? !badaccess.equals(that.badaccess) : that.badaccess != null) return false;
        if (activation != null ? !activation.equals(that.activation) : that.activation != null) return false;
        if (authorid != null ? !authorid.equals(that.authorid) : that.authorid != null) return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = memberid != null ? memberid.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (countryid != null ? countryid.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (autologin != null ? autologin.hashCode() : 0);
        result = 31 * result + (timezone != null ? timezone.hashCode() : 0);
        result = 31 * result + (expirationdate != null ? expirationdate.hashCode() : 0);
        result = 31 * result + (lastlogindate != null ? lastlogindate.hashCode() : 0);
        result = 31 * result + (lastloginaddress != null ? lastloginaddress.hashCode() : 0);
        result = 31 * result + (creationdate != null ? creationdate.hashCode() : 0);
        result = 31 * result + (lastchangedate != null ? lastchangedate.hashCode() : 0);
        result = 31 * result + (visits != null ? visits.hashCode() : 0);
        result = 31 * result + (badaccess != null ? badaccess.hashCode() : 0);
        result = 31 * result + (activation != null ? activation.hashCode() : 0);
        result = 31 * result + (authorid != null ? authorid.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        return result;
    }
}
