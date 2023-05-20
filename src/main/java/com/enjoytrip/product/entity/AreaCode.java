//package com.enjoytrip.product.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class AreaCode {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "area_code")
//    private Long code;
//
//    private String name;
//
//    @OneToMany(mappedBy = "areaCode")
//    private List<SigugunCode> sigugunCodeList = new ArrayList<>();
//
//}
