import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameClass {

        private final List<Question> questions  = new ArrayList<>();
        private Player player;
        private final String fileName;
        Scanner sc = new Scanner(System.in);

        public GameClass(String fileName, Player player) {

            this.fileName = fileName;
            this.player = player;
        }

        public void StartGame() throws FileNotFoundException {
            System.out.printf("Welcome %s to Who Wants To Be A Millionare\n", player.name());
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
                System.out.println(q.getQuestionId() + " " + q.getQuestion() + " " + q.getAnswers()+"\n");
                System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT+"Plase enter the answer, a number between 1 and 4\n"+ConsoleColors.RESET);


                if(checkAnswer(q)) {
                    score++;
                }
                else {
                    System.out.println("Your current score is " + score);
                    if(score!=25) {
                        System.out.println("Sorry you dindt become a millionare\n you want to try again?");
                        String option;

                        option = sc.nextLine();
                        if(option.equalsIgnoreCase("yes")) {
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
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        int option=-1;
        switch (answer) {
            case "a" -> askAudience(q);//ask audience
            case "c" -> callFriend();//call a friend
            case "f" -> make50_50();//make 50/50;
            case "1", "2", "3", "4" -> option = Integer.parseInt(answer) ;
            default -> System.out.println("Invalid option");
        };
        if(option==-1) {
            System.out.println("plase Select the option");
            int varinat = sc.nextInt();
            return varinat - 1 == q.getCorrectAnswer();
        }
        else return option -1 == q.getCorrectAnswer();
    }
    void askAudience(Question q)
    {
        int[] audienceResponse =  PercentageDistribution.generatePercentages();
        swapInList(audienceResponse,q.getCorrectAnswer());
    }
    void swapInList(int[] newAudience, int correctIndex)
    {
        int maxIndex = 0;

        for (int j = 1; j < newAudience.length; j++) {
            if (newAudience[j] > newAudience[maxIndex]) {
                maxIndex = j;
            }
        }

        var tmp =  newAudience[maxIndex];
        newAudience[maxIndex] = newAudience[correctIndex];
        newAudience[correctIndex] = tmp;
        System.out.println(Arrays.toString(newAudience));

    }
    int getOption(String option, Question q)
    {
        return switch (option) {
            case "a" -> 1;
            case "c" -> 2;
            case "f" -> 3;
            case "1", "2", "3", "4" -> 0;
            default -> -1;
        };
    }
    void callFriend()
    {}
    void make50_50()
    {}


}
