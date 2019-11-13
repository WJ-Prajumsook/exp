package org.wj.prajumsook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wj.prajumsook.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
