package com.py.aso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.DashboardDTO;
import com.py.aso.dto.Months;
import com.py.aso.dto.ReportDTO;
import com.py.aso.dto.detail.IncidenceCodeDetailDTO;
import com.py.aso.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
	private IncidenceCodeService IncidenceCodeService;

	@Autowired
	private ReportRepository reportRepository;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ReportDTO findByYearAndCode(final DashboardDTO dashboardDTO) throws Exception {
		final IncidenceCodeDetailDTO incidenceDTO = this.IncidenceCodeService.findByCode(dashboardDTO.getCode());

		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setYear(dashboardDTO.getYear());
		reportDTO.setCode(incidenceDTO.getCode());
		reportDTO.setDescription(incidenceDTO.getDescription());

		Months months = new Months();
		final Long year = dashboardDTO.getYear();
		final Long code = incidenceDTO.getId();
		months.setJanuary(
				this.reportRepository.countByYearAndMonthAndCodeIdAndDeletedAndEnabled(year, 1L, code, false, true));
		months.setFebruary(
				this.reportRepository.countByYearAndMonthAndCodeIdAndDeletedAndEnabled(year, 2L, code, false, true));
		months.setMarch(
				this.reportRepository.countByYearAndMonthAndCodeIdAndDeletedAndEnabled(year, 3L, code, false, true));
		months.setApril(
				this.reportRepository.countByYearAndMonthAndCodeIdAndDeletedAndEnabled(year, 4L, code, false, true));
		months.setMay(
				this.reportRepository.countByYearAndMonthAndCodeIdAndDeletedAndEnabled(year, 5L, code, false, true));
		months.setJune(
				this.reportRepository.countByYearAndMonthAndCodeIdAndDeletedAndEnabled(year, 6L, code, false, true));
		months.setJuly(
				this.reportRepository.countByYearAndMonthAndCodeIdAndDeletedAndEnabled(year, 7L, code, false, true));
		months.setAugust(
				this.reportRepository.countByYearAndMonthAndCodeIdAndDeletedAndEnabled(year, 8L, code, false, true));
		months.setSeptember(
				this.reportRepository.countByYearAndMonthAndCodeIdAndDeletedAndEnabled(year, 9L, code, false, true));
		months.setOctober(
				this.reportRepository.countByYearAndMonthAndCodeIdAndDeletedAndEnabled(year, 10L, code, false, true));
		months.setNovember(
				this.reportRepository.countByYearAndMonthAndCodeIdAndDeletedAndEnabled(year, 11L, code, false, true));
		months.setDecember(
				this.reportRepository.countByYearAndMonthAndCodeIdAndDeletedAndEnabled(year, 12L, code, false, true));
		reportDTO.setMonths(months);
		
		reportDTO.setQuantity(this.reportRepository.countByYearAndCodeIdAndDeletedAndEnabled(year, code, false, true));
		
		return reportDTO;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ReportDTO findByYearAndUserAndCode(final DashboardDTO dashboardDTO) throws Exception {
		final IncidenceCodeDetailDTO incidenceDTO = this.IncidenceCodeService.findByCode(dashboardDTO.getCode());

		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setYear(dashboardDTO.getYear());
		reportDTO.setCode(incidenceDTO.getCode());
		reportDTO.setDescription(incidenceDTO.getDescription());

		Months months = new Months();
		final Long year = dashboardDTO.getYear();
		final Long code = incidenceDTO.getId();
		final Long user = dashboardDTO.getUserId();
		months.setJanuary(
				this.reportRepository.countByYearAndMonthAndCodeIdAndBrigadeIdAndDeletedAndEnabled(year, 1L, code, user, false, true));
		months.setFebruary(
				this.reportRepository.countByYearAndMonthAndCodeIdAndBrigadeIdAndDeletedAndEnabled(year, 2L, code, user, false, true));
		months.setMarch(
				this.reportRepository.countByYearAndMonthAndCodeIdAndBrigadeIdAndDeletedAndEnabled(year, 3L, code, user, false, true));
		months.setApril(
				this.reportRepository.countByYearAndMonthAndCodeIdAndBrigadeIdAndDeletedAndEnabled(year, 4L, code, user, false, true));
		months.setMay(
				this.reportRepository.countByYearAndMonthAndCodeIdAndBrigadeIdAndDeletedAndEnabled(year, 5L, code, user, false, true));
		months.setJune(
				this.reportRepository.countByYearAndMonthAndCodeIdAndBrigadeIdAndDeletedAndEnabled(year, 6L, code, user, false, true));
		months.setJuly(
				this.reportRepository.countByYearAndMonthAndCodeIdAndBrigadeIdAndDeletedAndEnabled(year, 7L, code, user, false, true));
		months.setAugust(
				this.reportRepository.countByYearAndMonthAndCodeIdAndBrigadeIdAndDeletedAndEnabled(year, 8L, code, user, false, true));
		months.setSeptember(
				this.reportRepository.countByYearAndMonthAndCodeIdAndBrigadeIdAndDeletedAndEnabled(year, 9L, code, user, false, true));
		months.setOctober(
				this.reportRepository.countByYearAndMonthAndCodeIdAndBrigadeIdAndDeletedAndEnabled(year, 10L, code, user, false, true));
		months.setNovember(
				this.reportRepository.countByYearAndMonthAndCodeIdAndBrigadeIdAndDeletedAndEnabled(year, 11L, code, user, false, true));
		months.setDecember(
				this.reportRepository.countByYearAndMonthAndCodeIdAndBrigadeIdAndDeletedAndEnabled(year, 12L, code, user, false, true));
		reportDTO.setMonths(months);
		
		reportDTO.setQuantity(this.reportRepository.countByYearAndCodeIdAndBrigadeIdAndDeletedAndEnabled(year, code, user, false, true));
		
		return reportDTO;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ReportDTO findByYearAndUser(final DashboardDTO dashboardDTO) throws Exception {

		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setYear(dashboardDTO.getYear());

		Months months = new Months();
		final Long year = dashboardDTO.getYear();
		final Long user = dashboardDTO.getUserId();
		months.setJanuary(
				this.reportRepository.countByYearAndMonthAndUserIdAndDeletedAndEnabled(year, 1L, user, false, true));
		months.setFebruary(
				this.reportRepository.countByYearAndMonthAndUserIdAndDeletedAndEnabled(year, 2L, user, false, true));
		months.setMarch(
				this.reportRepository.countByYearAndMonthAndUserIdAndDeletedAndEnabled(year, 3L, user, false, true));
		months.setApril(
				this.reportRepository.countByYearAndMonthAndUserIdAndDeletedAndEnabled(year, 4L, user, false, true));
		months.setMay(
				this.reportRepository.countByYearAndMonthAndUserIdAndDeletedAndEnabled(year, 5L, user, false, true));
		months.setJune(
				this.reportRepository.countByYearAndMonthAndUserIdAndDeletedAndEnabled(year, 6L, user, false, true));
		months.setJuly(
				this.reportRepository.countByYearAndMonthAndUserIdAndDeletedAndEnabled(year, 7L, user, false, true));
		months.setAugust(
				this.reportRepository.countByYearAndMonthAndUserIdAndDeletedAndEnabled(year, 8L, user, false, true));
		months.setSeptember(
				this.reportRepository.countByYearAndMonthAndUserIdAndDeletedAndEnabled(year, 9L, user, false, true));
		months.setOctober(
				this.reportRepository.countByYearAndMonthAndUserIdAndDeletedAndEnabled(year, 10L, user, false, true));
		months.setNovember(
				this.reportRepository.countByYearAndMonthAndUserIdAndDeletedAndEnabled(year, 11L, user, false, true));
		months.setDecember(
				this.reportRepository.countByYearAndMonthAndUserIdAndDeletedAndEnabled(year, 12L, user, false, true));
		reportDTO.setMonths(months);
		
		reportDTO.setQuantity(this.reportRepository.countByYearAndUserIdAndDeletedAndEnabled(year, user, false, true));
		
		return reportDTO;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ReportDTO findByCodeAndUser(final DashboardDTO dashboardDTO) throws Exception {

		final IncidenceCodeDetailDTO incidenceDTO = this.IncidenceCodeService.findByCode(dashboardDTO.getCode());
		
		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setYear(dashboardDTO.getYear());

		Months months = new Months();
		final Long code = incidenceDTO.getId();
		final Long user = dashboardDTO.getUserId();
		months.setJanuary(
				this.reportRepository.countByCodeIdAndMonthAndUserIdAndDeletedAndEnabled(code, 1L, user, false, true));
		months.setFebruary(
				this.reportRepository.countByCodeIdAndMonthAndUserIdAndDeletedAndEnabled(code, 2L, user, false, true));
		months.setMarch(
				this.reportRepository.countByCodeIdAndMonthAndUserIdAndDeletedAndEnabled(code, 3L, user, false, true));
		months.setApril(
				this.reportRepository.countByCodeIdAndMonthAndUserIdAndDeletedAndEnabled(code, 4L, user, false, true));
		months.setMay(
				this.reportRepository.countByCodeIdAndMonthAndUserIdAndDeletedAndEnabled(code, 5L, user, false, true));
		months.setJune(
				this.reportRepository.countByCodeIdAndMonthAndUserIdAndDeletedAndEnabled(code, 6L, user, false, true));
		months.setJuly(
				this.reportRepository.countByCodeIdAndMonthAndUserIdAndDeletedAndEnabled(code, 7L, user, false, true));
		months.setAugust(
				this.reportRepository.countByCodeIdAndMonthAndUserIdAndDeletedAndEnabled(code, 8L, user, false, true));
		months.setSeptember(
				this.reportRepository.countByCodeIdAndMonthAndUserIdAndDeletedAndEnabled(code, 9L, user, false, true));
		months.setOctober(
				this.reportRepository.countByCodeIdAndMonthAndUserIdAndDeletedAndEnabled(code, 10L, user, false, true));
		months.setNovember(
				this.reportRepository.countByCodeIdAndMonthAndUserIdAndDeletedAndEnabled(code, 11L, user, false, true));
		months.setDecember(
				this.reportRepository.countByCodeIdAndMonthAndUserIdAndDeletedAndEnabled(code, 12L, user, false, true));
		reportDTO.setMonths(months);
		
		reportDTO.setQuantity(this.reportRepository.countByCodeIdAndUserIdAndDeletedAndEnabled(code, user, false, true));
		
		return reportDTO;
	}
}
