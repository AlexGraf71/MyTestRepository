package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {


  @Test
  public void testCreationNewContact() {
    app.getContactHelper().initCreateContact();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "I.I.I.", "Ivanov", "Vano", "Title", "Smartech", "Moskow",
            "8999999999", "8999999999", "8777777777", "707070", "test@mail.ru","test1"), true);
    app.getContactHelper().submitCreateContact();
    app.getNavigationHelper().gotoHomePage();
  }


}
