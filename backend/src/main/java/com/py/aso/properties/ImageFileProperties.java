package com.py.aso.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "image.files.images")
public class ImageFileProperties {

	private String root;

}
