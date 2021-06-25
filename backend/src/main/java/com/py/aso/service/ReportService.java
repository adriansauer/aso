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
	public ReportDTO findByYear(final DashboardDTO dashboardDTO) throws Exception {
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
}
