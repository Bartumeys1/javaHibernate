package program;

import helpClasses.AnswerHandler;
import helpClasses.QuestionHandler;
import models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        testLesson_4();
    }


    private static void menuQuestion() {
        System.out.println("0 - Вихід з редагування");
        System.out.println("1 - Показати всі питання");
        System.out.println("2 - Додати нове питання");
        System.out.println("3 - Редагувати запитання по id");
        System.out.println("4 - Редагувати Відповіді у питанні по id");
    }

    private static void menu() {
        System.out.println("\n\n ================= CRUD Menu =================");
        System.out.println("--------------------------------------");
        System.out.println("0 - Exit ");
        System.out.println("1 - View Roles ");
        System.out.println("2 - Create new role");
        System.out.println("3 - Delete");
        System.out.println("4 - Update");
    }

    //CRUD to database
    private static void testLesson_1() {
        Scanner input = new Scanner(System.in);
        Session context = HibernateSessionUtils.getSessionFactory().openSession();
        Transaction tr = context.getTransaction();
        int operation;
        try {

            while (true) {
                menu();
                try {
                    System.out.print("Select > ");
                    operation = Integer.parseInt(input.nextLine());

                    switch (operation) {
                        case 0: {
                            System.out.println("exit...");
                        }
                        return;
                        case 1: {
                            Query query = context.createQuery("FROM Role");
                            List<Role> list = query.list();
                            for (Role role : list) {
                                System.out.println("Назва: " + role.getName());
                            }
                        }
                        break;
                        case 2: {
                            System.out.print("Enter name: ");
                            String name = input.nextLine();
                            Role role = new Role();
                            role.setName(name);
                            context.save(role);
                        }
                        break;
                        case 3: {
                            System.out.print("Enter id: ");
                            int id = Integer.parseInt(input.nextLine());
                            Role role_result = context.get(Role.class, id);
                            if (role_result == null)
                                throw new Exception("Role not faund");

                            tr.begin();
                            context.delete(role_result);
                            tr.commit();
                        }
                        break;
                        case 4: {
                            System.out.print("Enter id: ");
                            int id = Integer.parseInt(input.nextLine());
                            Role role = context.get(Role.class, id);
                            System.out.println("Enter new name: ");
                            String name = input.nextLine();
                            role.setName(name);
                            context.update(role);
                        }
                        break;
                        default:
                            System.out.print("Bad input number");
                    }
                } catch (NumberFormatException err) {
                    System.out.println("Error input " + err.getMessage());

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());

                }
            }
        } finally {
            System.out.println("Close connection");
            context.close();
        }
    }

    //Relationship
    private static void testLesson_2() {
        //addQuestion();
        // showQuestions();
    }

    private static void addQuestion() {
        try (Session context = HibernateSessionUtils.getSessionFactory().openSession()) {
            Scanner in = new Scanner(System.in);
            Transaction tx = context.beginTransaction();
            System.out.println("Вкажіть питання:");
            String questionText = in.nextLine();
            Question q = new Question();
            q.setName(questionText);
            context.save(q);
            String action = "";
            do {
                System.out.println("Вкажіть відповідь:");
                String text = in.nextLine();
                System.out.println("1-правильно, 2-невірно");
                boolean isTrue = Byte.parseByte(in.nextLine()) == 1;
                Answer answer = new Answer();
                answer.setText(text);
                answer.setTrue(isTrue);
                answer.setQuestion(q);
                context.save(answer);
                System.out.println("0. Вихід");
                System.out.println("1. Наступний варіант");
                System.out.println("->_");
                action = in.nextLine();
            } while (!action.equals("0"));
            tx.commit();
        }
    }

    private static void showQuestions() {
        try (Session contect = HibernateSessionUtils.getSessionFactory().openSession()) {
            Query query = contect.createQuery("FROM Question");
            List<Question> list = query.list();
            for (Question q : list)
                System.out.println(q);
        }

    }

    private void testLesson_3() {
        Scanner input = new Scanner(System.in);
        String action = "";
        do {
            System.out.println("1-Робота над питаннями\n2-Використовування опитування");
            System.out.print("->_");
            action = input.nextLine();
            switch (action) {
                case "1": {
                    menuQuestion();
                    System.out.print("->_");
                    String operation = input.nextLine();
                    try (Session context = HibernateSessionUtils.getSessionFactory().openSession()) {

                        QuestionHandler questionHandler = new QuestionHandler(context);
                        switch (operation) {
                            case "1": {
                                questionHandler.showQuestions();
                            }
                            break;
                            case "2": {
                                questionHandler.addQuestion();
                            }
                            break;
                            case "3": {
                                questionHandler.updateQuestion();
                            }
                            break;
                            case "4": {
                                questionHandler.updateQuestionAnswers();
                            }
                            break;
                            default:
                                return;
                        }
                    }
                }
                break;
                case "2": {
                    System.out.print("Ведіть кількість питань: ");
                    int countQuestion = Integer.parseInt(input.nextLine());
                    AnswerHandler ans = new AnswerHandler(countQuestion);
                    ans.Start();
                    System.out.println(ans.toString());
                }
                break;
                default:
                    return;
            }

        } while (!action.equals("0"));
    }

    private static void testLesson_4() {
//        AddUserRole();
        addCategory();
    }

    private static void AddUserRole() {
        try (Session context = HibernateSessionUtils.getSessionFactory().openSession()) {
            Transaction tx = context.beginTransaction();
            User user = new User("Муха", "Бобер", "bober@gmai.com",
                    "+38097 98 76 786", "123456");
            context.save(user);
            var role = context.get(Role.class, 1);
            var ur = new UserRole();
            ur.setUser(user);
            ur.setRole(role);
            context.save(ur);
            tx.commit();
        }
    }

    private static void addCategory() {
        try (Session context = HibernateSessionUtils.getSessionFactory().openSession()) {

//            Category category = new Category("Ноутбуки", "1.jpg", new Date(), false);
//            context.save(category);
        }
    }
}






