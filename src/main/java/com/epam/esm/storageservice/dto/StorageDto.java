package com.epam.esm.storageservice.dto;

import com.epam.esm.storageservice.entity.StorageType;
import lombok.*;

import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class StorageDto {
    private long id;
    private StorageType storageType;
    @Pattern(regexp = "\\w{1,99}", message = "Validation error bucket name should be less 100 characters.")
    private String bucket;
}
