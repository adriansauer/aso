package com.py.aso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.PublicationEntity;

@Repository
public interface ReportRepository extends JpaRepository<PublicationEntity, Long> {

	@Query(value = "SELECT COUNT(p.id) FROM PUBLICATIONS p INNER JOIN USERS u ON p.user_id = u.id WHERE YEAR(p.created_at) = ?1  AND MONTH(p.created_at) = ?2 AND INCIDENCE_CODE_ID = ?3 AND p.deleted = ?4 AND u.enabled = ?5", nativeQuery = true)
	public Long countByYearAndMonthAndCodeIdAndDeletedAndEnabled(final Long year, final Long month, final Long codeId,
			final boolean deleted, final boolean enabled);

	@Query(value = "SELECT COUNT(p.id) FROM PUBLICATIONS p INNER JOIN USERS u ON p.user_id = u.id WHERE YEAR(p.created_at) = ?1 AND INCIDENCE_CODE_ID = ?2 AND p.deleted = ?3 AND u.enabled = ?4", nativeQuery = true)
	public Long countByYearAndCodeIdAndDeletedAndEnabled(final Long year, final Long codeId, final boolean deleted,
			final boolean enabled);

	@Query(value = "SELECT COUNT(p.id) FROM PUBLICATIONS p INNER JOIN USERS u ON p.user_id = u.id WHERE YEAR(p.created_at) = ?1  AND MONTH(p.created_at) = ?2 AND INCIDENCE_CODE_ID = ?3 AND u.id = ?4 AND p.deleted = ?5 AND u.enabled = ?6", nativeQuery = true)
	public Long countByYearAndMonthAndCodeIdAndBrigadeIdAndDeletedAndEnabled(final Long year, final Long month,
			final Long codeId, final Long userId, final boolean deleted, final boolean enabled);

	@Query(value = "SELECT COUNT(p.id) FROM PUBLICATIONS p INNER JOIN USERS u ON p.user_id = u.id WHERE YEAR(p.created_at) = ?1  AND INCIDENCE_CODE_ID = ?2 AND u.id = ?3 AND p.deleted = ?4 AND u.enabled = ?5", nativeQuery = true)
	public Long countByYearAndCodeIdAndBrigadeIdAndDeletedAndEnabled(final Long year, final Long codeId,
			final Long userId, final boolean deleted, final boolean enabled);

	@Query(value = "SELECT COUNT(p.id) FROM PUBLICATIONS p INNER JOIN USERS u ON p.user_id = u.id WHERE YEAR(p.created_at) = ?1  AND MONTH(p.created_at) = ?2 AND u.id = ?3 AND p.deleted = ?4 AND u.enabled = ?5", nativeQuery = true)
	public Long countByYearAndMonthAndUserIdAndDeletedAndEnabled(final Long year, final Long month, final Long userId,
			final boolean deleted, final boolean enabled);

	@Query(value = "SELECT COUNT(p.id) FROM PUBLICATIONS p INNER JOIN USERS u ON p.user_id = u.id WHERE YEAR(p.created_at) = ?1  AND u.id = ?2 AND p.deleted = ?3 AND u.enabled = ?4", nativeQuery = true)
	public Long countByYearAndUserIdAndDeletedAndEnabled(final Long year, final Long userId, final boolean deleted,
			final boolean enabled);

	@Query(value = "SELECT COUNT(p.id) FROM PUBLICATIONS p INNER JOIN USERS u ON p.user_id = u.id WHERE INCIDENCE_CODE_ID = ?1  AND MONTH(p.created_at) = ?2 AND u.id = ?3 AND p.deleted = ?4 AND u.enabled = ?5", nativeQuery = true)
	public Long countByCodeIdAndMonthAndUserIdAndDeletedAndEnabled(final Long codeId, final Long month,
			final Long userId, final boolean deleted, final boolean enabled);

	@Query(value = "SELECT COUNT(p.id) FROM PUBLICATIONS p INNER JOIN USERS u ON p.user_id = u.id WHERE INCIDENCE_CODE_ID = ?1  AND u.id = ?2 AND p.deleted = ?3 AND u.enabled = ?4", nativeQuery = true)
	public Long countByCodeIdAndUserIdAndDeletedAndEnabled(final Long CodeId, final Long userId, final boolean deleted,
			final boolean enabled);

	@Query(value = "SELECT p.INCIDENCE_CODE_ID, month(p.CREATED_AT), COUNT(p.id)\r\n"
			+ "FROM PUBLICATIONS p INNER JOIN USERS u ON p.user_id = u.id \r\n"
			+ "WHERE YEAR(p.created_at) = ?1  AND u.id = ?2 AND p.deleted = ?3 AND u.enabled = ?4 And p.INCIDENCE_CODE_ID IN(SELECT  id FROM INCIDENCE_CODES LIMIT ?5)\r\n"
			+ "GROUP BY p.INCIDENCE_CODE_ID, month(p.CREATED_AT)\r\n" 
			+ "ORDER BY p.INCIDENCE_CODE_ID, month(p.CREATED_AT), COUNT(p.id)\r\n"
			+ "", nativeQuery = true)
	public List<Object[]> findAllYearAndUserIdAndDeletedAndEnabledAndLimit(final Long year, final Long userId,
			final boolean deleted, final boolean enabled, final Long limit);
}
