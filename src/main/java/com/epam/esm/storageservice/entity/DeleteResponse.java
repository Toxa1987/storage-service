package com.epam.esm.storageservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DeleteResponse {
    private List<Long> ids;
}
