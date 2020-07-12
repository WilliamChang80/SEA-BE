package com.sea.be.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity{
    private String name;
}
