package org.wj.prajumsook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wj.prajumsook.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
