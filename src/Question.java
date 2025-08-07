import java.util.List;
import java.util.Vector;

public class Question {
    private int questionId;
    private String question;
    private List<String> answers;
    private int correctAnswer;//index as

    public Question(int questionId, String question, List<String> answers, int correctAnswer) {
        this.questionId = questionId;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }
    public int getCorrectAnswer() {
        return correctAnswer;
    }
    public List<String> getAnswers() {
        return answers;
    }
    public String getQuestion() {
        return question;
    }
}
