package program;

import models.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) {

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

    private static void menu() {
        System.out.println("\n\n ================= CRUD Menu =================");
        System.out.println("--------------------------------------");
        System.out.println("0 - Exit ");
        System.out.println("1 - View Roles ");
        System.out.println("2 - Create new role");
        System.out.println("3 - Delete");
        System.out.println("4 - Update");
    }
}




