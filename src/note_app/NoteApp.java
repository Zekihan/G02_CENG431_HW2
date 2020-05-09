package note_app;

import io.ConsoleDisplay;
import io.ConsoleInput;
import io.Display;
import io.Input;
import note.INote;
import note.Note;
import note.NoteGroup;
import note.state.INoteState;
import note.state.IncompleteState;
import note.state.PermanentState;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class NoteApp {

    INote root;
    Display display;
    Input input;

    public void init(){

        Date date = new Date();

        INote note1 = new Note(1,"title1", new PermanentState(), "content1", date);
        INote note2 = new Note(2,"title2", new IncompleteState(), "content2", date);
        INote note3 = new Note(3,"title3", new PermanentState(), "content3", date);
        INote note4 = new Note(4,"title4", new IncompleteState(), "content4", date);
        INote note5 = new Note(5,"title5", new IncompleteState(), "content5", date);

        List<INote> notelist = new LinkedList<>();

        NoteGroup group1 = new NoteGroup(6, "birthday notes", new LinkedList<>());

        group1.add(note3);
        group1.add(note4);

        notelist.add(note1);
        notelist.add(group1);
        notelist.add(note2);

        root = new NoteGroup(0, "Notes", notelist);

        ((NoteGroup) root).add(note5);
        ((NoteGroup) root).get(5).displayContent();



    }

    public void start(){

        input = new ConsoleInput();
        display = new ConsoleDisplay();

        while(true){

            display.displayTitle(1, "Notes");
            display.displayTitle(2, "Export Notes as Json");
            display.displayTitle(3, "Reset Notes");


            switch (input.readString()){
                case "1":
                        notesMenu();
                        break;
                case "2":
                        break;
                case "3":
                        break;
                default:
                        break;
            }

        }

    }

    public void notesMenu(){

        Stack<NoteGroup> noteGroupStack = new Stack<>();
        NoteGroup currentGroup = (NoteGroup) root;

        boolean isBrowsing = true;

        currentGroup.displayContent();

        while(isBrowsing){
            String inputStr = input.readString();
            if(isNumeric(inputStr)){
                int i = Integer.parseInt(inputStr);
                INote nextNote = currentGroup.get(i);

                if (isNote(nextNote)){
                    nextNote.displayContent();
                    /*
                    switch (inputStr){
                        case "c":

                            break;
                        case "r":
                    */
                }else{
                    nextNote.displayContent();
                    noteGroupStack.add(currentGroup);
                    currentGroup = (NoteGroup) nextNote;
                    continue;
                }

            }else{
                String title;
                int newId;

                switch (inputStr){
                    case "c":
                        display.displayMessage("Enter Title: ");
                        title = input.readString();
                        display.displayMessage("Enter Content: ");
                        String content = input.readString();
                        display.displayMessage("Enter State: ");
                        String stateStr = input.readString();

                        INoteState state;
                        switch (stateStr){
                            case "p":
                                state = new PermanentState();
                                break;
                            default:
                                state = new IncompleteState();
                                break;
                        }

                        newId = generateId(currentGroup);
                        Note newNote = new Note(newId, title, state, content, new Date());
                        currentGroup.add(newNote);
                        break;

                    case "g":
                        display.displayMessage("Enter Title: ");
                        title = input.readString();

                        newId = generateId(currentGroup);
                        NoteGroup newNoteGroup = new NoteGroup(newId, title);
                        currentGroup.add(newNoteGroup);
                        break;

                    case "b":
                        currentGroup = noteGroupStack.pop();
                        currentGroup.displayContent();
                        break;
                    case "q":
                        isBrowsing = false;
                        break;
                    default:
                        break;
                }
            }

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
