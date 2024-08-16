package examen1_lab;

import java.util.Calendar;
import java.util.GregorianCalendar;

public final class Comment {
    private int postId;
    private String autor;
    private String contenido;
    private Calendar fecha;

    public Comment(int postId, String author, String content) {
        this.postId = postId;
        this.autor = author;
        this.contenido = content;
        this.fecha = new GregorianCalendar();
    }

    public void print() {
        System.out.println(autor + " – " + fecha.get(Calendar.DAY_OF_MONTH) + "/" 
            + (fecha.get(Calendar.MONTH) + 1) + "/" + fecha.get(Calendar.YEAR));
        System.out.println(contenido + "\n");
    }

    public int getPostId() {
        return postId;
    }

    public String getAutor() {
        return autor;
    }

    public String getContenido() {
        return contenido;
    }
}
