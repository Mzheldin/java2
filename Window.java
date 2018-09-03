package hw4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    // в переменные класса вынесены те элементы gui, к которым обращаются методы
    private JPanel pnl = new JPanel(); //панель для поля ввода и кнопки
    private JTextArea txta = new JTextArea(); // область отображения чата...
    private JScrollPane scp = new JScrollPane(txta); // ... которая помещена в скролл для возможности прокрутки
    private JTextField txtf = new JTextField(); // однострочное поле ввода сообщения


    Window() {
        setTitle("Homework 4"); // общая настройка окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setResizable(false);
        addMenu(); // добавление меню, в задании не было, но может пригодится
        setLayout(new BorderLayout()); // компоновщик окна
        txta.setFocusable(false); // недоступность области отображения чата для пользователя, можно переместить в метод
        add(scp, BorderLayout.CENTER); // добавление области отображения чата
        add(pnl, BorderLayout.SOUTH); // добавление панели инструментов ввода сообщения
        pnl.setLayout(new GridLayout(2, 1)); // компоновщик панели ввода
        addTxtField(); // добавление текстового поля ввода
        addBtnEnter(); // добавление кнопки отправки сообщения
        setVisible(true);
    }
    // метод, добавляющий поле ввода на панель и слушателя клавиши Enter
    private void addTxtField(){
        pnl.add(txtf);
        txtf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtMove();
            }
        });
    }
    // метод, создающий и добавляющий кнопку отправки сообщения на панель и слушателя ее нажатия
    private void addBtnEnter(){
        JButton JBEnter = new JButton("Отправить сообщение");
        pnl.add(JBEnter);
        JBEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtMove();
            }
        });
    }
    // метод "перемещения" текста
    private void txtMove(){
        if (txta.getText().equals("")) txta.setText(txtf.getText()); // если область отображения пуста, то = сообщению
        else txta.setText(txta.getText() + "\n" + txtf.getText()); // если нет, то перенос строки и + сообщения
        txtf.setText(null); // "очистка" поля ввода сообщения
    }
    // метод, добавляющий меню с одним разделом и одним подпунктом закрытия программы
    private void addMenu(){
        JMenuBar jMenuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem fexit = new JMenuItem("Exit");
        setJMenuBar(jMenuBar);
        jMenuBar.add(file);
        file.add(fexit);
        // закрытие окна при нажати Exit
        fexit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }



    public static void main(String[] args) {
        Window window = new Window();
    }

}
