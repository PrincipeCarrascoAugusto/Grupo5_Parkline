package testing.Controlador;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import testing.Servicio.ReservaService;
import testing.modelo.Reserva;

//Indica que la clase es un controlador
@Controller
public class ControlReservas {
    
    //Inyecta el servicio reserva
    @Autowired
    private ReservaService resService;
    //Lista de espacios
    List<String> listaespacio = Arrays.asList("A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4");

    @GetMapping("/reservas")
    public String mostrarReservas(Model modelo) {
        //Crea un nuevo objeto reserva, trae las lissta de espacio y retorna a Reservas
        Reserva res = new Reserva();
        modelo.addAttribute("reserva", res);
        modelo.addAttribute("espacio", listaespacio);
        return "Reservas";
    }
    
    @GetMapping("/guarres")
    public String GuadarRes(@ModelAttribute("reserva") Reserva res){
        //Guarda el objeto Reserva en la BD y retorna a pago
        resService.save(res);
        return "pago";
    }
    
    @PostMapping("/api/reserva")
    public String handleReserva(@ModelAttribute("reserva") Reserva nuevaReserva) {
        
        resService.save(nuevaReserva); // Guarda la nueva reserva utilizando el servicio
        return "redirect/api/dashboard"; // Redirige al dashboard después de guardar
    }
    
    // Método para generar el reporte en Excel con estilos
    @GetMapping("api/reporte/excel")
    public ResponseEntity<byte[]> generarReporteExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reporte de Reservas");
            List<Reserva> reservas = resService.get();

            // Crear estilo para el título
            CellStyle titleStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);

            // Título del reporte
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Reporte de Reservas");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Crear estilo para encabezados
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            // Encabezados de columnas
            Row headerRow = sheet.createRow(1);
            String[] headers = {"ID", "Usuario", "Placa", "Hora Entrada", "Hora Salida", "Espacio", "Pago", "Fecha"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Estilo para datos
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Rellenar datos de reservas
            int rowNum = 2;
            for (Reserva reserva : reservas) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(reserva.getID());
                row.createCell(1).setCellValue(reserva.getUsuario());
                row.createCell(2).setCellValue(reserva.getPlaca());
                row.createCell(3).setCellValue(reserva.getHora_entrada().toString());
                row.createCell(4).setCellValue(reserva.getHora_salida().toString());
                row.createCell(5).setCellValue(reserva.getEspacio());
                row.createCell(6).setCellValue(reserva.getPago());
                row.createCell(7).setCellValue(reserva.getFecha().toString());
                
                // Aplicar estilo de datos a cada celda
                for (int i = 0; i < headers.length; i++) {
                    row.getCell(i).setCellStyle(dataStyle);
                }
            }

            // Ajustar ancho de columnas automáticamente
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Convertir a arreglo de bytes para descargar
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] excelBytes = outputStream.toByteArray();

            // Configuración de headers HTTP para descarga
            HttpHeaders headersHttp = new HttpHeaders();
            headersHttp.add("Content-Disposition", "attachment; filename=reporte_reservas.xlsx");

            return new ResponseEntity<>(excelBytes, headersHttp, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
