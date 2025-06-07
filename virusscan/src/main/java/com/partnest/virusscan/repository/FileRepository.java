package com.partnest.virusscan.repository;

import com.partnest.virusscan.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {

    Optional<File> findFileByFileName(String fileName);

}
