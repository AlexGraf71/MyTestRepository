package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTest extends TestBase {

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("-()", "");
  }

  public static String cleaningAllInformation(String allInfo) {
    return allInfo.replaceAll("-()", "");
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withName("Ivan").withLastName("Ivanov")
              .withAddress("Тургениевская 67")
              .withHomePhoneNumber("8999999999").withMobilePhoneNumber("99999999009909")
              .withWorkPhoneNumber("8999999999").withEmail("test@mail.ru"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactPhone() {
    ContactData contact = app.db().contacts().iterator().next();
    ContactData contactInfoFromInfoForm = app.contact().allFromInfoForm(contact);
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contactInfoFromInfoForm.getAllInfo(), equalTo(mergeAllInformation(contactInfoFromEditForm)));
  }

  private String mergeAllInformation(ContactData contact) {

    String fullName = Stream.of(contact.getName(), contact.getLastName()).filter(s -> !(s == null) && !s.equals(""))
            .map(ContactInfoTest::cleaningAllInformation)
            .collect(Collectors.joining(" "));

    String address = Stream.of(contact.getAddress()).filter(s -> !(s == null) && !s.equals(""))
            .map(ContactInfoTest::cleaningAllInformation)
            .collect(Collectors.joining("\n"));

    String homePhone = "H: " + Stream.of(contact.getHomePhoneNumber()).filter(s -> !(s == null) && !s.equals(""))
            .map(ContactInfoTest::cleaned)
            .collect(Collectors.joining("\n")) + "\n";

    String mobilePhone = "M: " + Stream.of(contact.getMobilePhoneNumber()).filter(s -> !(s == null) && !s.equals(""))
            .map(ContactInfoTest::cleaned)
            .collect(Collectors.joining("\n")) + "\n";

    String workPhone = "W: " + Stream.of(contact.getWorkPhoneNumber()).filter(s -> !(s == null) && !s.equals(""))
            .map(ContactInfoTest::cleaned)
            .collect(Collectors.joining("\n")) + "\n";

    String firstEmail = Stream.of(contact.getEmail()).filter(s -> !(s == null) && !s.equals(""))
            .map(ContactInfoTest::cleaningAllInformation)
            .collect(Collectors.joining("\n"));

    return fullName + "\n" + address + "\n\n" + homePhone + mobilePhone + workPhone + "\n" + firstEmail +
            " (www." + firstEmail.substring(firstEmail.indexOf("@") + 1) + ")";
  }

}

