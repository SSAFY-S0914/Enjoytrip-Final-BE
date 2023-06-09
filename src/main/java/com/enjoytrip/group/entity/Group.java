package com.enjoytrip.group.entity;

import com.enjoytrip.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Groups")
public class Group extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    List<GroupMember> groupMemberList = new ArrayList<>();

    public void addGroupMember(GroupMember groupMember) {
        this.groupMemberList.add(groupMember);
    }

    public GroupMember getGroupMember(Long memberId) {
        for (GroupMember groupMember : groupMemberList) {
            if (groupMember.getMember().getId().equals(memberId)) {
                return groupMember;
            }
        }
        return null;
    }

    public void deleteGroupMember(GroupMember groupMember) {
        this.groupMemberList.remove(groupMember);
    }
}
