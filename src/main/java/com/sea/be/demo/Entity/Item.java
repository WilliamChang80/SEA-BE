package com.sea.be.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Item extends BaseEntity{

    private String name;

    private String description;

    @OneToOne
    private Category category;

    private Long price;

    private Long userId;
}
