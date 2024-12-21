package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Task;
import com.example.demo.form.TaskForm;
import com.example.demo.mapper.TaskMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TaskController {
	// DI
	private final TaskMapper taskMapper;
	
	// メニュー画面を表示
	@GetMapping("/")
	public String showIndex() {
		return "task/index";
	}
	
	@GetMapping("/list")
	public String showTable(Model model) {
		model.addAttribute("tasks", taskMapper.getAllTasks());
		return "task/list";
	}
	
	@GetMapping("/registertable")
	public String showRegisterTable(@ModelAttribute("taskForm") TaskForm taskForm) {
		return "task/registerTable";
	}
	
	@PostMapping("/register")
	public String register(@Validated TaskForm form,
		BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "task/registerTable";
		}
		Task task = new Task();
		task.setTask(form.getRegisterString());
		taskMapper.insertTask(task);
		return "redirect:/";
	}
	
	@GetMapping("/deletetable")
	public String showDeleteTable(Model model) {
		model.addAttribute("tasks", taskMapper.getAllTasks());
		return "task/deleteTable";
	}
	
	@PostMapping("/deletetable/delete")
	public RedirectView deleteRows(@RequestParam(name = "sendCheckNumber[]") String[] sendCheckNumber) {
		int count = sendCheckNumber.length;
		
		int[] checkNumber = new int[count];
		int zero = 0;
		while (zero < count) {
			String stringnum = sendCheckNumber[zero];
			int stringtonum = Integer.parseInt(stringnum);
			checkNumber[zero] = stringtonum;
			++zero;
		}
		zero = 0;
		while (count >= zero + 1) {
			taskMapper.deleteTaskById(checkNumber[zero]);
			++zero;
		}
		//
		//
		// 本番環境ではTotalTestにリダイレクトするようにする
		return new RedirectView("/TotalTest/");
	}
	
	@GetMapping("/updatetable")
	public String showUpdateTable(@ModelAttribute("taskForm") TaskForm taskForm, Model model) {
		model.addAttribute("tasks", taskMapper.getAllTasks());
		return "task/updateTable";
	}
	
	@GetMapping("/updatetable/updatetask")
	public String showUpdateTask(@RequestParam(name = "submitTaskId") String id, @ModelAttribute("taskForm") TaskForm taskForm, Model model) {
		System.out.println("アップデートメソッド");
		System.out.println(id);
		model.addAttribute("fromId", id);
		return "task/updateTask";
	}
	
	@PostMapping("/updatetable/updatetask/update")
	public String updateRows(@Validated TaskForm form, @RequestParam(name = "submitTaskId") String id, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "task/updateTask";
		}
		int idNumber = Integer.parseInt(id);
		form.setId(idNumber);
		
		taskMapper.updateTask(form);
		return "redirect:/";
	}
}