package tutoring.dao.lectureDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import tutoring.entity.Lecture;
import tutoring.entity.LectureDetail;



@Repository
public interface LectureDetailRepository extends JpaRepository<LectureDetail, Long>{


}
