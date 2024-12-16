package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskForm {
	@NotNull(message = "タスクが未入力です")
	@Length(min = 1, max = 10, message = "1～10文字の範囲で入力して下さい")
	private String registerString;
	private int id;
	
	@Override
	public String toString() {
		//
		return id + "+" + registerString;
	}
}
