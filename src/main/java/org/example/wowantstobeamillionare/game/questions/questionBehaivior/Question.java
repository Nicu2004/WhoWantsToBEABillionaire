package org.example.wowantstobeamillionare.game.questions.questionBehaivior;
import java.util.List;

public class Question
{

    private int questionId;
    private String question;
    private List<String> answers;
    private final int correctAnswer;

    public Question(int questionId, String question, List<String> answers, int correctAnswer)
    {
        this.questionId = questionId;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }
    public String getQuestion()
    {
        return question;
    }
    public void setQuestion(String question)
    {
        this.question = question;
    }
    public List<String> getAnswers()
    {
        return answers;
    }
    public void setAnswers(List<String> answers)
    {
        this.answers = answers;
    }
    public int getCorrectAnswer()
    {
        return correctAnswer;
    }
    public String getFirstAnswer()
    {
        return answers.getFirst();
    }
    public String getSecondAnswer()
    {
        return answers.get(1);
    }
    public String getThirdAnswer()
    {
        return answers.get(2);
    }
    public String getFourthAnswer()
    {
        return answers.get(3);
    }
}


