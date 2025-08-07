import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
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
            System.out.printf("Welcome %s to Who Wants To Be A Millionaire\n", player.name());
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
                String questionText = lineScanner.hasNext()? lineScanner.nextLine().trim() : "";
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
                Question q = new Question(questionNumber, questionText, answers, correctAnswer);
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
                System.out.println(ConsoleColors.RED+"Please enter the answer, a number between 1 and 4\n  or a to ask the audience, c to call a friend or f to make it 50% 50%"+ConsoleColors.RESET);

                System.out.println(q.getQuestionId() + " " + q.getQuestion() + " " + q.getAnswers()+"\n");
                if(checkAnswer(q)) {
                    System.out.println(ConsoleColors.GREEN_BOLD+"Correct!"+ConsoleColors.RESET);
                    score++;
                }
                else {
                    System.out.println("Your current score is " + score);
                    if(score!=25) {
                        System.out.println("Sorry you didn't become a millionaire\n you want to try again?");
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
            case "c" -> System.out.println(callFriend(q));//call a friend
            case "f" -> option = make50_50(q);//make 50/50;
            case "1", "2", "3", "4" -> option = Integer.parseInt(answer) ;
            default -> {
                System.out.println("Invalid option please select a number if you know the answer \n between 1 - 4 or a to ask the audience, c to call a friend or f to make it 50% 50%");
                checkAnswer(q);
            }
        }
        if(option==-1) {
            int variant = sc.nextInt();
            return variant - 1 == q.getCorrectAnswer();
        }
        else return option -1 == q.getCorrectAnswer();
    }

    void distributeAudienceCorrectAnswer(int[] newAudience, int correctIndex)
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
    //methods to ask for help
    void askAudience(Question q)
    {
        System.out.println("Asking audience for the answer...");
        int[] audienceResponse =  PercentageDistribution.generatePercentages();
        distributeAudienceCorrectAnswer(audienceResponse,q.getCorrectAnswer());
    }

    int callFriend(Question q)
    {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if(chance<90){
            chance = q.getCorrectAnswer()+1;
        }
        else
        {
            do {
                chance = rand.nextInt(3)+1;
            }while(chance==q.getCorrectAnswer());
        }
        return chance;
    }
    int make50_50(Question q)
    {
        List<String> fiftyLine = new ArrayList<>(2);
        int correctAnswer = q.getCorrectAnswer();
        Random rand = new Random();
        int randomAnswer;
        do{
            randomAnswer = rand.nextInt(q.getAnswers().size());
        }while(randomAnswer==correctAnswer);
        fiftyLine.add(q.getAnswers().get(randomAnswer));
        fiftyLine.add(q.getAnswers().get(correctAnswer));
        Collections.shuffle(fiftyLine);
        System.out.println(Arrays.toString(fiftyLine.toArray()));
        correctAnswer -=2;
        if(correctAnswer<0)
            correctAnswer = 0;
        return correctAnswer;
    }
}
