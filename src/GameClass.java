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
        Scanner sc = new Scanner(System.in)
                ;
        public GameClass(String fileName, Player player) {

            this.fileName = fileName;
            this.player = player;
        }

        public void StartGame() throws FileNotFoundException {
            loadQuestions() ;
        }
        private void loadQuestions() throws FileNotFoundException {
            File questionsFile = new File(fileName);
            Scanner reader = new Scanner(questionsFile);

            while (reader.hasNextLine()) {
                // read question line number and question text
                String questionLine = reader.nextLine();
                Scanner lineScanner= new Scanner(questionLine);
                int questionNumber = lineScanner.nextInt();
                String questiontext = lineScanner.hasNext()? lineScanner.nextLine().trim() : "";
                lineScanner.close();

                //read the answers
                String answerLine = reader.nextLine();
                List<String> answers = new ArrayList<>(4);
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
            reader.close();

            List<Question>groupedQuestions = new ArrayList<>(5);
            int level = 1;
            for(int i=0; i<questions.size();i++) {
                groupedQuestions.add(questions.get(i));
                if((i+1)%5==0)
                {
                    System.out.println("Level " + level);
                   int status = DisplayQuestions(groupedQuestions);
                    level++;
                   if(status == 0)
                       break;
                   else if(status == 2) {
                       i = -1;
                       level = 1;
                       groupedQuestions.clear();
                   }else {
                       groupedQuestions.clear();
                   }
                }
            }
        }

        public int DisplayQuestions(List<Question> questions) {
            Collections.shuffle(questions);

            int score = 0;
            for(Question q : questions) {
                System.out.println(q.getQuestionId() + " " + q.getQuestion() + " " + q.getAnswers());
                System.out.println("Plase enter the answer, a number between 1 and 4");

                if(checkAnswer(q)) {
                    score++;
                }
                else {
                    System.out.println("Your current score is " + score);
                    if(score!=25) {
                        System.out.println("Sorry you dindt become a millionare\n you want to try again?");
                        String option;

                        option = sc.nextLine();
                        if(option.equals("yes")) {
                            return 2;//restart the game, so the level becomes 1 again
                        }
                        else
                           return 0;//close the game
                    }

                }
            }
            return 1;//continue the game
        }

    boolean checkAnswer(Question q)
    {
        int answer = sc.nextInt();
        sc.nextLine();
        if(answer-1 <0||answer-1>3) {
            System.out.println("Number between 1 and 4");
            checkAnswer(q);
        }
        if(answer-1 == q.getCorrectAnswer()) {
            return true;
        }
        return false;
    }

}
