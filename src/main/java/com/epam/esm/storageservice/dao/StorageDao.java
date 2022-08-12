package com.epam.esm.storageservice.dao;

import com.epam.esm.storageservice.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageDao extends JpaRepository<Storage,Long> {
    Optional<Storage> findById(long[] id);
}
