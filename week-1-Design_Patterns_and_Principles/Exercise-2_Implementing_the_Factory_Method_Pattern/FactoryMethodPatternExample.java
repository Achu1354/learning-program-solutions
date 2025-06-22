import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.*;
interface Document {
    void open();
}

class WordDocument implements Document {
    public void open() {
        openFile("sample.docx");
    }

    private void openFile(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
                System.out.println("Opened Word document.");
            } else {
                System.out.println("Word file not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class PdfDocument implements Document {
    public void open() {
        openFile("sample.pdf");
    }

    private void openFile(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
                System.out.println("Opened PDF document.");
            } else {
                System.out.println("PDF file not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ExcelDocument implements Document {
    public void open() {
        openFile("sample.xlsx");
    }

    private void openFile(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
                System.out.println("Opened Excel document.");
            } else {
                System.out.println("Excel file not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

abstract class DocumentFactory {
    public abstract Document createDocument();
}

class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new ExcelDocument();
    }
}

public class FactoryMethodPatternExample {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		while(true) {
			
		System.out.println("Select:");
		System.out.println("1.Open Word Document");
		System.out.println("2.Open PDF Document");
		System.out.println("3.Open Excel Document");
		int x=sc.nextInt();
		switch(x) {
		case 1:
        DocumentFactory word= new WordDocumentFactory();
        Document wordDoc = word.createDocument();
        wordDoc.open();
        break;
        
		case 2:
        DocumentFactory pdf = new PdfDocumentFactory();
        Document pdfDoc = pdf.createDocument();
        pdfDoc.open();
        break;

		case 3:
        DocumentFactory excel = new ExcelDocumentFactory();
        Document excelDoc = excel.createDocument();
        excelDoc.open();
        break;
		}
		}
    }
}
