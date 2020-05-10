package note_app;

import io.ConsoleDisplay;
import io.ConsoleInput;
import io.Display;
import io.Input;
import json.NoteSaver;
import json.NoteStorage;
import note.INote;
import note.Note;
import note.NoteGroup;
import note.state.INoteState;
import note.state.IllegalStateChange;
import note.state.IncompleteState;
import note.state.PermanentState;

import java.util.*;

public class NoteApp {

    INote root;
    Display display;
    Input input;

    private void demo(){
        Date date = new Date();
        INote note1 = new Note(1,"title1", new PermanentState(), "content1", date);
        INote note2 = new Note(2,"title2", new IncompleteState(), "content2", date);
        INote note3 = new Note(3,"title3", new PermanentState(), "content3", date);
        INote note4 = new Note(4,"title4", new IncompleteState(), "content4", date);
        INote note5 = new Note(5,"title5", new IncompleteState(), "content5", date);
        NoteGroup group1 = new NoteGroup(6, "birthday notes", new LinkedList<>());
        group1.add(note3);
        group1.add(note4);
        ((NoteGroup)root).add(note1);
        ((NoteGroup)root).add(note2);
        ((NoteGroup)root).add(note5);
        ((NoteGroup)root).add(group1);
    }

    public void start(){

        root = new NoteGroup(0, "Notes");
        input = new ConsoleInput();
        display = new ConsoleDisplay();
        NoteStorage noteStorage = new NoteSaver();

        demo();

        while(true){
            display.displayMessage("\nNote App");
            display.displayTitle(1, "Notes");
            display.displayTitle(2, "Export Notes as Json");
            display.displayTitle(3, "Import Notes");
            display.displayTitle(4, "Reset Notes");
            display.displayTitle(5, "Exit");

            switch (input.readString()){
                case "1":   notesMenu();
                            break;
                case "2":
                            noteStorage.saveNotes(root);
                            break;
                case "3":
                            if(noteStorage.checkSave())
                                root = noteStorage.loadNotes();
                            else
                                display.displayMessage("No file to import.");
                            break;
                case "4":   root = new NoteGroup(1, "Notes");
                            break;
                case "5":   System.exit(0);
                            break;
                default:    break;
            }
        }
    }

    public void notesMenu(){

        Stack<NoteGroup> noteGroupStack = new Stack<>();
        NoteGroup currentGroup = (NoteGroup) root;

        boolean isBrowsing = true;

        while(isBrowsing){
            currentGroup.displayContent();
            display.displayMessage("C:CreateNote G:CreateNoteGroup B:Back Q:Quit");
            String inputStr = input.readString().toUpperCase();

            if(isNumeric(inputStr)){
                INote nextNote;
                int i = Integer.parseInt(inputStr);
                try{
                    nextNote = currentGroup.get(i);
                }catch (NoSuchElementException ex){
                    display.displayMessage("Note or Note Group with given id does not exist!\n");
                    continue;
                }

                if (isNote(nextNote)){
                    nextNote.displayContent();
                    display.displayMessage("C:ChangeState B:Back");
                    inputStr = input.readString().toUpperCase();

                    if(inputStr.equals("C")){
                        display.displayMessage("1:Complete 2:Cancel");
                        i = Integer.parseInt(input.readString());
                        changeNoteState((Note) nextNote, i);
                    }
                }else{
                    noteGroupStack.add(currentGroup);
                    currentGroup = (NoteGroup) nextNote;
                }
            }else{
                switch (inputStr){
                    case "C":   createNote(currentGroup);
                                break;
                    case "G":   createNoteGroup(currentGroup);
                                break;
                    case "B":   if(!noteGroupStack.empty()){
                                    currentGroup = noteGroupStack.pop();
                                }else{
                                    display.displayMessage("You reached root note group cannot go back!\n");
                                }
                                break;
                    case "Q":   isBrowsing = false;
                                break;
                    default:    break;
                }
            }
        }
    }

    private void createNote(NoteGroup group) {
        display.displayMessage("Enter Title: ");
        String title = input.readString();
        display.displayMessage("Enter Content: ");
        String content = input.readString();
        display.displayMessage("Enter State(P or I): ");
        String stateStr = input.readString().toUpperCase();
        INoteState state;
        if (stateStr.equals("P")){
            state = new PermanentState();
        }else if(stateStr.equals("I")){
            state = new IncompleteState();
        }else{
            return;
        }
        int id = generateId(group);
        Note newNote = new Note(id, title, state, content, new Date());
        group.add(newNote);
    }

    private void createNoteGroup(NoteGroup group) {
        display.displayMessage("Enter Title: ");
        String title = input.readString();
        int id = generateId(group);
        NoteGroup newNoteGroup = new NoteGroup(id, title);
        group.add(newNoteGroup);
    }

    private void changeNoteState(Note note, int op){
        try {
            if(op == 1){
                    note.cancel();
            }else if(op == 2){
                    note.complete();
            }else{
                throw new IllegalArgumentException("Illegal State Operation");
            }
            display.displayMessage("Successfuly changed the state");

        } catch (IllegalStateChange | IllegalArgumentException ex) {

            display.displayMessage("Failed state change: " + ex.getMessage() + "\n");
        }
    }

    private int generateId(NoteGroup noteGroup){
        List<INote> notes = noteGroup.getNotes();
        int max = 1;
        for (INote note: notes) {
            if(note.getId() > max){
                max = note.getId();
            }
        }
        return max + 1;
    }

    private boolean isNote(INote note){
        return Note.class == note.getClass();
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
