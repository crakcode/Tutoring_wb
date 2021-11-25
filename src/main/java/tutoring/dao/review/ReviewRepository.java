package tutoring.dao.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import tutoring.entity.Review;



@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{


}
