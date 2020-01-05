package com.jng.tasks.quote;

import java.util.Optional;
import java.util.Random;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.jng.tasks.model.ITodoService;
import com.jng.tasks.model.Todo;

@Component
public class QuoteService implements IQuoteService {
	private ITodoService todoService;
	private Random rand = new Random();

	@Override
	public String getQuote() {
		// create a number between 0 and 2
		int nextInt = rand.nextInt(3);
		Optional<Todo> todo = todoService.getTodo(nextInt);
		return todo.map(Todo::getSummary).orElse("Not found");
	}

	@Reference
	public void setITodoService(ITodoService todoService) {
		this.todoService = todoService;
	}
}
