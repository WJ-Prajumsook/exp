package org.wj.prajumsook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wj.prajumsook.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Address findByUserId(Integer userId);

}
