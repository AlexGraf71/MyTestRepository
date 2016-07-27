package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

  @Test
  public void ContactDeletionTest() {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().closeDialog();
    app.getNavigationHelper().gotoHome();

  }

}
