package com.eoptech.shopdoda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AjaxResponse {

	private int statusCode;
	private Object data;
	
}
