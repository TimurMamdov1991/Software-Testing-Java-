package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testUntitledTestCase() {
    app.gotoGroupPage();
    app.selectGroup();
    app.DeleteSelectedGroup();
    app.returnToGroupPage();
  }

}
