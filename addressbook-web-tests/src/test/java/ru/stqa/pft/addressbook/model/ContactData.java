package ru.stqa.pft.addressbook.model;

public class ContactData {
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

  public ContactData(String name, String initials, String lastname, String nik, String title, String company, String address, String homephonenumber, String mobilephonenumber, String workphonenumber, String fax, String email) {
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
}
