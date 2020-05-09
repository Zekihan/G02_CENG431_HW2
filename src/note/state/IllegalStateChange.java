package note.state;

public class IllegalStateChange extends Exception{
    public IllegalStateChange(String errorMessage) {
        super(errorMessage);
    }
}
