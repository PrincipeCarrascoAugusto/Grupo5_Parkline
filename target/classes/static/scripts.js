// Inicializar la lista de autos como vacía
let autosData = [];

// Función para mostrar las diferentes secciones del dashboard
function mostrarSeccion(seccion) {
    // Ocultar todas las secciones
    document.querySelectorAll('.seccion').forEach(s => s.style.display = 'none');
    // Mostrar la sección seleccionada
    document.getElementById(seccion).style.display = 'block';
}

//Funcion para la busqueda por placa en el dashboard
function buscarPorPlaca() {
    // Obtener el valor del campo de búsqueda
    let input = document.getElementById("buscarAuto").value.toUpperCase();
    let table = document.getElementById("reservas-body");
    let rows = table.getElementsByTagName("tr");

    // Iterar sobre todas las filas de la tabla
    for (let i = 0; i < rows.length; i++) {
        // Obtener la tercera columna (Placa)
        let placa = rows[i].getElementsByTagName("td")[2];
        if (placa) {
            let txtValue = placa.textContent || placa.innerText;
            if (txtValue.toUpperCase().indexOf(input) > -1) {
                // Mostrar la fila si la placa coincide con el filtro
                rows[i].style.display = "";
            } else {
                // Ocultar la fila si la placa no coincide
                rows[i].style.display = "none";
            }
        }
    }
}

function buscarPorNombre() {
    // Obtener el valor del campo de búsqueda y convertirlo a minúsculas
    let input = document.getElementById("buscarEmpleado").value.toLowerCase();
    let table = document.getElementById("empleados-body");
    let rows = table.getElementsByTagName("tr");

    // Iterar sobre todas las filas de la tabla
    for (let i = 0; i < rows.length; i++) {
        // Obtener la segunda columna (Nombre del empleado)
        let nombre = rows[i].getElementsByTagName("td")[1]; // Suponiendo que el nombre está en la segunda columna (índice 1)
        if (nombre) {
            let txtValue = nombre.textContent || nombre.innerText;
            // Comparar el texto del nombre con el valor de entrada
            if (txtValue.toLowerCase().indexOf(input) > -1) {
                // Mostrar la fila si el nombre coincide con el filtro
                rows[i].style.display = "";
            } else {
                // Ocultar la fila si el nombre no coincide
                rows[i].style.display = "none";
            }
        }
    }
}

