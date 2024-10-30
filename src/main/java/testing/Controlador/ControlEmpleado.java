package testing.Controlador;

//Librerias
import jakarta.validation.*;
import java.util.Arrays;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import testing.Servicio.*;
import testing.modelo.Reserva;
import testing.modelo.empleado;
import testing.modelo.Recompensas;


//Indica que la clase es un controlador 

@Controller
public class ControlEmpleado {

    //Inyecta los servicio de reserva, empleado y recompensa
    @Autowired
    private ReservaService reservaservice;

    @Autowired
    private empleadoService empleadoservice;
    
    @Autowired
    private RecompensasService recompensasservice;
    
    //Listas de roles y espacios disponibles
    List<String> listarol = Arrays.asList("Administrador","Empleado");
    List<String> listaespacio = Arrays.asList("A1","A2","A3","A4","B1","B2","B3","B4");
    
    //CRUD para los empleados
    
    
    
    @GetMapping("/nuevoemp")
    public String NuevoEmp(Model modelo){
        //Se crea un nuevo objeto "emp", lo agrega al modelo junto con la lista de roles y retorna la vista "nuevoempleado"
        empleado emp = new empleado();
        modelo.addAttribute("empleado", emp);
        modelo.addAttribute("roles", listarol);
        return "nuevoempleado";
    }

    @GetMapping("/guardaremp")
    public String GuardarEmp(@Valid @ModelAttribute("empleado") empleado empl, BindingResult resultado, Model modelo){
        //Valida el objeto empleado si hay errores retorna a nuevoempleado, si no, te retorna al dashboard
        modelo.addAttribute("roles", listarol);
        if(resultado.hasErrors()){
            return "nuevoempleado";
        }
        
        empleadoservice.save(empl);
        return "redirect:/api/dashboard";
    }
    
    
    @GetMapping("/empleado/editar/{id}")
    public String EditarEmp(@PathVariable Integer id,Model modelo){
        //Método para llamar a un empleado a traves de su id
        modelo.addAttribute("empleado",empleadoservice.get(id));
        modelo.addAttribute("roles",listarol);
        return "editarempleado";
    }

    @GetMapping("/empleado/actualizar/{id}")
    public String ActualizarEmp(@PathVariable Integer id,@ModelAttribute("empleado") empleado empleado){
        //Método para actualizar a un empleado que sera llamado a traves de su id
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
    public String EliminarEmp(@PathVariable Integer id){
        //Método para eliminar un empleado que sera llamado a traves de su id
        empleadoservice.delete(id);
        return "redirect:/api/dashboard";
    }

    // CRUD para la reserva
    @GetMapping("/nuevares")
    public String NuevoRes(Model modelo){
        //Se crea un nuevo objeto "res", lo agrega al modelo junto con la lista de roles y retorna la vista "nuevareserva"
        Reserva res = new Reserva();
        modelo.addAttribute("reserva", res);
        modelo.addAttribute("espacio", listaespacio);
        return "nuevareserva";
    }

    @GetMapping("/guardarres")
    public String GuardarRes(@ModelAttribute("reserva") Reserva res){
        //Valida el objeto reserva, si es asi te lleva la dashboard
        reservaservice.save(res);
        return "redirect:/api/dashboard";
    }

    @GetMapping("/reserva/editar/{ID}")
    public String EditarRes(@PathVariable Integer ID,Model modelo){
        //Método para llamar a una reserva a traves de su id
        modelo.addAttribute("reserva",reservaservice.get(ID));
        modelo.addAttribute("espacio", listaespacio);
        return "editarreserva";
    }

    @GetMapping("/reserva/actualizar/{ID}")
    public String ActualizarRes(@PathVariable Integer ID,@ModelAttribute("reserva") Reserva reserva){
        //Método para actualizar a una reserva que sera llamado a traves de su id

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
    public String EliminarRes(@PathVariable Integer ID){
        //Método para eliminar una reserva a traves de su id
        reservaservice.delete(ID);
        return "redirect:/api/dashboard";
    }

    @GetMapping("/dashboard")
    public String get(Model modelo) {
        modelo.addAttribute("lista", empleadoservice.get());
        modelo.addAttribute("listareserva", reservaservice.get());
        modelo.addAttribute("listarecompensas",recompensasservice.get());
        return "/api/dashboard";
    }

    // Método para generar el reporte de empleados en Excel y almacenarlo
    @GetMapping("/api/reporte/empleados/excel")
    public ResponseEntity<byte[]> generarYGuardarReporteEmpleados() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reporte de Empleados");
            List<empleado> empleados = empleadoservice.get(); // Asegúrate de que la clase se llame "Empleado"

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
            titleCell.setCellValue("Reporte de Empleados");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6)); // Ajusta el número de columnas según sea necesario

            // Crear estilo para encabezados
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            // Encabezados de columnas
            Row headerRow = sheet.createRow(1);
            String[] headers = {"ID", "Nombre", "Apellido", "DNI", "Teléfono", "Email", "Rol"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Estilo para datos con color morado claro
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);
            dataStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex()); // Cambiar a morado claro
            dataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Rellenar datos de empleados
            int rowNum = 2;
            for (empleado emp : empleados) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = row.createCell(i);
                    switch (i) {
                        case 0:
                            cell.setCellValue(emp.getId());
                            break;
                        case 1:
                            cell.setCellValue(emp.getNombre_empl());
                            break;
                        case 2:
                            cell.setCellValue(emp.getApellido_empl());
                            break;
                        case 3:
                            cell.setCellValue(emp.getDni());
                            break;
                        case 4:
                            cell.setCellValue(emp.getTelefono());
                            break;
                        case 5:
                            cell.setCellValue(emp.getEmail());
                            break;
                        case 6:
                            cell.setCellValue(emp.getRol());
                            break;
                    }
                    cell.setCellStyle(dataStyle); // Aplicar estilo de datos a cada celda
                }
            }

            // Ajustar ancho de columnas automáticamente
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Guardar el archivo en una carpeta organizada por fecha
            String fechaActual = LocalDate.now().toString();
            String nombreArchivo = "reporte_empleados_" + fechaActual + ".xlsx";
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

  
    
    //CRUD para las recompensas
    @GetMapping("/nuevarecom")
    public String NuevaRecom(Model modelo){
        //Se crea un nuevo objeto "rec", retorna la vista "nuevareserva"
        Recompensas rec = new Recompensas();
        modelo.addAttribute("recompensas", rec);
        return "nuevarecompensa";
    }
    
    
    @GetMapping("/guardarrecom")
    public String GuardarRec(@ModelAttribute("recompensas") Recompensas rec){
        //Valida el objeto recompensas, si es asi te lleva la dashboard
        recompensasservice.save(rec);
        return "redirect:/api/dashboard";
    }
    
    
    @GetMapping("/recompensas/editar/{ID_recom}")
    public String EditarRec(@PathVariable Integer ID_recom,Model modelo){
        //Método para llamar a una recompensa a traves de su id
        modelo.addAttribute("recompensas", recompensasservice.get(ID_recom));
        return "editarrecompensa";
    }
    
    @GetMapping("/recompensas/actualizar/{ID_recom}")
    public String ActualizarRec(@PathVariable Integer ID_recom,@ModelAttribute("recompensas") Recompensas recompensas){
        //Método para actualizar a una recompensas que sera llamado a traves de su id
        Recompensas actual = recompensasservice.get(ID_recom);
        actual.setID_recom(ID_recom);
        actual.setNom_recom(recompensas.getNom_recom());
        actual.setDescri_recom(recompensas.getDescri_recom());
        actual.setImagen(recompensas.getImagen());
        actual.setPuntos_necesarios(recompensas.getPuntos_necesarios());
        actual.setActivo(recompensas.isActivo());
        recompensasservice.update(actual);
        return "redirect:/api/dashboard";
    }
    
    @GetMapping("/recompensas/eliminar/{ID_recom}")
    public String EliminarRec(@PathVariable Integer ID_recom){
        //Método para eliminar una recompensas a traves de su id
        recompensasservice.delete(ID_recom);
        return "redirect:/api/dashboard";
    }
    
}
