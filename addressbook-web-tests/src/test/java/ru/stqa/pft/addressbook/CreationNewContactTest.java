package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class CreationNewContactTest extends TestBase {


  @Test
  public void testCreationNewContact() {
    initCreateContact();
    fillContactForm(new ContactData("Ivan", "I.I.I.", "Ivanov", "Vano", "Title", "Smartech", "Moskow", "8999999999", "8999999999", "8777777777", "707070", "test@mail.ru"));
    submitCreateContact();
    gotoHomePage();
  }


}
