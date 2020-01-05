package org.wj.prajumsook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wj.prajumsook.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
}
