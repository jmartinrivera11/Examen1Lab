package examen1_lab;

import java.util.ArrayList;

public abstract class SocialClass {
    protected ArrayList<String> friends;
    protected ArrayList<String> posts;
    protected String username;

    public SocialClass(String username) {
        this.username = username;
        this.friends = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public boolean addFriend(String user) {
        if (!friends.contains(user) && !user.equals(username)) {
            friends.add(user);
            return true;
        }
        return false;
    }

    public void addPost(String msg) {
        posts.add(msg);
    }

    public void myProfile() {
        System.out.println("Username: " + username);
        timeline();
        System.out.println("Friends: ");
        int counter = 0;
        for (String friend : friends) {
            System.out.print(friend + " ");
            counter++;
            if (counter == 10) {
                System.out.println();
                counter = 0;
            }
        }
        System.out.println();
    }

    public abstract void timeline();
}
