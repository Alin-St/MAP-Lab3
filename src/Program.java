import controller.Controller;
import model.exceptions.InterpreterException;
import repository.IRepository;
import repository.Repository;
import view.View;

public class Program {
    public static void main(String[] args) throws InterpreterException {
        IRepository repository = new Repository();
        var controller = new Controller(repository);
        var view = new View(controller);

        view.run();
    }
}
