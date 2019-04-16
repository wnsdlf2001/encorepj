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
		alge= new JButton("대수 공식");
		geo = new JButton("도형 공식");
		tri = new JButton("삼각 함수 공식");
		integ = new JButton("적분 공식");
		
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
	
	public void actionPerformed(ActionEvent e){ //이벤트를 발생시킨 컴포넌트 파악이 1순위		
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