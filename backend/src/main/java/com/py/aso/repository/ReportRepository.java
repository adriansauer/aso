package com.py.aso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.PublicationEntity;

@Repository
public interface ReportRepository extends JpaRepository<PublicationEntity, Long> {

	@Query(value = "SELECT COUNT(p.id) FROM PUBLICATIONS p INNER JOIN USERS u ON p.user_id = u.id WHERE YEAR(p.created_at) = ?1  AND MONTH(p.created_at) = ?2 AND INCIDENCE_CODE_ID = ?3 AND p.deleted = ?4 AND u.enabled = ?5", nativeQuery = true)
	public Long countByYearAndMonthAndCodeIdAndDeletedAndEnabled(final Long year, final Long month, final Long codeId, final boolean deleted, final boolean enabled);
	
	@Query(value = "SELECT COUNT(p.id) FROM PUBLICATIONS p INNER JOIN USERS u ON p.user_id = u.id WHERE YEAR(p.created_at) = ?1 AND INCIDENCE_CODE_ID = ?2 AND p.deleted = ?3 AND u.enabled = ?4", nativeQuery = true)
	public Long countByYearAndCodeIdAndDeletedAndEnabled(final Long year, final Long codeId, final boolean deleted, final boolean enabled);
	
}
