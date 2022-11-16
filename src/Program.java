import controller.Controller;
import model.exceptions.InterpreterException;
import repository.IRepository;
import repository.Repository;
import view.View;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws InterpreterException {
        System.out.print("Repository log file name: ");
        var lfn = new Scanner(System.in).nextLine();

        IRepository repository = new Repository(lfn);
        var controller = new Controller(repository);
        var view = new View(controller);

        view.run();
    }
}
