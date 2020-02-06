package org.wj.prajumsook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wj.prajumsook.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
