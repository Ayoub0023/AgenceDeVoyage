// document.addEventListener('DOMContentLoaded', function() {
//     // Gestion du toggle password
//     const togglePasswords = document.querySelectorAll('.toggle-password');
//     togglePasswords.forEach(togglePassword => {
//         togglePassword.addEventListener('click', function() {
//             const input = this.previousElementSibling;
//             const type = input.getAttribute('type') === 'password' ? 'text' : 'password';
//             input.setAttribute('type', type);
//             this.classList.toggle('fa-eye');
//             this.classList.toggle('fa-eye-slash');
//         });
//     });
//
//     // Gestion du formulaire d'inscription
//     const registerForm = document.querySelector('#registerForm');
//     registerForm.addEventListener('submit', function(e) {
//         e.preventDefault();
//
//         const username = document.querySelector('#username').value;
//         const email = document.querySelector('#email').value;
//         const password = document.querySelector('#password').value;
//         const confirmPassword = document.querySelector('#confirmPassword').value;
//
//         if (password !== confirmPassword) {
//             alert('Les mots de passe ne correspondent pas.');
//             return;
//         }
//
//         // Si validation réussie, soumettre le formulaire
//         this.submit();
//     });
// });
// register.js
// document.addEventListener('DOMContentLoaded', function() {
//     // Toggle password visibility
//     const togglePassword = document.querySelector('.toggle-password');
//     const passwordInput = document.querySelector('input[type="password"]');
//
//     if (togglePassword && passwordInput) {
//         togglePassword.addEventListener('click', function() {
//             const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
//             passwordInput.setAttribute('type', type);
//             this.classList.toggle('fa-eye');
//             this.classList.toggle('fa-eye-slash');
//         });
//     }
//
//     // Form validation
//     const form = document.querySelector('.register-form');
//     form.addEventListener('submit', function(e) {
//         const password = passwordInput.value;
//
//         // Exemple de validation basique du mot de passe
//         if (password.length < 6) {
//             e.preventDefault();
//             alert('Le mot de passe doit contenir au moins 6 caractères');
//             return;
//         }
//     });
// });
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('signup-form');

    // Fonction pour basculer la visibilité du mot de passe
    window.togglePassword = function(inputId) {
        const input = document.getElementById(inputId);
        const icon = input.nextElementSibling;

        if (input.type === 'password') {
            input.type = 'text';
            icon.classList.remove('fa-eye');
            icon.classList.add('fa-eye-slash');
        } else {
            input.type = 'password';
            icon.classList.remove('fa-eye-slash');
            icon.classList.add('fa-eye');
        }
    };

    if (form) {
        form.addEventListener('submit', function(e) {
            e.preventDefault();

            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirm-password').value;

            // Validation du mot de passe
            if (password.length < 6) {
                alert('Le mot de passe doit contenir au moins 6 caractères');
                return;
            }

            // Vérification que les mots de passe correspondent
            if (password !== confirmPassword) {
                alert('Les mots de passe ne correspondent pas');
                return;
            }

            // Si tout est valide, soumettre le formulaire
            form.submit();
        });
    }
});