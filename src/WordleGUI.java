import javax.swing.*;
import java.awt.*;

public class WordleGUI extends JFrame implements WordleView {

    JTextField[][] cells;
    JTextField textField;
    WordleModel model;
    String word;

    public WordleGUI() {
        super("Wordle");
        word = JOptionPane.showInputDialog(null, "Please Enter the 5-character word to be guessed:");
        model = new WordleModel(word);
        model.addWordleView(this);
        //this.setLayout(new GridLayout(WordleModel.SIZE, WordleModel.SIZE));
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(WordleModel.SIZE, WordleModel.SIZE));
        this.add(boardPanel, BorderLayout.CENTER);

        JPanel textPanel = new JPanel();
        textField = new JTextField(10);
        textField.setEditable(true);
        textPanel.add(textField);
        this.add(textPanel, BorderLayout.SOUTH);

        WordleModelController controller = new WordleModelController(model,this);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(controller);
        submitButton.setActionCommand("submit");
        textPanel.add(submitButton);

        cells = new JTextField[WordleModel.SIZE][WordleModel.SIZE];
        for(int i = 0; i < WordleModel.SIZE; i++) {
            for(int j = 0; j < WordleModel.SIZE; j++) {
                 cells[i][j] = new JTextField(" ");
                 cells[i][j].setEditable(false);
                 cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                 cells[i][j].setBackground(Color.WHITE);
                 boardPanel.add(cells[i][j]);


            }
        }


        JButton OkayButton = new JButton("Okay");
        OkayButton.setActionCommand("okay");
        OkayButton.addActionListener(controller);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(controller);




        this.setVisible(true);

    }
    public String getText() {
        return textField.getText();
    }

    public String getWord() {
        return word;
    }

    @Override
    public void handleWordleDisplay(WordleModel model, char[][] result) {
        for(int i = 0; i < WordleModel.SIZE; i++) {
            for(int j = 0; j < WordleModel.SIZE; j++) {
                cells[i][j].setText(String.valueOf(model.getGrid()[i][j]));

                if (result[i][j] == 'G') cells[i][j].setBackground(Color.GREEN);
                else if (result[i][j] == 'Y') cells[i][j].setBackground(Color.YELLOW);
                else if (result[i][j] == 'X') cells[i][j].setBackground(Color.GRAY);
            }
        }

    }

    public static void main(String[] args) {
        WordleGUI window = new WordleGUI();
    }
}
