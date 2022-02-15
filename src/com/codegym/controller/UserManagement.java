package com.codegym.controller;

import com.codegym.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagement implements ReadFile,WriteFile,Serializable {
    private List<User> users = new ArrayList<>();
    private static UserManagement userManagement;

    public UserManagement(){
        File file = new File("user.txt");
        if(file.exists()){
            try{
                readFile("user.txt");
            }
            catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        users.add(new User("admin","123456","ROLE_ADMIN"));
    }

    public void displayAll(){
        for (User user: users) {
            System.out.println(user);
        }
    }

    public void register(User user){
        this.users.add(user);
        try{
            writeFile("user.txt");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.users = (List<User>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.users);
    }

    public boolean checkUsernameExist(String username) {//Kiểm tra xem username đã tồn tại hay chưa
        boolean isExisted = false;
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                isExisted = true;
                break;
            }
        }
        return isExisted;
    }

    public boolean checkUserLogin(String username, String password,String role) {//Kiểm tra xem user đăng nhập có tồn tại hay không
        boolean isLogin = false;
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword()) && role.equals(users.get(i).getRole())) {
                isLogin = true;
                break;
            }
        }
        return isLogin;
    }

    public boolean isAdmin(User user){
        if (user.getRole().equals("ROLE_ADMIN")){
            return true;
        } else {
            return false;
        }
    }

    public User findUserByUsername(String username){
        for (User user: users) {
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public static UserManagement getInstance(){
        if(userManagement == null){
            userManagement = new UserManagement();
        }
        return userManagement;
    }
}
