import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameClass {

        private final List<Question> questions  = new ArrayList<>();
        private final boolean isPlayer;
        private Player player;
        private final String fileName;
        
        public GameClass(String fileName, boolean isPlayer) {//this constructor is called if there is a player, not a user

            this.fileName = fileName;
            this.isPlayer = isPlayer;
            
            if (isPlayer) {
                this.player = new Player(getPlayerName());
            }
        }
        String getPlayerName() {
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter your name: ");
            return input.next();
        }
        public void startGame(){
            displayWelcomeMessage();
            loadQuestions();
            if (isPlayer) {
                playGameByLevels();
            } else {
                playGameByUser();
            }
        }

        private void displayWelcomeMessage() {
            if (isPlayer) {
                System.out.printf("Welcome %s to Who Wants To Be A Millionaire%n", player.name());
            } else {
                System.out.println("Welcome to game trial");
            }
        }
        private void loadQuestions() {

            File questionsFile = new File(fileName);
            Scanner reader;
            try {
                reader = new Scanner(questionsFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            while (reader.hasNextLine()) {
                // read question line number and question text
                String questionLine = reader.nextLine();
                Scanner lineScanner = new Scanner(questionLine);
                int questionNumber = lineScanner.nextInt();
                String questionText = lineScanner.hasNext() ? lineScanner.nextLine().trim() : "";
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
        }
        void playGameByUser(){

            Collections.shuffle(questions);
            System.out.println(ConsoleColors.RED+"Please enter the answer, a number between 1 and 4\n"+ConsoleColors.RESET);
            int i =1;
            Random rand = new Random();
            int randomScore = rand.nextInt(questions.size());
            for (var q: questions) {
                if(i == randomScore) {
                    System.out.println("Congrats, you finished your game");
                    break;
                }
                else {
                    System.out.println(i + " " + q.getQuestion() + " " + q.getAnswers()+"\n");
                    if(checkAnswer(q, false))
                        i++;
                    else{
                        System.out.println(ConsoleColors.RED+"you want to try again?");
                        Scanner sc = new Scanner(System.in);

                        if(sc.nextLine().equalsIgnoreCase("yes"))
                        {
                            playGameByUser();
                        }
                        else break;
                    }
                }
            }
        }
        private void playGameByLevels()
        {
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
        int score = 0;
        public int DisplayQuestions(List<Question> questions) {

            Collections.shuffle(questions);

            Random rand = new Random();
            int index = rand.nextInt(questions.size());
            Question q = questions.get(index);
            System.out.println(ConsoleColors.RED+"Please enter the answer, a number between 1 and 4\n  or a to ask the audience, c to call a friend or f to make it 50% 50%"+ConsoleColors.RESET);

            System.out.println(q.getQuestionId() + " " + q.getQuestion() + " " + q.getAnswers()+"\n");
            if(checkAnswer(q, true)) {
                System.out.println(ConsoleColors.GREEN_BOLD+"Correct!"+ConsoleColors.RESET);
                score++;
                System.out.println(ConsoleColors.GREEN_BOLD+"Score: "+score);
                if(score==5){
                    System.out.printf(ConsoleColors.GREEN_BOLD_BRIGHT+"Hooray, %s you have become a millionaire"+ConsoleColors.RESET, player.name());
                    return 0;
                }
                return 1;
            }
            else {
                System.out.println("Your current score is " + score);
                System.out.println("Sorry you didn't become a millionaire\n you want to try again?");
                return 0;//close the game
            }

        }

    boolean checkAnswer(Question q, boolean isPLayer)
    {
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        int option;
        switch (answer) {
            case "a" -> {
                if(!isPLayer) {
                    System.out.print("Sorry, Asking the audience is only available for players");
                    return checkAnswer(q, false);
                }
                askAudience(q);
                return checkAnswer(q, true);
            }
            case "c" -> {
                if(!isPLayer) {
                    System.out.print("Sorry, Call a friend is only available for players");
                    return checkAnswer(q, false);
                }
                System.out.printf("I think it is %s, but I'm not 100%% sure", String.valueOf(callFriend(q)));
                return checkAnswer(q, true);
            }
            case "f" -> {
                if(!isPLayer) {
                    System.out.print("Sorry, 50%/50% is only available for players");
                    return checkAnswer(q, false);
                }
                option = make50_50(q);
                System.out.println("Chose 1 or 2");
                try{
                    int userChoise = Integer.parseInt(sc.nextLine());
                    return userChoise == option;
                }catch(NumberFormatException e) {
                    System.out.println(ConsoleColors.RED+"Invalid input"+ConsoleColors.RESET);
                    return checkAnswer(q, true);
                }
            }//make 50/50;
            case "1", "2", "3", "4" -> option = Integer.parseInt(answer) ;
            default -> {
                System.out.println("Invalid option please select a number if you know the answer \n between 1 - 4 or a to ask the audience, c to call a friend or f to make it 50% 50%");
                return checkAnswer(q, true);
            }
        }
        return option -1 == q.getCorrectAnswer();
    }
    void distributeAudienceCorrectAnswer(int[] newAudience, int correctIndex, List<String> questions)
    {
        int maxIndex = 0;
        for (int j = 1; j < newAudience.length; j++) {
            if (newAudience[j] > newAudience[maxIndex]) {
                maxIndex = j;
            }
        }
        var tmp =  newAudience[maxIndex];
        newAudience[maxIndex] = newAudience[correctIndex];
        StringBuilder st = new StringBuilder();
        st.append("The audience results are: ");

        newAudience[correctIndex] = tmp;
        int j =0;
        for(var item: newAudience)
        {
            st.append(questions.get(j) +" "+item + "% ");
            j++;
        }
        System.out.println(st.toString());
    }
    //methods to ask for help
    void askAudience(Question q)
    {
        System.out.println("Asking audience for the answer...");
        int[] audienceResponse =  PercentageDistribution.generatePercentages();
        distributeAudienceCorrectAnswer(audienceResponse,q.getCorrectAnswer(), q.getAnswers());
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
        boolean firstIsCorrect = rand.nextBoolean();
        if(firstIsCorrect)
        {
            fiftyLine.add(q.getAnswers().get(correctAnswer));
            fiftyLine.add(q.getAnswers().get(randomAnswer));
        }
        else {
            fiftyLine.add(q.getAnswers().get(randomAnswer));
            fiftyLine.add(q.getAnswers().get(correctAnswer));
        }
        System.out.println(Arrays.toString(fiftyLine.toArray()));
        if(firstIsCorrect)
            return 1;
        else return 2;
    }
}
