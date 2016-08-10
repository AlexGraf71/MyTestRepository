package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getContactHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    int after = app.getContactHelper().getGroupCount();
    Assert.assertEquals(after, before + 1);
  }

}
