package com.squad.cccreview.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse {

	boolean success;
	String message;
}
