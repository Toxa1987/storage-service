package com.epam.esm.storageservice.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "storages")
public class Storage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "storage_type")
    @Enumerated(EnumType.STRING)
    private StorageType storageType;
    @Column(name = "bucket")
    private String bucket;
}
