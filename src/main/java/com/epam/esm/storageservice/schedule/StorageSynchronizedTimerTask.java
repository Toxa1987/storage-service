package com.epam.esm.storageservice.schedule;

import com.amazonaws.services.s3.AmazonS3;
import com.epam.esm.storageservice.dao.StorageDao;
import com.epam.esm.storageservice.entity.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StorageSynchronizedTimerTask {

    private StorageDao storageDao;
    private AmazonS3 amazonS3;

    public StorageSynchronizedTimerTask(StorageDao storageDao, AmazonS3 amazonS3) {
        this.storageDao = storageDao;
        this.amazonS3 = amazonS3;
    }

    /**
     * The method checkBucket useful only for this study task.
     * I add some data into database when application starts, and I want that those buckets will be created.
     * In real project this method mustn't use because this service won't be responsible for bucket creation.
     */
    @Scheduled(fixedDelay = 1000*60*60)
    private void checkBucketExists() {
        for (Storage s : storageDao.findAll()
        ) {
            String bucketName = s.getBucket();
            if (!amazonS3.doesBucketExistV2(bucketName)) {
                log.error(String.format("S3 bucket %s doesn't exist", bucketName));
                log.info(String.format("Creating bucket %s", bucketName));
                amazonS3.createBucket(bucketName);
                log.info(String.format("Bucket with name %s created", bucketName));
            }
        }
    }

}
