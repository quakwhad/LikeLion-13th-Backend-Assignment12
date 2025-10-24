package com.likelion.search_likelion.post.domain.repository;

import com.likelion.search_likelion.post.domain.Category;
import com.likelion.search_likelion.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    //제목, 내용, 카테고리로 검색
    @Query("SELECT p FROM Post p WHERE " +
            "(:keyword IS NULL OR :keyword = '' OR LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
            "(:category IS NULL OR p.category = :category)")
    Page<Post> findKeywordAndCategory(@org.springframework.data.repository.query.Param("keyword") String keyword,
                                      @org.springframework.data.repository.query.Param("category") Category category, Pageable pageable);
}