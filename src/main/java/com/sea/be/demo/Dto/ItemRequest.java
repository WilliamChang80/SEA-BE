package com.sea.be.demo.Dto;

import com.sea.be.demo.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ItemRequest {
    private String name;

    private String description;

    private Long categoryId;

    private Long price;

    private Long userId;
}
