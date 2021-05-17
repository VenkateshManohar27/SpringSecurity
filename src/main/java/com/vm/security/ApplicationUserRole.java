package com.vm.security;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;
import static com.vm.security.ApplicationUserPermission.COURSE_READ;
import static com.vm.security.ApplicationUserPermission.COURSE_WRITE;
import static com.vm.security.ApplicationUserPermission.STUDENT_READ;
import static com.vm.security.ApplicationUserPermission.STUDENT_WRITE;

public enum ApplicationUserRole {
	STUDENT(Sets.newHashSet()), 
	ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)), 
	ADMINTRAINEE(Sets.newHashSet(COURSE_READ,STUDENT_READ));

	private final Set<ApplicationUserPermission> permission;

	private ApplicationUserRole(Set<ApplicationUserPermission> permission) {
		this.permission = permission;

	}

	public Set<ApplicationUserPermission> getPermission() {
		return permission;
	}
}
