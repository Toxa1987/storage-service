package com.epam.esm.storageservice.converter;

import com.epam.esm.storageservice.entity.StorageType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToStorageTypeConverter implements Converter<String, StorageType> {
    @Override
    public StorageType convert(String source) {
        return StorageType.valueOf(source.toUpperCase());
    }
}
