package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testUntitledTestCase() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isTheareAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test1", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().DeleteSelectedGroup();
    app.getGroupHelper().returnToGroupPage();
    app.getSessionHelper().logout();
  }

}
