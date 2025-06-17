package com.lab10.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lab10.domain.Dept;
import com.lab10.service.DeptService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/dept")
public class DeptController {

	private final DeptService deptService;

	public DeptController(DeptService deptService) {
		this.deptService = deptService;
	}

	// 1. 등록 및 수정 @RequestBody -> 요청 시 Json 데이터
	@PostMapping
	public String save(@RequestBody Dept dept) {
		deptService.save(dept);
		return "saved";
	}

	// 2. 전체 조회 /dept -> 전체 부서 조회를 목적
	@GetMapping
	public List<Dept> findAll() {
		return deptService.findAll();
	}

	// 3.단일 조회 /dept/10
	@GetMapping("/{deptno}")
	public Dept findByDeptno(@PathVariable int deptno) {
		return deptService.findByDeptno(deptno);
	}

	// 4. 부서명 키워드가 검색 /dept/search?keyword=
	@GetMapping("/search")
	public List<Dept> searchByName(@RequestParam String keyword) {
		return deptService.findByDnameContaining(keyword);
	}

	// 5. 지도 마커용 API(위도/경도 포함) /dept/api-> 지도용 json 리턴
	// 목적을 2번과 다르게 함
	@GetMapping("/api")
	public List<Dept> findForMap() {
		return deptService.findAll();
	}
}
