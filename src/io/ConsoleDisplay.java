package io;

public class ConsoleDisplay implements Display {

    public void displayNote(String title, String content, String date, String state){

        String note =  "Title: " + title +"\n"+ "Content: " + content +"\n"+ "Date: " + date +"\n"+ "State: " + state;
        System.out.println(note);
    }

    public void displayTitle(int id, String title){

        String noteItem = "(" + id + ") " + title;

        System.out.println(noteItem);
    }

    public void displayMessage(String message){

        System.out.println(message);
    }

}

