const signInContainer = document.querySelector('.sign-in');
const signUpContainer = document.querySelector('.sign-up');
const container = document.getElementById('container');
const showSignUpButton = document.getElementById('show-signup');
const showLoginButton = document.getElementById('show-login');

// Muestra el formulario de inicio de sesión al cargar la página
document.addEventListener('DOMContentLoaded', () => {
    signUpContainer.classList.add('hidden'); // Oculta el registro al inicio
});

showSignUpButton.addEventListener('click', () => {
    container.classList.add('active');
    signInContainer.classList.add('hidden');
    signUpContainer.classList.remove('hidden');
});

showLoginButton.addEventListener('click', () => {
    container.classList.remove('active');
    signUpContainer.classList.add('hidden');
    signInContainer.classList.remove('hidden');
});

    

//Funcion para darle animacion a la palabra "ParkLine"
const companyName = "ParkLine";
const companyNameElement = document.getElementById("company-name");
let index = 0;
let direction = 1; // 1 para escribir, -1 para borrar

function typeText() {
    if (direction === 1 && index <= companyName.length) {
        companyNameElement.textContent = companyName.substring(0, index);
        index++;
    } else if (direction === -1 && index >= 0) {
        companyNameElement.textContent = companyName.substring(0, index);
        index--;
    }

    if (index === companyName.length) {
        setTimeout(() => {
            direction = -1; // Comienza a borrar
        }, 1000); // Pausa antes de empezar a borrar
    } else if (index === 0) {
        direction = 1; // Comienza a escribir nuevamente
    }

    setTimeout(typeText, 150); // Velocidad de escritura
}
typeText();


