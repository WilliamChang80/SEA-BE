package com.sea.be.demo.Dto;

import com.sea.be.demo.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private String name;

    private String description;

    private Category category;

    private Long price;

    private String username;
}
