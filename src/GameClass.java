import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameClass {
        private List<Question> questions  = new ArrayList<>();
        private Player player;
        private String fileName;

        public GameClass(String fileName, Player player) throws FileNotFoundException {

            this.fileName = fileName;
            this.player = player;
        }
        public void StartGame() throws FileNotFoundException {
            loadQuestions() ;
        }
        File questionsFile = new File(fileName);
        Scanner reader = new Scanner(questionsFile);
        private void loadQuestions() throws FileNotFoundException {



            while (reader.hasNextLine()) {
                // read question line number and question text
                String questionLine = reader.nextLine();
                Scanner lineScanner= new Scanner(questionLine);
                int questionNumber = lineScanner.nextInt();
                String questiontext = lineScanner.hasNext()? lineScanner.nextLine().trim() : "";
                lineScanner.close();

                //read the answers
                String answerLine = reader.nextLine();
                List<String> answers = new ArrayList<>(3);
                Scanner answerLineScanner = new Scanner(answerLine);

                Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(answerLine);
                while (m.find()) {
                    answers.add(m.group(1)); // text inside parentheses
                }
                answerLineScanner.close();
                int correctAnswer = Integer.parseInt(reader.nextLine().trim());
                Question q = new Question(questionNumber, questiontext, answers, correctAnswer);
                questions.add(q);
            }


            List<Question>groupedQuestions = new ArrayList<>(5);
            int level = 1;
            for(int i=0; i<questions.size();i++) {
                groupedQuestions.add(questions.get(i));
                if((i+1)%5==0)
                {
                    System.out.println("Level " + level);
                    DisplayQuestions(groupedQuestions);
                    level++;
                    System.out.println();
                    groupedQuestions.clear();
                }

            }
            if(!groupedQuestions.isEmpty()) {
                System.out.println("Level " + level);
                DisplayQuestions(groupedQuestions);

            }

        }

        public void DisplayQuestions(List<Question> questions) {
            Collections.shuffle(questions);
            int score = 0;
            for(Question q : questions) {
                System.out.println(q.getQuestionId() + " " + q.getQuestion() + " " + q.getAnswers());
                System.out.println("Plase enter the answer, a number between 0, 2");

                if(checkAnswer(q)) {
                    score++;
                }
                else {
                    System.out.println("Your current score is " + score);
                    if(score!=25) {
                        System.out.println("Sorry you dindt become a millionare\n you want to try again?");
                        String option;

                        option = reader.nextLine();
                        if(option.equals("yes")) {
                            DisplayQuestions(questions);
                        }
                        else
                            break;

                    }
                }
            }
        }

    boolean checkAnswer(Question q)
    {
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        if(answer <0||answer>2) {
            System.out.println("Number between 0 and 2");
            checkAnswer(q);
        }
        if(answer == q.getCorrectAnswer()) {
            return true;
        }
        return false;
    }




}
