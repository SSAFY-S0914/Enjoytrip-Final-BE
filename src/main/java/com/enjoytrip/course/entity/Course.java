package com.enjoytrip.course.entity;

import com.enjoytrip.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    private String title;
    private String content;

    private CourseScope scope;

    @OneToMany(mappedBy = "course")
    private List<CourseProduct> courseProductList = new ArrayList<>();
}
