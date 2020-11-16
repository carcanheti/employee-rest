package br.com.register.employee.dto;

import java.util.List;

public class DataListOutputDTO<T> {

	private List<T> data;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
