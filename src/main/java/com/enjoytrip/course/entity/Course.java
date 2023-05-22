package com.enjoytrip.course.entity;

import com.enjoytrip.member.entity.Member;
import com.enjoytrip.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Member writer;
    
    @ElementCollection
    @CollectionTable(name = "Products", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "product_id_list")
    private List<String> productIdList;
}
