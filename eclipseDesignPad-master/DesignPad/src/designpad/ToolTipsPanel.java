package designpad;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ToolTipsPanel extends JScrollPane{
	
	JTextArea ta;
	
	public ToolTipsPanel() {
		ta = new JTextArea(40,500);
		
		ta.setText("\n" + 
				"\n" + 
				"Tool Tips\n" + 
				"\n" + 
				"New-To create a new file that can contain your artwork first click the “File” \n" + 
				"button on the menu bar. When the drop-down menu appears click new. \n" + 
				"Follow the instruction presented in the pop-up window.\n" + 
				"\n" + 
				"Save- To save your current piece of artwork click the “File” button from \n" + 
				"the menu bar. When the menu appears click the “Save” button.\n" + 
				"\n" + 
				"Open- To open a previously saved file click the “File” button of the menu \n" + 
				"bar. Once the menu opens click the “Open” option. Follow the dialog until \n" + 
				"the file you want appears on the screen.\n" + 
				"\n" + 
				"Export- To export a file to an image file format first click on the “File” button\n" + 
				" on the menu bar. Then click export to export the file.\n" + 
				"\n" + 
				"Undo – To undo click the “Edit” button from the menu bar. Then click undo.\n" + 
				"\n" + 
				"Redo- To redo first click the “Edit” button from the menu bar. Then click redo.\n" + 
				"\n" + 
				"Cut- To eliminate a shape first select the shape by choosing “Select” from the \n" + 
				"tool bar and clicking on the border or the shape. Then click the “Edit” button \n" + 
				"on the menu bar and find the “Cut” button and click it.\n" + 
				"\n" + 
				"Copy- To save a shape to the clipboard click the “Select” button from the tool \n" + 
				"bar. Click on the edge of the shape. Then choose the “Edit” button from the\n" + 
				" menu bar and click “Copy” when the drop-down menu appears.\n" + 
				"\n" + 
				"Paste- To paste a shape from the clipboard click the “Edit” button from the \n" + 
				"menu bar. When the drop-down menu appears click the “Paste” button.\n" + 
				"\n" + 
				"Zoom In- To zoom in click on the “View” button from the menu bar and when \n" + 
				"the drop-down menu appears click “Zoom In”.\n" + 
				"\n" + 
				" Zoom Out- To zoom in click on the “View” button from the menu bar and \n" + 
				"when the drop-down menu appears click “Zoom Out”.\n" + 
				"\n" + 
				"50% - Zoom directly to 50% by clicking “View” on the menu bar. When the\n" + 
				" drop-down menu appears click “50%”.\n" + 
				"\n" + 
				"75% - Zoom directly to 75% by clicking “View” on the menu bar. When the \n" + 
				"drop-down menu appears click “75%”.\n" + 
				"\n" + 
				"100% - Zoom directly to 100% by clicking “View” on the menu bar. When \n" + 
				"the drop-down menu appears click “100%”.\n" + 
				"\n" + 
				"150% - Zoom directly to 150% by clicking “View” on the menu bar. When the \n" + 
				"drop-down menu appears click “150%”.\n" + 
				"\n" + 
				"To get help with how to use this program click “Help” button on the menu \n" + 
				"bar the click “Tool Tips”\n" + 
				"\n" + 
				"To find out more about this program click the “Help” menu bar selection and \n" + 
				"click “About”.\n" + 
				"\n" + 
				"To draw a shape chose a shape type from the tool bar. Click and drag the \n" + 
				"shape to the appropriate size and placement.\n" + 
				"");
		
		ta.setText("\n" + 
				"\n" + 
				"New-To create a new file that can contain your artwork first click the “File” button on the menu bar. When the drop-down menu appears click new. Follow the instruction presented in the pop-up window.\n" + 
				"\n" + 
				"Save- To save your current piece of artwork click the “File” button from the menu bar. When the menu appears click the “Save” button.\n" + 
				"\n" + 
				"Open- To open a previously saved file click the “File” button of the menu bar. Once the menu opens click the “Open” option. Follow the dialog until the file you want appears on the screen.\n" + 
				"\n" + 
				"Export- To export a file to an image file format first click on the “File” button on the menu bar. Then click export to export the file.\n" + 
				"\n" + 
				"Undo – To undo click the “Edit” button from the menu bar. Then click undo.\n" + 
				"\n" + 
				"Redo- To redo first click the “Edit” button from the menu bar. Then click redo.\n" + 
				"\n" + 
				"Cut- To eliminate a shape first select the shape by choosing “Select” from the tool bar and clicking on the border or the shape. Then click the “Edit” button on the menu bar and find the “Cut” button and click it.\n" + 
				"\n" + 
				"Copy- To save a shape to the clipboard click the “Select” button from the tool bar. Click on the edge of the shape. Then choose the “Edit” button from the menu bar and click “Copy” when the drop-down menu appears.\n" + 
				"\n" + 
				"Paste- To paste a shape from the clipboard click the “Edit” button from the menu bar. When the drop-down menu appears click the “Paste” button.\n" + 
				"\n" + 
				"Zoom In- To zoom in click on the “View” button from the menu bar and when the drop-down menu appears click “Zoom In”.\n" + 
				"\n" + 
				" Zoom Out- To zoom in click on the “View” button from the menu bar and when the drop-down menu appears click “Zoom Out”.\n" + 
				"\n" + 
				"50% - Zoom directly to 50% by clicking “View” on the menu bar. When the drop-down menu appears click “50%”.\n" + 
				"\n" + 
				"75% - Zoom directly to 75% by clicking “View” on the menu bar. When the drop-down menu appears click “75%”.\n" + 
				"\n" + 
				"100% - Zoom directly to 100% by clicking “View” on the menu bar. When the drop-down menu appears click “100%”.\n" + 
				"\n" + 
				"150% - Zoom directly to 150% by clicking “View” on the menu bar. When the drop-down menu appears click “150%”.\n" + 
				"\n" + 
				"To get help with how to use this program click “Help” button on the menu bar the click “Tool Tips”\n" + 
				"\n" + 
				"To find out more about this program click the “Help” menu bar selection and click “About”.\n" + 
				"\n" + 
				"To draw a shape chose a shape type from the tool bar. Click and drag the shape to the appropriate size and placement.\n" + 
				"");
		this.add(ta);
		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
}
