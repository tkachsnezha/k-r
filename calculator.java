java
import java.util.*;
import java.io.*;

public class Calculator {
    
    // Модель
    static class Equation {
        String expression;
        double result;
        
        public Equation(String expression, double result) {
            this.expression = expression;
            this.result = result;
        }
    }
    
    // Представление
    static class View {
        void displayMenu() {
            System.out.println("Калькулятор:\n1. Расчет уравнений\n2. Показать сохраненные уравнения\n3. Сохранить результаты в файл\n4. Выход");
        }
        
        void displayEquations(List<Equation> equations) {
            System.out.println("Сохраненные уравнения:");
            for (Equation equation : equations) {
                System.out.println(equation.expression + " = " + equation.result);
            }
        }
        
        void displayMessage(String message) {
            System.out.println(message);
        }
    }
    
    // Контроллер
    static class Controller {
        private final View view;
        private List<Equation> equations;
        
        public Controller(View view) {
            this.view = view;
            this.equations = new ArrayList<>();
        }
        
        public void run() {
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                view.displayMenu();
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        calculateEquation();
                        break;
                    case 2:
                        view.displayEquations(equations);
                        break;
                    case 3:
                        saveEquationsToFile();
                        break;
                    case 4:
                        return;
                    default:
                        view.displayMessage("Некорректный выбор, попробуйте еще раз");
                        break;
                }
            }
        }
        
        private void calculateEquation() {
            Scanner scanner = new Scanner(System.in);
            view.displayMessage("Введите уравнение:");
            String expression = scanner.nextLine();
            
            try {
                double result = evaluateExpression(expression);
                Equation equation = new Equation(expression, result);
                equations.add(equation);
                view.displayMessage("Результат: " + result);
            } catch (Exception e) {
                view.displayMessage("Ошибка при вычислении уравнения: " + e.getMessage());
            }
        }
        
        private double evaluateExpression(String expression) {
            // Здесь нужно реализовать алгоритм вычисления выражения
            // Можно использовать готовую библиотеку, например, JavaScript
            return 0.0;
        }
        
        private void saveEquationsToFile() {
            Scanner scanner = new Scanner(System.in);
            view.displayMessage("Введите имя файла:");
            String filename = scanner.nextLine();
            
            try {
                FileWriter writer = new FileWriter(filename);
                
                for (Equation equation : equations) {
                    writer.write(equation.expression + " = " + equation.result + "\n");
                }
                
                writer.close();
                view.displayMessage("Результаты сохранены в файл: " + filename);
            } catch (IOException e) {
                view.displayMessage("Ошибка при сохранении файла: " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        controller.run();
    }
}
