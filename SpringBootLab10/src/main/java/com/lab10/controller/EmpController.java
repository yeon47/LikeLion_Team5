package com.lab10.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lab10.domain.Emp;
import com.lab10.service.EmpService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/emp")
public class EmpController {
private final EmpService empService;
	
	public EmpController(EmpService empService) {
		this.empService = empService;
	}
	
	// ResponseEntity를 쓰는 이유 -> 상태코드,헤더,본문제어용
	
	//1. 등록 및 수정
	@PostMapping
	public ResponseEntity<String> save(@RequestBody Emp emp) {
		empService.save(emp);
		return ResponseEntity.ok("saved");
		// ok()=상태코드 200+saved 문자열
	}
	
	//2. 전체조회
	@GetMapping
	public List<Emp> findAll(){
		return empService.findAll();
	}
	
	//3. 단일 조회 
	@GetMapping("/{empno}")
	public Emp findByEmpno(@PathVariable int empno) {
		return empService.findByEmpno(empno);
	}
	
	//4. 삭제
	@DeleteMapping("/{empno}")
	public ResponseEntity<String> delete(@PathVariable int empno) {
		empService.delete(empno);
		return ResponseEntity.ok("deleted");
		//ok() = 상태코드 200+deleted 문자열
	}
	
	//5. 이름 키워드 검색
	@GetMapping("/search")
	public List<Emp> searchByName(@RequestParam String keyword){
		return empService.searchByName(keyword);
	}
	
	//6. 급여 조건 검색
	@GetMapping("/sal")
	public List<Emp> findBySalaryAbove(@RequestParam Integer min){
		return empService.findBySalaryAbove(min);
	}
	
	//7. 부서번호로 사원 검색
	@GetMapping("/dept/{deptno}")
	public List<Emp> findByDeptno(@PathVariable Integer deptno){
		return empService.findByDeptno(deptno);
	}
	
	
	

}
