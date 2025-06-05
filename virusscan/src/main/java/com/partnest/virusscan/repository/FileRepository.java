package com.partnest.virusscan.repository;

import com.partnest.virusscan.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

    Optional<File> findFileByFileName(String fileName);

}
