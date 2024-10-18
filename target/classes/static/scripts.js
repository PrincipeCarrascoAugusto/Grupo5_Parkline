// Inicializar la lista de autos como vacía
let autosData = [];

// Función para mostrar las diferentes secciones del dashboard
function mostrarSeccion(seccion) {
    // Ocultar todas las secciones
    document.querySelectorAll('.seccion').forEach(s => s.style.display = 'none');
    // Mostrar la sección seleccionada
    document.getElementById(seccion).style.display = 'block';
}

// Función para mostrar los autos en la tabla
function mostrarAutos(autos = autosData) {
    const tbody = document.getElementById('autos-body');
    tbody.innerHTML = ''; // Limpiar el contenido actual

    autos.forEach(auto => {
        const row = `
            <tr>
                <td>${auto.id}</td>
                <td>${auto.placa}</td>
                <td>${auto.horaEntrada}</td>
                <td>${auto.espacio}</td>
                <td>
                    <button class="btn" onclick="modificarAuto(${auto.id})">Modificar</button>
                    <button class="btn delete" onclick="eliminarAuto(${auto.id})">Eliminar</button>
                </td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

// Función para agregar un nuevo auto
function agregarAuto() {
    const nuevoId = autosData.length + 1;
    const nuevaPlaca = prompt('Ingrese la placa del auto:');
    const nuevaHoraEntrada = prompt('Ingrese la hora de entrada:');
    const nuevoEspacio = prompt('Ingrese el espacio asignado:');

    if (nuevaPlaca && nuevaHoraEntrada && nuevoEspacio) {
        autosData.push({ id: nuevoId, placa: nuevaPlaca, horaEntrada: nuevaHoraEntrada, espacio: nuevoEspacio });
        mostrarAutos(); // Refrescar la tabla
    } else {
        alert('Todos los campos son obligatorios.');
    }
}

// Función para modificar un auto existente
function modificarAuto(id) {
    const auto = autosData.find(a => a.id === id);
    if (auto) {
        const nuevaPlaca = prompt('Modifique la placa del auto:', auto.placa);
        const nuevaHoraEntrada = prompt('Modifique la hora de entrada:', auto.horaEntrada);
        const nuevoEspacio = prompt('Modifique el espacio asignado:', auto.espacio);

        if (nuevaPlaca && nuevaHoraEntrada && nuevoEspacio) {
            auto.placa = nuevaPlaca;
            auto.horaEntrada = nuevaHoraEntrada;
            auto.espacio = nuevoEspacio;
            mostrarAutos(); // Refrescar la tabla
        } else {
            alert('Todos los campos son obligatorios.');
        }
    } else {
        alert('Auto no encontrado.');
    }
}

// Función para eliminar un auto
function eliminarAuto(id) {
    const confirmacion = confirm('¿Está seguro de que desea eliminar este auto?');
    if (confirmacion) {
        autosData = autosData.filter(auto => auto.id !== id);
        mostrarAutos(); // Refrescar la tabla
    }
}

// Función para buscar autos
function buscarAutos() {
    const searchInput = document.getElementById('buscarAutos').value.toLowerCase();
    const autosFiltrados = autosData.filter(auto => auto.placa.toLowerCase().includes(searchInput));
    mostrarAutos(autosFiltrados); // Mostrar autos filtrados
}

// Inicializar la tabla de autos al cargar la página
document.addEventListener('DOMContentLoaded', () => {
    mostrarAutos(); // Mostrar la tabla vacía al cargar
});
