package org.wj.prajumsook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wj.prajumsook.entity.Member;

public interface MemberRipository extends JpaRepository<Member, Integer> {
}
