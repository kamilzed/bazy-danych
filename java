import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyFrame extends JFrame implements ActionListener {

    JLabel l1,l2,l3;
    JButton b1 = new JButton();
    JTextField t1 = new JTextField();
    public MyFrame() {
        this.setLayout(null);
        System.out.println(validPesel("08280810917"));
        this.setTitle("PESEL");
        this.setSize(500,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        l1 = new JLabel("PESEL - aplikacja do sprawdzenia (53082806059)");
        l1.setBounds(10,20,400,30);
        this.add(l1);

        l2 = new JLabel("Wpisz swój PESEL:");
        l2.setBounds(30,50,150,30);
        this.add(l2);
        t1.setBounds(180,50,200,30);
        this.add(t1);
        b1.setText("Sprawdź PESEL");
        b1.setBounds(30,100,180,30);
        b1.addActionListener(this);
        this.add(b1);

        l3 = new JLabel("");
        l3.setBounds(60,150,200,30);
        l3.setVisible(false);
        this.add(l3);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (validPesel(t1.getText()))
                l3.setText("PESEL - prawidłowy");
            else
                l3.setText("PESEL - nieprawidłowy");
            l3.setVisible(true);
            this.revalidate();
            this.repaint();
        }
    }

    private boolean validPesel(String pesel)
    {
        if(pesel.length() != 11)
            return false;
        //pre procesing
        ArrayList<Integer> peselInt = new ArrayList<Integer>();
        int verificationArr[] = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        for(String i : pesel.split(""))
            peselInt.add(Integer.parseInt(i));

        int day = peselInt.get(4)*10 + peselInt.get(5);
        int month = peselInt.get(2)*10 + peselInt.get(3);
        int year = 1900 + peselInt.get(0)*10 + peselInt.get(1);
        if(month>12)
        {
            year += 100;
            month -=20;
        }
        System.out.println(day+" "+month+" "+year+" "+valodMonthDay(month,day,year));

        //sprawdzanie
        for(int i = 0 ; i < 10 ; i++)
        {
            peselInt.set(i, peselInt.get(i)* verificationArr[i]);
        }
        int sum = 0;
        for(int i  = 0 ; i < 10 ; i++)
            sum += peselInt.get(i);
        int re = sum %10;

        if(re==0)
        {
            if(peselInt.get(10) == 0)
                return true;
            return false;
        }
            if(re+peselInt.get(10) == 10)
                return true;
            return false;
    }



    private boolean valodMonthDay(int month,int day,int year)
    {
        if(month>12)
            return false;

        if(month>=8)
        {
            if(month%2 == 0 && day<=31)
                return true;
            else if(day<=30)
                return true;
            return false;
        }
        if(month == 2 )
        {
            if(day <=29 && year %4 == 0)
                return true;
            else if(day <=28)
                return true;
            return  false;
        }
        if(month % 2 == 0 && day <= 30)
            return true;
        else if(day <= 30)
            return true;

        return false;
    }

    public static void main(String[] args) {MyFrame myFrame = new MyFrame();}
}
//1 31
//2 28-29
//3 31
//4 30
//5 31
//6 30
//7 31

//8 31
//9 30
//10 31
//11 30
//12 31
