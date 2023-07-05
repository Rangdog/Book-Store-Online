// DEFAULT BUTTON.

const defaultMarks = document.querySelectorAll('.address-default');
let defaultPairs = [];



defaultPairs.forEach(pair => {
    pair[0].addEventListener('click', () => {
        defaultMarks.forEach(mark => mark.classList.add('hide'));
        pair[1].classList.remove('hide');
    })
})

// HANDLING BUTTON.
const btnUpdate = document.querySelectorAll('button[name="update"]');
const modal = document.querySelector('#modal');
const modalUpdate = document.querySelector('#modal-update');
const modalDelete = document.querySelector('#delete');
const btnClose = document.querySelectorAll('.modal-close');
const btnNew = document.querySelector('#add-address');
const btnCloseDelet = document.querySelector("#button-close");

btnNew.addEventListener('click', () => {
    modal.classList.toggle('hide');
})

btnUpdate.forEach(btn => {
    btn.addEventListener('click', () => {
        modalUpdate.classList.toggle('hide');
        return
    })
})

btnClose.forEach(btn => {
	 btn.addEventListener('click', () => {
		if(!modal.classList.contains('hide')){
			modal.classList.toggle('hide');	
		}
		else if(!modalUpdate.classList.contains('hide')){
			modalUpdate.classList.toggle('hide');
		}
        return
    })
})
btnCloseDelet.addEventListener('click',() =>{
	modalDelete.classList.toggle('hide');
})

modal.addEventListener('click', function (e) {
    if (e.target == e.currentTarget) {
        modal.classList.toggle('hide');
    }
});

modalUpdate.addEventListener('click', function (e) {
    if (e.target == e.currentTarget) {
        modalUpdate.classList.toggle('hide');
    }
});

/* TOAST MESSAGE*/
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
                        <ion-icon name="${icons[type]}"></ion-icon>
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

