import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * QuizCalculator
 * - Center: question boxes (num1 op num2 = ?)
 * - Under boxes: small textual indicators "Correct" and "Incorrect"
 * - Right: big Score (Correct) and Incorrect boxes, with labels beneath
 * - Bottom-left: vertical radio buttons for operators (symbol + name) with border
 *
 * Comments added throughout.
 */
public class FinalArithmeticGame extends JFrame {
    // Big question labels
    private JLabel lblNum1, lblOp, lblNum2, lblEquals, lblCorrectAnswer;

    // Small text indicators under the big boxes (text only)
    private JLabel smallCorrectText, smallIncorrectText;

    // Answer input and feedback
    private JTextField answerField;
    private JLabel feedbackLabel;
    private JButton tryAgainButton;

    // Score displays on the right (numbers inside box)
    private JLabel totalCorrectLabel;
    private JLabel totalIncorrectLabel;

    // Internal counters and state
    private int totalCorrect = 0;
    private int totalIncorrect = 0;
    private String currentOperator = null; // e.g. "Addition", "Subtraction", ...
    private int levelMin = -1, levelMax = -1;
    private double correctAnswer = 0.0;
    private boolean awaitingAnswer = false;

    private final Random rand = new Random();
    private final ButtonGroup operatorGroup = new ButtonGroup();

    public FinalArithmeticGame() {
        setTitle("Arithmetic Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        // Main gradient background panel
        GradientPanel main = new GradientPanel();
        main.setLayout(new BorderLayout(18, 18));
        main.setBorder(new EmptyBorder(18, 18, 18, 18));
        setContentPane(main);

        // ---------------------------
        // CENTER: Question / Input
        // ---------------------------
        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        // QUESTION: boxes in a row
        JPanel questionRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 0));
        questionRow.setOpaque(false);
        lblNum1 = makeBoxLabel("");
        lblOp = makeBoxLabel("");
        lblNum2 = makeBoxLabel("");
        lblEquals = makeBoxLabel("=");
        lblCorrectAnswer = makeBoxLabel("?");

        questionRow.add(lblNum1);
        questionRow.add(lblOp);
        questionRow.add(lblNum2);
        questionRow.add(lblEquals);
        questionRow.add(lblCorrectAnswer);

        gbc.gridy = 0;
        center.add(questionRow, gbc);

        // SMALL INDICATORS below question boxes: texts only (words below boxes)
        JPanel smallIndicators = new JPanel(new FlowLayout(FlowLayout.CENTER, 160, 0));
        smallIndicators.setOpaque(false);
        smallCorrectText = new JLabel("Correct");
        smallIncorrectText = new JLabel("Incorrect");
        smallCorrectText.setFont(new Font("SansSerif", Font.BOLD, 16));
        smallIncorrectText.setFont(new Font("SansSerif", Font.BOLD, 16));
        // The user asked: number inside the box, word outside below — these are the words.
        smallIndicators.add(smallCorrectText);
        smallIndicators.add(smallIncorrectText);

        gbc.gridy = 1;
        center.add(smallIndicators, gbc);

        // ANSWER FIELD
        gbc.gridy = 2;
        answerField = new JTextField(10);
        answerField.setFont(new Font("SansSerif", Font.BOLD, 32));
        answerField.setHorizontalAlignment(SwingConstants.CENTER);
        answerField.setPreferredSize(new Dimension(220, 68));
        center.add(answerField, gbc);

        // TRY AGAIN button (hidden initially)
        gbc.gridy = 3;
        tryAgainButton = new JButton("Try Again");
        tryAgainButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        tryAgainButton.setVisible(false);
        tryAgainButton.addActionListener(e -> {
            tryAgainButton.setVisible(false);
            lblCorrectAnswer.setBackground(new Color(255, 255, 255, 220));
            generateQuestion();
        });
        center.add(tryAgainButton, gbc);

        // FEEDBACK label
        gbc.gridy = 4;
        feedbackLabel = new JLabel("Select an operator and a difficulty level to start.");
        feedbackLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        center.add(feedbackLabel, gbc);

        main.add(center, BorderLayout.CENTER);

        // ---------------------------
        // RIGHT: Score boxes + Level buttons
        // ---------------------------
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(new EmptyBorder(80, 20, 80, 20));

        // Score (Correct) box: number inside, word below handled by the label inside this panel
        JPanel correctBox = new JPanel();
        correctBox.setLayout(new BoxLayout(correctBox, BoxLayout.Y_AXIS));
        correctBox.setBackground(new Color(255, 255, 255, 220));
        correctBox.setBorder(new LineBorder(new Color(255, 182, 193), 3, true));
        correctBox.setMaximumSize(new Dimension(170, 90));
        correctBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        totalCorrectLabel = new JLabel(String.valueOf(totalCorrect), SwingConstants.CENTER);
        totalCorrectLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        totalCorrectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel correctWord = new JLabel("Correct", SwingConstants.CENTER);
        correctWord.setFont(new Font("SansSerif", Font.PLAIN, 14));
        correctWord.setAlignmentX(Component.CENTER_ALIGNMENT);

        correctBox.add(Box.createVerticalGlue());
        correctBox.add(totalCorrectLabel);
        correctBox.add(Box.createVerticalStrut(6));
        correctBox.add(correctWord);
        correctBox.add(Box.createVerticalGlue());

        // Incorrect box
        JPanel incorrectBox = new JPanel();
        incorrectBox.setLayout(new BoxLayout(incorrectBox, BoxLayout.Y_AXIS));
        incorrectBox.setBackground(new Color(255, 240, 240, 220));
        incorrectBox.setBorder(new LineBorder(new Color(255, 182, 193), 3, true));
        incorrectBox.setMaximumSize(new Dimension(170, 90));
        incorrectBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        totalIncorrectLabel = new JLabel(String.valueOf(totalIncorrect), SwingConstants.CENTER);
        totalIncorrectLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        totalIncorrectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel incorrectWord = new JLabel("Incorrect", SwingConstants.CENTER);
        incorrectWord.setFont(new Font("SansSerif", Font.PLAIN, 14));
        incorrectWord.setAlignmentX(Component.CENTER_ALIGNMENT);

        incorrectBox.add(Box.createVerticalGlue());
        incorrectBox.add(totalIncorrectLabel);
        incorrectBox.add(Box.createVerticalStrut(6));
        incorrectBox.add(incorrectWord);
        incorrectBox.add(Box.createVerticalGlue());

        // Add both boxes to right panel
        right.add(correctBox);
        right.add(Box.createRigidArea(new Dimension(0, 12)));
        right.add(incorrectBox);
        right.add(Box.createRigidArea(new Dimension(0, 24)));

        // Difficulty level buttons
        JButton level1 = createLevelButton("Level 1 (1-100)", 1, 100);
        JButton level2 = createLevelButton("Level 2 (101-200)", 101, 200);
        JButton level3 = createLevelButton("Level 3 (201-300)", 201, 300);

        level1.setAlignmentX(Component.CENTER_ALIGNMENT);
        level2.setAlignmentX(Component.CENTER_ALIGNMENT);
        level3.setAlignmentX(Component.CENTER_ALIGNMENT);

        right.add(level1);
        right.add(Box.createRigidArea(new Dimension(0, 8)));
        right.add(level2);
        right.add(Box.createRigidArea(new Dimension(0, 8)));
        right.add(level3);

        main.add(right, BorderLayout.EAST);

        // ---------------------------
        // BOTTOM-LEFT: Vertical operator radio buttons
        // ---------------------------
        JPanel bottomLeft = new JPanel();
        bottomLeft.setOpaque(false);
        bottomLeft.setLayout(new BoxLayout(bottomLeft, BoxLayout.Y_AXIS));
        bottomLeft.setBorder(new EmptyBorder(0, 18, 28, 18));

        JLabel opTitle = new JLabel("Operations");
        opTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        opTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottomLeft.add(opTitle);
        bottomLeft.add(Box.createRigidArea(new Dimension(0, 8)));

        // operator definitions: {symbol, name}
        String[][] ops = {
            {"+", "Addition"},
            {"-", "Subtraction"},
            {"×", "Multiplication"},
            {"÷", "Division"},
            {"%", "Modulo"}
        };

        for (String[] op : ops) {
            // Build radio button showing symbol + name and a pink rounded border
            JRadioButton rb = new JRadioButton(op[0] + "  " + op[1]);
            rb.setOpaque(false);
            rb.setFont(new Font("SansSerif", Font.PLAIN, 14));
            // add pink rounded border for clarity (line border with rounded corners)
            rb.setBorder(new LineBorder(new Color(255, 182, 193), 2, true));
            rb.setAlignmentX(Component.LEFT_ALIGNMENT);

            // action: set operator symbol for computation and display
            rb.addActionListener(e -> {
                currentOperator = op[1];       // store name like "Addition"
                lblOp.setText(op[0]);         // set symbol in the center boxes
                feedbackLabel.setText("Operator set to " + currentOperator + ". Choose a difficulty.");
            });

            operatorGroup.add(rb);
            bottomLeft.add(rb);
            bottomLeft.add(Box.createRigidArea(new Dimension(0, 6)));
        }

        main.add(bottomLeft, BorderLayout.SOUTH);

        // Enter key submits answer
        answerField.addActionListener(e -> checkAnswer());

        // Show window
        setVisible(true);
    }

    // Creates the big square/rectangular box labels used for numbers and operator
    private JLabel makeBoxLabel(String text) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setOpaque(true);
        lbl.setBackground(new Color(255, 255, 255, 220));
        lbl.setBorder(new LineBorder(new Color(255, 182, 193), 3, true));
        lbl.setFont(new Font("SansSerif", Font.BOLD, 42));
        lbl.setPreferredSize(new Dimension(140, 120));
        return lbl;
    }

    // Create difficulty button (sets levelMin/levelMax and generates question)
    private JButton createLevelButton(String text, int min, int max) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.PLAIN, 14));
        b.addActionListener(e -> {
            if (currentOperator == null) {
                feedbackLabel.setText("Please select an operator first!");
                return;
            }
            this.levelMin = min;
            this.levelMax = max;
            feedbackLabel.setText("Level set to " + text + ". Generating question...");
            generateQuestion();
        });
        return b;
    }

    // Generate a new question using current operator and selected level
    private void generateQuestion() {
        // Safety: ensure level has been chosen
        if (levelMin == -1 || levelMax == -1) {
            feedbackLabel.setText("Choose difficulty first.");
            return;
        }
        int a = randInt(levelMin, levelMax);
        int b = randInt(levelMin, levelMax);

        // Avoid division/modulo by zero
        String symbol = lblOp.getText();
        if ((symbol.equals("÷") || symbol.equals("%")) && b == 0) {
            b = Math.max(1, randInt(levelMin, levelMax));
        }

        // Compute correctAnswer (division rounded to 2 decimal places)
        switch (symbol) {
            case "+":
                correctAnswer = a + b;
                break;
            case "-":
                correctAnswer = a - b;
                break;
            case "×":
                correctAnswer = a * b;
                break;
            case "÷":
                correctAnswer = Math.round(((double) a / b) * 100.0) / 100.0;
                break;
            case "%":
                correctAnswer = a % b;
                break;
            default:
                correctAnswer = 0;
        }

        lblNum1.setText(String.valueOf(a));
        lblNum2.setText(String.valueOf(b));
        lblCorrectAnswer.setText("?");
        lblCorrectAnswer.setBackground(new Color(255, 255, 255, 220));
        answerField.setText("");
        awaitingAnswer = true;
    }

    // Check user answer when Enter is pressed
    private void checkAnswer() {
        if (!awaitingAnswer) {
            feedbackLabel.setText("No active question. Choose operator and level.");
            return;
        }

        String txt = answerField.getText().trim();
        if (txt.isEmpty()) {
            feedbackLabel.setText("Type an answer first.");
            return;
        }

        try {
            double userAns = Double.parseDouble(txt);
            boolean correct;

            String symbol = lblOp.getText();
            if (symbol.equals("÷")) {
                // round user answer to 2 decimals for comparison
                double rounded = Math.round(userAns * 100.0) / 100.0;
                correct = Math.abs(rounded - correctAnswer) < 1e-9;
            } else if (symbol.equals("%")) {
                // modulo expects integer result
                int ua = (int) Math.round(userAns);
                correct = ua == (int) correctAnswer;
            } else {
                correct = Math.abs(userAns - correctAnswer) < 1e-9;
            }

            if (correct) {
                totalCorrect++;
                totalCorrectLabel.setText(String.valueOf(totalCorrect));
                smallCorrectText.setText("Correct");
                smallIncorrectText.setText("Incorrect");
                lblCorrectAnswer.setText(String.valueOf(correctAnswer));
                lblCorrectAnswer.setBackground(new Color(144, 238, 144, 200)); // light green
                feedbackLabel.setText("✓ Correct! Press Try Again or choose another level.");
            } else {
                totalIncorrect++;
                totalIncorrectLabel.setText(String.valueOf(totalIncorrect));
                smallCorrectText.setText("Correct");
                smallIncorrectText.setText("Incorrect");
                lblCorrectAnswer.setText(String.valueOf(correctAnswer));
                lblCorrectAnswer.setBackground(new Color(255, 182, 193, 200)); // light red/pink
                feedbackLabel.setText("✗ Wrong! The correct answer was " + correctAnswer);
            }

            awaitingAnswer = false;
            tryAgainButton.setVisible(true);

        } catch (NumberFormatException nfe) {
            feedbackLabel.setText("Invalid number. Use digits only.");
        }
    }

    // Utility: random int between min and max (inclusive)
    private int randInt(int min, int max) {
        return min + rand.nextInt(max - min + 1);
    }

    // Pink-to-white gradient background panel
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(0, 0, new Color(255, 182, 193),
                    0, getHeight(), Color.WHITE);
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    // Program entry
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FinalArithmeticGame::new);
    }
}