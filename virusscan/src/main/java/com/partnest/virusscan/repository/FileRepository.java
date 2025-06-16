package com.partnest.virusscan.repository;

import com.partnest.virusscan.entity.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<UploadedFile, UUID> {

}
