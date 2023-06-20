import DAO.*;
import model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TestUser {

    public static void main(String[] args) throws SQLException {
        // CRUD
        // Создать пользователя (C)
        User user = new User();
        user.setLogin("Тест");
        user.setPassword("Тест");
        user.setUsername("Тест");
        user.setBirthDate(new Date());
        UserRole userRole = new UserRole();
        User User = new UserDAO().add(user);
        userRole.setUser(User);
        userRole.setRole(new RoleDAO().getById(1));
        UserRole Role = new UserRoleDAO().add(userRole);
        System.out.println("Созданый пользователь: "+User);
        System.out.println("Добаввленная роль: "+Role);

        // Добавить созданному пользователю роль
        userRole.setUser(User);
        userRole.setRole(new RoleDAO().getById(2));
        Role = new UserRoleDAO().add(userRole);
        System.out.println("Пользователь: "+User);
        System.out.println("Добаввленная роль: "+Role);

        //Вывести пользователя по ID (R)
        user = new UserDAO().getById(User.getId());
        System.out.println("Вывод пользователя: "+user);

        // Обновить пользователя (U)
        User.setLogin("Тест update");
        User.setPassword("Тест update");
        User.setUsername("Тест update");
        User.setBirthDate(new Date());
        User = new UserDAO().update(User);
        System.out.println("Результат обновления пользователя: "+User);

        // Вывести всех пользователей (R)
        List<User> users1 = new UserDAO().getAll();
        System.out.println("Список пользователей:");
        for (User user2 : users1) {
            System.out.println(user2);
        }

        // Удалить пользователя по ID (D)
        user = new UserDAO().delete(User.getId());
        System.out.println("Результат удаления пользователя: "+user);

    }
}
