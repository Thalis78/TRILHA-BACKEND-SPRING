document.addEventListener('DOMContentLoaded', function () {
    const passwordField = document.getElementById('password');
    const toggleButton = document.querySelector('.password-toggle');
    const eyeIcon = toggleButton.querySelector('.eye-icon');

    toggleButton.addEventListener('click', function () {
        const isPassword = passwordField.type === 'password';
        passwordField.type = isPassword ? 'text' : 'password';

        eyeIcon.src = isPassword ? 'static/icons/eye.svg' : 'static/icons/eye-off.svg';
    });
});
function confirmarExclusao() {
    window.location.href = '/confirmacao';
}