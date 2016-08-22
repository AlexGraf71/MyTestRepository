package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


  @Test
  public void testCreationNewContact() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withName("Ivan").withInitials("I.I.I.").withLastname("Ivanov").withNik("Vano")
            .withTitle("Title").withCompany("Smartech").withHomePhoneNumber("8999999999").withWorkPhoneNumber("8999999999").withGroup("test1");
    app.contact().create(contact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }


}
