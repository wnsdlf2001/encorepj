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

 

public class CalUI extends JFrame implements ActionListener{
	private JButton bn0, bn1, bn2, bn3, bn4, bn5, bn6, bn7, bn8, bn9, //숫자
		badd, bsub, bmult, bdiv, bmod, brt, bsq, bcv, bdot, bequ, bcl, bcla, bbk, //연산자
		bform, bfact, bpi,
		bmc, bmr, bmadd, bmsub, bms,bmst; //메모리관련 
	private JPanel numpad, calarea, memarea, memcalpanel; //판넬 영역
	private JScrollPane jp;
	private JList<String> list;
	DefaultListModel<String> listModel;
	private	JTextField formula, curnum;	//텍스트 영역
	private static int check =0; // 연산자 입력 후 1로 바꿔서 숫자 다시 입력받도록 해주는 변수. = 에도 있음
	private static int cont = 0; // = 의 연속 연산중인지 확인해주는 변수. 다른 연산자나 숫자를 입력하면 0으로 다시 바뀐다
	private static int opercont = 0; // 사용 안함
	private static int numdis =0; //메모리 영역 확인하기 위한 플래그 변수 
	private static int visible =0; // 식 기록 프레임의 팝업유무
	private static int curnumfsize = 50;
	Formulas fmls = new Formulas();
	private static String oper = "";
	private static String num1 = "";
	private	static String num2 = "";
	
	ArrayList<String> eqlist = new ArrayList<String>(){ //어레이 리스트 투스트링 오버라이드 
		@Override 
		public String toString(){
			String val = "";
			Iterator it = eqlist.iterator();
			while(it.hasNext()){
				val += it.next() +" ";      
			}
			return val;
		}
	};
	int i=1;
	public CalUI(){


		this.setIconImage((new ImageIcon("image/cal2.png")).getImage());
		setLayout(new GridBagLayout());
		setTitle("Simple Calculator");
			

		//메인 판넬 2개 배치 설정
		GridBagConstraints c1= new GridBagConstraints();
		GridBagConstraints c2= new GridBagConstraints();
		c1.gridx=0;
		c1.gridy=1;
		c1.gridwidth = 1;
		c1.gridheight =1;
		c1.weightx = 1;
        c1.weighty = 1;
		c1.fill = GridBagConstraints.BOTH;

		c2.gridx=0;
		c2.gridy=2;
		c2.gridwidth = 1;
		c2.gridheight =1;
		c2.weightx = 1;
        c2.weighty = 1;
		c2.fill = GridBagConstraints.BOTH;

		//calarea 판넬 보더 설정
		
		Font fort = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		Font numt = new Font(Font.MONOSPACED, Font.BOLD, curnumfsize);
	
		calarea = new JPanel();
		calarea.setLayout(new GridBagLayout());
		//calarea.setBorder(new LineBorder(Color.black));
		calarea.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 5, 5), new EtchedBorder()));
		
		calarea.setBackground(Color.white);


		ImageIcon normalbk = getScaledImage(new ImageIcon("image/bk.png"), 70, 60);
		ImageIcon pressbk = getScaledImage(new ImageIcon("image/bkf.png"), 70, 60);
		ImageIcon normalrt = getScaledImage(new ImageIcon("image/rt.png"), 45, 40);
		ImageIcon pressrt = getScaledImage(new ImageIcon("image/rtf.png"), 45, 40);
		ImageIcon normalsq = getScaledImage(new ImageIcon("image/sq.png"), 45, 40);
		ImageIcon presssq = getScaledImage(new ImageIcon("image/sqf.png"), 45, 40);
		ImageIcon normalmc = getScaledImage(new ImageIcon("image/mc.png"), 45, 40);
		ImageIcon pressmc = getScaledImage(new ImageIcon("image/mcf.png"), 45, 40);
		ImageIcon normalmr = getScaledImage(new ImageIcon("image/mr.png"), 45, 40);
		ImageIcon pressmr = getScaledImage(new ImageIcon("image/mrf.png"), 45, 40);
		ImageIcon normalms = getScaledImage(new ImageIcon("image/ms.png"), 45, 40);
		ImageIcon pressms = getScaledImage(new ImageIcon("image/msf.png"), 45, 40);
		ImageIcon normalmst = getScaledImage(new ImageIcon("image/mst.png"), 45, 40);
		ImageIcon pressmst = getScaledImage(new ImageIcon("image/mstf.png"), 45, 40);

		formula = new JTextField();
		curnum = new JTextField("0",10);

		brt = new JButton(normalrt);
		bsq = new JButton(normalsq);
		JLabel et = new JLabel("                ");  
		bbk = new JButton(normalbk);
		bmc = new JButton(normalmc);
		bmr = new JButton(normalmr);
		bms = new JButton(normalms);
		bmst = new JButton(normalmst);
		brt.setPreferredSize(new Dimension(25, 25));
		bsq.setPreferredSize(new Dimension(25, 25));
		bmc.setPreferredSize(new Dimension(25, 25));
		bmr.setPreferredSize(new Dimension(25, 25));
		bms.setPreferredSize(new Dimension(25, 25));
		bmst.setPreferredSize(new Dimension(25, 25));
		
		GridBagConstraints cc1= new GridBagConstraints();
		GridBagConstraints cc2= new GridBagConstraints();
		GridBagConstraints cc3= new GridBagConstraints();
		GridBagConstraints cc4= new GridBagConstraints();
		GridBagConstraints cc5= new GridBagConstraints();
		GridBagConstraints cc6= new GridBagConstraints();
		GridBagConstraints cc7= new GridBagConstraints();
		GridBagConstraints cc8= new GridBagConstraints();
		GridBagConstraints cc9= new GridBagConstraints();
		GridBagConstraints cc10= new GridBagConstraints();

		cc1.gridx=0;
		cc1.gridy=0;
		cc1.gridwidth = 4;
		cc1.gridheight =1;
		cc1.weightx = 1;
        cc1.weighty = 1;
		cc1.fill = GridBagConstraints.BOTH;

		cc2.gridx=0;
		cc2.gridy=1;
		cc2.gridwidth = 4;
		cc2.gridheight =1;
		cc2.weightx = 1;
        cc2.weighty = 2;
		cc2.fill = GridBagConstraints.BOTH;

		cc3.gridx=0;
		cc3.gridy=2;
		cc3.gridwidth = 1;
		cc3.gridheight =1;
		cc3.weightx = 1;
        cc3.weighty = 1;
		cc3.fill = GridBagConstraints.BOTH;

		cc4.gridx=1;
		cc4.gridy=2;
		cc4.gridwidth = 1;
		cc4.gridheight =1;
		cc4.weightx = 1;
        cc4.weighty = 1;
		cc4.fill = GridBagConstraints.BOTH;

		cc5.gridx=2;
		cc5.gridy=2;
		cc5.gridwidth = 1;
		cc5.gridheight =1;
		cc5.weightx = 1;
        cc5.weighty = 1;
		cc5.fill = GridBagConstraints.BOTH;

		cc6.gridx=3;
		cc6.gridy=2;
		cc6.gridwidth = 1;
		cc6.gridheight =1;
		cc6.weightx = 1;
        cc6.weighty = 1;
		cc6.fill = GridBagConstraints.BOTH;

		cc7.gridx=0;
		cc7.gridy=3;
		cc7.gridwidth = 1;
		cc7.gridheight =1;
		cc7.weightx = 1;
        cc7.weighty = 1;
		cc7.fill = GridBagConstraints.BOTH;

		cc8.gridx=1;
		cc8.gridy=3;
		cc8.gridwidth = 1;
		cc8.gridheight =1;
		cc8.weightx = 1;
        cc8.weighty = 1;
		cc8.fill = GridBagConstraints.BOTH;

		cc9.gridx=2;
		cc9.gridy=3;
		cc9.gridwidth = 1;
		cc9.gridheight =1;
		cc9.weightx = 1;
        cc9.weighty = 1;
		cc9.fill = GridBagConstraints.BOTH;

		cc10.gridx=3;
		cc10.gridy=3;
		cc10.gridwidth = 1;
		cc10.gridheight =1;
		cc10.weightx = 1;
        cc10.weighty = 1;
		cc10.fill = GridBagConstraints.BOTH;
		
		//기타 설정들
		formula.setHorizontalAlignment(JTextField.RIGHT);
		curnum.setHorizontalAlignment(JTextField.RIGHT);
		formula.setBorder(BorderFactory.createEmptyBorder());
		curnum.setBorder(BorderFactory.createEmptyBorder());
		formula.setFont(fort);
		curnum.setFont(numt);
		formula.setBackground(Color.black);
		curnum.setBackground(Color.black);
		formula.setForeground(Color.white);
		curnum.setForeground(Color.white);
		formula.setEditable(false);
		curnum.setEditable(false);

		brt.setBorderPainted(false);
		bsq.setBorderPainted(false);
		bbk.setBorderPainted(false);
		bmc.setBorderPainted(false);
		bmr.setBorderPainted(false);
		bms.setBorderPainted(false);
		bmst.setBorderPainted(false);

		brt.setContentAreaFilled(false);
		bsq.setContentAreaFilled(false);
		bbk.setContentAreaFilled(false);
		bmc.setContentAreaFilled(false);
		bmr.setContentAreaFilled(false);
		bms.setContentAreaFilled(false);
		bmst.setContentAreaFilled(false);

		brt.setFocusPainted(false);
		bsq.setFocusPainted(false);
		bbk.setFocusPainted(false);
		bmc.setFocusPainted(false);
		bmr.setFocusPainted(false);
		bms.setFocusPainted(false);
		bmst.setFocusPainted(false);

		bbk.setPreferredSize(new Dimension(45, 28));
		brt.setPreferredSize(new Dimension(45, 28));
		bsq.setPreferredSize(new Dimension(45, 28));

		bbk.setRolloverIcon(pressbk);
		brt.setRolloverIcon(pressrt);
		bsq.setRolloverIcon(presssq);
		bmc.setRolloverIcon(pressmc);
		bmr.setRolloverIcon(pressmr);
		bms.setRolloverIcon(pressms);
		bmst.setRolloverIcon(pressmst);



		//판넬에 붙이기
		calarea.add(formula, cc1);
		calarea.add(curnum, cc2);
		calarea.add(brt, cc3);
		calarea.add(bsq, cc4);
		calarea.add(et, cc5); // 빈라벨로 공간 채우기
		calarea.add(bbk, cc6);
		calarea.add(bmc, cc7);
		calarea.add(bmr, cc8);
		calarea.add(bms, cc9);
		calarea.add(bmst, cc10);

		add(calarea, c1);
		
		memcalpanel = new JPanel();
		memcalpanel.setLayout( new OverlayLayout(memcalpanel) );

		numpad = new JPanel();
		numpad.setLayout(new GridLayout(0,4));
		numpad.setBackground(Color.white);

		//버튼 이미지 로딩

		
		ImageIcon normal1 = getScaledImage(new ImageIcon("image/1.png"), 70, 60);
		ImageIcon press1 = getScaledImage(new ImageIcon("image/1f.png"), 70, 60);
		ImageIcon normal2 = getScaledImage(new ImageIcon("image/2.png"), 70, 60);
		ImageIcon press2 = getScaledImage(new ImageIcon("image/2f.png"), 70, 60);
		ImageIcon normal3 = getScaledImage(new ImageIcon("image/3.png"), 70, 60);
		ImageIcon press3 = getScaledImage(new ImageIcon("image/3f.png"), 70, 60);
		ImageIcon normal4 = getScaledImage(new ImageIcon("image/4.png"), 70, 60);
		ImageIcon press4 = getScaledImage(new ImageIcon("image/4f.png"), 70, 60);
		ImageIcon normal5 = getScaledImage(new ImageIcon("image/5.png"), 70, 60);
		ImageIcon press5 = getScaledImage(new ImageIcon("image/5f.png"), 70, 60);
		ImageIcon normal6 = getScaledImage(new ImageIcon("image/6.png"), 70, 60);
		ImageIcon press6 = getScaledImage(new ImageIcon("image/6f.png"), 70, 60);
		ImageIcon normal7 = getScaledImage(new ImageIcon("image/7.png"), 70, 60);
		ImageIcon press7 = getScaledImage(new ImageIcon("image/7f.png"), 70, 60);
		ImageIcon normal8 = getScaledImage(new ImageIcon("image/8.png"), 70, 60);
		ImageIcon press8 = getScaledImage(new ImageIcon("image/8f.png"), 70, 60);
		ImageIcon normal9 = getScaledImage(new ImageIcon("image/9.png"), 70, 60);
		ImageIcon press9 = getScaledImage(new ImageIcon("image/9f.png"), 70, 60);
		ImageIcon normal0 = getScaledImage(new ImageIcon("image/0.png"), 70, 60);
		ImageIcon press0 = getScaledImage(new ImageIcon("image/0f.png"), 70, 60);

		ImageIcon normaladd = getScaledImage(new ImageIcon("image/add.png"), 70, 60);
		ImageIcon pressadd = getScaledImage(new ImageIcon("image/addf.png"), 70, 60);
		ImageIcon normalsub = getScaledImage(new ImageIcon("image/sub.png"), 70, 60);
		ImageIcon presssub = getScaledImage(new ImageIcon("image/subf.png"), 70, 60);
		ImageIcon normalmult = getScaledImage(new ImageIcon("image/mult.png"), 70, 60);
		ImageIcon pressmult = getScaledImage(new ImageIcon("image/multf.png"), 70, 60);
		ImageIcon normaldiv = getScaledImage(new ImageIcon("image/div.png"), 70, 60);
		ImageIcon pressdiv = getScaledImage(new ImageIcon("image/divf.png"), 70, 60);
		ImageIcon normalequ = getScaledImage(new ImageIcon("image/equ.png"), 70, 60);
		ImageIcon pressequ = getScaledImage(new ImageIcon("image/equf.png"), 70, 60);
		ImageIcon normalcv = getScaledImage(new ImageIcon("image/cv.png"), 70, 60);
		ImageIcon presscv = getScaledImage(new ImageIcon("image/cvf.png"), 70, 60);
		ImageIcon normalmod = getScaledImage(new ImageIcon("image/mod.png"), 70, 60);
		ImageIcon pressmod = getScaledImage(new ImageIcon("image/modf.png"), 70, 60);
		ImageIcon normaldot = getScaledImage(new ImageIcon("image/dot.png"), 70, 60);
		ImageIcon pressdot = getScaledImage(new ImageIcon("image/dotf.png"), 70, 60);
		ImageIcon normalcl = getScaledImage(new ImageIcon("image/cl.png"), 70, 60);
		ImageIcon presscl = getScaledImage(new ImageIcon("image/clf.png"), 70, 60);
		ImageIcon normalcla = getScaledImage(new ImageIcon("image/cla.png"), 70, 60);
		ImageIcon presscla = getScaledImage(new ImageIcon("image/claf.png"), 70, 60);
		ImageIcon normalform = getScaledImage(new ImageIcon("image/form.png"), 70, 60);
		ImageIcon pressform = getScaledImage(new ImageIcon("image/formf.png"), 70, 60);
		ImageIcon normalfact = getScaledImage(new ImageIcon("image/fact.png"), 70, 60);
		ImageIcon pressfact = getScaledImage(new ImageIcon("image/factf.png"), 70, 60);
		ImageIcon normalpi = getScaledImage(new ImageIcon("image/pi.png"), 70, 60);
		ImageIcon presspi = getScaledImage(new ImageIcon("image/pif.png"), 70, 60);

		
		bcla = new JButton(normalcla);
		bcl = new JButton(normalcl);
		bmod = new JButton(normalmod);
		bdiv = new JButton(normaldiv);
		bn7 = new JButton(normal7);
		bn8 = new JButton(normal8);
		bn9 = new JButton(normal9);
		bmult = new JButton(normalmult);
		bn4 = new JButton(normal4);
		bn5 = new JButton(normal5);
		bn6 = new JButton(normal6);
		bsub = new JButton(normalsub);
		bn1 = new JButton(normal1);
		bn2 = new JButton(normal2);
		bn3 = new JButton(normal3);
		badd = new JButton(normaladd);
		bcv = new JButton(normalcv);
		bn0 = new JButton(normal0);
		bdot = new JButton(normaldot);
		bequ = new JButton(normalequ);
		
		bform = new JButton(normalform);
		bfact = new JButton(normalfact);
		bpi = new JButton(normalpi);
		JLabel et2 = new JLabel("                ");  
		
		bn1.setBorderPainted(false);
		bn2.setBorderPainted(false);
		bn3.setBorderPainted(false);
		bn4.setBorderPainted(false);
		bn5.setBorderPainted(false);
		bn6.setBorderPainted(false);
		bn7.setBorderPainted(false);
		bn8.setBorderPainted(false);
		bn9.setBorderPainted(false);
		bn0.setBorderPainted(false);
		badd.setBorderPainted(false);
		bsub.setBorderPainted(false);
		bmult.setBorderPainted(false);
		bdiv.setBorderPainted(false);
		bmod.setBorderPainted(false);
		bcv.setBorderPainted(false);
		bequ.setBorderPainted(false);
		bdot.setBorderPainted(false);
		bcl.setBorderPainted(false);
		bcla.setBorderPainted(false);
		bform.setBorderPainted(false);
		bfact.setBorderPainted(false);
		bpi.setBorderPainted(false);


		bn1.setContentAreaFilled(false);
		bn2.setContentAreaFilled(false);
		bn3.setContentAreaFilled(false);
		bn4.setContentAreaFilled(false);
		bn5.setContentAreaFilled(false);
		bn6.setContentAreaFilled(false);
		bn7.setContentAreaFilled(false);
		bn8.setContentAreaFilled(false);
		bn9.setContentAreaFilled(false);
		bn0.setContentAreaFilled(false);
		badd.setContentAreaFilled(false);
		bsub.setContentAreaFilled(false);
		bmult.setContentAreaFilled(false);
		bdiv.setContentAreaFilled(false);
		bmod.setContentAreaFilled(false);
		bcv.setContentAreaFilled(false);
		bequ.setContentAreaFilled(false);
		bdot.setContentAreaFilled(false);
		bcl.setContentAreaFilled(false);
		bcla.setContentAreaFilled(false);
		bform.setContentAreaFilled(false);
		bfact.setContentAreaFilled(false);
		bpi.setContentAreaFilled(false);


		bn1.setFocusPainted(false);
		bn2.setFocusPainted(false);
		bn3.setFocusPainted(false);
		bn4.setFocusPainted(false);
		bn5.setFocusPainted(false);
		bn6.setFocusPainted(false);
		bn7.setFocusPainted(false);
		bn8.setFocusPainted(false);
		bn9.setFocusPainted(false);
		bn0.setFocusPainted(false);
		badd.setFocusPainted(false);
		bsub.setFocusPainted(false);
		bmult.setFocusPainted(false);
		bdiv.setFocusPainted(false);
		bmod.setFocusPainted(false);
		bcv.setFocusPainted(false);
		bequ.setFocusPainted(false);
		bdot.setFocusPainted(false);
		bcl.setFocusPainted(false);
		bcla.setFocusPainted(false);
		bform.setFocusPainted(false);
		bfact.setFocusPainted(false);
		bpi.setFocusPainted(false);


		bn1.setRolloverIcon(press1);
		bn2.setRolloverIcon(press2);
		bn3.setRolloverIcon(press3);
		bn4.setRolloverIcon(press4);
		bn5.setRolloverIcon(press5);
		bn6.setRolloverIcon(press6);
		bn7.setRolloverIcon(press7);
		bn8.setRolloverIcon(press8);
		bn9.setRolloverIcon(press9);
		bn0.setRolloverIcon(press0);
		badd.setRolloverIcon(pressadd);
		bsub.setRolloverIcon(presssub);
		bmult.setRolloverIcon(pressmult);
		bdiv.setRolloverIcon(pressdiv);
		bmod.setRolloverIcon(pressmod);
		bcv.setRolloverIcon(presscv);
		bequ.setRolloverIcon(pressequ);
		bdot.setRolloverIcon(pressdot);
		bcl.setRolloverIcon(presscl);
		bcla.setRolloverIcon(presscla);
		bform.setRolloverIcon(pressform);
		bfact.setRolloverIcon(pressfact);
		bpi.setRolloverIcon(presspi);

		bn1.setPreferredSize(new Dimension(45, 28));
		bn2.setPreferredSize(new Dimension(45, 28));
		bn3.setPreferredSize(new Dimension(45, 28));
		bn4.setPreferredSize(new Dimension(45, 28));
		bn5.setPreferredSize(new Dimension(45, 28));
		bn6.setPreferredSize(new Dimension(45, 28));
		bn7.setPreferredSize(new Dimension(45, 28));
		bn8.setPreferredSize(new Dimension(45, 28));
		bn9.setPreferredSize(new Dimension(45, 28));
		bn0.setPreferredSize(new Dimension(45, 28));
		badd.setPreferredSize(new Dimension(45, 28));
		bsub.setPreferredSize(new Dimension(45, 28));
		bmult.setPreferredSize(new Dimension(45, 28));
		bdiv.setPreferredSize(new Dimension(45, 28));
		bmod.setPreferredSize(new Dimension(45, 28));
		bcv.setPreferredSize(new Dimension(45, 28));
		bequ.setPreferredSize(new Dimension(45, 28));
		bdot.setPreferredSize(new Dimension(45, 28));
		bcl.setPreferredSize(new Dimension(45, 28));
		bcla.setPreferredSize(new Dimension(45, 28));
		bform.setPreferredSize(new Dimension(30, 12));
		bfact.setPreferredSize(new Dimension(45, 28));
		bpi.setPreferredSize(new Dimension(45, 28));
		
		numpad.add(et2);
		numpad.add(bform);
		numpad.add(bfact);
		numpad.add(bpi);
		numpad.add(bcla);
		numpad.add(bcl);
		numpad.add(bmod);
		numpad.add(bdiv);
		numpad.add(bn7);
		numpad.add(bn8);
		numpad.add(bn9);
		numpad.add(bmult);
		numpad.add(bn4);
		numpad.add(bn5);
		numpad.add(bn6);
		numpad.add(bsub);
		numpad.add(bn1);
		numpad.add(bn2);
		numpad.add(bn3);
		numpad.add(badd);
		numpad.add(bcv);
		numpad.add(bn0);
		numpad.add(bdot);
		numpad.add(bequ);
		
		
		
		//메모리 계산 합칠 판넬



		Font mf = new Font(Font.MONOSPACED, Font.BOLD,13);
		memarea = new JPanel();
		memarea.setLayout(new GridLayout());
		memarea.setBackground(Color.white);
		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		list.setFont(mf);
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)list.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.RIGHT);
		jp = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		

		memarea.add(jp);
		memcalpanel.add(memarea);
		memcalpanel.add(numpad);
	
		
		//리스트일단 안보이게
		memarea.setOpaque(false);
		jp.setVisible(false); 
	
		numpad.setOpaque(true);
		
	

		add(memcalpanel, c2);

		pack();
		setBounds(1000,300,355,800);
		setVisible(true);
	
		addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.out.println("프로그램 종료");
					System.exit(0);
				}
			}
		);
		// 각 버튼의 리스너 
		bn0.addActionListener(this);
		bn1.addActionListener(this);
		bn2.addActionListener(this);
		bn3.addActionListener(this);
		bn4.addActionListener(this);
		bn5.addActionListener(this);
		bn6.addActionListener(this);
		bn7.addActionListener(this);
		bn8.addActionListener(this);
		bn9.addActionListener(this);
		badd.addActionListener(this);
		bsub.addActionListener(this);
		bmult.addActionListener(this);
		bdiv.addActionListener(this);
		bmod.addActionListener(this);
		brt.addActionListener(this);
		bsq.addActionListener(this);
		bbk.addActionListener(this);
		bdot.addActionListener(this);
		bcv.addActionListener(this);
		bequ.addActionListener(this);
		bcl.addActionListener(this);
		bcla.addActionListener(this);
		bms.addActionListener(this);
		bmc.addActionListener(this);
		bmr.addActionListener(this);
		bmst.addActionListener(this);
		bform.addActionListener(this);
		bfact.addActionListener(this);
		bpi.addActionListener(this);
		
		//텍스트 리스너
		//Font numt = new Font(Font.MONOSPACED, Font.BOLD, 30);
		DecimalFormat df = new DecimalFormat("#,###.##########");
		curnum.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent documentEvent) {
			}
			public void insertUpdate(DocumentEvent documentEvent) {
				insertcomma();
			}
			private void insertcomma() {
				Runnable doAssist = new Runnable() {
					public void run() {
						Font numt = new Font(Font.MONOSPACED, Font.BOLD, curnumfsize);
						BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
						FontMetrics fm = img.getGraphics().getFontMetrics(numt);
						int width = fm.stringWidth(curnum.getText()); //현재 글씨 넓이
						int c = curnum.getSize().width; //화면넓이
						//System.out.println(width+"  "+c);
						if(c < width) {	
							while(true) {	
								numt = new Font(Font.MONOSPACED, Font.BOLD, curnumfsize);
								curnumfsize-=0.1;
								fm = img.getGraphics().getFontMetrics(numt);
								width = fm.stringWidth(curnum.getText()); //현재 글씨 넓이
								if(c > width)
									break;							
							}
							curnum.setFont(numt);
						}
						else {					
							numt = new Font(Font.MONOSPACED, Font.BOLD, curnumfsize);
							curnumfsize=50;
							curnum.setFont(numt);	
						}
						if(curnum.getText().length()>=21) {
							//curnum.setText(curnum.getText().substring(0,20));
						}
					}
				};
				SwingUtilities.invokeLater(doAssist);
            }

			public void removeUpdate(DocumentEvent documentEvent) {
			}
		});

		// 키 이벤트 리스너

		curnum.requestFocus();
		curnum.addKeyListener(
			new KeyAdapter(){
				public void keyPressed(KeyEvent e){
					Calculate c = new Calculate();
					DecimalFormat df = new DecimalFormat("####.#######E0");
					DecimalFormat dfs = new DecimalFormat("####.########");
					int numlimit = 15;
					switch(e.getKeyCode()){
					case KeyEvent.VK_0:
					case KeyEvent.VK_NUMPAD0:
						if(curnum.getText().equals("0") || check==1){
							curnum.setText("");
							check = 0;
							cont = 0;
						}
						curnum.setText(curnum.getText() + "0");
						break;
					case KeyEvent.VK_1:
					case KeyEvent.VK_NUMPAD1:
						if(curnum.getText().equals("0") || check==1){
							curnum.setText("");
							check = 0;
							cont = 0;
						}
						curnum.setText(curnum.getText() + "1");
						break;
					case KeyEvent.VK_2:
					case KeyEvent.VK_NUMPAD2:
						if(curnum.getText().equals("0") || check==1){
							curnum.setText("");
							check = 0;
							cont = 0;
						}
						curnum.setText(curnum.getText() + "2");
						break;
					case KeyEvent.VK_3:
					case KeyEvent.VK_NUMPAD3:
						if(curnum.getText().equals("0") || check==1){
							curnum.setText("");
							check = 0;
							cont = 0;
						}
						curnum.setText(curnum.getText() + "3");
						break;
					case KeyEvent.VK_4:
					case KeyEvent.VK_NUMPAD4:
						if(curnum.getText().equals("0") || check==1){
							curnum.setText("");
							check = 0;
							cont = 0;
						}
						curnum.setText(curnum.getText() + "4");
						break;
					case KeyEvent.VK_5:
					case KeyEvent.VK_NUMPAD5:
						if(curnum.getText().equals("0") || check==1){
							curnum.setText("");
							check = 0;
							cont = 0;
						}
						curnum.setText(curnum.getText() + "5");
						break;
					case KeyEvent.VK_6:
					case KeyEvent.VK_NUMPAD6:
						if(curnum.getText().equals("0") || check==1){
							curnum.setText("");
							check = 0;
							cont = 0;
						}
						curnum.setText(curnum.getText() + "6");
						break;
					case KeyEvent.VK_7:
					case KeyEvent.VK_NUMPAD7:
						if(curnum.getText().equals("0") || check==1){
							curnum.setText("");
							check = 0;
							cont = 0;
						}
						curnum.setText(curnum.getText() + "7");
						break;
					case KeyEvent.VK_8:
					case KeyEvent.VK_NUMPAD8:
						if(curnum.getText().equals("0") || check==1){
							curnum.setText("");
							check = 0;
							cont = 0;
						}
						curnum.setText(curnum.getText() + "8");
						break;
					case KeyEvent.VK_9:
					case KeyEvent.VK_NUMPAD9:
						if(curnum.getText().equals("0") || check==1){
							curnum.setText("");
							check = 0;
							cont = 0;
						}
						curnum.setText(curnum.getText() + "9");
						break;
					case KeyEvent.VK_PLUS:
					case KeyEvent.VK_ADD:
						if(check==1 && eqlist.isEmpty() ==false){
							oper = "+";
							eqlist.remove(eqlist.size()-1);
							eqlist.add(oper);
							formula.setText(eqlist.toString());
							num1 = curnum.getText();
				
						}
						else{
							if(eqlist.size()>1){
								if(oper.equals("+")){
									num2 = curnum.getText();
									eqlist.add(num2);
									eqlist.add(oper);
									formula.setText(eqlist.toString());
									if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
										curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									}
									else
										curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									opercont = 0;
									check=1;
									num1 = curnum.getText();
								}
								else{
									num2 = curnum.getText();
									if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
										curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									}
									else
										curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									oper ="+";
									eqlist.add(num2);
									eqlist.add(oper);
									formula.setText(eqlist.toString());
									check =1;
									num1 = curnum.getText();
								}
							}
							else{
								oper = "+";
								num1 = curnum.getText();
								check = 1;
								cont = 0;
					
								eqlist.add(num1);
								eqlist.add(oper);
								formula.setText(eqlist.toString());
							}
						}
						break;
					case KeyEvent.VK_MINUS:
					case KeyEvent.VK_SUBTRACT:
						if(check==1 && eqlist.isEmpty() ==false){
							oper = "-";
							eqlist.remove(eqlist.size()-1);
							eqlist.add(oper);
							formula.setText(eqlist.toString());
							num1 = curnum.getText();
						}
						else{
							if(eqlist.size()>1){
								if(oper.equals("-")){
									num2 = curnum.getText();
									eqlist.add(num2);
									eqlist.add(oper);
									formula.setText(eqlist.toString());
									if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
										curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									}
									else
										curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									opercont = 0;
									check=1;
									num1 = curnum.getText();
								}
								else{
									num2 = curnum.getText();
									if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
										curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									}
									else
										curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									oper ="-";
									eqlist.add(num2);
									eqlist.add(oper);
									formula.setText(eqlist.toString());
									check =1;
									num1 = curnum.getText();
								}
							}
							else{
								oper = "-";
								num1 = curnum.getText();
								check = 1;
								cont = 0;
					
								eqlist.add(num1);
								eqlist.add(oper);
								formula.setText(eqlist.toString());
							}
						}
						break;
					case KeyEvent.VK_MULTIPLY:
						if(check==1 && eqlist.isEmpty() ==false){
							oper = "*";
							eqlist.remove(eqlist.size()-1);
							eqlist.add(oper);
							formula.setText(eqlist.toString());
							num1 = curnum.getText();
						}
						else{
							if(eqlist.size()>1){
								if(oper.equals("*")){
									num2 = curnum.getText();
									eqlist.add(num2);
									eqlist.add(oper);
									formula.setText(eqlist.toString());
									if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
										curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									}
									else
										curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									opercont = 0;
									check=1;
									num1 = curnum.getText();
								}
								else{
									num2 = curnum.getText();
									if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
										curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									}
									else
										curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									oper ="*";
									eqlist.add(num2);
									eqlist.add(oper);
									formula.setText(eqlist.toString());
									check =1;
									num1 = curnum.getText();
								}
							}
							else{
								oper = "*";
								num1 = curnum.getText();
								check = 1;
								cont = 0;
								eqlist.add(num1);
								eqlist.add(oper);
								formula.setText(eqlist.toString());
							}
						}
						break;
					case KeyEvent.VK_DIVIDE:
					case KeyEvent.VK_SLASH:
						if(check==1 && eqlist.isEmpty() ==false){
							oper = "/";
							eqlist.remove(eqlist.size()-1);
							eqlist.add(oper);
							formula.setText(eqlist.toString());
							num1 = curnum.getText();
						}
						else{
							if(eqlist.size()>1){
								if(oper.equals("/")){		
									num2 = curnum.getText();
									eqlist.add(num2);
									eqlist.add(oper);
									formula.setText(eqlist.toString());
									if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
										curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									}
									else
										curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									opercont = 0;
									check=1;
									num1 = curnum.getText();
								}
								else{
									num2 = curnum.getText();				
									if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
										curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									}
									else
										curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
									oper ="/";
									eqlist.add(num2);
									eqlist.add(oper);
									formula.setText(eqlist.toString());
									check =1;
									num1 = curnum.getText();
								}
							}
							else{
								oper = "/";
								num1 = curnum.getText();
								check = 1;
								cont = 0;
					
								eqlist.add(num1);
								eqlist.add(oper);
								formula.setText(eqlist.toString());
							}
						}
						break;
					case KeyEvent.VK_DECIMAL:
					case KeyEvent.VK_PERIOD:
						if(curnum.getText().indexOf(".") ==-1)
							curnum.setText(curnum.getText() + ".");
						if(check==1){
							curnum.setText("0.");
							check=0;
						}
						break;
					case KeyEvent.VK_ENTER:
					case KeyEvent.VK_EQUALS:
						if(cont ==0){			
							System.out.println("1");
							num2 = curnum.getText();
							eqlist.clear();
						}
						else{
							System.out.println("2");
							num1 = (c.calculate(oper, num1, num2)).toString();
							eqlist.clear();
						}

						if((oper.equals("/") && num2.equals("0")) || (oper.equals("%") && num2.equals("0"))){
							//JOptionPane.showMessageDialog(this, "0 으로 나눌 수 없습니다!", "경고", JOptionPane.ERROR_MESSAGE);
							curnum.setText("0");
						}
						else{
							System.out.println("3");
							if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
								curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
							}
							else
								curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
							formula.setText("");
							check =1;
							cont =1;
							eqlist.clear();
						}
						break;
					case KeyEvent.VK_BACK_SPACE:
					case KeyEvent.VK_DELETE:
						curnum.setText(""+curnum.getText().substring(0, curnum.getText().length() - 1));
						break;
					}
				}
			}
		);
		//마우스 이벤트 리스너 
		list.addMouseListener(
			new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					if (e.getClickCount() == 2) { 
					curnum.setText((String)list.getModel().getElementAt(list.locationToIndex(e.getPoint())));
					}
				}
		});
		
	
		curnum.requestFocus();
		
	}

	
	public void actionPerformed(ActionEvent e){ //이벤트를 발생시킨 컴포넌트 파악이 1순위
		//String com = e.getActionCommand();
		Calculate c = new Calculate();
		DecimalFormat df = new DecimalFormat("####.#######E0");
		DecimalFormat dfs = new DecimalFormat("####.########");
		int numlimit = 15;
		//숫자 입력
		// 식 담을 어레이리스트 이름 : eqlist
		
		
		if(e.getSource().equals(bn0)){
			if(curnum.getText().equals("0") || check==1){
				curnum.setText("");
				check = 0;
				cont = 0;
			}
			curnum.setText(curnum.getText() + "0");
		}
		else if(e.getSource().equals(bn1)){
			if(curnum.getText().equals("0") || check==1){
				curnum.setText("");
				check = 0;
				cont = 0;
			}
			curnum.setText(curnum.getText() + "1");
		}
		else if(e.getSource().equals(bn2)){
			if(curnum.getText().equals("0") || check==1){
				curnum.setText("");
				check = 0;
				cont = 0;
			}
			curnum.setText(curnum.getText() + "2");
		}
		else if(e.getSource().equals(bn3)){
			if(curnum.getText().equals("0") || check==1){
				curnum.setText("");
				check = 0;
				cont = 0;
			}
			curnum.setText(curnum.getText() + "3");
		}
		else if(e.getSource().equals(bn4)){
			if(curnum.getText().equals("0") || check==1){
				curnum.setText("");
				check = 0;
				cont = 0;
			}
			curnum.setText(curnum.getText() + "4");
		}
		else if(e.getSource().equals(bn5)){
			if(curnum.getText().equals("0") || check==1){
				curnum.setText("");
				check = 0;
			}
			curnum.setText(curnum.getText() + "5");
		}
		else if(e.getSource().equals(bn6)){
			if(curnum.getText().equals("0") || check==1){
				curnum.setText("");
				check = 0;
				cont = 0;
			}
			curnum.setText(curnum.getText() + "6");
		}
		else if(e.getSource().equals(bn7)){
			if(curnum.getText().equals("0") || check==1){
				curnum.setText("");
				check = 0;
				cont = 0;
			}
			curnum.setText(curnum.getText() + "7");
		}
		else if(e.getSource().equals(bn8)){
			if(curnum.getText().equals("0") || check==1){
				curnum.setText("");
				check = 0;
				cont = 0;
			}
			curnum.setText(curnum.getText() + "8");
		}
		else if(e.getSource().equals(bn9)){
			if(curnum.getText().equals("0") || check==1){
				curnum.setText("");
				check = 0;
				cont = 0;
			}
			curnum.setText(curnum.getText() + "9");
		}
		else if(e.getSource().equals(bdot)){
			if(curnum.getText().indexOf(".") ==-1)
				curnum.setText(curnum.getText() + ".");
			if(check==1){
				curnum.setText("0.");
				check=0;
			}
		}

		//계산 연산자 부분
		else if(e.getSource().equals(badd)){
			if(check==1 && eqlist.isEmpty() ==false){
				oper = "+";
				eqlist.remove(eqlist.size()-1);
				eqlist.add(oper);
				formula.setText(eqlist.toString());
				num1 = curnum.getText();
	
			}
			else{
				if(eqlist.size()>1){
					if(oper.equals("+")){
						num2 = curnum.getText();
						eqlist.add(num2);
						eqlist.add(oper);
						formula.setText(eqlist.toString());
						if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
							curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						}
						else
							curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						opercont = 0;
						check=1;
						num1 = curnum.getText();
					}
					else{
						num2 = curnum.getText();
						if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
							curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						}
						else
							curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						oper ="+";
						eqlist.add(num2);
						eqlist.add(oper);
						formula.setText(eqlist.toString());
						check =1;
						num1 = curnum.getText();
					}
				}
				else{
					oper = "+";
					num1 = curnum.getText();
					check = 1;
					cont = 0;
		
					eqlist.add(num1);
					eqlist.add(oper);
					formula.setText(eqlist.toString());
				}
			}
		}
		else if(e.getSource().equals(bsub)){
			if(check==1 && eqlist.isEmpty() ==false){
				oper = "-";
				eqlist.remove(eqlist.size()-1);
				eqlist.add(oper);
				formula.setText(eqlist.toString());
				num1 = curnum.getText();
			}
			else{
				if(eqlist.size()>1){
					if(oper.equals("-")){
						num2 = curnum.getText();
						eqlist.add(num2);
						eqlist.add(oper);
						formula.setText(eqlist.toString());
						if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
							curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						}
						else
							curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						opercont = 0;
						check=1;
						num1 = curnum.getText();
					}
					else{
						num2 = curnum.getText();
						if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
							curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						}
						else
							curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						oper ="-";
						eqlist.add(num2);
						eqlist.add(oper);
						formula.setText(eqlist.toString());
						check =1;
						num1 = curnum.getText();
					}
				}
				else{
					oper = "-";
					num1 = curnum.getText();
					check = 1;
					cont = 0;
		
					eqlist.add(num1);
					eqlist.add(oper);
					formula.setText(eqlist.toString());
				}
			}
		}
		else if(e.getSource().equals(bmult)){
			if(check==1 && eqlist.isEmpty() ==false){
				oper = "*";
				eqlist.remove(eqlist.size()-1);
				eqlist.add(oper);
				formula.setText(eqlist.toString());
				num1 = curnum.getText();
			}
			else{
				if(eqlist.size()>1){
					if(oper.equals("*")){
						num2 = curnum.getText();
						eqlist.add(num2);
						eqlist.add(oper);
						formula.setText(eqlist.toString());
						if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
							curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						}
						else
							curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						opercont = 0;
						check=1;
						num1 = curnum.getText();
					}
					else{
						num2 = curnum.getText();
						if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
							curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						}
						else
							curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						oper ="*";
						eqlist.add(num2);
						eqlist.add(oper);
						formula.setText(eqlist.toString());
						check =1;
						num1 = curnum.getText();
					}
				}
				else{
					oper = "*";
					num1 = curnum.getText();
					check = 1;
					cont = 0;
					eqlist.add(num1);
					eqlist.add(oper);
					formula.setText(eqlist.toString());
				}
			}
		}
		else if(e.getSource().equals(bdiv)){
			if(check==1 && eqlist.isEmpty() ==false){
				oper = "/";
				eqlist.remove(eqlist.size()-1);
				eqlist.add(oper);
				formula.setText(eqlist.toString());
				num1 = curnum.getText();
			}
			else{
				if(eqlist.size()>1){
					if(oper.equals("/")){		
						num2 = curnum.getText();
						eqlist.add(num2);
						eqlist.add(oper);
						formula.setText(eqlist.toString());
						if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
							curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						}
						else
							curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						opercont = 0;
						check=1;
						num1 = curnum.getText();
					}
					else{
						num2 = curnum.getText();				
						if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
							curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						}
						else
							curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						oper ="/";
						eqlist.add(num2);
						eqlist.add(oper);
						formula.setText(eqlist.toString());
						check =1;
						num1 = curnum.getText();
					}
				}
				else{
					oper = "/";
					num1 = curnum.getText();
					check = 1;
					cont = 0;
		
					eqlist.add(num1);
					eqlist.add(oper);
					formula.setText(eqlist.toString());
				}
			}
		}
		else if(e.getSource().equals(bmod)){
			if(check==1 && eqlist.isEmpty() ==false){
				oper = "%";
				eqlist.remove(eqlist.size()-1);
				eqlist.add(oper);
				formula.setText(eqlist.toString());
				num1 = curnum.getText();
			}
			else{
				if(eqlist.size()>1){
					if(oper.equals("%")){
						num2 = curnum.getText();
						eqlist.add(num2);
						eqlist.add(oper);
						formula.setText(eqlist.toString());
						if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
							curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						}
						else
							curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						opercont = 0;
						check=1;
						num1 = curnum.getText();
					}
					else{
						num2 = curnum.getText();
						if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
							curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						}
						else
							curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
						oper ="%";
						eqlist.add(num2);
						eqlist.add(oper);
						formula.setText(eqlist.toString());
						check =1;
						num1 = curnum.getText();
					}
				}
				else{
					oper = "%";
					num1 = curnum.getText();
					check = 1;
					cont = 0;
		
					eqlist.add(num1);
					eqlist.add(oper);
					formula.setText(eqlist.toString());
				}
			}
		}
		
		else if(e.getSource().equals(brt)){	
			formula.setText("√( " +curnum.getText() +" )");
			if(c.calculate("rt", curnum.getText()).toString().length()>numlimit)
				curnum.setText(df.format(Double.parseDouble((c.calculate("rt", curnum.getText()).toString()))));
			else
				curnum.setText(dfs.format(Double.parseDouble((c.calculate("rt", curnum.getText()).toString()))));		
		}
		else if(e.getSource().equals(bcv)){	
			curnum.setText(c.calculate("cv", curnum.getText()).toString());
		}
		else if(e.getSource().equals(bsq)){
			formula.setText("sqr( " +curnum.getText() +" )");
			if(c.calculate("sq", curnum.getText()).toString().length()>numlimit)
				curnum.setText(df.format(Double.parseDouble((c.calculate("sq", curnum.getText()).toString()))));
			else
				curnum.setText(dfs.format(Double.parseDouble((c.calculate("sq", curnum.getText()).toString()))));	
		}
		else if(e.getSource().equals(bbk))
			curnum.setText(""+curnum.getText().substring(0, curnum.getText().length() - 1));
		else if(e.getSource().equals(bcla)){
			curnum.setText("0");
			formula.setText("");
			num1 = "";
			num2 = "";
			opercont = 0;
			cont = 0;
			check =0;
			eqlist.clear();
		}
		else if(e.getSource().equals(bcl)){
			curnum.setText("0");
		}
		//게산 이콜
		else if(e.getSource().equals(bequ)){
			if(cont ==0){			
				System.out.println("1");
				num2 = curnum.getText();
				eqlist.clear();
			}
			else{
				System.out.println("2");
				num1 = (c.calculate(oper, num1, num2)).toString();
				eqlist.clear();
			}

			if((oper.equals("/") && num2.equals("0")) || (oper.equals("%") && num2.equals("0"))){
				JOptionPane.showMessageDialog(this, "0 으로 나눌 수 없습니다!", "경고", JOptionPane.ERROR_MESSAGE);
				curnum.setText("0");
			}
			else{
				System.out.println("3");
				if(c.calculate(oper, num1, num2).toString().length()>numlimit) {
					curnum.setText(df.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
				}
				else
					curnum.setText(dfs.format(Double.parseDouble((c.calculate(oper, num1, num2)).toString())));
				formula.setText("");
				check =1;
				cont =1;
				eqlist.clear();
			}
		}
		else if(e.getSource().equals(bform)){	
			if(visible ==0) {
				fmls.setVisible(true);
				visible =1;
			}
			else {
				fmls.setVisible(false);
				visible =0;
			}
		}
		else if(e.getSource().equals(bfact)){
			formula.setText("fact( " + curnum.getText() +" )");
			curnum.setText(c.calculate("fac", curnum.getText()).toString());
			check =1;
			
		}
		else if(e.getSource().equals(bpi)){	
			curnum.setText("3.1415926535897932");
			check = 1;
		}

		//메모리 영역 처리
		else if(e.getSource().equals(bmst)){ // 메모리 스토리지 나타내는 부분
			if(numdis ==0){
				Component[] com = numpad.getComponents();
				for (int a = 0; a < com.length; a++) {
					 com[a].setEnabled(false);
				}
				memarea.setOpaque(true);
				jp.setVisible(true);
				revalidate();
				repaint();
				numdis =1;
			}
			else{
				Component[] com = numpad.getComponents();
				for (int a = 0; a < com.length; a++) {
					 com[a].setEnabled(true);
				}
				memarea.setOpaque(false);
				jp.setVisible(false);
				repaint();
				numdis =0;
			}
		}
		else if(e.getSource().equals(bms)){
			listModel.addElement(curnum.getText().toString());
			check=1;
		}
		else if(e.getSource().equals(bmc)){
			listModel.clear();
			check=1;
		}
		else if(e.getSource().equals(bmr)){
			curnum.setText((String)list.getModel().getElementAt(0));
		}
		else
			System.exit(0);
		curnum.requestFocus();
	}

	public ImageIcon getScaledImage(ImageIcon srcImg, int row, int col){ // 이미지 리사이징
	
		BufferedImage resizedImg = new BufferedImage(row, col, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		Image originImg = srcImg.getImage();
		
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(originImg, 0, 0, row, col, null);
		g2.dispose();
		ImageIcon icon = new ImageIcon(resizedImg);
		return icon;
	}


	public static String insertString(String originalString, String stringToBeInserted, int index) { 
        // Create a new string 
        String newString = originalString.substring(0, index + 1) 
                           + stringToBeInserted 
                           + originalString.substring(index + 1); 
  
        // return the modified String 
        return newString; 
	}
 
	public static void main(String[] args) {
		//JFrame.setDefaultLookAndFeelDecorated(true);
		new CalUI();
	}
}
