package com.lab10.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lab10.domain.Dept;
import com.lab10.domain.Emp;

public interface EmpRepository extends MongoRepository<Emp, Integer> {
	List<Emp> findByEnameContaining(String keyword);
    // {ename : {$regex: *.keyword.*,$options: 1} }
	// Query query = new Query();
	// query.addCriteria(Criteria.where("ename").regex( *.keyword ))
	
	List<Emp> findBySalGreaterThanEqual(Integer sal);
    // {sal : {$gte: salValue } }
	// Query query = new Query();
	// query.addCriteria(Criteria.where("sal").gte( sqlValue ))
	
	List<Emp> findByDept(Integer dept);
	// {dept: deptnoValue}
	// Query query = new Query();
	// query.addCriteria(Criteria.where("dept").is( sqlValue ))
}