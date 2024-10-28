package testing.Controlador;

// Librerías
import java.util.Arrays;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
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
import org.springframework.web.bind.annotation.PathVariable;
import testing.Servicio.ReservaService;
import testing.Servicio.empleadoService;
import testing.modelo.Reserva;
import testing.modelo.empleado;

@Controller
public class ControlEmpleado {

    @Autowired
    private ReservaService reservaservice;

    @Autowired
    private empleadoService empleadoservice;

    List<String> listarol = Arrays.asList("Administrador", "Empleado");
    List<String> listaespacio = Arrays.asList("A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4");

    // CRUD para los empleados
    @GetMapping("/nuevoemp")
    public String NuevoEmp(Model modelo) {
        empleado emp = new empleado();
        modelo.addAttribute("empleado", emp);
        modelo.addAttribute("roles", listarol);
        return "nuevoempleado";
    }

    @GetMapping("/guardaremp")
    public String GuardarEmp(@ModelAttribute("empleado") empleado empl) {
        empleadoservice.save(empl);
        return "redirect:/api/dashboard";
    }

    @GetMapping("/empleado/editar/{id}")
    public String EditarEmp(@PathVariable Integer id, Model modelo) {
        modelo.addAttribute("empleado", empleadoservice.get(id));
        modelo.addAttribute("roles", listarol);
        return "editarempleado";
    }

    @GetMapping("/empleado/actualizar/{id}")
    public String ActualizarEmp(@PathVariable Integer id, @ModelAttribute("empleado") empleado empleado) {
        empleado actual = empleadoservice.get(id);
        actual.setId(id);
        actual.setNombre_empl(empleado.getNombre_empl());
        actual.setApellido_empl(empleado.getApellido_empl());
        actual.setDni(empleado.getDni());
        actual.setTelefono(empleado.getTelefono());
        actual.setEmail(empleado.getEmail());
        actual.setRol(empleado.getRol());
        actual.setContrasena(empleado.getContrasena());
        empleadoservice.update(actual);
        return "redirect:/api/dashboard";
    }

    @GetMapping("/empleado/eliminar/{id}")
    public String EliminarEmp(@PathVariable Integer id) {
        empleadoservice.delete(id);
        return "redirect:/api/dashboard";
    }

    // CRUD para la reserva
    @GetMapping("/nuevares")
    public String NuevoRes(Model modelo) {
        Reserva res = new Reserva();
        modelo.addAttribute("reserva", res);
        modelo.addAttribute("espacio", listaespacio);
        return "nuevareserva";
    }

    @GetMapping("/guardarres")
    public String GuadarRes(@ModelAttribute("reserva") Reserva res) {
        reservaservice.save(res);
        return "redirect:/api/dashboard";
    }

    @GetMapping("/reserva/editar/{ID}")
    public String EditarRes(@PathVariable Integer ID, Model modelo) {
        modelo.addAttribute("reserva", reservaservice.get(ID));
        modelo.addAttribute("espacio", listaespacio);
        return "editarreserva";
    }

    @GetMapping("/reserva/actualizar/{ID}")
    public String ActualizarRes(@PathVariable Integer ID, @ModelAttribute("reserva") Reserva reserva) {
        Reserva actual = reservaservice.get(ID);
        actual.setID(ID);
        actual.setUsuario(reserva.getUsuario());
        actual.setPlaca(reserva.getPlaca());
        actual.setHora_entrada(reserva.getHora_entrada());
        actual.setHora_salida(reserva.getHora_salida());
        actual.setEspacio(reserva.getEspacio());
        actual.setPago(reserva.getPago());
        actual.setFecha(reserva.getFecha());
        reservaservice.update(actual);
        return "redirect:/api/dashboard";
    }

    @GetMapping("/reserva/eliminar/{ID}")
    public String EliminarRes(@PathVariable Integer ID) {
        reservaservice.delete(ID);
        return "redirect:/api/dashboard";
    }

    @GetMapping("/dashboard")
    public String get(Model modelo) {
        modelo.addAttribute("lista", empleadoservice.get());
        modelo.addAttribute("listareserva", reservaservice.get());
        return "/api/dashboard";
    }

    // Método para generar el reporte en Excel de empleados
    @GetMapping("/api/reporte/empleados/excel")
    public ResponseEntity<byte[]> generarReporteEmpleados() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reporte de Empleados");
            List<empleado> empleados = empleadoservice.get();

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
            titleCell.setCellValue("Reporte de Empleados");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6)); // Ajusta el número de columnas según sea necesario

            // Crear estilo para encabezados
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            // Encabezados de columnas
            Row headerRow = sheet.createRow(1);
            String[] headers = {"ID", "Nombre", "Apellido", "DNI", "Teléfono", "Email", "Rol"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Rellenar datos de empleados
            int rowNum = 2;
            for (empleado emp : empleados) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(emp.getId());
                row.createCell(1).setCellValue(emp.getNombre_empl());
                row.createCell(2).setCellValue(emp.getApellido_empl());
                row.createCell(3).setCellValue(emp.getDni());
                row.createCell(4).setCellValue(emp.getTelefono());
                row.createCell(5).setCellValue(emp.getEmail());
                row.createCell(6).setCellValue(emp.getRol());
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
            headersHttp.add("Content-Disposition", "attachment; filename=reporte_empleados.xlsx");

            return new ResponseEntity<>(excelBytes, headersHttp, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
