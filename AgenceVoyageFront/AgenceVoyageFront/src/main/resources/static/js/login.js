document.addEventListener('DOMContentLoaded', function() {
    // Gestion du toggle password
    const togglePassword = document.querySelector('.toggle-password');
    const passwordInput = document.querySelector('#password');

    togglePassword.addEventListener('click', function() {
        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordInput.setAttribute('type', type);
        this.classList.toggle('fa-eye');
        this.classList.toggle('fa-eye-slash');
    });

    // Animation des inputs
    const inputs = document.querySelectorAll('input');
    inputs.forEach(input => {
        input.addEventListener('focus', function() {
            this.parentElement.classList.add('focused');
        });

        input.addEventListener('blur', function() {
            if (!this.value) {
                this.parentElement.classList.remove('focused');
            }
        });
    });

    // Gestion du formulaire
    const loginForm = document.querySelector('#loginForm');
    loginForm.addEventListener('submit', function(e) {
        e.preventDefault();

        // Récupération des valeurs
        const email = document.querySelector('#email').value;
        const password = document.querySelector('#password').value;

        // Ici vous pouvez ajouter votre logique de validation
        // et l'envoi vers votre backend

        // Exemple de validation basique
        if (email && password) {
            // Animation du bouton pendant la soumission
            const submitBtn = this.querySelector('.login-btn');
            submitBtn.textContent = 'Connexion en cours...';
            submitBtn.disabled = true;

            // Soumission du formulaire après validation
            this.submit();
        }
    });

    // Animation smooth pour les liens
    document.querySelectorAll('a').forEach(anchor => {
        anchor.addEventListener('click', function(e) {
            const href = this.getAttribute('href');
            if (href.startsWith('#')) {
                e.preventDefault();
                document.querySelector(href).scrollIntoView({
                    behavior: 'smooth'
                });
            }
        });
    });
});