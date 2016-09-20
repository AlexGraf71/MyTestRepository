package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")

public class ContactData {
  @XStreamOmitField
  @Id
  private int id;

  @Expose
  @Column(name = "firstname")
  private String name;

  @Transient
  private String initials;

  @Expose
  @Column(name = "lastName")
  private String lastName;

  @Transient
  private String nik;

  @Transient
  private String title;

  @Transient
  private String company;

  @Expose
  @Transient
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homephonenumber;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilephonenumber;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workphonenumber;

  @Transient
  private String fax;

  @Expose
  @Transient
  private String email;

  @Transient
  private String allPhones;

  @Transient
  private String allInfo;

  @Transient
  private File photo;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
      joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public String getAllPhones() {
    return allPhones;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getInitials() {
    return initials;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNik() {
    return nik;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhoneNumber() {
    return homephonenumber;
  }

  public String getMobilePhoneNumber() {
    return mobilephonenumber;
  }

  public String getWorkPhoneNumber() {
    return workphonenumber;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail() {
    return email;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public void setGroups(Set<GroupData> groups) {
    this.groups = groups;
  }

  public String getAllInfo() {
    return allInfo;
  }

  public File getPhoto() {
    return photo;
  }


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withInitials(String initials) {
    this.initials = initials;
    return this;
  }

  public ContactData withLastName(String lastname) {
    this.lastName = lastname;
    return this;
  }

  public ContactData withNik(String nik) {
    this.nik = nik;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhoneNumber(String homephonenumber) {
    this.homephonenumber = homephonenumber;
    return this;
  }

  public ContactData withMobilePhoneNumber(String mobilephonenumber) {
    this.mobilephonenumber = mobilephonenumber;
    return this;
  }

  public ContactData withWorkPhoneNumber(String workphonenumber) {
    this.workphonenumber = workphonenumber;
    return this;
  }

  public ContactData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }


  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withAllInfo(String allInfo) {
    this.allInfo = allInfo;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;

  }
}
