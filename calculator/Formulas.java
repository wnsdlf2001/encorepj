import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.text.DecimalFormat;
import java.awt.geom.*;
import javax.swing.text.*; 
import javax.swing.border.*; 
import java.io.*;

public class Formulas extends JFrame implements ActionListener{
	JButton alge, geo, tri, dervlim, integ;
	public Formulas() {
		setLayout(new FlowLayout());
		alge= new JButton("��� ����");
		geo = new JButton("���� ����");
		tri = new JButton("�ﰢ �Լ� ����");
		integ = new JButton("���� ����");
		
		add(alge);
		add(geo);
		add(tri);
		add(integ);
		
		
		alge.addActionListener(this);
		geo.addActionListener(this);
		tri.addActionListener(this);
		integ.addActionListener(this);
		
	
		setBounds(800, 300, 200,200);
		
	}
	
	public void actionPerformed(ActionEvent e){ //�̺�Ʈ�� �߻���Ų ������Ʈ �ľ��� 1����		
		if(e.getSource().equals(alge)){
			openFile("form1");
		}
		else if(e.getSource().equals(geo)){
			openFile("form2");
		}
		else if(e.getSource().equals(tri)){
			openFile("form4");
		}
		else if(e.getSource().equals(integ)){
			openFile("form6");
		}
		
	}
	public void openFile(String n) {
	    File f = new File("image/"+n+".jpg");
	    File newf = new File(f.getAbsolutePath());
	    Desktop dt = Desktop.getDesktop();
	    try {
			dt.open(newf);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}