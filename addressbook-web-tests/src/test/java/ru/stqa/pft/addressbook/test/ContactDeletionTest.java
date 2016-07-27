package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

  @Test
  public void ContactDeletionTest() {
    //app.getNavigationHelper().gotoHomePage();
    app.selectContact();
    app.deleteContact();
    app.closeDialog();
    //app.getNavigationHelper().gotoHomePage();

  }

}
