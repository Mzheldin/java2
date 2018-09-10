package hw6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientWindow extends JFrame {
    // в переменные класса вынесены те элементы gui, к которым обращаются методы
    private JPanel pnl = new JPanel(); //панель для поля ввода и кнопки
    private JTextArea txta = new JTextArea(); // область отображения чата...
    private JScrollPane scp = new JScrollPane(txta); // ... которая помещена в скролл для возможности прокрутки
    private JTextField txtf = new JTextField(); // однострочное поле ввода сообщения
    DateFormat df = new SimpleDateFormat("(HH:mm:ss)");

    private Client client;


    ClientWindow(Client client) {
        this.client = client;
        setWindow();
    }

    private void setWindow(){
        setTitle("Client"); // общая настройка окна
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
        addWindowClose();
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
        if (txtf.getText().trim().equals("")) return;
        if (txta.getText().equals("")) {
            txta.append(df.format(new Date()) + " : " + txtf.getText() + "\n");// если область отображения пуста, то = сообщению
            client.sendMsg();
        }
        else {
            txta.append(df.format(new Date()) + " : " + txtf.getText() + "\n");// если нет, то перенос строки и + сообщения
            client.sendMsg();
        }
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
                client.close();
                System.exit(0);
            }
        });
    }

    private void addWindowClose(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                client.close();
            }
        });
    }
    // геттер содержимого поля ввода для отправки с клиента
    protected String getTxtF(){
        return txtf.getText();
    }
    // сеттер поля отображения для приема клиентом
    protected void appendTxtA(String string){
        txta.append(df.format(new Date()) + " : " + string);
    }

}
