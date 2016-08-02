package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {
  @Test
  public void testEditContact(){
    app.getContactHelper().initEditContact();
    app.getContactHelper().fillContactForm(new ContactData("Сергей", "I.I.I.", "Сергеевич", "Серый", "титл", "Смартек", "Москва", "899676544564",
            "4564646456", "456464645646", "7070464645670", "test@mail.ru", null), false);
    app.getContactHelper().submitEditContact();
    app.getNavigationHelper().gotoHomePage();
  }
}
