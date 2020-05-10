package io;

import java.util.Scanner;

public class ConsoleInput implements Input{

    Scanner sc;

    public ConsoleInput() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public String readString() {
        return sc.nextLine();
    }

    public void close(){
        sc.close();
    }

}
