package helpClasses;

import models.Answer;
import models.Question;
import models.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionUtils;
;

import java.util.List;
import java.util.Scanner;

public class QuestionHandler {

    private Session _context;

    public QuestionHandler(Session context) {
        _context = context;
    }

    public  void addQuestion() {
        Scanner in = new Scanner(System.in);
        Transaction tx = _context.getTransaction();
        tx.begin();
        System.out.print("Вкажіть питання: ");
        String questionText = in.nextLine();
        Question q = new Question();
        q.setName(questionText);
        _context.save(q);
        String action = "";
        do {
            System.out.println("Вкажіть відповідь:");
            String text = in.nextLine();
            System.out.println("1-правильно , 2-невірно");
            boolean isTrue = Byte.parseByte(in.nextLine()) == 1;
            Answer answer = new Answer();
            answer.setText(text);
            answer.setTrue(isTrue);
            answer.setQuestion(q);
            _context.save(answer);
            System.out.println("0: Вихід");
            System.out.println("1: Наступний варіант");
            System.out.print("->_ ");
            action = in.nextLine();

        } while (!action.equals("0"));

        tx.commit();
    }

    public void showQuestions() {

        Query query = _context.createQuery("FROM Question");
        List<Question> list = query.list();
        for (Question q : list)
            System.out.println(q);
    }

    public void updateQuestion()
    {
        Scanner input = new Scanner(System.in);

        Transaction tx = _context.getTransaction();
        tx.begin();
        System.out.print("Enter question id: ");
        int id = Integer.parseInt(input.nextLine());
        Question question = _context.get(Question.class, id);
        System.out.println("Вибрано питання: "+question.getName());
        System.out.println("Enter new name: ");
        String text = input.nextLine();
        question.setName(text);
        _context.update(question);
        tx.commit();
    }

    public void updateQuestionAnswers()
    {
        Scanner input = new Scanner(System.in);
        Transaction tx = _context.getTransaction();
        tx.begin();

        System.out.print("Enter question id: ");
        int id = Integer.parseInt(input.nextLine());
        Question question = _context.get(Question.class, id);
        System.out.println(question);

        System.out.print("Enter answer number: ");
        int indexCurrentAnswer = Integer.parseInt(input.nextLine());
        Answer ans = question.getAnswers().get(indexCurrentAnswer-1);

        System.out.print("Enter answer new text: ");
        String text = input.nextLine();
        ans.setText(text);
        System.out.println("Відповідь: 1-правильно , 2-невірно");
        boolean isTrue = Byte.parseByte(input.nextLine())==1;
        ans.setTrue(isTrue);
        _context.update(ans);
        tx.commit();
    }


}
