package tutoring.rest.review;

import lombok.RequiredArgsConstructor;
import tutoring.dao.lecture.LectureRepository;
import tutoring.dao.review.ReviewRepository;
import tutoring.entity.Lecture;
import tutoring.entity.Review;
import tutoring.security.JwtTokenProvider;
import tutoring.security.User;
import tutoring.security.UserRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1/review")
public class ReviewCtrl {
	
	  @Autowired
	  private LectureRepository lecReps;
	  
	  @Autowired
	  private  UserRepository userReps;

	  @Autowired
	  private  JwtTokenProvider jwtProvider;

	  @Autowired
	  private  ReviewRepository reviewReps;

//	  @GetMapping("/list")
//	  public List<Post> getAllPost(){
//		  return postServ.getAllPost();
//	  }
//
	  @PostMapping("/write/{l_id}")
	  public void uploadSingle(@PathVariable Long l_id,@RequestHeader(value="X-AUTH-TOKEN") String token,@RequestBody Review review) throws Exception {
		  String email=jwtProvider.getUserPk(token);
		  User user=userReps.findByEmail(email).get();
		  Lecture lecture=lecReps.findById(l_id).get();
		  
		  review.setUser(user);
		  review.setLecture(lecture);
		  
		  reviewReps.save(review);
	  }
	  
	  @PostMapping("/write")
	  public void createPost(@RequestBody Lecture lecture,@RequestHeader(value="X-AUTH-TOKEN") String token) {
		  System.out.println("hello world");
		  String email=jwtProvider.getUserPk(token);
		  User user=userReps.findByEmail(email).get();
		  lecture.setUser(user);
		  lecReps.save(lecture);
	  }
//
//	  @GetMapping("/{id}")
//	  public ResponseEntity<Post> getPostById(@PathVariable Long id){
//		  return postServ.getPostById(id);
//	  }
////    
////    
//	  @PutMapping("/{id}")
//	  public ResponseEntity<Post> updatePost(@PathVariable Long id,@RequestBody Post post){
//		  return postServ.updatePost(id,post);
//	  }
//
//	  @DeleteMapping("/{id}")
//	  public void deletePost(@PathVariable Long id) {
//		  postServ.deletePost(id);
//	  }
}
