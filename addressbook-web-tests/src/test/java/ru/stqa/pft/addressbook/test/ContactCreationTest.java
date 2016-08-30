package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


  @Test
  public void testCreationNewContact() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withName("Ivan").withInitials("I.I.I.").withLastName("Ivanov").withNik("Vano")
            .withTitle("Title").withCompany("Smartech").withAddress("Пушкинская 169").withPhoto(new File("src/test/resources/test.png"))
            .withHomePhoneNumber("8999999999").withMobilePhoneNumber("899889890980")
            .withWorkPhoneNumber("8999999999").withGroup("test1").withEmail("test@mail.ru");
    app.contact().create(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }


}
