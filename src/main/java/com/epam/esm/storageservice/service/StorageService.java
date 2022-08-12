package com.epam.esm.storageservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.epam.esm.storageservice.dao.StorageDao;
import com.epam.esm.storageservice.dto.StorageDto;
import com.epam.esm.storageservice.entity.SaveResponse;
import com.epam.esm.storageservice.entity.Storage;
import com.epam.esm.storageservice.exception.S3BucketNotExistException;
import com.epam.esm.storageservice.mapper.StorageDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class StorageService {
    private AmazonS3 amazonS3;
    private StorageDao storageDao;

    public StorageService(AmazonS3 amazonS3, StorageDao storageDao) {
        this.amazonS3 = amazonS3;
        this.storageDao = storageDao;
    }

    @Transactional
    public SaveResponse saveStorage(StorageDto storageDto) {
        checkBucket(storageDto.getBucket());
        Storage storage = StorageDtoMapper.INSTANCE.toEntity(storageDto);
        storageDao.save(storage);
        return new SaveResponse(storage.getId());
    }

    public List<StorageDto> getStorages() {
        return storageDao.findAll()
                .stream()
                .map(StorageDtoMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Long> deleteStorages(long[] ids) {
        return Arrays.stream(ids)
                .boxed()
                .filter(id -> storageDao.findById(id).isPresent())
                .peek(id-> {
                    String bucketName = storageDao.findById(id).get().getBucket();
                    amazonS3.deleteBucket(bucketName);
                })
                .peek(id -> storageDao.deleteById(id))
                .collect(Collectors.toList());
    }

    private void checkBucket(String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            log.error(String.format("S3 bucket %s doesn't exist", bucketName));
            throw new S3BucketNotExistException(String.format("S3 bucket %s doesn't exist", bucketName));
        }
    }
}
