import java.util.Vector;

public class Question {
    String question;
    Vector<String> answers = new Vector<>(3);
    int correctAnswer;

    public Question(String question, Vector<String> answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }
}
