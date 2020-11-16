package com.cenfotec.crud.repo;

import com.cenfotec.crud.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloRepository extends JpaRepository<Article, Long> {
}
