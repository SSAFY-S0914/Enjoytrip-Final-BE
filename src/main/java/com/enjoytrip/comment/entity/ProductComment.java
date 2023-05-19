package com.enjoytrip.comment.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductComment extends Comment {

    private Integer star;

    private Long productId;
}
