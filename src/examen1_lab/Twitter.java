package examen1_lab;

import javax.swing.JOptionPane;

public class Twitter extends SocialClass {
    public Twitter(String username) {
        super(username);
    }

    @Override
    public void timeline() {
        StringBuilder timeline = new StringBuilder("Perfil de: " + username + "\n\n");

        timeline.append("Amigos:\n");
        for (int i = 0; i < friends.size(); i++) {
            timeline.append(friends.get(i));
            if ((i + 1) % 10 == 0) {
                timeline.append("\n"); 
            } else {
                timeline.append(", ");
            }
        }
        timeline.append("\n\n");

        for (int i = 0; i < posts.size(); i++) {
            timeline.append("POST ").append(i + 1).append(":\n");
            timeline.append(posts.get(i)).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, timeline.toString());
    }
}
