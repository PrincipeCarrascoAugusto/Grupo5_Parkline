package testing.Controlador;

import com.google.common.base.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
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
    public String GuadarRes(@ModelAttribute("reserva") Reserva res, Model modelo) {
        try {
            // Validaciones utilizando Preconditions
            Preconditions.checkArgument(res.getUsuario() != null && !res.getUsuario().trim().isEmpty(), "El nombre de usuario es obligatorio");
            Preconditions.checkArgument(res.getPlaca() != null && !res.getPlaca().trim().isEmpty(), "La placa del vehículo es obligatoria");
            Preconditions.checkArgument(res.getHora_entrada().isBefore(res.getHora_salida()), "La hora de entrada debe ser anterior a la hora de salida");        
            Preconditions.checkArgument(res.getFecha() != null, "La fecha es obligatoria");
            // Guarda el objeto Reserva en la BD
            resService.save(res);
            return "redirect:/parkline"; // Redirige a la página principal después de guardar
        } catch (IllegalArgumentException e) {
            // Agrega un mensaje de error al modelo
            modelo.addAttribute("error", e.getMessage());
            modelo.addAttribute("espacio", listaespacio);
            modelo.addAttribute("reserva", res); // Mantiene los datos del formulario
            return "Reservas"; // Retorna a la vista de index para que el usuario vea el mensaje de error
        }
    }

    @PostMapping("/guardar")
    public String handleReserva(@ModelAttribute("reserva") Reserva nuevaReserva, Model modelo) {
        try {
            // Validaciones utilizando Preconditions
            Preconditions.checkArgument(nuevaReserva.getUsuario() != null && !nuevaReserva.getUsuario().trim().isEmpty(), "El nombre de usuario es obligatorio");
            Preconditions.checkArgument(nuevaReserva.getPlaca() != null && !nuevaReserva.getPlaca().trim().isEmpty(), "La placa del vehículo es obligatoria");
            Preconditions.checkArgument(nuevaReserva.getHora_entrada().isBefore(nuevaReserva.getHora_salida()), "La hora de entrada debe ser anterior a la hora de salida");
            Preconditions.checkArgument(nuevaReserva.getFecha() != null, "La fecha es obligatoria");
            // Guarda la nueva reserva utilizando el servicio
            resService.save(nuevaReserva);
            return "redirect:/api/dashboard"; // Redirige al dashboard después de guardar
        } catch (IllegalArgumentException e) {
            // Agrega un mensaje de error al modelo
            modelo.addAttribute("error", e.getMessage());
            // Retorna a la vista de reservas para que el usuario vea el mensaje de error
            modelo.addAttribute("espacio", listaespacio);
            modelo.addAttribute("reserva", nuevaReserva); // Mantiene los datos del formulario
            return "nuevareserva"; // Retorna a la vista de reservas
        }
    }

    // Método para generar el reporte de reservas en Excel y almacenarlo
    @GetMapping("/api/reporte/excel")
    public ResponseEntity<byte[]> generarYGuardarReporteExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reporte de Reservas");
            List<Reserva> reservas = resService.get();

            // Crear estilo para el título
            CellStyle titleStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleFont.setColor(IndexedColors.WHITE.getIndex());
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
            titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

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
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            // Estilo para bordes
            BorderStyle borderStyle = BorderStyle.THIN;
            headerStyle.setBorderBottom(borderStyle);
            headerStyle.setBorderTop(borderStyle);
            headerStyle.setBorderLeft(borderStyle);
            headerStyle.setBorderRight(borderStyle);

            // Encabezados de columnas
            Row headerRow = sheet.createRow(1);
            String[] headers = {"ID", "Usuario", "Placa", "Hora Entrada", "Hora Salida", "Espacio", "Pago", "Fecha"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Estilo para datos (morado claro)
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            dataStyle.setWrapText(true);
            dataStyle.setBorderBottom(borderStyle);
            dataStyle.setBorderTop(borderStyle);
            dataStyle.setBorderLeft(borderStyle);
            dataStyle.setBorderRight(borderStyle);
            dataStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex()); // Cambiar a un tono morado claro
            dataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

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

            // Guardar el archivo en una carpeta organizada por fecha
            String fechaActual = LocalDate.now().toString();
            String nombreArchivo = "reporte_reservas_" + fechaActual + ".xlsx";
            String rutaCarpeta = "reportes/" + fechaActual;
            File directorio = new File(rutaCarpeta);

            // Crear el directorio si no existe
            if (!directorio.exists()) {
                FileUtils.forceMkdir(directorio);
            }

            // Guardar el archivo en el directorio de reportes
            File archivoReporte = new File(directorio, nombreArchivo);
            try (FileOutputStream fileOut = new FileOutputStream(archivoReporte)) {
                workbook.write(fileOut);
            }

            // Convertir a arreglo de bytes para ofrecer como descarga
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] excelBytes = outputStream.toByteArray();

            // Configuración de headers HTTP para descarga
            HttpHeaders headersHttp = new HttpHeaders();
            headersHttp.add("Content-Disposition", "attachment; filename=" + nombreArchivo);

            return new ResponseEntity<>(excelBytes, headersHttp, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
