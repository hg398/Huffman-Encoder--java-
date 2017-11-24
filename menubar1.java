//package project;

import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;
import java.util.*;

abstract class HuffmanTree implements Comparable<HuffmanTree> {
    public final int frequency; // the frequency of this tree
    public HuffmanTree(int freq) { frequency = freq; }
 
    // compares on the frequency
    public int compareTo(HuffmanTree tree) {
        return frequency - tree.frequency;
    }
}
 
class HuffmanLeaf extends HuffmanTree {
    public final char value; // the character this leaf represents
 
    public HuffmanLeaf(int freq, char val) {
        super(freq);
        value = val;
    }
}
 
class HuffmanNode extends HuffmanTree {
    public final HuffmanTree left, right; // subtrees
 
    public HuffmanNode(HuffmanTree l, HuffmanTree r) {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}
 
 class HuffmanCode{
    // input is an array of frequencies, indexed by character code
	 static StringBuilder str = new StringBuilder();
	 static String [] str1= new String[256];
	 static int i=0,count=0;
    public static HuffmanTree buildTree(int[] charFreqs) {
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
        // initially, we have a forest of leaves
        // one for each non-empty character
        for (int i = 0; i < charFreqs.length; i++)
            if (charFreqs[i] > 0)
                trees.offer(new HuffmanLeaf(charFreqs[i], (char)i));
 
        assert trees.size() > 0;
        // loop until there is only one tree left
        while (trees.size() > 1) {
            // two trees with least frequency
            HuffmanTree a = trees.poll();
            HuffmanTree b = trees.poll();
 
            // put into new node and re-insert into queue
            trees.offer(new HuffmanNode(a, b));
        }
        return trees.poll();
    }
 
    public static void printCodes(HuffmanTree tree, StringBuffer prefix, char c) {
        assert tree != null;
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
 
            // print out character, frequency, and code for this leaf (which is just the prefix)
            if(leaf.value==c)
            {
            	str.append(prefix);
            	//System.out.print(prefix);
            }
            //jta1.setJTextPane(prefix);
           
 
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
 
            // traverse left
            prefix.append('0');
            printCodes(node.left, prefix ,c);
            prefix.deleteCharAt(prefix.length()-1);
 
            // traverse right
            prefix.append('1');
            printCodes(node.right, prefix ,c);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
    public static void printCodes1(HuffmanTree tree, StringBuffer prefix) {
        assert tree != null;
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
 
            // print out character, frequency, and code for this leaf (which is just the prefix)
            //System.out.println(leaf.value + "\t" + leaf.frequency + "\t\t" + prefix);
            str1[i]=leaf.value + "\t" + leaf.frequency + "\t\t" + prefix;
            i+=1;
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
 
            // traverse left
            prefix.append('0');
            printCodes1(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);
 
            // traverse right
            prefix.append('1');
            printCodes1(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
 
    public static String huff(String s) throws IOException{

    	String test = new String(Files.readAllBytes(Paths.get(s)));
    	for(int j=1;j<test.length();j++)
    	{
    		if(test.charAt(j-1)==test.charAt(j))
    			count++;
    	}
    	str.delete(0,str.length());
    	if(count==test.length()-1)
    	{
    		str.append("Invalid Input !!");
    		return str.toString();
    	}
    	else {
        int[] charFreqs = new int[256];
      
        for (char c : test.toCharArray())
            charFreqs[c]++;
 
        
        HuffmanTree tree = buildTree(charFreqs);
 
        for(int i=0;i<test.length();i++) {
               printCodes(tree, new StringBuffer(), test.charAt(i));
        }
        //System.out.println();
        //System.out.println(str);
        return str.toString();
    	}
    }
    public static void huff1(String s) throws IOException{
    	String test = new String(Files.readAllBytes(Paths.get(s)));
    	str.delete(0, str.length());
        int[] charFreqs = new int[256];
      
        for (char c : test.toCharArray())
        {
        	charFreqs[c]++;
        }
 
        
        HuffmanTree tree = buildTree(charFreqs);
 
        //System.out.println("SYMBOL\tFREQUENCY\tHUFFMAN CODE");
        printCodes1(tree, new StringBuffer());
        //return str.toString();
    }
    public static double huff2(String s) throws IOException{

    	String test = new String(Files.readAllBytes(Paths.get(s)));
    	str.delete(0,str.length());
    	int l=8*test.length();
        int[] charFreqs = new int[256];
      
        for (char c : test.toCharArray())
            charFreqs[c]++;
 
        
        HuffmanTree tree = buildTree(charFreqs);
 
        for(int i=0;i<test.length();i++) {
               printCodes(tree, new StringBuffer(), test.charAt(i));
        }
        //System.out.println();
        int l1=str.length();
        double ans=(double)l/(double)l1;
        return ans;
        
    }
    public static String huff3(int j) {
    	if(str1[j]!=null)
    	return str1[j].toString();
    	else
    		return("");
    }
     
}
public class menubar1 extends JFrame {
	  String aa;
	  int n=0;
    public menubar1() {
    	
    	 JTextPane jta = new JTextPane ();
         JTextPane jta1 = new JTextPane ();
        setTitle("Huffman Encoder");
        setSize(1200, 700);
        this.setResizable(false);
         
        // Creates a menubar for a JFrame
        JMenuBar menuBar = new JMenuBar();
         
        // Add the menubar to the frame
        setJMenuBar(menuBar);
      
       JMenu Open = new JMenu("Open");
       JMenu options = new JMenu("Option");
       JMenu about = new JMenu("About");
       JMenuItem open = new JMenuItem("Browse File");
       JMenuItem CompressionRatio= new JMenuItem("Compression Ratio");
       JMenuItem Encode = new JMenuItem("Encode");
       JMenuItem CharacterCode= new JMenuItem("Character Codes");
       Open.add(open);
       
       	menuBar.add(Open);
       	menuBar.add(options); 
        menuBar.add(about);
        options.add(Encode);
        options.add(CharacterCode);
        options.add(CompressionRatio);
        
        
        JSplitPane splitPane = new JSplitPane();
        splitPane.setSize(1000, 500);
        splitPane.setDividerSize(10);
        splitPane.setDividerLocation(600);
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(jta);
        splitPane.setRightComponent(jta1);
        jta.setEditable(false);
        jta1.setEditable(false);
		        
	        this.add(splitPane);
        Encode.addActionListener(new ActionListener() {
        	String s;
        	public void actionPerformed(ActionEvent e) {
        		try {
					s=HuffmanCode.huff(aa);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		jta1.setText(s);;
        	}
        });
       
        CharacterCode.addActionListener(new ActionListener() {
        	String s,str;
        	public void actionPerformed(ActionEvent e) {
        		try {
        		 HuffmanCode.huff1(aa);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		if(n==0) {
        		jta1.setText("SYMBOL\tFREQUENCY\tHUFFMAN CODE\n");
        		for(int i=0;i<256;i++) 
        		{
					   s=HuffmanCode.huff3(i);
					   jta1.setText(jta1.getText() + s +"\n");
			    }
        		n=1;
        		}
        		else if(n==1)
        		{
        			jta1.setText("");
        			jta1.setText(str);
        		}
        		str=jta1.getText();
        	}
        });
        
        CompressionRatio.addActionListener(new ActionListener() {
        	double ans=0;
        	public void actionPerformed(ActionEvent e) {
        		try {
					ans=HuffmanCode.huff2(aa);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		Double a=new Double(ans);
        		String s=String.valueOf(a);
        		jta1.setText(s);
        	}
        });
        open.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
        		 int result = dialog.showOpenDialog(splitPane);
        		 
        		 
        		if(dialog.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
        			
        			File file = dialog.getSelectedFile();
        			aa = dialog.getSelectedFile().getAbsolutePath();
        			BufferedReader reader = null;
					try {
						reader = new BufferedReader(new FileReader (file));
					} catch (FileNotFoundException e2) {
						e2.printStackTrace();
					}
        			StringBuilder  stringBuilder = new StringBuilder();
        		    String         ls = System.getProperty("line.separator");
        			String line=null;
        			try {
        		        while((line = reader.readLine()) != null) {
        		            stringBuilder.append(line);
        		            stringBuilder.append(ls);
        		        }
        		        stringBuilder.toString();
        		    } catch (IOException e1) {
						e1.printStackTrace();
					} finally {
        		        try {
							reader.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
        		    }
                    try {
                        jta.setPage(file.toURI().toURL());
                    } catch(Exception e1) {
                        e1.printStackTrace();
                    }
        				
        			}
        		}
        }
        		);
        setVisible(true);
    }
    
    public static void main(String[] args) {

    	menubar1 me = new menubar1();
        me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        me.setVisible(true);
    }
}