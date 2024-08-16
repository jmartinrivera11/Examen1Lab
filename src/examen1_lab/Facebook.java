package examen1_lab;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Facebook extends SocialClass implements Commentable {
    public ArrayList<Comment> comments;

    public Facebook(String username) {
        super(username);
        this.comments = new ArrayList<>();
    }

    @Override
    public boolean addComment(Comment comment) {
        if (posts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe crear un post antes de poder comentar.");
            return false;
        }

        if (comment.getPostId() >= 0 && comment.getPostId() < posts.size()) {
            comments.add(comment);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El ID del post no es valido.");
            return false;
        }
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
            timeline.append(posts.get(i)).append("\n");

            int commentNumber = 1;
            for (Comment comment : comments) {
                if (comment.getPostId() == i) {
                    timeline.append("  ").append(commentNumber).append(". ").append(comment.getAutor()).append(": ").append(comment.getContenido()).append("\n");
                    commentNumber++;
                }
            }
            timeline.append("\n");
        }

        JOptionPane.showMessageDialog(null, timeline.toString());
    }
}
