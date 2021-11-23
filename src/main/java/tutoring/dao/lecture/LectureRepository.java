package tutoring.dao.lecture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import tutoring.entity.Lecture;



@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long>{


}
