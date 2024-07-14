package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author victortinoco
 */
public class ExcelUtils {

    public static void crearExcel(String nombre, List<String[]> data) throws FileNotFoundException, IOException {
        var wb = new XSSFWorkbook();

        XSSFSheet hoja = wb.createSheet();

        for (int i = 0; i < data.size(); i++) {
            XSSFRow fila = hoja.createRow(i);

            int celdaNum = 0;
            for (String item : data.get(i)) {
                XSSFCell celda = fila.createCell(celdaNum);

                celda.setCellValue(item);
                celdaNum++;
            }
        }

        try (var out = new FileOutputStream(nombre + ".xlsx")) {
            wb.write(out);
            out.close();
            wb.close();
        }
    }

    public static List<String[]> leerExcel(String nombre) throws InvalidFormatException, IOException {
        File archivo = new File(nombre.replace(Constantes.EXTENSION_XLSX, "") + Constantes.EXTENSION_XLSX);
        List<String[]> datos = new ArrayList<>();

        if (!archivo.exists()) {
            return datos;
        }

        var wb = new XSSFWorkbook(archivo);
        var hoja = wb.getSheetAt(0);

        // si la hoja tiene mas filas
        if (hoja != null && hoja.getLastRowNum() != -1) {
            var iteradorDeFila = hoja.rowIterator();

            // Si el iterador tiene un siguiente objeto
            while (iteradorDeFila.hasNext()) {
                var fila = iteradorDeFila.next();

                String[] datosFila = new String[fila.getLastCellNum()];
                var iteradorCelda = fila.cellIterator();

                int cellNum = 0;
                while (iteradorCelda.hasNext()) {
                    var celda = iteradorCelda.next();

                    var dataFormatter = new DataFormatter();
                    datosFila[cellNum] = dataFormatter.formatCellValue(celda);
                    cellNum++;
                }
                datos.add(datosFila);
            }
        }

        wb.close();

        return datos;
    }

}
