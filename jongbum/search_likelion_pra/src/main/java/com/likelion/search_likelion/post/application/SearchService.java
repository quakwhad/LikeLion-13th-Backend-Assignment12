package com.likelion.search_likelion.post.application;

import com.likelion.search_likelion.post.api.dto.response.PostSummaryResDto;
import com.likelion.search_likelion.post.domain.Category;
import com.likelion.search_likelion.post.domain.Post;
import com.likelion.search_likelion.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final PostRepository postRepository;

    public Page<PostSummaryResDto> searchPosts(String keyword, Category category, Pageable pageable) {

        //postRepository에서 검색해서 전달
        Page<Post> allPosts = postRepository.findKeywordAndCategory(keyword,category, pageable);

        //DB에서 페이징된 Post 객체들을 Dto로 매핑해서 반환
        return allPosts.map(PostSummaryResDto::from);
    }
}