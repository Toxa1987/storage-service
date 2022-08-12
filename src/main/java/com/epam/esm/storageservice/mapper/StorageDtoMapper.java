package com.epam.esm.storageservice.mapper;

import com.epam.esm.storageservice.dto.StorageDto;
import com.epam.esm.storageservice.entity.Storage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StorageDtoMapper {
    StorageDtoMapper INSTANCE = Mappers.getMapper(StorageDtoMapper.class);

    StorageDto toDto(Storage storage);

    Storage toEntity(StorageDto storageDto);
}
