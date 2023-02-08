package helpClasses;

import models.Answer;
import models.Question;
import org.hibernate.Session;
import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.query.Query;
import utils.HibernateSessionUtils;

import java.util.*;

public class AnswerHandler {
    private static final int MAX_RESULT = 12;
    private List<Question> questions;
    private  int result;
    private int countCorrectAnswer;
    private int countShowQuestions;

    public AnswerHandler(int countOfQuestions){
        result =0;
        countCorrectAnswer=0;
        countShowQuestions=countOfQuestions;
        this.getAllQuestions();
    }

    public void Start()
    {
            StringBuilder str = new StringBuilder();
            int currentQuestion = 0;
            Question q = null;
            Scanner input = new Scanner(System.in);
            int step = 0;
            while (countShowQuestions > step) {
                // change current question
                currentQuestion = getRandomQuestionIndex();
                if (str.toString().contains("" + currentQuestion)) continue;
                str.append(currentQuestion);

                //View question
                q = questions.get(currentQuestion);

                System.out.println(q);
                System.out.println();
                System.out.print("Ваша відповідь №: ");
                int ans = Integer.parseInt(input.nextLine());

                List<Answer> listAnswer = q.getAnswers();
                if (listAnswer.get(ans - 1).isTrue() == true)
                    this.countCorrectAnswer++;

                //next question
                step++;
            }

    }

    private int getRandomQuestionIndex(){
        return (int) ((Math.random() * (questions.stream().count() - 0)) + 0) -0;
    }
    private void getAllQuestions(){
        try(Session context = HibernateSessionUtils.getSessionFactory().openSession()) {

            questions = new ArrayList<>();
            Query query = context.createQuery("FROM Question");
            List<Question> list = query.list();
            for (Question q : list) {
               // System.out.println(q);
                String str = q.toString();
                questions.add(q);
            }

        }
    }
    private int Ranc(){
        return (int)( countCorrectAnswer*MAX_RESULT/countShowQuestions);
    }
    @Override
    public String toString(){
        return "Ви відповіли правильно на "+countCorrectAnswer+" питань. Вана оцінка: "+Ranc();
    }

}
