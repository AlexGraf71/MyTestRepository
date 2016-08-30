package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
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
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromInfoForm = app.contact().allFromInfoForm(contact);
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contactInfoFromInfoForm.getAllInfo(), equalTo(mergeAllInformation(contactInfoFromEditForm)));
  }

  private String mergeAllInformation(ContactData contact) {

    String fullName =  Arrays.asList(contact.getName(),contact.getLastName())
            .stream().filter(s -> !(s == null) && ! s.equals(""))
            .map(ContactInfoTest::cleaningAllInformation )
            .collect(Collectors.joining(" "));

    String address = Arrays.asList(contact.getAddress())
            .stream().filter(s -> !(s == null) && ! s.equals(""))
            .map(ContactInfoTest::cleaningAllInformation )
            .collect(Collectors.joining("\n")) ;

    String homePhone = "H: " + Arrays.asList(contact.getHomePhoneNumber())
            .stream().filter(s -> !(s == null) && ! s.equals(""))
            .map(ContactInfoTest::cleaned )
            .collect(Collectors.joining("\n")) + "\n";

    String mobilePhone = "M: " + Arrays.asList(contact.getMobilePhoneNumber())
            .stream().filter(s -> !(s == null) && ! s.equals(""))
            .map(ContactInfoTest::cleaned )
            .collect(Collectors.joining("\n"))+ "\n";

    String workPhone = "W: " + Arrays.asList(contact.getWorkPhoneNumber())
            .stream().filter(s -> !(s == null) && ! s.equals(""))
            .map(ContactInfoTest::cleaned )
            .collect(Collectors.joining("\n"))+ "\n";

    String firstEmail =Arrays.asList(contact.getEmail())
            .stream().filter(s -> !(s == null) && ! s.equals(""))
            .map(ContactInfoTest::cleaningAllInformation )
            .collect(Collectors.joining("\n"));

    return fullName + "\n" + address +"\n\n" + homePhone + mobilePhone + workPhone + "\n" + firstEmail +
            " (www." + firstEmail.substring(firstEmail.indexOf("@") + 1) + ")";
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("-()", "");
  }

  public static String cleaningAllInformation(String allInfo){
    return allInfo.replaceAll("-()","");
  }

}

