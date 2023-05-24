package com.enjoytrip.course.mapper;

import com.enjoytrip.course.dto.CourseDto.Patch;
import com.enjoytrip.course.dto.CourseDto.Post;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.member.dto.MemberDto.Get;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-24T09:36:04+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Azul Systems, Inc.)"
)
@Component
public class CourseMapperImpl extends CourseMapper {

    @Override
    public com.enjoytrip.course.dto.CourseDto.Get courseToGetRequest(Course course) {
        if ( course == null ) {
            return null;
        }

        String title = null;
        String content = null;

        title = course.getTitle();
        content = course.getContent();

        Get memberDto = null;
        List<String> productId = null;

        com.enjoytrip.course.dto.CourseDto.Get get = new com.enjoytrip.course.dto.CourseDto.Get( title, content, memberDto, productId );

        return get;
    }

    @Override
    public List<com.enjoytrip.course.dto.CourseDto.Get> courseToGetRequest(List<Course> course) {
        if ( course == null ) {
            return null;
        }

        List<com.enjoytrip.course.dto.CourseDto.Get> list = new ArrayList<com.enjoytrip.course.dto.CourseDto.Get>( course.size() );
        for ( Course course1 : course ) {
            list.add( courseToGetRequest( course1 ) );
        }

        return list;
    }

    @Override
    public Course postRequestToCourse(Post postRequest) {
        if ( postRequest == null ) {
            return null;
        }

        Course course = new Course();

        course.setWriter( mapMember( postRequest.getMemberId() ) );
        course.setTitle( postRequest.getTitle() );
        course.setContent( postRequest.getContent() );
        course.setScope( postRequest.getScope() );
        List<String> list = postRequest.getProductIdList();
        if ( list != null ) {
            course.setProductIdList( new ArrayList<String>( list ) );
        }

        return course;
    }

    @Override
    public void patchRequestToCourse(Patch patchRequest, Course course) {
        if ( patchRequest == null ) {
            return;
        }

        course.setTitle( patchRequest.getTitle() );
        course.setContent( patchRequest.getContent() );
        course.setScope( patchRequest.getScope() );
        if ( course.getProductIdList() != null ) {
            List<String> list = patchRequest.getProductIdList();
            if ( list != null ) {
                course.getProductIdList().clear();
                course.getProductIdList().addAll( list );
            }
            else {
                course.setProductIdList( null );
            }
        }
        else {
            List<String> list = patchRequest.getProductIdList();
            if ( list != null ) {
                course.setProductIdList( new ArrayList<String>( list ) );
            }
        }
    }
}
