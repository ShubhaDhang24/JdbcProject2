package model;

import Data.TodoItems;
import com.shubha.database.MySql;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Todo_Item implements TodoItems {
    private int todo_id;
    private String title;
    private String description;
    LocalDate deadline;
    private boolean done;
    private int assignee_id;


    public Todo_Item(int todo_id, String title, String description, LocalDate deadline, boolean done, int assignee_id) {
        this.todo_id = todo_id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
        this.assignee_id = assignee_id;
    }

    public Todo_Item(String title, String description, LocalDate deadline, int assignee_id) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.assignee_id = assignee_id;

    }

    public int getTodo_id() {
        return todo_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean getDone() {
        return done;
    }

    public int getAssignee_id() {
        return assignee_id;
    }

    @Override
    public String toString() {
        return "todo_Item{" +
                "todo_id=" + todo_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", done=" + done +
                ", assignee_id=" + assignee_id +
                '}';
    }

    @Override
    public Todo_Item create(Todo_Item item) {
        String sqlQuery = "INSERT INTO your_table_name (column1, column2, column3) VALUES (?, ?, ?)\";\n";

        try {
            Connection connection = MySql.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, getTodo_id());
            statement.setString(2, "java_book");
            statement.setString(3, "fundamentals of java");
            statement.setDate(4, Date.valueOf("LocalDate.now()"));
            statement.setBoolean(5, true);
            statement.setInt(6, getAssignee_id());
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new record was inserted successfully.");
            }
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
        return item;
    }

    @Override
    public Collection<Todo_Item> findAll() {

        List<Todo_Item> items = new ArrayList<>();
        try (Connection connection = MySql.getConnection();

             Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery("Select * from Todo_item");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                LocalDate date = resultSet.getDate(4).toLocalDate();
                boolean done = resultSet.getBoolean(5);
                int assignId = resultSet.getInt(6);
                Todo_Item item1 = new Todo_Item(id, title, description, date, done, assignId);
                items.add(item1);
            }
        } catch (SQLException s) {
            System.out.println(s.getStackTrace());
        }
        return items;
    }

    @Override
    public Todo_Item findById(int id) {
        Todo_Item item = null;
        String sqlQuery = "Select * from Todo_Item where todo_id=?";
        try (Connection connection = MySql.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, 2);
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                int todoId = resultSet.getInt(todo_id);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                LocalDate date = resultSet.getDate(4).toLocalDate();
                boolean done = resultSet.getBoolean(5);
                int assignId = resultSet.getInt(6);
                item = new Todo_Item(todoId, title, description, date, done, assignId);
            }
        } catch (SQLException s) {
            System.out.println(s.getStackTrace());
        }
        return item;
    }

    @Override
    public Collection<Todo_Item> findByDoneStatus(boolean val) {
        List<Todo_Item> todoItems = new ArrayList<>();
        String sql = "SELECT id, description, done FROM todo_items WHERE done = ?";
        try {
            Connection connection = MySql.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, val);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                boolean done = resultSet.getBoolean("done");
                Todo_Item todoItem = new Todo_Item(id, title, description, deadline, done, assignee_id);
                todoItems.add(todoItem);
            }
        } catch (SQLException s) {
            System.out.println(s.getStackTrace());
        }
        return todoItems;
    }

    @Override
    public Collection<Todo_Item> findByAssignee(Person per) {
        String sql = "SELECT id, description, done FROM todo_items WHERE assignee_id = ?";
        List<Todo_Item> todoItems = new ArrayList<>();
        Connection connection = MySql.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("sql");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                boolean done = resultSet.getBoolean("done");
                Todo_Item todoItem = new Todo_Item(id, title, description, deadline, done, assignee_id);
                todoItems.add(todoItem);

            }
        } catch (SQLException e) {
            System.out.println("Database does not exist");
            ;
        }


        return todoItems;
    }

    @Override
    public Collection<Todo_Item> findByUnassignedTodoItems() {

        String sql = "SELECT id, description, done FROM todo_items WHERE assignee_id is NULL";
        List<Todo_Item> todoItems = new ArrayList<>();
        Connection connection = MySql.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("sql");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                boolean done = resultSet.getBoolean("done");
                Todo_Item todoItem = new Todo_Item(id, title, description, deadline, done, assignee_id);
                todoItems.add(todoItem);
            }
        } catch (SQLException e) {
            System.out.println("Database does not exist");
        }
        return todoItems;
    }

    @Override
    public Todo_Item update(Todo_Item item) {
        String sql = "UPDATE Todo_Item SET title=?, description=?, done=? FROM todo_items WHERE id=?";
        Connection connection = MySql.getConnection();
        int updateRow;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2, item.getTitle());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setBoolean(4, item.getDone());
            updateRow = preparedStatement.executeUpdate();
            if (updateRow > 0)
                System.out.println("Update was Success full");
        } catch (SQLException e) {
            System.out.println("Data base does not exist");;
        }
        return item;
    }

    @Override
    public boolean deleteById(int delId) {
        String sql="Delete from Todo_Item where id=?";
        Connection connection=MySql.getConnection();
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,delId);
            int rowDeleted=statement.executeUpdate();
            return rowDeleted>0;

        } catch (SQLException e) {
            System.out.println("Data Base does not exist");
        }
        return false;
    }
}
