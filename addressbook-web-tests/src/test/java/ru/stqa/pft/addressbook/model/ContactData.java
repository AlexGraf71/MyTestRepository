package ru.stqa.pft.addressbook.model;

public class ContactData {

  private int id;
  private final String name;
  private final String initials;
  private final String lastname;
  private final String nik;
  private final String title;
  private final String company;
  private final String address;
  private final String homephonenumber;
  private final String mobilephonenumber;
  private final String workphonenumber;
  private final String fax;
  private final String email;
  private final String group;

  public ContactData(int id, String name, String initials, String lastname, String nik, String title, String company, String address, String homephonenumber,
                     String mobilephonenumber, String workphonenumber, String fax, String email, String group) {
    this.id = id;
    this.name = name;
    this.initials = initials;
    this.lastname = lastname;
    this.nik = nik;
    this.title = title;
    this.company = company;
    this.address = address;
    this.homephonenumber = homephonenumber;
    this.mobilephonenumber = mobilephonenumber;
    this.workphonenumber = workphonenumber;
    this.fax = fax;
    this.email = email;
    this.group = group;
  }

  public ContactData(String name, String initials, String lastname, String nik, String title, String company, String address, String homephonenumber,
                     String mobilephonenumber, String workphonenumber, String fax, String email, String group) {
    this.id = Integer.MAX_VALUE;
    this.name = name;
    this.initials = initials;
    this.lastname = lastname;
    this.nik = nik;
    this.title = title;
    this.company = company;
    this.address = address;
    this.homephonenumber = homephonenumber;
    this.mobilephonenumber = mobilephonenumber;
    this.workphonenumber = workphonenumber;
    this.fax = fax;
    this.email = email;
    this.group = group;
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

  public String getHomephonenumber() {
    return homephonenumber;
  }

  public String getMobilephonenumber() {
    return mobilephonenumber;
  }

  public String getWorkphonenumber() {
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

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

}
