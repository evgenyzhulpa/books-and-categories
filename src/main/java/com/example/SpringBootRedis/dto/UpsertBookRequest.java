package com.example.SpringBootRedis.dto;

import lombok.Data;

@Data
public class UpsertBookRequest {
    private String name;
    private String author;
    private String categoryName;
}
