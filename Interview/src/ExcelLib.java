	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;

	import org.apache.poi.ss.usermodel.CellStyle;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelLib 
	{
		public FileInputStream fis;
		public FileOutputStream fos;
		public XSSFWorkbook workbook;
		public XSSFSheet sheet;
		public XSSFRow row;
		public XSSFCell cell;
		public CellStyle style;   
		String path;
			
			ExcelLib(String path)
			{
				this.path=path;
			}
				
			public int getRowCount(String sheetName) throws IOException 
			{
				fis=new FileInputStream(path);
				workbook=new XSSFWorkbook(fis);
				sheet=workbook.getSheet(sheetName);
				int rowcount=sheet.getLastRowNum();
				workbook.close();
				fis.close();
				return rowcount;		
			}
			

			public int getCellCount(String sheetName,int rownum) throws IOException
			{
				fis=new FileInputStream(path);
				workbook=new XSSFWorkbook(fis);
				sheet=workbook.getSheet(sheetName);
				row=sheet.getRow(rownum);
				int cellcount=row.getLastCellNum();
				workbook.close();
				fis.close();
				return cellcount;
			}
			
			
			public String getCellData(String sheetName,int rownum,int colnum) throws IOException
			{
				fis=new FileInputStream(path);
				workbook=new XSSFWorkbook(fis);
				sheet=workbook.getSheet(sheetName);
				row=sheet.getRow(rownum);
				cell=row.getCell(colnum);
				
				DataFormatter formatter = new DataFormatter();
				String data;
				try{
				data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
				}
				catch(Exception e)
				{
					data="";
				}
				workbook.close();
				fis.close();
				return data;
			}
			
			public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
			{
				File xlfile=new File(path);
				if(!xlfile.exists())    // If file not exists then create new file
				{
				workbook=new XSSFWorkbook();
				fos=new FileOutputStream(path);
				workbook.write(fos);
				}
						
				fis=new FileInputStream(path);
				workbook=new XSSFWorkbook(fis);
					
				if(workbook.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet
					workbook.createSheet(sheetName);
				
				sheet=workbook.getSheet(sheetName);
							
				if(sheet.getRow(rownum)==null)   // If row not exists then create new Row
						sheet.createRow(rownum);
				row=sheet.getRow(rownum);
				
				cell=row.createCell(colnum);
				cell.setCellValue(data);
				fos=new FileOutputStream(path);
				workbook.write(fos);		
				workbook.close();
				fis.close();
				fos.close();
			
		}

	}


