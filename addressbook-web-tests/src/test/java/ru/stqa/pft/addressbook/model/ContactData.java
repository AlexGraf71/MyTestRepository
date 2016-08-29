package ru.stqa.pft.addressbook.model;

public class ContactData {

  private int id;
  private String name;
  private String initials;
  private String lastname;
  private String nik;
  private String title;
  private String company;
  private String address;
  private String homephonenumber;
  private String mobilephonenumber;
  private String workphonenumber;
  private String fax;
  private String email;
  private String group;
  private String allPhones;
  private String allInfo;

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

  public String getLastname() {
    return lastname;
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

  public String getGroup() {
    return group;
  }

  public String getAllInfo() {
    return allInfo;
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
    this.lastname = lastname;
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

  public ContactData withGroup(String group) {
    this.group = group;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }

}
