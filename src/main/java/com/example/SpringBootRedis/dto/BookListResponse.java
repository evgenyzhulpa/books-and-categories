package com.example.SpringBootRedis.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookListResponse {
    private List<BookResponse> bookResponseList = new ArrayList<>();
}
