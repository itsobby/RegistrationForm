import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class RegistrationForm extends MIDlet implements CommandListener {

    private Display display;
    private Form form;
    private Command exitCommand;
    private Command saveCommand;
    private Command clearCommand;

    public RegistrationForm() {
        display = Display.getDisplay(this);
        exitCommand = new Command("Exit", Command.EXIT, 0);
        saveCommand = new Command("Save", Command.OK, 0);
        clearCommand = new Command("Clear", Command.CANCEL, 1);

        form = new Form("Registration Form");
        form.append(new TextField("Name:", "", 30, TextField.ANY));
        form.append(new TextField("Course:", "", 30, TextField.ANY));
        form.append(new TextField("Semester:", "", 30, TextField.NUMERIC));

        // Add spacer item to push commands to the bottom
        form.append(new Spacer(0, 10));

        form.addCommand(exitCommand);
        form.addCommand(saveCommand);
        form.addCommand(clearCommand);
        form.setCommandListener(this);
    }

    public void startApp() {
        display.setCurrent(form);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        // Placeholder implementation of destroyApp method
    }

    public void commandAction(Command c, Displayable d) {
        if (c == exitCommand) {
            destroyApp(true);
            notifyDestroyed();
        } else if (c == saveCommand) {
            // Perform form submission here
            // For simplicity, we'll just display submitted data in an alert
            showAlert("Form Saved", getFormData());
        } else if (c == clearCommand) {
            // Clear form fields
            clearForm();
        }
    }

    private String getFormData() {
        String data = "";
        for (int i = 0; i < form.size(); i++) {
            Item item = form.get(i);
            if (item instanceof TextField) {
                TextField tf = (TextField) item;
                data += tf.getLabel() + ": " + tf.getString() + "\n";
            }
        }
        return data;
    }

    private void clearForm() {
        for (int i = 0; i < form.size(); i++) {
            Item item = form.get(i);
            if (item instanceof TextField) {
                TextField tf = (TextField) item;
                tf.setString("");
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(title);
        alert.setString(message);
        alert.setTimeout(Alert.FOREVER);
        display.setCurrent(alert, form); // Display the alert, with form as the next displayable
    }
}
