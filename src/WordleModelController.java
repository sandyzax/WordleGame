import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordleModelController implements ActionListener {

    WordleModel model;
    WordleGUI gui;
    public WordleModelController(WordleModel model, WordleGUI view) {
        this.model = model;
        this.gui = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();



        if (command.equals("submit")) {
            String guessedWord = gui.getText();
            guessedWord = guessedWord.trim();
            model.addWord(guessedWord);
            if(model.checkWinner()){
                JOptionPane.showMessageDialog(gui, "You have won the wordle!");
            }
        }









    }
}
