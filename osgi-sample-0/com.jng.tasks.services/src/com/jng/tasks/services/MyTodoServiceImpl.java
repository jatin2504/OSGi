package com.jng.tasks.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;

import com.jng.tasks.model.ITodoService;
import com.jng.tasks.model.Todo;

@Component
public class MyTodoServiceImpl implements ITodoService {

	private static AtomicInteger current = new AtomicInteger(1);
    private List<Todo> todos;
    
    public MyTodoServiceImpl() {
        todos = createInitialModel();
    }
	
	@Override
	public void getTodos(Consumer<List<Todo>> todosConsumer) {
		todosConsumer.accept(todos.stream().map(t -> t.copy()).collect(Collectors.toList()));
	}

	@Override
	public boolean saveTodo(Todo newTodo) {
		Optional<Todo> todoOptional = findById(newTodo.getId());

        // get the actual todo or create a new one
        Todo todo = todoOptional.orElse(new Todo(current.getAndIncrement()));
        todo.setSummary(newTodo.getSummary());
        todo.setDescription(newTodo.getDescription());
        todo.setDone(newTodo.isDone());
        todo.setDueDate(newTodo.getDueDate());

        if (!todoOptional.isPresent()) {
            todos.add(todo);
        }
        return true;
	}

	@Override
	public Optional<Todo> getTodo(long id) {
		return findById(id).map(todo -> todo.copy());
	}
	
	protected List<Todo> getTodosInternal() {
        return todos;
    }

	@Override
	public boolean deleteTodo(long id) {
		Optional<Todo> deleteTodo = findById(id);

        deleteTodo.ifPresent(todo -> {
            todos.remove(todo);
        });

        return deleteTodo.isPresent();
	}
	
	private List<Todo> createInitialModel() {
        List<Todo> list = new ArrayList<>();
        list.add(createTodo("Application model", "Flexible and extensible"));
        list.add(createTodo("DI", "@Inject as programming mode"));
        list.add(createTodo("OSGi", "Services"));
        list.add(createTodo("SWT", "Widgets"));
        list.add(createTodo("JFace", "Especially Viewers!"));
        list.add(createTodo("CSS Styling", "Style your application"));
        list.add(createTodo("Eclipse services", "Selection, model, Part"));
        list.add(createTodo("Renderer", "Different UI toolkit"));
        list.add(createTodo("Compatibility Layer", "Run Eclipse 3.x"));
        return list;
    }
	
	private Todo createTodo(String summary, String description) {
        return new Todo(current.getAndIncrement(), summary, description, false, new Date());
    }
	
	private Optional<Todo> findById(long id) {
        return getTodosInternal().stream().filter(t -> t.getId() == id).findAny();
    }


}
