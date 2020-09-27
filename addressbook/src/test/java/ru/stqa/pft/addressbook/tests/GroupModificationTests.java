package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData()
          .withName("Test1")
          .withHeader("123")
          .withFooter("345"));
    }
  }

  @Test
  public void testGroupModification(){
    Groups before = app.group().all();
    GroupData modifyGroup = before.iterator().next();
    GroupData group = new GroupData()
        .withId(modifyGroup.getId())
        .withName("Test1")
        .withHeader("444")
        .withFooter("555");
    app.group().modify(group);
    Assert.assertEquals(app.group().count(),before.size());
    Groups after = app.group().all();
    Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.withOut(modifyGroup).withAdded(group)));
  }



}
