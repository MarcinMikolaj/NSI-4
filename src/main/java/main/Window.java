package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import encryption.AES;
import encryption.AESimpl;

public class Window extends JFrame implements ActionListener {

	private static final long serialVersionUID = -4810618286807932601L;
	private static final String windowTitle = "AES Encryption and Decryption";
	
	private AES aes;
	IvParameterSpec ivParameterSpec;
	
	// ** AES Encryption
	private JLabel labelAesEncryption;
	private JLabel labelEnterEncryptedText;
	private JTextArea textAreaEnterEncryptedText;
	private JLabel labelEnterEncryptionKey;
	private JTextField textFieldForEncryptionKey;
	private JButton buttonGenerateAESSecretKey;
	private JButton copyGeneratedAESecretKey;
	private JButton buttonEncrypt;
	private JLabel labelOutputTextEncrypt;
	private JTextArea textAreaOutputTextEncrypt;
	
	// ** AES File Encryption
	private JLabel labelAesEncryptionFile;
	private JLabel labelChooseFileToEncrypt;
	private JButton buttonSelectFileToEncrypt;
	private JLabel jlabelSelectedFileToNcrypt;
	private JLabel labelEnterEncryptionKeyForFile;
	private JTextField textFieldForEncryptionKeyForFile;
	private JButton buttonGenerateAESsecretKeyForFile;
	private JButton copyGeneratedAESsecretKeyForFile;
	private JButton buttonEncryptFile;
	private JFileChooser jFileChooseFileToEncrypt;
	private JFileChooser jFileChooseFileToSave;
	File selectedFileToEncrypt;
	
	// ** AES Decryption
	private JLabel labelAesDecryption;
	private JLabel labelEnterDecryptedText;
	private JTextArea textAreaEnterDecryptedText;
	private JLabel labelEnterDecryptionKey;
	private JTextField textFieldForDecryptionKey;
	private JButton buttonDecrypt;
	private JLabel labelOutputTextDecrypt;
	private JTextArea textAreaOutputTextDecrypt;
	
	// ** AES File Decryption
	private JLabel labelAesDecryptionFile;
	private JLabel labelChooseFileToDecrypt;
    private JButton buttonSelectFileToDecrypt;
	private JLabel jlabelSelectedFileToDecrypt;
	private JLabel labelEnterDecryptionKeyForFile;
	private JTextField textFieldForDecryptionKeyForFile;
	private JButton buttonDecryptFile;
	private JFileChooser jFileChooseFileToDecrypt;
	private JFileChooser jFileChooseFileToSaveAfterDecryption;
	File selectedFileToDecrypt;

	// Addidtional
	Color royalBlue = new Color(65,105,255);
	Color chocolate = new Color(210,105,30);
	Color mediumSeaGreen = new Color(60,179,113);
	
	// Constructor
	public Window() {
		
		this.aes = new AESimpl();
		
		setSize(1700, 800);
		setTitle(windowTitle);
		setLayout(null);

		
		// ** AES Encryption String
		labelAesEncryption = new JLabel("Encryption Text");
		labelAesEncryption.setBounds(100, 100, 200, 30);
		labelAesEncryption.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		add(labelAesEncryption);
		
		
		labelEnterEncryptedText = new JLabel("Enter text to be Encrypted");
		labelEnterEncryptedText.setBounds(100, 150, 200, 30);
		labelEnterEncryptedText.setFont(new Font("Sans Serif", Font.PLAIN, 13));
		add(labelEnterEncryptedText);
		
		
		textAreaEnterEncryptedText = new JTextArea("This sentence will be encrypted !", 100,20); //ROWS, COLS
		textAreaEnterEncryptedText.setBounds(100, 200, 300, 150);
		textAreaEnterEncryptedText.setLineWrap(true);
		add(textAreaEnterEncryptedText);
		
		
		labelEnterEncryptionKey = new JLabel("Enter Secret Key");
		labelEnterEncryptionKey.setBounds(100, 370, 300, 30);
		labelEnterEncryptionKey.setFont(new Font("Sans Serif", Font.PLAIN, 13));
		add(labelEnterEncryptionKey);
		
		
		textFieldForEncryptionKey = new JTextField();
		textFieldForEncryptionKey.setBounds(100, 400, 200, 30);
		add(textFieldForEncryptionKey);
		
		
		buttonGenerateAESSecretKey = new JButton("Generate");
		buttonGenerateAESSecretKey.setBounds(320, 400, 100, 30);
		buttonGenerateAESSecretKey.setForeground(Color.WHITE);
		buttonGenerateAESSecretKey.setBackground(royalBlue);
		buttonGenerateAESSecretKey.addActionListener(this);
		add(buttonGenerateAESSecretKey);
		
		
		copyGeneratedAESecretKey = new JButton("Copy");
		copyGeneratedAESecretKey.setBounds(420, 400, 100, 30);
		copyGeneratedAESecretKey.setForeground(Color.WHITE);
		copyGeneratedAESecretKey.setBackground(royalBlue);
		copyGeneratedAESecretKey.addActionListener(this);
		add(copyGeneratedAESecretKey);
		
		
		buttonEncrypt = new JButton("Encrypt");
		buttonEncrypt.setBounds(100, 450, 200, 30);
		buttonEncrypt.setForeground(Color.WHITE);
		buttonEncrypt.setBackground(mediumSeaGreen);
		buttonEncrypt.addActionListener(this);
		add(buttonEncrypt);
		
		
		labelOutputTextEncrypt = new JLabel("AES Encrypted Output (base64):");
		labelOutputTextEncrypt.setBounds(100, 500, 200, 30);
		labelOutputTextEncrypt.setFont(new Font("Sans Serif", Font.PLAIN, 13));
		add(labelOutputTextEncrypt);
		
		
		textAreaOutputTextEncrypt = new JTextArea(100,20);
		textAreaOutputTextEncrypt.setBounds(100, 530, 300, 150);
		textAreaOutputTextEncrypt.setLineWrap(true);
		add(textAreaOutputTextEncrypt);
		
		
		
		
		// ** AES File Encryption
		labelAesEncryptionFile = new JLabel("Encrypt File");
		labelAesEncryptionFile.setBounds(1200, 100, 200, 30);
		labelAesEncryptionFile.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		add(labelAesEncryptionFile);
		
		
		labelChooseFileToEncrypt = new JLabel("Select File to encrypt:");
		labelChooseFileToEncrypt.setFont(new Font("Sans Serif", Font.PLAIN, 13));
		labelChooseFileToEncrypt.setBounds(1200, 170, 200, 30);
		add(labelChooseFileToEncrypt);
		
		
		buttonSelectFileToEncrypt = new JButton("Select");
		buttonSelectFileToEncrypt.setBounds(1200, 200, 200, 30);
		buttonSelectFileToEncrypt.setForeground(Color.WHITE);
		buttonSelectFileToEncrypt.setBackground(royalBlue);
		add(buttonSelectFileToEncrypt);
		buttonSelectFileToEncrypt.addActionListener(this);
		
		
		jlabelSelectedFileToNcrypt = new JLabel("Selected File: ...");
		jlabelSelectedFileToNcrypt.setBounds(1200, 230, 200, 30);
		jlabelSelectedFileToNcrypt.setFont(new Font("Sans Serif", Font.PLAIN, 13));
		add(jlabelSelectedFileToNcrypt);
		
		
		labelEnterEncryptionKeyForFile = new JLabel("Enter secure key:");
		labelEnterEncryptionKeyForFile.setBounds(1200, 270, 200, 30);
		add(labelEnterEncryptionKeyForFile);
		
		
		textFieldForEncryptionKeyForFile = new JTextField("");
		textFieldForEncryptionKeyForFile.setBounds(1200, 300, 200, 30);
		add(textFieldForEncryptionKeyForFile);
		
		
		buttonGenerateAESsecretKeyForFile = new JButton("Generate");
		buttonGenerateAESsecretKeyForFile.setBounds(1420, 300, 100, 30);
		buttonGenerateAESsecretKeyForFile.setForeground(Color.WHITE);
		buttonGenerateAESsecretKeyForFile.setBackground(royalBlue);
		buttonGenerateAESsecretKeyForFile.addActionListener(this);
		add(buttonGenerateAESsecretKeyForFile);
		
		
		copyGeneratedAESsecretKeyForFile = new JButton("Copy");
		copyGeneratedAESsecretKeyForFile.setBounds(1520, 300, 100, 30);
		copyGeneratedAESsecretKeyForFile.setForeground(Color.WHITE);
		copyGeneratedAESsecretKeyForFile.setBackground(royalBlue);
		copyGeneratedAESsecretKeyForFile.addActionListener(this);
		add(copyGeneratedAESsecretKeyForFile);
		
		
		buttonEncryptFile = new JButton("Encrypt selected file");
		buttonEncryptFile.setBounds(1200, 340, 200, 30);
		buttonEncryptFile.setForeground(Color.WHITE);
		buttonEncryptFile.setBackground(mediumSeaGreen);
		add(buttonEncryptFile);
		buttonEncryptFile.addActionListener(this);
		
		// ** AES File Decryption
		labelAesDecryptionFile = new JLabel("Decrypt File");
		labelAesDecryptionFile.setBounds(1200, 420, 200, 30);
		labelAesDecryptionFile.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		add(labelAesDecryptionFile);
		
		
		labelChooseFileToDecrypt = new JLabel("Select File to decrypt:");
		labelChooseFileToDecrypt.setFont(new Font("Sans Serif", Font.PLAIN, 13));
		labelChooseFileToDecrypt.setBounds(1200, 490, 200, 30);
		add(labelChooseFileToDecrypt);
		
		
		buttonSelectFileToDecrypt = new JButton("Select");
		buttonSelectFileToDecrypt.setBounds(1200, 520, 200, 30);
		buttonSelectFileToDecrypt.setForeground(Color.WHITE);
		buttonSelectFileToDecrypt.setBackground(royalBlue);
		add(buttonSelectFileToDecrypt);
		buttonSelectFileToDecrypt.addActionListener(this);
		
		
		jlabelSelectedFileToDecrypt = new JLabel("Selected File: ...");
		jlabelSelectedFileToDecrypt.setBounds(1200, 550, 200, 30);
		jlabelSelectedFileToDecrypt.setFont(new Font("Sans Serif", Font.PLAIN, 13));
		add(jlabelSelectedFileToDecrypt);
		
		
		labelEnterDecryptionKeyForFile = new JLabel("Enter secure key:");
		labelEnterDecryptionKeyForFile.setBounds(1200, 580, 200, 30);
		labelEnterDecryptionKeyForFile.setFont(new Font("Sans Serif", Font.PLAIN, 13));
		add(labelEnterDecryptionKeyForFile);
		
		
		textFieldForDecryptionKeyForFile = new JTextField("");
		textFieldForDecryptionKeyForFile.setBounds(1200, 610, 200, 30);
		add(textFieldForDecryptionKeyForFile);
		
		
		buttonDecryptFile = new JButton("Encrypt selected file");
		buttonDecryptFile.setBounds(1200, 650, 200, 30);
		buttonDecryptFile.setForeground(Color.WHITE);
		buttonDecryptFile.setBackground(mediumSeaGreen);
		add(buttonDecryptFile);
		buttonDecryptFile.addActionListener(this);
		
		
			
		// ** AES Decryption String
		labelAesDecryption = new JLabel("Decryption Text");
		labelAesDecryption.setBounds(700, 100, 300, 30);
		labelAesDecryption.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		add(labelAesDecryption);
		
		
		labelEnterDecryptedText = new JLabel("Enter text to be Decrypted (base64)");
		labelEnterDecryptedText.setBounds(700, 150, 300, 30);
		labelEnterDecryptedText.setFont(new Font("Sans Serif", Font.PLAIN, 13));
		add(labelEnterDecryptedText);
		
		
		textAreaEnterDecryptedText = new JTextArea("", 100,20); //ROWS, COLS
		textAreaEnterDecryptedText.setBounds(700, 200, 300, 150);
		textAreaEnterDecryptedText.setLineWrap(true);
		add(textAreaEnterDecryptedText);
		
		
		labelEnterDecryptionKey = new JLabel("Enter Secret Key");
		labelEnterDecryptionKey.setBounds(700, 370, 300, 30);
		labelEnterDecryptionKey.setFont(new Font("Sans Serif", Font.PLAIN, 13));
		add(labelEnterDecryptionKey);
		
		
		textFieldForDecryptionKey = new JTextField();
		textFieldForDecryptionKey.setBounds(700, 400, 200, 30);
		add(textFieldForDecryptionKey);
		
		
		buttonDecrypt = new JButton("Decrypt");
		buttonDecrypt.setBounds(700, 450, 200, 30);
		buttonDecrypt.setForeground(Color.WHITE);
		buttonDecrypt.setBackground(mediumSeaGreen);
		buttonDecrypt.addActionListener(this);
		add(buttonDecrypt);
		
		
		labelOutputTextDecrypt = new JLabel("AES Decrypted Output:");
		labelOutputTextDecrypt.setBounds(700, 500, 200, 30);
		labelOutputTextDecrypt.setFont(new Font("Sans Serif", Font.PLAIN, 13));
		add(labelOutputTextDecrypt);
		
		
		textAreaOutputTextDecrypt = new JTextArea(100,20);
		textAreaOutputTextDecrypt.setBounds(700, 530, 300, 150);
		textAreaOutputTextDecrypt.setLineWrap(true);
		add(textAreaOutputTextDecrypt);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		// ** AES Encryption String
		if(buttonGenerateAESSecretKey == source) {
			
			try {
				
				SecretKey secretKey = aes.generateKey(128);
				String secretKeyAsString = aes.convertSecretKeyToString(secretKey);
				textFieldForEncryptionKey.setText(secretKeyAsString);
				
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
			
			
		}
		else if(buttonEncrypt == source) {
			
				try {
					
					IvParameterSpec iv = aes.genrateInitializationVector();
					this.ivParameterSpec = iv;
					String secretKeyAsString = textFieldForEncryptionKey.getText();
					SecretKey secretKey = aes.converStringToSecretKey(secretKeyAsString);
					String output = aes.encryptString(textAreaEnterEncryptedText.getText(), secretKey, ivParameterSpec);
					textAreaOutputTextEncrypt.setText(output);
					
				} catch (InvalidKeyException e1) {
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					e1.printStackTrace();
				} catch (InvalidAlgorithmParameterException e1) {
					e1.printStackTrace();
				} catch (IllegalBlockSizeException e1) {
					e1.printStackTrace();
				} catch (BadPaddingException e1) {
					e1.printStackTrace();
				}
		} else if(copyGeneratedAESecretKey == source) {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			StringSelection stringSelection = new StringSelection(textFieldForEncryptionKey.getText());
			clipboard.setContents(stringSelection, null);
		}
		
		
		// ** AES Decryption String
		if(buttonDecrypt == source) {
			
			try {
				String toDecrypted = textAreaEnterDecryptedText.getText();
				String secretKeyAsString = textFieldForDecryptionKey.getText();
				SecretKey secretKey = aes.converStringToSecretKey(secretKeyAsString);
				String decrypted = aes.decryptString(toDecrypted, secretKey, this.ivParameterSpec);
				textAreaOutputTextDecrypt.setText(decrypted);
				
			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (NoSuchPaddingException e1) {
				e1.printStackTrace();
			} catch (InvalidAlgorithmParameterException e1) {
				e1.printStackTrace();
			} catch (IllegalBlockSizeException e1) {
				e1.printStackTrace();
			} catch (BadPaddingException e1) {
				e1.printStackTrace();
			}
		}
		
		// ** AES Encryption File
		if(buttonSelectFileToEncrypt == source) {
			
			jFileChooseFileToEncrypt = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jFileChooseFileToEncrypt.showOpenDialog(null);	
			this.selectedFileToEncrypt = jFileChooseFileToEncrypt.getSelectedFile();		
			jlabelSelectedFileToNcrypt.setText("Selected File: " + selectedFileToEncrypt.getName());
						
		}
		else if(buttonEncryptFile == source) {
			
			jFileChooseFileToSave = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int result = jFileChooseFileToSave.showSaveDialog(null);
			

			if(result == 0) {
				try {
					
					IvParameterSpec ivParameterSpec = aes.genrateInitializationVector();
					this.ivParameterSpec = ivParameterSpec;
					
					String secretKeyAsString = textFieldForEncryptionKeyForFile.getText();
					SecretKey secretKey = aes.converStringToSecretKey(secretKeyAsString);
					
					String pathToSelectedFileAsString = this.selectedFileToEncrypt.getAbsolutePath();
					String pathToSaveNewFile = jFileChooseFileToSave.getSelectedFile().getAbsolutePath();
					
					aes.encryptFile(pathToSelectedFileAsString, pathToSaveNewFile, secretKey, ivParameterSpec);
					
					System.out.println("Success!");
					
				} catch (InvalidKeyException e1) {
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					e1.printStackTrace();
				} catch (InvalidAlgorithmParameterException e1) {	
					e1.printStackTrace();
				} catch (IllegalBlockSizeException e1) {	
					e1.printStackTrace();
				} catch (BadPaddingException e1) {	
					e1.printStackTrace();
				} catch (IOException e1) {	
					e1.printStackTrace();
				}
			}
			
			
			
		}
		if(buttonGenerateAESsecretKeyForFile == source) {
			
			try {
				SecretKey secretKey = aes.generateKey(128);
				String secretKeyAsString = aes.convertSecretKeyToString(secretKey);
				textFieldForEncryptionKeyForFile.setText(secretKeyAsString);
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
		}
		else if(copyGeneratedAESsecretKeyForFile == source) {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			StringSelection stringSelection = new StringSelection(textFieldForEncryptionKeyForFile.getText());
			clipboard.setContents(stringSelection, null);
		}
		
		
		// ** AES Decryption File
		if(buttonSelectFileToDecrypt == source) {
			jFileChooseFileToDecrypt = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jFileChooseFileToDecrypt.showOpenDialog(null);	
			this.selectedFileToDecrypt = jFileChooseFileToDecrypt.getSelectedFile();		
			jlabelSelectedFileToDecrypt.setText("Selected File: " + selectedFileToDecrypt.getName());
		}
		else if(buttonDecryptFile == source) {
			
			jFileChooseFileToSaveAfterDecryption = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int result = jFileChooseFileToSaveAfterDecryption.showSaveDialog(null);
			
			if(result == 0) {
				try {
					System.out.println("1");
					String secretKeyAsString = textFieldForDecryptionKeyForFile.getText();
					SecretKey secretKey = aes.converStringToSecretKey(secretKeyAsString);
					
					String pathToSelectedFileAsString = this.selectedFileToDecrypt.getAbsolutePath();
					String pathToSaveNewFile = jFileChooseFileToSaveAfterDecryption.getSelectedFile().getAbsolutePath();
					
					System.out.println("2");
					aes.decryptFile(pathToSelectedFileAsString, pathToSaveNewFile, secretKey, this.ivParameterSpec);
					
					System.out.println("3");
				} catch (InvalidKeyException e1) {
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					e1.printStackTrace();
				} catch (InvalidAlgorithmParameterException e1) {
					e1.printStackTrace();
				} catch (IllegalBlockSizeException e1) {
					e1.printStackTrace();
				} catch (BadPaddingException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
	}
	
}
