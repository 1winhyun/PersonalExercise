package seunghyun.personalproject.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seunghyun.personalproject.article.domain.Article;

public interface PostRepository extends JpaRepository<Article,Long> {
}
