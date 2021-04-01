package com.py.aso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import com.py.aso.dto.SettingDTO;
import com.py.aso.service.SettingService;

import io.swagger.annotations.Api;

@Api(value = "Controlador de configuraciones")
@RestController
public class SettingController implements BaseController<SettingDTO> {

	@Autowired
	private SettingService settingService;

	@Override
	public Page<SettingDTO> index(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SettingDTO find(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SettingDTO create(SettingDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SettingDTO update(long id, SettingDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleted(long id) throws Exception {
		// TODO Auto-generated method stub

	}

}
