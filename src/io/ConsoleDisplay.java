package io;

public class ConsoleDisplay implements Display {

    public void displayNote(String title, String content){

        String note = title + "\n" + content;
        System.out.println(note);
    }

    public void displayTitle(int id, String title){

        String noteItem = "(" + id + ") " + title;

        System.out.println(noteItem);
    }

}

