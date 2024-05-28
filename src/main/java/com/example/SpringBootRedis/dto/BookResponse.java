package com.example.SpringBootRedis.dto;

import lombok.Data;

@Data
public class BookResponse {

    private Long id;
    private String name;
    private String author;
}
