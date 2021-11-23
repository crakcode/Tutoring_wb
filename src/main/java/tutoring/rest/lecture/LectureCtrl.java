package tutoring.rest.lecture;

import lombok.RequiredArgsConstructor;
import tutoring.dao.lecture.LectureRepository;
import tutoring.entity.Lecture;
import tutoring.security.JwtTokenProvider;
import tutoring.security.User;
import tutoring.security.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1/lecture")
public class LectureCtrl {
	
	  @Autowired
	  private LectureRepository lecReps;
	  
	  @Autowired
	  private  UserRepository userReps;

	  @Autowired
	  private  JwtTokenProvider jwtProvider;

//	  @GetMapping("/list")
//	  public List<Post> getAllPost(){
//		  return postServ.getAllPost();
//	  }
//
	  
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
