package examen1_lab;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UberSocial {
    private ArrayList<SocialClass> socialAccounts;

    public UberSocial() {
        socialAccounts = new ArrayList<>();
    }

    public SocialClass buscar(String username) {
        for (SocialClass account : socialAccounts) {
            if (account.username.equals(username)) {
                return account;
            }
        }
        return null;
    }

    public void agregarCuenta(String username, String tipo) {
        if (buscar(username) == null) {
            if (tipo.equalsIgnoreCase("FACEBOOK")) {
                socialAccounts.add(new Facebook(username));
            } else if (tipo.equalsIgnoreCase("TWITTER")) {
                socialAccounts.add(new Twitter(username));
            }
            JOptionPane.showMessageDialog(null, "Usuario agregado: " + username);
        } else {
            JOptionPane.showMessageDialog(null, "El usuario ya existe");
        }
    }

    public void agregarPost(String user, String post) {
        SocialClass account = buscar(user);
        if (account != null) {
            account.addPost(post);
            JOptionPane.showMessageDialog(null, "Post agregado para el usuario: " + user);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
        }
    }

    public void agregarAmigo(String user1, String user2) {
        SocialClass account1 = buscar(user1);
        SocialClass account2 = buscar(user2);
        if (account1 != null && account2 != null && account1.getClass().equals(account2.getClass())) {
            account1.addFriend(user2);
            account2.addFriend(user1);
            JOptionPane.showMessageDialog(null, "Amigo agregado: " + user2);
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden agregar amigos");
        }
    }

    public void agregarComment(String user, int postID, String author, String content) {
        SocialClass account = buscar(user);  
        if (account != null) {
            if (account instanceof Twitter) {
                JOptionPane.showMessageDialog(null, "No puedes agregar comentarios en una cuenta de Twitter");
            } 
            else if (account instanceof Facebook) {
                Comment comment = new Comment(postID, author, content);
                if (!((Facebook) account).addComment(comment)) {
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
        }
    }

    public void profileFrom(String user) {
        SocialClass account = buscar(user);
        if (account != null) {
            if (account instanceof Facebook) {
                Facebook fbAccount = (Facebook) account;
                fbAccount.timeline(); 
            } else {
                account.timeline();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
        }
    }




    public static void main(String[] args) {
        JFrame frame = new JFrame("UberSocial Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        UberSocial uberSocial = new UberSocial();

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("UberSocial - Gestion de Redes Sociales");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton addUserButton = estiloboton("Agregar usuario");
        JButton addPostButton = estiloboton("Agregar post");
        JButton addFriendButton = estiloboton("Agregar amigo");
        JButton addCommentButton = estiloboton("Agregar comentario");
        JButton showProfileButton = estiloboton("Mostrar perfil");
        JButton exitButton = estiloboton("Salir");

        addUserButton.addActionListener(e -> {
            JPanel addUserPanel = new JPanel(new GridLayout(2, 2));

            JLabel usernameLabel = new JLabel("Nombre de usuario:");
            JTextField usernameField = new JTextField(10);

            JLabel tipoLabel = new JLabel("Selecciona tipo:");
            String[] opciones = {"Facebook", "Twitter"};
            JComboBox<String> tipoComboBox = new JComboBox<>(opciones);

            addUserPanel.add(usernameLabel);
            addUserPanel.add(usernameField);
            addUserPanel.add(tipoLabel);
            addUserPanel.add(tipoComboBox);

            int result = JOptionPane.showConfirmDialog(null, addUserPanel, "Agregar Usuario", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String username = usernameField.getText();
                String tipo = (String) tipoComboBox.getSelectedItem();

                if (!username.isEmpty() && tipo != null) {
                    uberSocial.agregarCuenta(username, tipo.toUpperCase());
                } else {
                    JOptionPane.showMessageDialog(null, "Ingresar todos los datos.");
                }
            }
        });

        addPostButton.addActionListener(e -> {
            String username = JOptionPane.showInputDialog("Ingresa el nombre de usuario:");
            String post = JOptionPane.showInputDialog("Ingresa el contenido del post:");

            if (username != null && post != null && !username.isEmpty() && !post.isEmpty()) {
                uberSocial.agregarPost(username, post);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese los datos correctamente");
            }
        });

        addFriendButton.addActionListener(e -> {
            String user1 = JOptionPane.showInputDialog("Ingresa tu nombre de usuario:");
            String user2 = JOptionPane.showInputDialog("Ingresa el nombre de usuario del amigo:");

            if (user1 != null && user2 != null && !user1.isEmpty() && !user2.isEmpty()) {
                uberSocial.agregarAmigo(user1, user2);
            } else {
                JOptionPane.showMessageDialog(null, "Ingresar los datos correctamente");
            }
        });

        addCommentButton.addActionListener(e -> {
            String user = JOptionPane.showInputDialog("Ingresa el nombre de usuario:");
            String postIdStr = JOptionPane.showInputDialog("Ingresa el ID del post:");
            String author = JOptionPane.showInputDialog("Ingresa el autor:");
            String commentContent = JOptionPane.showInputDialog("Ingresa el comentario:");

            if (user != null && postIdStr != null && author != null && commentContent != null) {
                try {
                    int postID = Integer.parseInt(postIdStr);
                    uberSocial.agregarComment(user, postID, author, commentContent);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID de post invalido");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingresar todos los datos.");
            }
        });

        showProfileButton.addActionListener(e -> {
            String username = JOptionPane.showInputDialog("Ingresa el nombre de usuario:");
            if (username != null && !username.isEmpty()) {
                uberSocial.profileFrom(username);
            } else {
                JOptionPane.showMessageDialog(null, "Ingresar un nombre de usuario valido");
            }
        });

        exitButton.addActionListener(e -> System.exit(0));

        gbc.gridy = 0;
        panel.add(titleLabel, gbc);
        gbc.gridy = 1;
        panel.add(addUserButton, gbc);
        gbc.gridy = 2;
        panel.add(addPostButton, gbc);
        gbc.gridy = 3;
        panel.add(addFriendButton, gbc);
        gbc.gridy = 4;
        panel.add(addCommentButton, gbc);
        gbc.gridy = 5;
        panel.add(showProfileButton, gbc);
        gbc.gridy = 6;
        panel.add(exitButton, gbc);

        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private static JButton estiloboton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        return button;
    }
}
