package com.company;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class Application {
    static JFrame myJFrame = new JFrame();
    static Graphics graphics;
    static Graphics2D graphics2D;
    static JTextField num1, num2, num3, num4, num5;
    static JTextField input1, input2, input3, input4, input5;
    static JButton runButton, resetButton;

    
    public static void main(String[] args) {
        myJFrame.setSize(800 , 800);
        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myJFrame.setTitle("AD");
        BackgroundColor panel = new BackgroundColor(new Color(0,0,0));
        myJFrame.getContentPane().add(panel);
        myJFrame.setBackground(new Color(0,0,0));
        Application.addPanelToPane(panel);
        myJFrame.setVisible(true);
    }

    static int[] longestCommonSubstring(int[] inputArray) {
        int[] constArray = new int[]{1,2,3,4,5};

        final int size = 5;
        int[][] myArray = new int[size + 1][size + 1];
        
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                if (i == 0 || j == 0)
                    myArray[i][j] = 0;
                else if (constArray[i - 1] == inputArray[j - 1])
                    myArray[i][j] = myArray[i - 1][j - 1] + 1;
                else
                    myArray[i][j] = Math.max(myArray[i - 1][j], myArray[i][j - 1]);
            }
        }
        int temp = myArray[size][size];

        int[] outPutArray = new int[temp + 1];

        int i = size;
        int j = size;
        while (i > 0 && j > 0) {
           if (myArray[i - 1][j] > myArray[i][j - 1]){
                i--;
           }
           else if (constArray[i - 1] == inputArray[j - 1]) {
               outPutArray[temp - 1] = constArray[i - 1];
               temp--;
               j--;
               i--;
           }else
                j--;
        }
        return outPutArray;
    }

    public static JTextField constantTextFieldConfiguration(int textFieldNum) {
        JTextField constTextField = new JTextField(String.format("  %d", textFieldNum), 2);
        constTextField.setBackground(Color.lightGray);
        constTextField.setEditable(false);
        constTextField.setFont(new Font("SansSerif" , Font.BOLD , 20));
        return constTextField;
    }

    public static JTextField variableTextFieldConfiguration() {
        JTextField inputTextField = new JTextField("", 2);
        inputTextField.setBackground(Color.cyan);
        inputTextField.setFont(new Font("SansSerif" , Font.BOLD , 20));
        return inputTextField;
    }

    public static JButton runButtonHandler(JPanel panel) {
        runButton = new JButton("Run");
        runButton.setBackground(Color.GREEN);
        runButton.setFont(new Font("SansSerif" , Font.BOLD , 20));
        runButton.addActionListener(e -> {
            int[] userInput = new int[6];
            int[] output_for_gui_drawing;
            userInput[0] = Integer.parseInt(input1.getText());
            userInput[1] = Integer.parseInt(input2.getText());
            userInput[2] = Integer.parseInt(input3.getText());
            userInput[3] = Integer.parseInt(input4.getText());
            userInput[4] = Integer.parseInt(input5.getText());
            System.out.println("userInput : " + Arrays.toString(userInput));
            System.out.println("output" + Arrays.toString(output_for_gui_drawing = longestCommonSubstring(userInput)));

            for (int j : output_for_gui_drawing) {
                int x_src = 0, y_src = 0, x_dst = 0, y_dst = 0;
                //---src---
                if (j == Integer.parseInt(String.valueOf(num5.getText().charAt(2)))) {
                    x_src = num5.getX();
                    y_src = num5.getY();
                }
                if (j == Integer.parseInt(String.valueOf(num3.getText().charAt(2)))) {
                    x_src = num3.getX();
                    y_src = num3.getY();
                }
                if (j == Integer.parseInt(String.valueOf(num2.getText().charAt(2)))) {
                    x_src = num2.getX();
                    y_src = num2.getY();
                }
                if (j == Integer.parseInt(String.valueOf(num4.getText().charAt(2)))) {
                    x_src = num4.getX();
                    y_src = num4.getY();
                }
                //---dest---
                if (j == Integer.parseInt(String.valueOf(input1.getText().charAt(0)))) {
                    x_dst = input1.getX();
                    y_dst = input1.getY();
                }
                if (j == Integer.parseInt(String.valueOf(input2.getText().charAt(0)))) {
                    x_dst = input2.getX();
                    y_dst = input2.getY();
                }
                if (j == Integer.parseInt(String.valueOf(num1.getText().charAt(2)))) {
                    x_src = num1.getX();
                    y_src = num1.getY();
                }
                if (j == Integer.parseInt(String.valueOf(input3.getText().charAt(0)))) {
                    x_dst = input3.getX();
                    y_dst = input3.getY();
                }
                if (j == Integer.parseInt(String.valueOf(input4.getText().charAt(0)))) {
                    x_dst = input4.getX();
                    y_dst = input4.getY();
                }
                if (j == Integer.parseInt(String.valueOf(input5.getText().charAt(0)))) {
                    x_dst = input5.getX();
                    y_dst = input5.getY();
                }

                graphics = panel.getGraphics();

                int dx = (x_src + 20) - x_dst, dy = (y_src + 20) - y_dst;
                int distance;
                int distance_1 = y_dst - (y_src + 20);
                int distance_2 = x_dst - (x_src + 20);
                distance = Math.max(dx, dy);
                distance = Math.max(distance, distance_1);
                distance = Math.max(distance, distance_2);

                double angle = Math.atan2(dy, dx);
//                    System.out.println(angle);
                if (angle < -2) {
                    distance += 30;
                    angle += (-0.2);
                }
                if (-1.9 > angle && angle > -2.0) {
                    angle += (-0.1);
                }
//                    System.out.println(angle);

                graphics2D = (Graphics2D) graphics;
                graphics2D.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
                LineArrow line = new LineArrow(x_dst + 10, y_dst - 13, angle * 180 / Math.PI, distance, Color.magenta, 7, 20);
                line.draw(graphics2D);
            }
        });
        return runButton;
    }

    public static JButton resetButtonHandler(JPanel panel) {
        resetButton = new JButton("Reset");
        resetButton.setBackground(Color.red);
        resetButton.setFont(new Font("SansSerif" , Font.BOLD , 20));
        resetButton.addActionListener(e -> {
            input1.setText("");
            input2.setText("");
            input3.setText("");
            input4.setText("");
            input5.setText("");
            panel.removeAll();
            BackgroundColor panel1 = new BackgroundColor(new Color(0,0,0));
            Application.addPanelToPane(panel1);
            myJFrame.getContentPane().add(panel1);
            panel1.setVisible(true);
            myJFrame.setVisible(true);
        });
        return resetButton;
    }

    public static void addPanelToPane(Container pane) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        pane.setLayout(new GridBagLayout());

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx =1;
        gridBagConstraints.insets = new Insets(120, 30, 20, 30);

        int textFieldNum = 1;
        num1 = constantTextFieldConfiguration(textFieldNum);
        textFieldNum += 1;
        num2 = constantTextFieldConfiguration(textFieldNum);
        textFieldNum += 1;
        num3 = constantTextFieldConfiguration(textFieldNum);
        textFieldNum += 1;
        num4 = constantTextFieldConfiguration(textFieldNum);
        textFieldNum += 1;
        num5 = constantTextFieldConfiguration(textFieldNum);

        input1 = variableTextFieldConfiguration();
        input2 = variableTextFieldConfiguration();
        input3 = variableTextFieldConfiguration();
        input4 = variableTextFieldConfiguration();
        input5 = variableTextFieldConfiguration();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pane.add(num1, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pane.add(num2, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        pane.add(num3, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        pane.add(num4, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        pane.add(num5, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pane.add(input1, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        pane.add(input2, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        pane.add(input3, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        pane.add(input4, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        pane.add(input5, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 2;
        pane.add(runButtonHandler((JPanel) pane), gridBagConstraints);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 2;
        pane.add(resetButtonHandler((JPanel) pane), gridBagConstraints);
    }


}
