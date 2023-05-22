package com.enjoytrip.like.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductLike extends Like {
    
    private Long productId;
}
