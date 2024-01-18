package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.model.repository.impl.UserRepository;
import notebook.util.Commands;



public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = UserRepository.prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    User u =UserRepository. createUser();
                    userController.saveUser(u);
                    break;
                case READ:
                    String id =UserRepository. prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    String userId = UserRepository.prompt("Enter user id: ");
                    userController.updateUser(userId, UserRepository.createUser());

                case LIST: 
                    System.out.println(userController.readAll());

                    case DELETE:
                    String deleteId =UserRepository. prompt("Enter user id for delete: ");
                    userController.deleteUser(deleteId);
                default:
                    break;    
            }
        }
    }
}

   
