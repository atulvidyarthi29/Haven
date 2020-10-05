package com.start.haven.users.model;

import com.google.common.collect.Sets;

import java.util.HashSet;

import static com.start.haven.users.model.UserPermission.*;

public enum UserRole {
    ADMIN(Sets.newHashSet(ADMIN_POST_READ,
            ADMIN_POST_WRITE,
            ADMIN_POST_DELETE,
            ADMIN_USER_READ,
            ADMIN_USER_WRITE,
            ADMIN_USER_DELETE)),
    AUTHORITY(Sets.newHashSet()),
    JOURNALIST(Sets.newHashSet()),
    USER(Sets.newHashSet(POST_READ,
            POST_WRITE,
            POST_UPDATE,
            POST_DELETE,
            COMMENT_READ,
            COMMENT_WRITE,
            COMMENT_UPDATE,
            COMMENT_DELETE));
    UserRole(HashSet<UserPermission> newHashSet) {

    }
}
