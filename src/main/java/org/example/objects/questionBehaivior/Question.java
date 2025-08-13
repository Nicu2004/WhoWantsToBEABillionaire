package org.example.objects.questionBehaivior;
import java.util.List;

public class Question
{

    private int questionId;
    private String question;
    private List<String> answers;
    private int correctAnswer;

    public Question(int questionId, String question, List<String> answers, int correctAnswer)
    {
        this.questionId = questionId;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }
    public int getQuestionId()
    {
        return questionId;
    }
    public void setQuestionId(int questionId)
    {
        this.questionId = questionId;
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
    public void setCorrectAnswer(int correctAnswer)
    {
        this.correctAnswer = correctAnswer;
    }
    public boolean isCorrect(int answerIndex)
    {
        return answerIndex == correctAnswer;
    }
}


