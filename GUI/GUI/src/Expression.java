import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;

import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JProgressBar;

import org.omg.CORBA.DynAnyPackage.InvalidValue;


public class Expression {

	private JFrame frame;
	private JTextField inputFile;
	private JTextField calculatingTimeField;
	private JTextField resultField;
	private final Action action = new SwingAction();
	private JTextField expressionField;
	private JSpinner spinner;
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Expression window = new Expression();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Expression() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblThreads_1 = new JLabel("Threads:");
		GridBagConstraints gbc_lblThreads_1 = new GridBagConstraints();
		gbc_lblThreads_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblThreads_1.gridx = 0;
		gbc_lblThreads_1.gridy = 1;
		frame.getContentPane().add(lblThreads_1, gbc_lblThreads_1);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 24, 1));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 1;
		frame.getContentPane().add(spinner, gbc_spinner);
		
		
		JLabel lblParameters = new JLabel("Parameters:");
		GridBagConstraints gbc_lblParameters = new GridBagConstraints();
		gbc_lblParameters.insets = new Insets(0, 0, 5, 5);
		gbc_lblParameters.gridx = 0;
		gbc_lblParameters.gridy = 2;
		frame.getContentPane().add(lblParameters, gbc_lblParameters);
		
		JLabel lblfFiletxt = new JLabel("input file:");
		GridBagConstraints gbc_lblfFiletxt = new GridBagConstraints();
		gbc_lblfFiletxt.insets = new Insets(0, 0, 5, 5);
		gbc_lblfFiletxt.gridx = 0;
		gbc_lblfFiletxt.gridy = 3;
		frame.getContentPane().add(lblfFiletxt, gbc_lblfFiletxt);
		
		inputFile = new JTextField();
		inputFile.setEditable(false);
		GridBagConstraints gbc_inputFile = new GridBagConstraints();
		gbc_inputFile.fill = GridBagConstraints.VERTICAL;
		gbc_inputFile.anchor = GridBagConstraints.WEST;
		gbc_inputFile.insets = new Insets(0, 0, 5, 5);
		gbc_inputFile.gridx = 1;
		gbc_inputFile.gridy = 3;
		frame.getContentPane().add(inputFile, gbc_inputFile);
		inputFile.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        ".txt files", "txt");
			    JFileChooser chooser = new JFileChooser();
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(frame);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	inputFile.setText(chooser.getSelectedFile().getAbsolutePath());
			    }
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 3;
		
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		
		
		JLabel lblNewLabel = new JLabel("expression: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		expressionField = new JTextField();
		GridBagConstraints gbc_expressionField = new GridBagConstraints();
		gbc_expressionField.insets = new Insets(0, 0, 5, 5);
		gbc_expressionField.fill = GridBagConstraints.HORIZONTAL;
		gbc_expressionField.gridx = 1;
		gbc_expressionField.gridy = 4;
		frame.getContentPane().add(expressionField, gbc_expressionField);
		expressionField.setColumns(10);
		
		JLabel lbloResulttxt = new JLabel("result");
		GridBagConstraints gbc_lbloResulttxt = new GridBagConstraints();
		gbc_lbloResulttxt.insets = new Insets(0, 0, 5, 5);
		gbc_lbloResulttxt.gridx = 0;
		gbc_lbloResulttxt.gridy = 5;
		frame.getContentPane().add(lbloResulttxt, gbc_lbloResulttxt);
		
		resultField = new JTextField();
		resultField.setEditable(false);
		GridBagConstraints gbc_resultField = new GridBagConstraints();
		gbc_resultField.anchor = GridBagConstraints.NORTHWEST;
		gbc_resultField.insets = new Insets(0, 0, 5, 5);
		gbc_resultField.gridx = 1;
		gbc_resultField.gridy = 5;
		frame.getContentPane().add(resultField, gbc_resultField);
		resultField.setColumns(10);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				CalculationInfo result;
				String expression;
				int numberOfThreads = (int) spinner.getValue();
				if(inputFile.getText().equals("")){
					expression = expressionField.getText();
					try {
						result = Worker.CalculateFromExpression(expression, null, numberOfThreads);
					} 
					catch (Exception e) {
						resultField.setText(e.getMessage());
						return;
					}
				}
				else{
					expression = inputFile.getText();
					try {
						result = Worker.CalculateFromFile(expression, null, numberOfThreads);
					} 
					catch (Exception e) {
						resultField.setText(e.getMessage());
						return;
					}
				}
				FillInformation(result);
			}
		});
		GridBagConstraints gbc_btnCalculate = new GridBagConstraints();
		gbc_btnCalculate.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalculate.gridx = 0;
		gbc_btnCalculate.gridy = 8;
		frame.getContentPane().add(btnCalculate, gbc_btnCalculate);
		
		
		JLabel lblLoading = new JLabel("Loading:");
		GridBagConstraints gbc_lblLoading = new GridBagConstraints();
		gbc_lblLoading.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoading.gridx = 1;
		gbc_lblLoading.gridy = 8;
		frame.getContentPane().add(lblLoading, gbc_lblLoading);
		
		progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar.gridheight = 2;
		gbc_progressBar.gridx = 2;
		gbc_progressBar.gridy = 8;
		progressBar.setValue(0);
		frame.getContentPane().add(progressBar, gbc_progressBar);
		
		JLabel lblCalculatingTime = new JLabel("Calculating Time:");
		GridBagConstraints gbc_lblCalculatingTime = new GridBagConstraints();
		gbc_lblCalculatingTime.gridheight = 3;
		gbc_lblCalculatingTime.insets = new Insets(0, 0, 0, 5);
		gbc_lblCalculatingTime.gridx = 0;
		gbc_lblCalculatingTime.gridy = 10;
		frame.getContentPane().add(lblCalculatingTime, gbc_lblCalculatingTime);
		
		calculatingTimeField = new JTextField();
		calculatingTimeField.setEditable(false);
		GridBagConstraints gbc_calculatingTimeField = new GridBagConstraints();
		gbc_calculatingTimeField.anchor = GridBagConstraints.NORTHWEST;
		gbc_calculatingTimeField.gridx = 2;
		gbc_calculatingTimeField.gridy = 12;
		frame.getContentPane().add(calculatingTimeField, gbc_calculatingTimeField);
		calculatingTimeField.setColumns(10);
	}
	
	private void FillInformation(CalculationInfo info){
		resultField.setText(info.getResult());
		String time = Long.toString(info.getCalculatingTime());
		calculatingTimeField.setText(time + " ms");
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
