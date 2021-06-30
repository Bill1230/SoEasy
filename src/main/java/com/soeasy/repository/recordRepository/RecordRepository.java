package com.soeasy.repository.recordRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soeasy.model.RecordBean;

public interface RecordRepository extends JpaRepository<RecordBean, Integer>{
	List<RecordBean> findByRecordId(Integer recordId);
	List<RecordBean> findByCustomerId(Integer customerId);
}
