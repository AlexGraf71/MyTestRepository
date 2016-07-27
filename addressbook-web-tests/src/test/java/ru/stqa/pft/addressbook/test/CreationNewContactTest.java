package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.NavigationHelper;
import ru.stqa.pft.addressbook.model.ContactData;

public class CreationNewContactTest extends TestBase {


  @Test
  public void testCreationNewContact() {
    app.initCreateContact();
    app.fillContactForm(new ContactData("Ivan", "I.I.I.", "Ivanov", "Vano", "Title", "Smartech", "Moskow", "8999999999", "8999999999", "8777777777", "707070", "test@mail.ru"));
    app.submitCreateContact();
    app.getNavigationHelper().gotoHomePage();
  }


}
