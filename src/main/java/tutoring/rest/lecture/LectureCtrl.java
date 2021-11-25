package tutoring.rest.lecture;

import lombok.RequiredArgsConstructor;
import tutoring.dao.lecture.LectureRepository;
import tutoring.entity.Lecture;
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
	  @PostMapping("/file")
	  public String uploadSingle(@RequestParam("files") MultipartFile files) throws Exception {
	      String rootPath = "C:/Users/forcs/git/Tutoring_wb/tutoring_web/src/main/resources/images";
	      String basePath = rootPath + "/" + "single";
	      String filePath = basePath + "/" + files.getOriginalFilename();
	      File dest = new File(filePath);
	      files.transferTo(dest); // 파일 업로드 작업 수행
	      return "uploaded";
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
