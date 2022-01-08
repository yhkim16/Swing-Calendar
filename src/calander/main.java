
package calander;
import java.util.Calendar;
import java.util.Date;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import static javax.swing.JOptionPane.*;

import java.net.*;

import javax.xml.*;

class MyFrame extends JFrame {


	MyFrame() {
		setTitle("달력");

		Calendar start_day = Calendar.getInstance();
		Calendar end_day = Calendar.getInstance();
		start_day.set(start_day.get(Calendar.YEAR), start_day.get(Calendar.MONTH), 1);
		end_day.set(1997, 8, 12);
		
		BufferedImage wPic = null;



		JPanel p1 = new JPanel();
		JLabel month = new JLabel(new ImageIcon(wPic = setMonth(start_day.get(Calendar.MONTH)+1)));
		JLabel today = new JLabel("0000년   0 월   0 일은  0 요일입니다.");
		today.setFont(new Font("굴림",Font.BOLD,20));
		
		JTextArea ser = new JTextArea("");
		JTextArea par = new JTextArea("");
		
		p1.add(month);
		
		add(p1, BorderLayout.LINE_START);
		
		

		JPanel p4 = new JPanel();

		p4.add(today);

		add(p4, BorderLayout.PAGE_START);
	


		


		JPanel p3 = new JPanel();
		JLabel[] bt = new JLabel[42];
		GridLayout gl = new GridLayout(7,8);
		JLabel days[] = new JLabel[7];
		String d[] = {"일", "월", "화", "수", "목", "금","토"};
		
		p3.setLayout(gl);
		
		for (int i= 0; i<days.length ; i++) {
	
			days[i] = new JLabel(d[i]);
			p3.add(days[i]);
		}

	    for (int i = 0; i < bt.length; i++) {
	            bt[i] = new JLabel(" ");
	            p3.add(bt[i]);
	    }
	    
	    for (int i = 0; i < bt.length; i++) {
	    	if(i < (start_day.get(Calendar.DAY_OF_WEEK)-1)) {
	    		bt[i].setText(" ");
	    	}
	    	else if(i>start_day.getActualMaximum(Calendar.DATE)+(start_day.get(Calendar.DAY_OF_WEEK)-2)) {
	    		bt[i].setText(" ");
	    	}
	    	else {

	    		bt[i].setText(Integer.toString(i+2-start_day.get(Calendar.DAY_OF_WEEK)));
	    		
	    	}
	    	bt[i].addMouseListener(new MouseListener() {
    			public void mouseClicked(MouseEvent e)  {
    				JLabel l = (JLabel)e.getSource();
    				String s  = new String(" ");
    				if(s.equals(l.getText())) {
    					
    				}
    				else {
    				
    					start_day.set(start_day.get(Calendar.YEAR), start_day.get(Calendar.MONTH), (Integer.parseInt(l.getText())));
    				
    					long diff = (start_day.getTimeInMillis() - end_day.getTimeInMillis())/1000/(60*60)/24;
    				
    					long year = diff / 365 ;
    				
    					long mon = diff % 365 / 30 % 12;
    				
    					long dd = diff % 365 % 30 % 31;  
    				
    				
    					showMessageDialog(null,"내가 태어난지" + year + "년  " + mon + "월 "+ dd +"일 차 입니다.");
    					
    					
    					String url = new String("https://www.google.com/search?q=");
    					url = url + (start_day.get(Calendar.MONTH)+1) +"월"+ Integer.parseInt(l.getText()) +"일태어난사람";
    					
    					System.out.println(url);

						
    					URL u;
						
							try {
								u = new URL(url);
						
						
    					URLConnection nc = u.openConnection();
    					nc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
    					InputStream input = nc.getInputStream();
				
    					int i = 500;
    					char c;
    					StringBuffer test = new StringBuffer();
    				
    					while (((c = (char) input.read()) != -1) && (--i > 0)) {
									
									test.append(c);
								}

									
    					input.close();
						
						
    					ser.setText(test.toString());
    					
    					int pt_start = -1;
						int pt_end = -1;
						
						String tag_start = "<title>";
						String tag_end = "</title>";
						
						String word = new String();
						
						pt_start = test.toString().indexOf(tag_start);
    					if(pt_start != -1) {
    						pt_end = test.toString().indexOf(tag_end);
    						if(pt_end != -1) {
    							word = test.toString().substring(pt_start + tag_start.length(), pt_end);
    						}
    					
    					}
    					
    					par.setText(word);
							} catch (MalformedURLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
    				}
    			}

    			public void mousePressed(MouseEvent e) {
    				
    			}

    			public void mouseReleased(MouseEvent e) {
    				
    			}

    			public void mouseEntered(MouseEvent e) {
    				JLabel l = (JLabel)e.getSource();
    				String s  = new String(" ");
    				if(s.equals(l.getText())) {
    					today.setText("0000년   0 월   0 일은  0 요일입니다.");
    				}
    				else {
    				
    					start_day.set(start_day.get(Calendar.YEAR), start_day.get(Calendar.MONTH), (Integer.parseInt(l.getText())));
    					today.setText(start_day.get(Calendar.YEAR)+ "년  " +(start_day.get(Calendar.MONTH)+1) + "월 "+ l.getText() +"일은 "+ d[start_day.get(Calendar.DAY_OF_WEEK)-1] + "요일입니다.");
    
							
    					
						
    				}
    			}

    			public void mouseExited(MouseEvent e) {
    				today.setText("0000년   0 월   0 일은  0 요일입니다.");
    			}
    		});
	    }
	    
	    add(p3);
		
	    
		JPanel combo = new JPanel();
		String [] mst = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		JComboBox c1 = new JComboBox(mst);
		JTextField t1 = new JTextField(Integer.toString(start_day.get(Calendar.YEAR)));
		c1.setSelectedIndex(start_day.get(Calendar.MONTH));
		c1.addItemListener(e ->{
			int index = ((JComboBox) c1).getSelectedIndex();

			month.setIcon(new ImageIcon(setMonth(index+1)));
			
			start_day.set(start_day.get(Calendar.YEAR), index, 1);
		   
			for (int i = 0; i < bt.length; i++) {
		    	if(i < (start_day.get(Calendar.DAY_OF_WEEK)-1)) {
		    		bt[i].setText(" ");
		    	}
		    	else if(i>start_day.getActualMaximum(Calendar.DATE)+(start_day.get(Calendar.DAY_OF_WEEK)-2)) {
		    		bt[i].setText(" ");
		    	}
		    	else {
		    		bt[i].setText(Integer.toString(i+2-start_day.get(Calendar.DAY_OF_WEEK)));
		    	}
		    }
			
		});
		t1.addActionListener(e ->{
			String s = t1.getText();
			start_day.set(Integer.parseInt(s), start_day.get(Calendar.MONTH), 1);
			
			for (int i = 0; i < bt.length; i++) {
		    	if(i < (start_day.get(Calendar.DAY_OF_WEEK)-1)) {
		    		bt[i].setText(" ");
		    	}
		    	else if(i>start_day.getActualMaximum(Calendar.DATE)+(start_day.get(Calendar.DAY_OF_WEEK)-2)) {
		    		bt[i].setText(" ");
		    	}
		    	else {
		    		bt[i].setText(Integer.toString(i+2-start_day.get(Calendar.DAY_OF_WEEK)));
		    	}
		    }
		    
		});
		
		

		//combo.add(ser);
		combo.add(par);
		
		combo.add(t1);
		combo.add(c1);
		
		add(combo, BorderLayout.PAGE_END);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 700);
		setVisible(true);
	}
	BufferedImage setMonth(int month) {
		
		String m = "";
		switch(month) {
		case 1:
			m = "pae0.png";
			break;
		case 2:
			m = "pae4.png";
			break;
		case 3:
			m = "pae8.png";
			break;
		case 4:
			m = "pae12.png";
			break;
		case 5:
			m = "pae16.png";
			break;
		case 6:
			m = "pae20.png";
			break;
		case 7:
			m = "pae24.png";
			break;
		case 8:
			m = "pae28.png";
			break;
		case 9:
			m = "pae32.png";
			break;
		case 10:
			m = "pae36.png";
			break;
		case 11:
			m = "pae40.png";
			break;
		case 12:
			m = "pae44.png";
			break;
		default:
			break;
		}


		BufferedImage wPic = null;
		try {
			wPic = ImageIO.read(this.getClass().getResource(m));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wPic;

	}

	
}
public class main {
	public static void main(String[] args) {

		MyFrame f = new MyFrame();

	}
}





















