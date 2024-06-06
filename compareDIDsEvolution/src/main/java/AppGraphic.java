import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppGraphic extends JFrame{

    private JPanel mainWindow;
    private JButton okButton;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    private String textFromField1;
    private String textFromField2;


    public String getTextFromField1() {
        return textFromField1;
    }

    public String getTextFromField2() {
        return textFromField2;
    }

    public void setTextFromField1(String textFromField1) {
        this.textFromField1 = textFromField1;
    }

    public void setTextFromField2(String textFromField2) {
        this.textFromField2 = textFromField2;
    }

    JFrame frame = new JFrame("Compare evolution between DID N-1 and DID N");
    public AppGraphic() {


        okButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

               String text1 = textField1.getText().toString();
               String text2 = textField2.getText().toString();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setTextFromField1(text1);
                setTextFromField2(text2);
                if (!text1.equals("") && !text2.equals("")) {
                    TransformFileInMatrix t = new TransformFileInMatrix(text1,text2);
                    t.printAll();
                }

            }
        });
    }

    public void frame(){


            frame.setContentPane(mainWindow);
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
