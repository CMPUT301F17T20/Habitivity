package main.habitivity;


import java.util.Comparator;
import java.util.List;

public class UserList {
    private List<User> users;
    private String ID;

    public UserList(){}

    public void addUser(User user) {
        users.add(user);
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public List<User> getSortedUserList() {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return user.getUserName().compareToIgnoreCase(t1.getUserName());
            }
        });
        return users;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
