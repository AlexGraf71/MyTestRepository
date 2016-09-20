package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInGroupTest extends TestBase {


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
  public void testContactInGroup() {
    Groups groupsBefore = app.db().groups();
    Contacts contactsBefore = app.db().contacts();
    ContactData selectedContact = contactsBefore.iterator().next();

    Groups groupsOfSelectedContact = selectedContact.getGroups();
    GroupData selectedGroup = groupsBefore.iterator().next();
    Iterator<ContactData> iteratorContacts = contactsBefore.iterator();

    while (iteratorContacts.hasNext()) {
      if (groupsOfSelectedContact.equals(groupsBefore)) {
        selectedContact = iteratorContacts.next();
        groupsOfSelectedContact = selectedContact.getGroups();
      } else break;
    }
    if (groupsOfSelectedContact.equals(groupsBefore)) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("18_test"));
    }

    app.goTo().gotoHome();
    app.contact().selectContactById(selectedContact.getId());
    app.contact().addToGroupById(selectedGroup.getId());
    app.goTo().homePageSelectedGroup();

    assertThat(selectedContact.getGroups(), equalTo(groupsOfSelectedContact));

  }

}
