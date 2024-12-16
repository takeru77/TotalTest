package com.example.demo.entity;

import lombok.Data;

@Data
public class Task {
	private int id;
	private String task;
	
	public String toString() {
		//
		return id + "/" + task;
	}
}
