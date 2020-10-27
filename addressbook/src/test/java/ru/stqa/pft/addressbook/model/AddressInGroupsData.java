package ru.stqa.pft.addressbook.model;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@XStreamAlias("AddressInGroups")
@Entity
@Table(name= "address_in_groups")
public class AddressInGroupsData  {
  @XStreamOmitField

  private int domain_ID;
  @Id
  @Column(name = "id",unique=true)

  private int id;



  @Column(name = "group_id",unique=true)

  private int groupId;

  public int getId() {
    return id;
  }

  public int getGroupId() {
    return groupId;
  }


  @Override
  public String toString() {
    return "AddressInGroupsData{" +
        "id=" + id +
        ", groupId=" + groupId +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AddressInGroupsData that = (AddressInGroupsData) o;
    return id == that.id &&
        groupId == that.groupId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, groupId);
  }
}
