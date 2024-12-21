package seunghyun.personalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seunghyun.personalproject.domain.Article;

public interface PostRepository extends JpaRepository<Article,Long> {
}
