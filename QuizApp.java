import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class QuizApp extends JFrame {

    // Variables
    private int currentQuestionIndex = 0;
    public  int score = 0;
    private List<Question> questions = new ArrayList<>();
    
    private JLabel questionLabel;
    private JRadioButton optionA, optionB, optionC, optionD;
    private ButtonGroup optionsGroup;
    private JButton nextButton;

    public QuizApp() {
        // Set up the frame
        setTitle("Quiz App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Set up the layout
        setLayout(new BorderLayout());

        // Initialize components
        questionLabel = new JLabel("Question will be here");
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));

        optionA = new JRadioButton("Option A");
        optionB = new JRadioButton("Option B");
        optionC = new JRadioButton("Option C");
        optionD = new JRadioButton("Option D");

        optionsGroup = new ButtonGroup();
        optionsGroup.add(optionA);
        optionsGroup.add(optionB);
        optionsGroup.add(optionC);
        optionsGroup.add(optionD);

        optionsPanel.add(optionA);
        optionsPanel.add(optionB);
        optionsPanel.add(optionC);
        optionsPanel.add(optionD);

        add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        add(nextButton, BorderLayout.SOUTH);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(); // Check the selected answer and update score
                currentQuestionIndex++; // Move to the next question
                if (currentQuestionIndex < questions.size()) {
                    displayQuestion(); // Display the next question
                } else {
                    showResult(); // Show the final result if there are no more questions
                }
            }
        });

        // Load questions
        loadQuestions();
        displayQuestion();

        setVisible(true);
    }

    // Method to load questions into the list
    private void loadQuestions() {
        questions.add(new Question("What is the default value of a boolean variable in Java?", "true", "false", "null", "0", "false"));
        questions.add(new Question("Which of the following is not a primitive data type in Java?", "int", "boolean", "String", "char", "String"));
        questions.add(new Question("Which method is used to start a thread in Java?", "start()", "run()", "execute()", "init()", "start()"));
        questions.add(new Question("What is the result of the following code: int x = 5; x = x++;?", "x = 6", "x = 5", "x = 4", "Compilation error", "x = 5"));
        questions.add(new Question("Which of the following data types does not exist in Python?", "list", "tuple", "set", "array", "array"));
        questions.add(new Question("What is the output of the following Python code: x = 10; y = 20; print(x + y);", "10", "20", "30", "None", "30"));
        questions.add(new Question("Which function is used to read input from the user in Python?", "get_input()", "input()", "read()", "readline()", "input()"));
        questions.add(new Question("What will be the output of the following Python code: def foo(x, y=10): return x + y; print(foo(5));", "5", "10", "15", "Error", "15"));
    }

    // Method to display the current question
    private void displayQuestion() {
        Question question = questions.get(currentQuestionIndex);
        questionLabel.setText(question.getQuestion());
        optionA.setText("A. " + question.getOptionA());
        optionB.setText("B. " + question.getOptionB());
        optionC.setText("C. " + question.getOptionC());
        optionD.setText("D. " + question.getOptionD());

        optionsGroup.clearSelection(); // Clear any previous selection
    }

    // Method to check the selected answer and update the score
    private void checkAnswer() {
        Question question = questions.get(currentQuestionIndex);
        String selectedOption = "";
    
        if (optionA.isSelected()) {
            selectedOption = optionA.getText().substring(3); // Get the actual text after "A. "
        } else if (optionB.isSelected()) {
            selectedOption = optionB.getText().substring(3); // Get the actual text after "B. "
        } else if (optionC.isSelected()) {
            selectedOption = optionC.getText().substring(3); // Get the actual text after "C. "
        } else if (optionD.isSelected()) {
            selectedOption = optionD.getText().substring(3); // Get the actual text after "D. "
        }
    
        // If the selected option is correct, increment the score
        if (selectedOption.equals(question.getCorrectAnswer())) {
            score++;
        }
    }

    // Method to show the result
    private void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz Over! Your score is: " + score + "/" + questions.size());
        System.exit(0); // Close the application after showing the result
    }

    // Question class to store question, options, and correct answer
    private static class Question {
        private String question, optionA, optionB, optionC, optionD, correctAnswer;

        public Question(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
            this.question = question;
            this.optionA = optionA;
            this.optionB = optionB;
            this.optionC = optionC;
            this.optionD = optionD;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public String getOptionA() {
            return optionA;
        }

        public String getOptionB() {
            return optionB;
        }

        public String getOptionC() {
            return optionC;
        }

        public String getOptionD() {
            return optionD;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new QuizApp();
    }
}
