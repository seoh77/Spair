package com.ssafy.spair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spair.model.dto.SearchFilter;
import com.ssafy.spair.model.dto.User;
import com.ssafy.spair.model.service.PostService;
import com.ssafy.spair.model.service.SearchService;
import com.ssafy.spair.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/search")
@Tag(name = "search-controller", description = "검색 API")
@CrossOrigin(origins = "http://localhost:5173")
public class SearchController {

	private final SearchService searchService ;
	private final PostService postService ;
	private final UserService userService ;
	
	@Autowired
	public SearchController(SearchService searchService, PostService postService, UserService userService) {
		this.searchService = searchService ;
		this.postService = postService ;
		this.userService = userService ;
	}
	
	// 전체 게시글 통합 검색
	@GetMapping("{keyword}")
	@Operation(summary = "전체 게시글 통합 검색", description = "전체 게시글에서 제목이나 작성자에 사용자가 입력한 keyword가 포함되어 있다면 반환")
	public ResponseEntity<?> searchKeyword(@PathVariable("keyword") String keyword) {
		return new ResponseEntity<>(searchService.searchKeyword(keyword), HttpStatus.OK) ;
	}
	
	// 전체 게시글 검색필터 (모집여부, 성별, 운동종류, 가격)
	@GetMapping
	@Operation(summary = "전체 게시글 검색 필터", description = "전체 게시글에서 '모집여부, 성별, 운동종류, 가격'를 필터링하여 조건을 충족하는 게시글만 반환")
	public ResponseEntity<?> filterSearch(
	        @RequestParam(value = "status", required = false) Integer status,
	        @RequestParam(value = "gender", required = false) Integer gender,
	        @RequestParam(value = "exerciseType", required = false) String exerciseType,
	        @RequestParam(value = "minPrice", required = false) Integer minPrice,
	        @RequestParam(value = "maxPrice", required = false) Integer maxPrice) {

	    SearchFilter searchFilter = new SearchFilter(status, gender, exerciseType, minPrice, maxPrice);
	    
	    if (searchFilter.isEmpty()) {
	        // 검색 필터가 비어 있으면 모든 게시글 반환
	        return new ResponseEntity<>(postService.searchAll(), HttpStatus.OK);
	    } else {
	        // 비어 있지 않으면 필터링된 결과 반환
	        return new ResponseEntity<>(searchService.filterSearch(searchFilter), HttpStatus.OK);
	    }
	}
	
	// 우리 동네 게시글 검색 필터 (모집여부, 성별, 운동종류, 가격)
	@GetMapping("town")
	@Operation(summary = "우리 동네 게시글 검색 필터", description = "DB에 저장된 사용자 위치의 반경 1km 이내에 있는 sports center의 게시글에서 '모집여부, 성별, 운동종류, 가격'를 필터링하여 조건을 충족하는 게시글만 반환")
	public ResponseEntity<?> townFilterSearch(
			@RequestParam(value = "userId") int userId,
	        @RequestParam(value = "status", required = false) Integer status,
	        @RequestParam(value = "gender", required = false) Integer gender,
	        @RequestParam(value = "exerciseType", required = false) String exerciseType,
	        @RequestParam(value = "minPrice", required = false) Integer minPrice,
	        @RequestParam(value = "maxPrice", required = false) Integer maxPrice) {

		User user = userService.search(userId) ;
		double latitude = user.getLatitude() ;
		double longitude = user.getLongitude() ;
		
	    SearchFilter searchFilter = new SearchFilter(status, gender, exerciseType, minPrice, maxPrice, latitude, longitude);
	    
	    if (searchFilter.isEmpty()) {
	        // 검색 필터가 비어 있으면 모든 게시글 반환
	        return new ResponseEntity<>(postService.townSearchAll(latitude, longitude), HttpStatus.OK);
	    } else {
	        // 비어 있지 않으면 필터링된 결과 반환
	        return new ResponseEntity<>(searchService.townFilterSearch(searchFilter), HttpStatus.OK);
	    }
	}
	
}
