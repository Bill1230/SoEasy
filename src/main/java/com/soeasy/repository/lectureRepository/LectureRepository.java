package com.soeasy.repository.lectureRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soeasy.model.LectureBean;
import com.soeasy.model.LecturerBean;

public interface LectureRepository extends JpaRepository<LectureBean, Integer> {

	List<LectureBean> findByLectureId(Integer lectureId);
	
}