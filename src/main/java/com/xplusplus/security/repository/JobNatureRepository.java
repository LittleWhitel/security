package com.xplusplus.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xplusplus.security.domain.JobNature;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午8:40:45 2018年5月23日
 */
@Repository
public interface JobNatureRepository extends JpaRepository<JobNature, Integer>{
	/**
	 * 通过名称模糊查询-分页
	 * 
	 * @param name
	 * @param pageable
	 * @return
	 */
	public Page<JobNature> findByNameLike(String name, Pageable pageable);
}
