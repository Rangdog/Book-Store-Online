// HANDLING BUTTON.
const login_form = document.getElementById('login-form');
const btn_register = document.getElementById('btn-register');
const register_form = document.getElementById('register-form');
const register_login = document.getElementById('register-login');
const foget_password = document.getElementById('foget-password');
const restore_form = document.getElementById('restore-form');
const btn_confirm = document.getElementById('btn-confirm');
const btn_back = document.getElementById('btn-back');
const confirm_form = document.getElementById('confirm-form');
const btn_reset_password = document.getElementById('btn-reset-password');

btn_register.addEventListener('click', () => {
    login_form.classList.remove('active');
    login_form.classList.add('hide');
    register_form.classList.remove('hide');
    register_form.classList.add('active');
});

register_login.addEventListener('click', () => {
    register_form.classList.remove('active');
    register_form.classList.add('hide');
    login_form.classList.remove('hide');
    login_form.classList.add('active');
});

foget_password.addEventListener('click', () => {
    login_form.classList.remove('active');
    login_form.classList.add('hide');
    restore_form.classList.remove('hide');
    restore_form.classList.add('active');
});

/*btn_confirm.addEventListener('click', () => {
    restore_form.classList.remove('active');
    restore_form.classList.add('hide');
    confirm_form.classList.remove('hide');
    confirm_form.classList.add('active');
});*/

btn_back.addEventListener('click', () => {
    restore_form.classList.remove('active');
    restore_form.classList.add('hide');
    login_form.classList.remove('hide');
    login_form.classList.add('active');
});

/*btn_reset_password.addEventListener('click', () => {
    confirm_form.classList.remove('active');
    confirm_form.classList.add('hide');
    login_form.classList.add('active');
});*/
/*TOAST MESSAGE*/
function toast({ title = "", message = "", type = "info", duration = 3000 }) {
  const main = document.getElementById("toast");
  if (main) {
    const toast = document.createElement("div");

    // Auto remove toast
    const autoRemoveId = setTimeout(function () {
      main.removeChild(toast);
    }, duration + 1000);

    // Remove toast when clicked
    toast.onclick = function (e) {
      if (e.target.closest(".toast__close")) {
        main.removeChild(toast);
        clearTimeout(autoRemoveId);
      }
    };

    const icons = {
      success: "checkmark-outline",
      info: "alert-circle-outline",
      warning: "alert-circle-outline",
      error: "alert-circle-outline"
    };
    const icon = icons[type];
    const delay = (duration / 1000).toFixed(2);

    toast.classList.add("toast", `toast--${type}`);
    toast.style.animation = `slideInLeft ease .3s, fadeOut linear 1s ${delay}s forwards`;

    toast.innerHTML = `
                    <div class="toast__icon">
                       <ion-icon name="${icon}"></ion-icon>
                    </div>
                    <div class="toast__body">
                        <h3 class="toast__title">${title}</h3>
                        <p class="toast__msg">${message}</p>
                    </div>
                    <div class="toast__close">
                        <i class="fas fa-times"></i>
                    </div>
                `;
    main.appendChild(toast);
  }	
}
