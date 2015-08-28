package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.TodoListDao;
import com.ebiz.mmt.domain.TodoList;
import com.ebiz.mmt.service.TodoListService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-12 10:26:26
 */
@Service
public class TodoListServiceImpl implements TodoListService {

	@Resource
	private TodoListDao todoListDao;
	

	public Long createTodoList(TodoList t) {
		return this.todoListDao.insertEntity(t);
	}

	public TodoList getTodoList(TodoList t) {
		return this.todoListDao.selectEntity(t);
	}

	public Long getTodoListCount(TodoList t) {
		return this.todoListDao.selectEntityCount(t);
	}

	public List<TodoList> getTodoListList(TodoList t) {
		return this.todoListDao.selectEntityList(t);
	}

	public int modifyTodoList(TodoList t) {
		return this.todoListDao.updateEntity(t);
	}

	public int removeTodoList(TodoList t) {
		return this.todoListDao.deleteEntity(t);
	}

	public List<TodoList> getTodoListPaginatedList(TodoList t) {
		return this.todoListDao.selectEntityPaginatedList(t);
	}

}
