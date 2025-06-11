package com.partnest.virusscan.entity;

import com.partnest.virusscan.constants.FileStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)     // GenerationType.IDENTITY did not work for MySQL
    private UUID fileId;

    private String fileName;

    @Enumerated(EnumType.STRING)
    private FileStatus fileStatus;

    @Column(columnDefinition = "BLOB")
    private byte[] fileData;

}
