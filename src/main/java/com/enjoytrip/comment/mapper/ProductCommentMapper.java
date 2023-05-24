package com.enjoytrip.comment.mapper;

import com.enjoytrip.comment.dto.ProductCommentDto;
import com.enjoytrip.comment.entity.ProductComment;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.service.MemberService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")

public abstract class ProductCommentMapper {

    @Autowired
    private MemberService memberService;
    
    @Mapping(source = "id", target = "commentId")
    public abstract ProductCommentDto.Get productCommentToGetRequest(ProductComment productComment);

    public abstract List<ProductCommentDto.Get> productCommentToGetRequest(List<ProductComment> productComment);

    @Mapping(target = "writer", source = "postRequest.writerId", qualifiedByName = "mapMember")
    public abstract ProductComment postRequestToProductComment(ProductCommentDto.Post postRequest, Long productId);

    public abstract void patchRequestToProductComment(ProductCommentDto.Patch patchRequest, @MappingTarget ProductComment productComment);

    @Named("mapMember")
    protected Member mapMember(Long memberId) {
        return memberService.findOneMember(memberId);
    }
}
