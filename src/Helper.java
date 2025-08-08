import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Helper {

    public static void askAudience(Question q)//ask audinece for ansswer, the resukt will come in % and the hightes %
    //must be the correct one
    {
        System.out.println("Asking audience for the answer...");
        int[] audienceResponse =  PercentageDistribution.generatePercentages();
        distributeAudienceCorrectAnswer(audienceResponse,q.correctAnswer(), q.answers());//the audinece is aked and a String is shows=d with the results
    }

    public static void distributeAudienceCorrectAnswer(int[] newAudience, int correctIndex, List<String> questions)//here we have the probabilities, and the bigger prob is inserted at the correct index from the answer
    {
        int maxIndex = 0;
        for (int j = 1; j < newAudience.length; j++) {
            if (newAudience[j] > newAudience[maxIndex]) {
                maxIndex = j;//check for the biggest probability
            }
        }
        var tmp =  newAudience[maxIndex];
        newAudience[maxIndex] = newAudience[correctIndex];
        //swap here
        StringBuilder st = new StringBuilder();
        //construct the string
        st.append("The audience results are: ");

        newAudience[correctIndex] = tmp;
        int j =0;
        for(var item: newAudience)
        {
            st.append(questions.get(j)).append(" ").append(item).append("% ");//insert the answers and their corespoonding proobailities
            j++;
        }
        System.out.println(st);
    }
    public static int callFriend(Question q)//call a friend and a number will be returned representing the possible correct answer, it gas a probability of 90% to return the correct answer
    {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if(chance<90){//the probability, if you know a smarter frined, increase the probability
            chance = q.correctAnswer()+1;
        }
        else
        {
            do {
                chance = rand.nextInt(3)+1;
            }while(chance==q.correctAnswer()+1);
        }
        return chance;
    }
    public static int make50_50(Question q)//make 50/50 will get a random answer and the correct one and also get the new correct response index, if the coreect one was before 3 or 4 now it becms 2 or 1
    {
        List<String> fiftyLine = new ArrayList<>(2);
        int correctAnswer = q.correctAnswer();
        Random rand = new Random();
        int randomAnswer;
        do{
            randomAnswer = rand.nextInt(q.answers().size());
        }while(randomAnswer==correctAnswer);
        boolean firstIsCorrect = rand.nextBoolean();//make a random position for the correct answer, if true the correct ans will be first, otherwise second
        //now will be inserted in positions
        if(firstIsCorrect)
        {
            fiftyLine.add(q.answers().get(correctAnswer));
            fiftyLine.add(q.answers().get(randomAnswer));
        }
        else {
            fiftyLine.add(q.answers().get(randomAnswer));
            fiftyLine.add(q.answers().get(correctAnswer));
        }
        System.out.println(Arrays.toString(fiftyLine.toArray()));
        //cehck and return
        if(firstIsCorrect)
            return 1;
        else return 2;
    }
}
