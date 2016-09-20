package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Iterator;

public class DeleteContactFromGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
          .withName("Ivan").withLastName("Ivanov")
          .withAddress("Тургениевская 67")
          .withHomePhoneNumber("8999999999").withMobilePhoneNumber("99999999009909")
          .withWorkPhoneNumber("8999999999").withEmail("test@mail.ru").withPhoto(new File("src/test/resources/test.png")));
      app.goTo().homePage();
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    Groups groupsBefore = app.db().groups();
    Contacts contactsBefore = app.db().contacts();
    ContactData selectedContact = contactsBefore.iterator().next();
    GroupData selectedGroup = groupsBefore.iterator().next();

    Groups groupsOfSelectedContact = selectedContact.getGroups();
    if (groupsOfSelectedContact.size() == 0) {
      app.contact().selectContactById(selectedContact.getId());
      app.contact().addToGroupById(selectedGroup.getId());
    }

    Iterator<ContactData> iteratorContacts = contactsBefore.iterator();

  }

}
