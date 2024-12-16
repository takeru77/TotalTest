package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Task;
import com.example.demo.form.TaskForm;

@Mapper
public interface TaskMapper {
	/** 全件取得 */
	List<TaskForm> getAllTasks();
	
	/** 登録 */
	void insertTask(Task task);
	
	/** 更新 */
	void updateTask(TaskForm task);
	
	/** 削除 */
	void deleteTaskById(int task);
}
