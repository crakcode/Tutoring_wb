package tutoring.rest.lecture;

import lombok.RequiredArgsConstructor;
import tutoring.dao.lecture.LectureRepository;
import tutoring.dao.lectureDetail.LectureDetailRepository;
import tutoring.entity.Lecture;
import tutoring.security.JwtTokenProvider;
import tutoring.security.User;
import tutoring.security.UserRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
	  private LectureDetailRepository lecDetailReps;
	  
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
		  System.out.println(files);
//	      String rootPath = new ClassPathResource("/static/images").getFile().getAbsolutePath();
	      String rootPath = "C:/Users/forcs/git/Tutoring_wb/tutoring_web/src/main/resources/static/images";
	      String filePath = rootPath + "/" + files.getOriginalFilename();
	      File dest = new File(filePath);
	      files.transferTo(dest); // 파일 업로드 작업 수행
	      
	      String path="http://localhost:8080/static/images"+files.getOriginalFilename();
	      return "uploaded";
	  }
	  
	  @PostMapping("/write")
	  public void createPost(@RequestBody Lecture lecture,@RequestHeader(value="X-AUTH-TOKEN") String token) {
		  System.out.println("hello world");
		  String email=jwtProvider.getUserPk(token);
		  User user=userReps.findByEmail(email).get();
		  lecture.setUser(user);
		  lecReps.save(lecture);
		  System.out.println(lecture.getId());
		  
	  }
//
	  @PostMapping("/read")
	  public List<Lecture> readPost() {
		  List<Lecture> lecture_list=new ArrayList<Lecture>();
		  lecture_list=lecReps.findAll();
		  return lecture_list;
	  }
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
