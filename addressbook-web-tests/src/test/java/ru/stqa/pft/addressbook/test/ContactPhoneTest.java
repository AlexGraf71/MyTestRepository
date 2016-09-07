package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withName("Ivan").withLastName("Ivanov")
              .withAddress("Тургениевская 67")
              .withHomePhoneNumber("8999999999").withMobilePhoneNumber("99999999009909")
              .withWorkPhoneNumber("8999999999").withEmail("test@mail.ru").withGroup(null));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactPhone() {
    ContactData contact = app.contact().incompleteContact().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getName(), equalTo(contactInfoFromEditForm.getName()));
    assertThat(contact.getLastName(), equalTo(contactInfoFromEditForm.getLastName()));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getEmail(), equalTo(contactInfoFromEditForm.getEmail()));

    assertThat(contact.getEmail(), equalTo(contactInfoFromEditForm.getEmail()));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhoneNumber(), contact.getMobilePhoneNumber(), contact.getWorkPhoneNumber()).stream()
            .filter((s) -> !s.equals("")).map(ContactPhoneTest::cleaned).collect(Collectors.joining("\n"));

  }

}
