package com.riderguru.rider_guru.groups;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupMemberDto {
    private Long id;
    private Long groupId;
    private Long userId;
    /**
     * The user who is requesting to add the member to the group.
     * If this matches the group's admin id, the member will be added immediately.
     * Otherwise an approval request will be created for the admin.
     */
    private Long requesterId;
}
