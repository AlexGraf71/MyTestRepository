package ru.stqa.pft.addressbook.test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData()
              .withName("test1").withFooter("test2").withHeader("test3"));
    }
  }

  @Test
  public void testModificationGroup() {
    Groups before = app.group().all();
    GroupData modifyGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifyGroup.getId()).withName("test1").withHeader("Test67").withFooter("Test65");
    app.group().modify(group);
    Groups after = app.group().all();
    assertThat(after.size(),equalTo(before.size()));
    equalTo(before.without(modifyGroup).withAdded(group));

  }


}
